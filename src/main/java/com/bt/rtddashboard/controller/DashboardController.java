/**
 * 
 */
package com.bt.rtddashboard.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.rtddashboard.container.IlsContainer;
import com.bt.rtddashboard.container.ServerContainer;
import com.bt.rtddashboard.model.Alert;
import com.bt.rtddashboard.model.ILSModel;
import com.bt.rtddashboard.model.IlsStats;
import com.bt.rtddashboard.model.IlsStatsHistory;
import com.bt.rtddashboard.model.MaxRecomStats;
import com.bt.rtddashboard.model.RecommendationStats;
//import com.bt.rtddashboard.dao.InlineDao;
import com.bt.rtddashboard.model.ServerMonitor;
import com.bt.rtddashboard.model.ServerStats;
import com.bt.rtddashboard.model.ServerStatsHistory;
import com.bt.rtddashboard.service.InlineService;
import com.bt.rtddashboard.utility.AlertUtility;
import com.bt.rtddashboard.utility.DateConversionUtility;
//import com.bt.rtddashboard.service.InlineService;
import com.bt.rtddashboard.utility.ServerMonitorUtility;
import com.javacleint.JavaClient;

/**
 * @author 609669080
 *
 */
@RestController
public class DashboardController {

	@Autowired
	private InlineService service;

