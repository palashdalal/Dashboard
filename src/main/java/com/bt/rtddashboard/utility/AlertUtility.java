/**
 * 
 */
package com.bt.rtddashboard.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.bt.rtddashboard.model.Alert;
import com.bt.rtddashboard.model.ILSModel;
import com.bt.rtddashboard.model.IlsStats;
import com.bt.rtddashboard.model.ServerMonitor;
import com.bt.rtddashboard.model.ServerStats;

/**
 * @author 609669080
 *	Check for ILS and server Status, Request time out and generate alerts for the same
 */
public class AlertUtility {
	
	
	public static List<Alert> checkForILSAlert(List<IlsStats> ilsList,HashMap<Integer,ServerMonitor> serverMap,HashMap<Integer,ILSModel> ilsMap){
		List<Alert> alertList=new ArrayList<Alert>();
		if(ilsList!=null && ilsList.size()>0){
			for(IlsStats ils:ilsList){
				if(ils.getStatus().equalsIgnoreCase("false")){
					Alert alert=new Alert();
					alert.setAlertType("ILS Alert");
					alert.setAlertDesc(ilsMap.get(ils.getIlsid()).getIlsName()+" ILS deployed on " + serverMap.get(ils.getServerId()).getServerName() + " is down");
					alert.setAlertDate(DateConversionUtility.convertDateToString(new Date()));
					alertList.add(alert);
				}
			}
			System.out.println("List size after ILS Stats check " + alertList.size());
		}else{
			return null;
		}
		return alertList;
	}
	
	
	public static List<Alert> checkForTimeOutAlert(List<ServerStats> serverStatList,HashMap<Integer,ServerMonitor> serverMap,HashMap<Integer,ILSModel> ilsMap,List<Alert> alertList){
	//	List<Alert> alertList=new ArrayList<Alert>();
		/*HashMap<Integer,ServerMonitor> serverMap=new HashMap<Integer, ServerMonitor>();
		HashMap<Integer,ILSModel> ilsMap=new HashMap<Integer, ILSModel>();
		for(ServerMonitor server:serverList){
			serverMap.put(server.getServerId(),server);
		}
		for(ILSModel ils:ilsList2){
			ilsMap.put(ils.getIlsId(),ils);
		}*/
		if(alertList==null){
			alertList=new ArrayList<Alert>();
		}
		if(serverStatList!=null && serverStatList.size()>0){
			for(ServerStats serverStats:serverStatList){
				if(serverStats.getTimedOutReqCountAfterRefresh()>0){
					Alert alert=new Alert();
					alert.setAlertType("TimeOut Alert");
					alert.setAlertDesc(serverStats.getTimedOutReqCountAfterRefresh() +" time outs happened on " + serverMap.get(serverStats.getServerId()).getServerName());
					alert.setAlertDate(DateConversionUtility.convertDateToString(new Date()));
					alertList.add(alert);
				}
			}
			System.out.println("List size after Server Stats check " + alertList.size());
		}else{
			return null;
		}
		return alertList;
	}
}
