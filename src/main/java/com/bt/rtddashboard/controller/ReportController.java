/**
 * 
 */
package com.bt.rtddashboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.rtddashboard.model.ServerMonitor;
import com.bt.rtddashboard.model.ServerStatsHistory;
import com.bt.rtddashboard.service.ReportService;
import com.bt.rtddashboard.utility.DateConversionUtility;

/**
 * @author 609669080
 *
 */
@RestController
public class ReportController {

	@Autowired
	ReportService service;
	
	/*@RequestMapping(value="/rtd/getAllContacts", method=RequestMethod.GET)
	public ResponseEntity<List<Contacts>> getAllContacts(){
		List<Contacts> ilsList=new ArrayList<Contacts>();
		ResponseEntity<List<Contacts>> response=null;
		
		try{
			ilsList=service.getAllContacts();
			response=new ResponseEntity<List<Contacts>>(ilsList,HttpStatus.OK);
		}catch(Exception e){
			
		}
		return response;
	}*/
	
	@RequestMapping(value="/rtd/getAllStatsHistory", method=RequestMethod.GET)
	public ResponseEntity<List<List<String>>> getAllStatsHistory(){
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
		ResponseEntity<List<List<String>>> response=null;
		List<List<String>> serverHistList=new ArrayList<List<String>>();
		try{
			serverStatList=service.getAllStatsHistory();
			for(ServerStatsHistory server:serverStatList){
				List<String> obj=new ArrayList<String>();
				obj.add(String.valueOf(server.getServerId()));
				//obj.add(server.getTotReqCount().toString());
			//	obj.add(server.getTotSessnCount().toString());
			//	obj.add(server.getTotTimedOutReqCount().toString());
				obj.add(server.getReqCountAfterRefresh().toString());
				obj.add(server.getSessnCountAfterRefresh().toString());
				obj.add(server.getTimedOutReqCountAfterRefresh().toString());
				obj.add(server.getRefreshTime().toString());
				serverHistList.add(obj);
			}
			response=new ResponseEntity<List<List<String>>>(serverHistList,HttpStatus.OK);
		}catch(Exception e){
			
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getServerStatsByServer/{serverId}", method=RequestMethod.GET)
	public ResponseEntity<List<List<String>>> getServerStatsByServer(@PathVariable(value="serverId")Integer serverId){
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
		ResponseEntity<List<List<String>>> response=null;
		List<List<String>> serverHistList=new ArrayList<List<String>>();
		try{
			serverStatList=service.getServerStatsByServer(serverId);
			for(ServerStatsHistory server:serverStatList){
				List<String> obj=new ArrayList<String>();
				obj.add(String.valueOf(server.getServerId()));
				obj.add(server.getTotReqCount().toString());
				obj.add(server.getTotSessnCount().toString());
				obj.add(server.getTotTimedOutReqCount().toString());
				obj.add(server.getReqCountAfterRefresh().toString());
				obj.add(server.getSessnCountAfterRefresh().toString());
				obj.add(server.getTimedOutReqCountAfterRefresh().toString());
				obj.add(server.getRefreshTime().toString());
				serverHistList.add(obj);
			}
			response=new ResponseEntity<List<List<String>>>(serverHistList,HttpStatus.OK);
		}catch(Exception e){
			
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getServerStatsTableByDate/{serverId}", method=RequestMethod.GET)
	public ResponseEntity<List<List<String>>> getServerStatsTableByDate(@PathVariable(value="serverId")Integer serverId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate){
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
		ResponseEntity<List<List<String>>> response=null;
		String TIMESTAMPFORMAT="yyyy-MM-dd hh:mm:ss";
		String newFormat="dd-MMM-yyyy";
		List<List<String>> serverHistList=new ArrayList<List<String>>();
		try{
			serverStatList=service.getServerStatsByDate(serverId,startDate,endDate);
			for(ServerStatsHistory server:serverStatList){
				List<String> obj=new ArrayList<String>();
				obj.add(String.valueOf(server.getServerId()));
				//obj.add(server.getTotReqCount().toString());
				//obj.add(server.getTotSessnCount().toString());
				//obj.add(server.getTotTimedOutReqCount().toString());
				obj.add(server.getReqCountAfterRefresh().toString());
				obj.add(server.getSessnCountAfterRefresh().toString());
				obj.add(server.getTimedOutReqCountAfterRefresh().toString());
				obj.add(DateConversionUtility.convertDateToAnotherFormat(server.getRefreshTime(), TIMESTAMPFORMAT, newFormat));
				serverHistList.add(obj);
			}
			response=new ResponseEntity<List<List<String>>>(serverHistList,HttpStatus.OK);
		}catch(Exception e){
			
		}
		return response;
	}
	
	/*public ResponseEntity<List<ServerStatsHistory>> getAllServerStatsByDate(@PathVariable(value="server")Integer serverId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate)
	 * Get the daily data for  the specific servers for the specified date
	 * The total request, session and time out count may not be correct for this
	 * */
	@RequestMapping(value="/rtd/getServerStatsByDate/server/{serverId}", method=RequestMethod.GET)
	public ResponseEntity<TreeMap<String,ServerStatsHistory>> getAllServerStatsByDate(@PathVariable(value="serverId")Integer serverId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate){
		List<ServerStatsHistory> serverStatsList=new ArrayList<ServerStatsHistory>();
		List<ServerMonitor> serverList=new ArrayList<ServerMonitor>();
		//List<Integer> serverIdList=new ArrayList<Integer>();
		TreeMap<String,ServerStatsHistory> newServerStatsMap=new TreeMap<String,ServerStatsHistory>();
		ResponseEntity<TreeMap<String,ServerStatsHistory>> response=null;
		try{
			serverStatsList=service.getServerStatsByDate(serverId,startDate,endDate);
			if(serverStatsList!=null && serverStatsList.size()>0){
				newServerStatsMap=transformMapDatatoDailyMapData(serverStatsList);
				response=new ResponseEntity<TreeMap<String,ServerStatsHistory>>(newServerStatsMap,HttpStatus.OK);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	public static TreeMap<String, ServerStatsHistory> transformMapDatatoDailyMapData(List<ServerStatsHistory> serverStatsList){
		String TIMESTAMPFORMAT="yyyy-MM-dd hh:mm:ss";
		String newFormat="dd-MMM-yyyy";
		TreeMap<String,ServerStatsHistory> finalMap=new TreeMap<String, ServerStatsHistory>();
		List<ServerStatsHistory> newServerStatsList=new ArrayList<ServerStatsHistory>();
		//transform Dates in serverStats List 
		for(ServerStatsHistory serverStats : serverStatsList){
			serverStats.setRefreshTime(DateConversionUtility.convertDateToAnotherFormat(serverStats.getRefreshTime(), TIMESTAMPFORMAT, newFormat));
			//newServerStatsList.add(serverStats);
			if(finalMap.containsKey(serverStats.getRefreshTime())){
				ServerStatsHistory presentServerStats=finalMap.get(serverStats.getRefreshTime());
				presentServerStats.setReqCountAfterRefresh(presentServerStats.getReqCountAfterRefresh()+serverStats.getReqCountAfterRefresh());
				presentServerStats.setSessnCountAfterRefresh(presentServerStats.getSessnCountAfterRefresh()+serverStats.getSessnCountAfterRefresh());
				presentServerStats.setTimedOutReqCountAfterRefresh(presentServerStats.getTimedOutReqCountAfterRefresh()+serverStats.getTimedOutReqCountAfterRefresh());
				finalMap.put(presentServerStats.getRefreshTime(), presentServerStats);
			}else{
				finalMap.put(serverStats.getRefreshTime(), serverStats);
			}
		}
		
		return finalMap;
	}
}