	@RequestMapping(value = "/rtd/checkILSHealth/{ilsName}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkILSHealth(@RequestHeader(value = "serverIP") String serverIP,
			@PathVariable(value = "ilsName") String ilsName) {
		ResponseEntity<Boolean> response = null;
		Boolean serviceStatus = false;
		try {
			System.out.println(serverIP);
			// logic to retreive server details based on serverIP
			// COTDecisioning MAX 172.25.52.138:61001
			// Code to retreive other details from DB or from Hashmap
			String deploymentState = "MAX";
			//serviceStatus = JavaClient.getDefaultResponse(ilsName, deploymentState, serverIP);
			response = new ResponseEntity<Boolean>(serviceStatus, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			serviceStatus = false;
			response = new ResponseEntity<Boolean>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(value = "/rtd/getServerStatus/{serverId}", method = RequestMethod.GET)
	public ResponseEntity<ServerStats> getServerStatus(@PathVariable(value = "serverId") Integer serverId) {
		ServerStats serverMonitor = new ServerStats();
		ResponseEntity<ServerStats> response = null;
		System.out.println("Came in");
		System.out.println(serverId);
		try {
			ServerMonitor serverDetails = new ServerMonitor();
			serverDetails = service.getAllInlineByServer(serverId);
			String userName = serverDetails.getUserId();
			String pass = serverDetails.getPassword();
			String portno = serverDetails.getServerPort().toString();
			String serverIP = serverDetails.getServerIp();
			serverMonitor = ServerMonitorUtility.getMonitorStatus(serverIP, userName, pass, portno);
			response = new ResponseEntity<ServerStats>(serverMonitor, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/rtd/getAllInline", method = RequestMethod.GET)
	public ResponseEntity<List<ILSModel>> getAllInline() {
		List<ILSModel> ilsList = new ArrayList<ILSModel>();
		ResponseEntity<List<ILSModel>> response = null;

		try {
			ilsList = service.getAllInlineService();
			response = new ResponseEntity<List<ILSModel>>(ilsList, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	@RequestMapping(value = "/rtd/getAllServers", method = RequestMethod.GET)
	public ResponseEntity<List<ServerMonitor>> getAllServers() {
		List<ServerMonitor> ilsList = new ArrayList<ServerMonitor>();
		ResponseEntity<List<ServerMonitor>> response = null;

		try {
			ilsList = service.getAllServers();
			response = new ResponseEntity<List<ServerMonitor>>(ilsList, HttpStatus.OK);
			System.out.println("Scheduled Time");

		} catch (Exception e) {

		}
		return response;
	}

	@RequestMapping(value = "/rtd/getAllServersStatusFromRemote", method = RequestMethod.GET)
	public ResponseEntity<List<ServerStats>> getAllServersStatusFromRemote() {
		List<ServerStats> serverStats = new ArrayList<ServerStats>();
		List<ServerStats> statsList = new ArrayList<ServerStats>();
		ResponseEntity<List<ServerStats>> response = null;

		try {
			serverStats = service.getAllServerStats();
			statsList = getAllServerStatsFromRemote(serverStats);
			response = new ResponseEntity<List<ServerStats>>(statsList, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	@RequestMapping(value = "/rtd/getCurrentServerStats", method = RequestMethod.GET)
	public ResponseEntity<List<ServerStats>> getCurrentServerStats() {
		List<ServerStats> serverStats = new ArrayList<ServerStats>();
		List<ServerStats> statsList = new ArrayList<ServerStats>();
		ResponseEntity<List<ServerStats>> response = null;

		try {
			serverStats = service.getAllServerStats();
			// statsList=getAllServerStatsFromRemote(serverStats);
			response = new ResponseEntity<List<ServerStats>>(serverStats, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	@RequestMapping(value = "/rtd/getServerStatusByDate/{serverId}", method = RequestMethod.GET)
	public ResponseEntity<ServerStatsHistory> getServerStatusByDate(@PathVariable(value = "serverId") Integer serverId,
			@QueryParam("startDate") String startDate) {
		ServerStatsHistory serverStats = new ServerStatsHistory();
		ResponseEntity<ServerStatsHistory> response = null;

		try {
			serverStats = service.getServerStatusByDate(serverId, startDate);
			response = new ResponseEntity<ServerStatsHistory>(serverStats, HttpStatus.OK);
			System.out.println("Scheduled Time");

		} catch (Exception e) {

		}
		return response;
	}

	/*
	 * public ResponseEntity<ServerStatsHistory> getAlertsByDate(String date)
	 * Return all the alerts for the given date
	 */
	@RequestMapping(value = "/rtd/getAlertsByDate", method = RequestMethod.GET)
	public ResponseEntity<List<Alert>> getAlertsByDate(@QueryParam("date") String date) {
		List<Alert> alrtList = new ArrayList<Alert>();
		List<Alert> newAlrtList = new ArrayList<Alert>();
		ResponseEntity<List<Alert>> response = null;
		String format = "yyyy-MM-dd hh:mm:ss";
		try {
			alrtList = service.getAlertsByDate(date);
			for (Alert alert : alrtList) {
				alert.setAlertDate(DateConversionUtility.getTimeFromDate(alert.getAlertDate(), format));
				newAlrtList.add(alert);
			}
			response = new ResponseEntity<List<Alert>>(newAlrtList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/rtd/getAllServerStatusByDate", method = RequestMethod.GET)
	public ResponseEntity<List<ServerStatsHistory>> getAllServerStatusByDate(
			@QueryParam("startDate") String startDate) {
		List<ServerStatsHistory> serverStatsList = new ArrayList<ServerStatsHistory>();
		ResponseEntity<List<ServerStatsHistory>> response = null;

		try {
			serverStatsList = service.getAllServerStatusByDate(startDate);
			response = new ResponseEntity<List<ServerStatsHistory>>(serverStatsList, HttpStatus.OK);
			System.out.println("Scheduled Time");

		} catch (Exception e) {

		}
		return response;
	}

	/*
	 * public ResponseEntity<List<ServerStatsHistory>>
	 * getAllServerStatsByDate(@QueryParam("startDate")String startDate) Get the
	 * houly data for all the servers for the specified date
	 */
	@RequestMapping(value = "/rtd/getAllServerStatsByDate", method = RequestMethod.GET)
	public ResponseEntity<HashMap<Integer, List<ServerStatsHistory>>> getAllServerStatsByDate(
			@QueryParam("startDate") String startDate) {
		List<ServerStatsHistory> serverStatsList = new ArrayList<ServerStatsHistory>();
		List<ServerMonitor> serverList = new ArrayList<ServerMonitor>();
		// List<Integer> serverIdList=new ArrayList<Integer>();
		HashMap<Integer, List<ServerStatsHistory>> serverStatsMap = new HashMap<Integer, List<ServerStatsHistory>>();
		HashMap<Integer, List<ServerStatsHistory>> newServerStatsMap = new HashMap<Integer, List<ServerStatsHistory>>();
		ResponseEntity<HashMap<Integer, List<ServerStatsHistory>>> response = null;
		String presentDate = DateConversionUtility.getCurrentDateTimeinString("dd/MM/yyyy");
		try {
			serverStatsList = service.getAllServerStatsByDate(startDate);
			if (serverStatsList != null && serverStatsList.size() > 0) {
				serverList = service.getAllServers();
				if (serverStatsList != null && serverStatsList.size() > 0) {
					for (ServerMonitor server : serverList) {
						serverStatsMap.put(server.getServerId(), null);
					}

					for (ServerStatsHistory serverStats : serverStatsList) {
						List<ServerStatsHistory> serverStatList = serverStatsMap.get(serverStats.getServerId());
						if (serverStatList == null) {
							serverStatList = new ArrayList<ServerStatsHistory>();
							serverStatList.add(serverStats);
							serverStatsMap.put(serverStats.getServerId(), serverStatList);
						} else {
							serverStatList.add(serverStats);
							serverStatsMap.put(serverStats.getServerId(), serverStatList);
						}
					}

				}
			}
			newServerStatsMap = transformMapDatatoHourlyMapData(serverStatsMap);
			response = new ResponseEntity<HashMap<Integer, List<ServerStatsHistory>>>(newServerStatsMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/rtd/getAllInlineByServer/{serverId}", method = RequestMethod.GET)
	public ResponseEntity<ServerContainer> getAllInlineByServer(@PathVariable(value = "serverId") Integer serverId) {
		ServerMonitor serverMonitor = new ServerMonitor();
		ResponseEntity<ServerContainer> response = null;
		List<IlsStats> ilsStatsList = new ArrayList<IlsStats>();
		List<ILSModel> ilsModelList = new ArrayList<ILSModel>();
		ServerContainer serverContainer = new ServerContainer();
		List<IlsContainer> ilsList = new ArrayList<IlsContainer>();

		try {
			ilsStatsList = service.getInlineStatusByServer(serverId);
			serverMonitor = service.getAllInlineByServer(serverId);
			if (serverMonitor != null) {
				serverContainer.setServerId(serverMonitor.getServerId());
				serverContainer.setServerName(serverMonitor.getServerName());
				serverContainer.setServerIp(serverMonitor.getServerIp());
				serverContainer.setServerPort(serverMonitor.getServerPort());
				serverContainer.setServerState(serverMonitor.getServerState());
				serverContainer.setUserId(serverMonitor.getUserId());
				serverContainer.setPassword(serverMonitor.getPassword());
				serverContainer.setAuthenticationReq(serverMonitor.getAuthenticationReq());
				ilsModelList = serverMonitor.getTeams();
				if (ilsModelList != null && ilsModelList.size() > 0) {
					for (ILSModel ilsModel : ilsModelList) {
						for (IlsStats ilsStats : ilsStatsList) {
							if (ilsModel.getIlsId() == ilsStats.getIlsid()) {
								IlsContainer ilsCon = new IlsContainer();
								ilsCon.setIlsId(ilsModel.getIlsId());
								ilsCon.setIlsName(ilsModel.getIlsName());
								ilsCon.setChannelName(ilsModel.getChannelName());
								ilsCon.setIlsDesc(ilsModel.getIlsDesc());
								ilsCon.setStatus(ilsStats.getStatus());
								ilsCon.setRefreshTime(ilsStats.getRefreshTime());
								ilsList.add(ilsCon);
								break;
							}
						}
					}
				}
				serverContainer.setIlsList(ilsList);
			}
			response = new ResponseEntity<ServerContainer>(serverContainer, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	@RequestMapping(value = "/rtd/getAllServersByState/serverState/{serverType}", method = RequestMethod.GET)
	public ResponseEntity<List<ServerMonitor>> getAllServersByState(
			@PathVariable(value = "serverType") String serverType) {
		List<ServerMonitor> ilsList = new ArrayList<ServerMonitor>();
		ResponseEntity<List<ServerMonitor>> response = null;

		try {
			ilsList = service.getAllServersByState(serverType);
			response = new ResponseEntity<List<ServerMonitor>>(ilsList, HttpStatus.OK);
			System.out.println("Scheduled Time");

		} catch (Exception e) {

		}
		return response;
	}

	/*
	 * public ResponseEntity<ServerStats> getServerStats(Integer serverId)
	 * Return the Request,Session and Time out count of the input server ID
	 */
	@RequestMapping(value = "/rtd/getServerStats/{serverId}", method = RequestMethod.GET)
	public ResponseEntity<ServerStats> getServerStats(@PathVariable(value = "serverId") Integer serverId) {
		ServerStats serverStats = new ServerStats();
		ResponseEntity<ServerStats> response = null;

		try {
			serverStats = service.getServerStats(serverId);
			response = new ResponseEntity<ServerStats>(serverStats, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	/*
	 * public ResponseEntity<T> getCurrentInlineStatusForAllServers() Gets the
	 * current status of all the inline services deployed in different servers.
	 * Pings each inline service for each server and return their status
	 
	@RequestMapping(value = "/rtd/getCurrentInlineStatusForAllServers", method = RequestMethod.GET)
	public ResponseEntity<List<IlsStats>> getCurrentInlineStatusForAllServers() {
		List<IlsStats> ilsStats = new ArrayList<IlsStats>();
		ResponseEntity<List<IlsStats>> response = null;
		try {
			ilsStats = getAllILSStatsFromServer();
			response = new ResponseEntity<List<IlsStats>>(ilsStats, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}*/

	/*
	 * public ResponseEntity<List<IlsStats>> getInlineStatusByServer(Integer
	 * serverId) Returns all the inline servers deployed on the given server and
	 * there status
	 */
	@RequestMapping(value = "/rtd/getInlineStatusByServer/{serverId}", method = RequestMethod.GET)
	public ResponseEntity<List<IlsStats>> getInlineStatusByServer(@PathVariable(value = "serverId") Integer serverId) {
		List<IlsStats> ilsStats = new ArrayList<IlsStats>();
		ResponseEntity<List<IlsStats>> response = null;

		try {
			ilsStats = service.getInlineStatusByServer(serverId);
			response = new ResponseEntity<List<IlsStats>>(ilsStats, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	/*
	 * public ResponseEntity<List<IlsStats>> getAllInlineStats() Returns List of
	 * all inline servers, server id on which they are deployed and there status
	 * whether they are active or not
	 */
	@RequestMapping(value = "/rtd/getAllInlineStats", method = RequestMethod.GET)
	public ResponseEntity<List<IlsStats>> getAllInlineStats() {
		// Returns present status of all inline services deployed on different
		// servers
		List<IlsStats> ilsStats = new ArrayList<IlsStats>();
		ResponseEntity<List<IlsStats>> response = null;

		try {
			ilsStats = service.getAllInlineStats();
			response = new ResponseEntity<List<IlsStats>>(ilsStats, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	@RequestMapping(value = "/rtd/getRecomStats", method = RequestMethod.GET)
	public ResponseEntity<List<RecommendationStats>> getRecomStats(@QueryParam("date") String date) {
		ResponseEntity<List<RecommendationStats>> response = null;
		List<RecommendationStats> recomList = new ArrayList<RecommendationStats>();
		System.out.println("Date =" + date);
		try {
			recomList = service.getRecomStats(date);
			response = new ResponseEntity<List<RecommendationStats>>(recomList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<List<RecommendationStats>>(recomList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "/rtd/getMaxRecomStats", method = RequestMethod.GET)
	public ResponseEntity<List<MaxRecomStats>> getMaxRecomStats() {
		ResponseEntity<List<MaxRecomStats>> response = null;
		List<MaxRecomStats> recomList = new ArrayList<MaxRecomStats>();
		System.out.println("Inside Max");
		try {
			recomList = service.getMaxRecomStats();
			response = new ResponseEntity<List<MaxRecomStats>>(recomList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<List<MaxRecomStats>>(recomList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/*
	 * public void performTask() Scheduled Task that runs every minute This
	 * includes testing ILS mapped to each server and gettin request, session
	 * and timeout count of each server
	 */
	// @Scheduled(fixedRate=60000)
	@Scheduled(fixedRate=3600000)
	public void performTask() {
		System.out.println("Refresh started at " + new Date());
		List<IlsStats> ilsStats = new ArrayList<IlsStats>();
		List<IlsStats> newIlsStatsList = new ArrayList<IlsStats>();
		List<IlsStatsHistory> ilsStatsHisList = new ArrayList<IlsStatsHistory>();
		List<ServerStats> serverStats = new ArrayList<ServerStats>();
		List<ServerStats> newServerStats = new ArrayList<ServerStats>();
		List<ServerStatsHistory> serverStatsHistory = new ArrayList<ServerStatsHistory>();
		List<ServerMonitor> serverList = new ArrayList<ServerMonitor>();
		List<Alert> alertList = new ArrayList<Alert>();
		List<ILSModel> ilsList = new ArrayList<ILSModel>();
		HashMap<Integer, ServerMonitor> serverMap = new HashMap<Integer, ServerMonitor>();
		HashMap<Integer, ILSModel> ilsMap = new HashMap<Integer, ILSModel>();
		try {
			serverList = service.getAllServers(); // Get List of all servers
			ilsList = service.getAllInlineService(); // Get List of all inline
														// services
			for (ServerMonitor server : serverList) {
				serverMap.put(server.getServerId(), server);
			}
			for (ILSModel ils : ilsList) {
				ilsMap.put(ils.getIlsId(), ils);
			}
			// get All the ILS Stats
			ilsStats = service.getAllInlineStats();
			/*if (ilsStats != null && ilsStats.size() > 0) {
				// Data is present in current ils table
				service.deleteAllInlineStats(); // Delete data in curent ILS
												// table
				newIlsStatsList = getAllILSStatsFromServer(); // Form data to be
																// saved in
																// current ILS
																// table
				alertList = AlertUtility.checkForILSAlert(newIlsStatsList, serverMap, ilsMap);
				service.insertInIlsStats(newIlsStatsList); // Insert data in
															// current ILS table
				ilsStatsHisList = formILSHistoryList(newIlsStatsList); // Form
																		// Data
																		// to be
																		// saved
																		// in
																		// history
																		// table
				service.insertInIlsStatsHistory(ilsStatsHisList); // insert data
																	// in ILS
																	// history
																	// table
			} else {
				newIlsStatsList = getAllILSStatsFromServer(); // Form data to be
																// saved in
																// current ILS
																// table
				alertList = AlertUtility.checkForILSAlert(newIlsStatsList, serverMap, ilsMap);
				service.insertInIlsStats(newIlsStatsList); // Insert data in
															// current ILS table
				ilsStatsHisList = formILSHistoryList(newIlsStatsList); // Form
																		// Data
																		// to be
																		// saved
																		// in
																		// history
																		// table
				service.insertInIlsStatsHistory(ilsStatsHisList); // insert data
																	// in ILS
																	// history
																	// table

			}*/
			serverStats = service.getAllServerStats();
			if (serverStats != null && serverStats.size() > 0) {
				service.deleteAllServerStats(); // Delete data from current
												// server stats table
				newServerStats = getAllServerStatsFromRemote(serverStats); // Get
																			// All
																			// Server
																			// Stats
				alertList = AlertUtility.checkForTimeOutAlert(newServerStats, serverMap, ilsMap, alertList);
				service.insertInServerStats(newServerStats); // Insert in
																// current
																// server stats
				serverStatsHistory = formServerStatsHistoryList(newServerStats); // Form
																					// Data
																					// to
																					// be
																					// saved
																					// in
																					// history
																					// table
				service.insertInServerStatsHistory(serverStatsHistory); // insert
																		// data
																		// in
																		// Server
																		// history
																		// table
			} else {
				newServerStats = getAllServerStatsFromRemote(serverStats); // Get
																			// All
																			// Server
																			// Stats
				alertList = AlertUtility.checkForTimeOutAlert(newServerStats, serverMap, ilsMap, alertList);
				service.insertInServerStats(newServerStats); // Insert in
																// current
																// server stats
				serverStatsHistory = formServerStatsHistoryList(newServerStats); // Form
																					// Data
																					// to
																					// be
																					// saved
																					// in
																					// history
																					// table
				service.insertInServerStatsHistory(serverStatsHistory); // insert
																		// data
																		// in
																		// Server
																		// history
																		// table

			}
			// Inserting Alerts in alert table
			service.insertAlertsData(alertList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public List<IlsStats> getAllILSStatsFromServer() Gets the List of all
	 * servers. Ping individual ILS mapped to each server and get the status of
	 * it
	 
	public List<IlsStats> getAllILSStatsFromServer() {
		List<ServerMonitor> serverList = new ArrayList<ServerMonitor>();
		List<IlsStats> newIlsStatsList = new ArrayList<IlsStats>();
		try {
			serverList = service.getAllServers();
			for (ServerMonitor server : serverList) {
				for (ILSModel ils : server.getTeams()) {
					String ip = server.getServerIp() + ":" + ils.getServerPort();
					//Boolean serviceStatus = JavaClient.getDefaultResponse(ils.getIlsName(), "MAX", ip);
					IlsStats ilsStats = new IlsStats();
					ilsStats.setIlsid(ils.getIlsId());
					ilsStats.setServerId(server.getServerId());
					ilsStats.setStatus(serviceStatus.toString());
					ilsStats.setRefreshTime(new Date());
					System.out.println("Refresh Status is " + serviceStatus + " For ILS " + ils.getIlsName()
							+ " in server " + server.getServerName());
					newIlsStatsList.add(ilsStats);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newIlsStatsList;
	}*/

	/*
	 * public List<ServerStats> getAllServerStatsFromRemote(List<ServerStats>
	 * serverStatList) Ping each server present in Database and get the Request,
	 * Session and Timeout Count
	 */
	public List<ServerStats> getAllServerStatsFromRemote(List<ServerStats> serverStatList) {
		List<ServerMonitor> serverList = new ArrayList<ServerMonitor>();
		List<ServerStats> newServerStats = new ArrayList<ServerStats>();
		serverList = service.getAllServers();
		for (ServerMonitor server : serverList) {
			String userName = server.getUserId();
			String pass = server.getPassword();
			String portno = server.getServerPort().toString();
			String serverIP = server.getServerIp();
			try {
				ServerStats serverMonitor = ServerMonitorUtility.getMonitorStatus(serverIP, userName, pass, portno);
				if (serverStatList != null && serverStatList.size() > 0) {
					for (ServerStats serverStats : serverStatList) {
						if (serverStats.getServerId() == server.getServerId()) {
							serverMonitor.setReqCountAfterRefresh(
									performSubstraction(serverMonitor.getTotReqCount(), serverStats.getTotReqCount()));
							serverMonitor.setSessnCountAfterRefresh(performSubstraction(
									serverMonitor.getTotSessnCount(), serverStats.getTotSessnCount()));
							serverMonitor.setTimedOutReqCountAfterRefresh(performSubstraction(
									serverMonitor.getTotTimedOutReqCount(), serverStats.getTotTimedOutReqCount()));
							break;
						}
					}
				} else {
					serverMonitor.setReqCountAfterRefresh(0L);
					serverMonitor.setSessnCountAfterRefresh(0L);
					serverMonitor.setTimedOutReqCountAfterRefresh(0L);
				}
				serverMonitor.setServerId(server.getServerId());
				serverMonitor.setRefreshTime(new Date());
				newServerStats.add(serverMonitor);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return newServerStats;
	}

	/*
	 * public static Long performSubstraction(Long val1, Long val2) Perfoms
	 * substraction of the input values
	 */
	public static Long performSubstraction(Long val1, Long val2) {
		return (val1 - val2 > 0) ? (val1 - val2) : 0L;
	}

	/*
	 * public static List<IlsStatsHistory> formILSHistoryList(List<IlsStats>
	 * ilsStatsList) Form a ILS history List from the ILS Current List to save
	 * it in ILS History table
	 */
	public static List<IlsStatsHistory> formILSHistoryList(List<IlsStats> ilsStatsList) {
		// Form Data to be saved in ILS history table
		List<IlsStatsHistory> ilsStatsHisList = new ArrayList<IlsStatsHistory>();
		for (IlsStats ilsStats : ilsStatsList) {
			IlsStatsHistory ilsStatsHist = new IlsStatsHistory();
			ilsStatsHist.setIlsid(ilsStats.getIlsid());
			ilsStatsHist.setServerId(ilsStats.getServerId());
			ilsStatsHist.setRefreshTime(ilsStats.getRefreshTime());
			ilsStatsHist.setStatus(ilsStats.getStatus());
			ilsStatsHisList.add(ilsStatsHist);
		}
		return ilsStatsHisList;
	}

	/*
	 * public static List<ServerStatsHistory>
	 * formServerStatsHistoryList(List<ServerStats> serverStatsList) Form a
	 * Server history List from the Server Current List to save it in Server
	 * History table
	 */
	public static List<ServerStatsHistory> formServerStatsHistoryList(List<ServerStats> serverStatsList) {
		// Form Data to be saved in Server history table
		List<ServerStatsHistory> serverStatsHisList = new ArrayList<ServerStatsHistory>();
		for (ServerStats serverStats : serverStatsList) {
			ServerStatsHistory serverStatsHist = new ServerStatsHistory();
			serverStatsHist.setServerId(serverStats.getServerId());
			serverStatsHist.setTotReqCount(serverStats.getTotReqCount());
			serverStatsHist.setTotSessnCount(serverStats.getTotSessnCount());
			serverStatsHist.setTotTimedOutReqCount(serverStats.getTotTimedOutReqCount());
			serverStatsHist.setReqCountAfterRefresh(serverStats.getReqCountAfterRefresh());
			serverStatsHist.setSessnCountAfterRefresh(serverStats.getSessnCountAfterRefresh());
			serverStatsHist.setTimedOutReqCountAfterRefresh(serverStats.getTimedOutReqCountAfterRefresh());
			serverStatsHist.setRefreshTime(DateConversionUtility.convertDateToString(serverStats.getRefreshTime()));
			serverStatsHisList.add(serverStatsHist);
		}
		return serverStatsHisList;
	}

	public static HashMap<Integer, List<ServerStatsHistory>> transformMapDatatoHourlyMapData(
			HashMap<Integer, List<ServerStatsHistory>> inputMapData) {
		Iterator it = inputMapData.entrySet().iterator();
		HashMap<Integer, List<ServerStatsHistory>> modifiedMap = inputMapData;
		String TIMESTAMPFORMAT = "yyyy-MM-dd hh:mm:ss";
		// String TIMESTAMPFORMAT="yyyy-MM-dd hh:mm:ss";
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Integer serverId = (Integer) pair.getKey();
			HashMap<Integer, ServerStatsHistory> tempHashMap = new HashMap<Integer, ServerStatsHistory>();
			for (int i = 0; i < 24; i++) {
				tempHashMap.put(i, null);
			}
			List<ServerStatsHistory> tempList = (List<ServerStatsHistory>) pair.getValue();
			for (ServerStatsHistory serverStats : tempList) {
				int hour = DateConversionUtility.getHMSFromDate(serverStats.getRefreshTime(), TIMESTAMPFORMAT, "HOUR");
				int min = DateConversionUtility.getHMSFromDate(serverStats.getRefreshTime(), TIMESTAMPFORMAT, "MIN");
				int sec = DateConversionUtility.getHMSFromDate(serverStats.getRefreshTime(), TIMESTAMPFORMAT, "SEC");
				ServerStatsHistory existingServerStats = tempHashMap.get(hour);
				if (existingServerStats == null) {
					tempHashMap.put(hour, serverStats);
				} else {
					int extHour = DateConversionUtility.getHMSFromDate(existingServerStats.getRefreshTime(),
							TIMESTAMPFORMAT, "HOUR");
					int extMin = DateConversionUtility.getHMSFromDate(existingServerStats.getRefreshTime(),
							TIMESTAMPFORMAT, "MIN");
					int extSec = DateConversionUtility.getHMSFromDate(existingServerStats.getRefreshTime(),
							TIMESTAMPFORMAT, "SEC");
					if (min > extMin) {
						serverStats.setReqCountAfterRefresh(
								serverStats.getReqCountAfterRefresh() + existingServerStats.getReqCountAfterRefresh());
						serverStats.setSessnCountAfterRefresh(serverStats.getSessnCountAfterRefresh()
								+ existingServerStats.getSessnCountAfterRefresh());
						serverStats.setTimedOutReqCountAfterRefresh(serverStats.getTimedOutReqCountAfterRefresh()
								+ existingServerStats.getTimedOutReqCountAfterRefresh());
						tempHashMap.put(hour, serverStats);
					} else {
						existingServerStats.setReqCountAfterRefresh(
								serverStats.getReqCountAfterRefresh() + existingServerStats.getReqCountAfterRefresh());
						existingServerStats.setSessnCountAfterRefresh(serverStats.getSessnCountAfterRefresh()
								+ existingServerStats.getSessnCountAfterRefresh());
						existingServerStats
								.setTimedOutReqCountAfterRefresh(serverStats.getTimedOutReqCountAfterRefresh()
										+ existingServerStats.getTimedOutReqCountAfterRefresh());
						tempHashMap.put(hour, existingServerStats);
					}
				}
			}
			List<ServerStatsHistory> tempServerStatsList = new ArrayList<ServerStatsHistory>();
			for (int i = 0; i < 24; i++) {
				ServerStatsHistory serverStats = tempHashMap.get(i);
				if (serverStats != null) {
					serverStats.setRefreshTime(
							DateConversionUtility.getHourFromDate(serverStats.getRefreshTime(), TIMESTAMPFORMAT));
					tempServerStatsList.add(serverStats);
				}
			}
			modifiedMap.put(serverId, tempServerStatsList);
		}

		return modifiedMap;
	}

}
