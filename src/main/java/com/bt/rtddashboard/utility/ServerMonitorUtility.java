package com.bt.rtddashboard.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.bt.rtddashboard.model.ServerMonitor;
import com.bt.rtddashboard.model.ServerStats;

public class ServerMonitorUtility {

	public static ServerStats getMonitorStatus(String host,String user, String pass, String port) throws IOException, InstanceNotFoundException, ReflectionException{
			//List<String> beanData=new ArrayList<String>();
			ServerStats serverMonitor=new ServerStats();
			try {
	    //    System.out.println("\n>>>Creating connection to server at " + host + ":" + port + " with " + user + "/" + pass + "<<<");
	        String jmxurl = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
	        JMXServiceURL url = new JMXServiceURL(jmxurl);
	        HashMap<String, String[]> hashMap = new HashMap<String, String[]>();
	        hashMap.put("jmx.remote.credentials", new String[]{user, pass});
	        JMXConnector jmxc = JMXConnectorFactory.connect(url, hashMap);
	        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
	        ObjectName mbeanName;
			mbeanName = new ObjectName("OracleRTD:scope=Server,name=DecisionService");
	        String[] attributes = new String[]{"TotalSessions", "TotalRequests", "TimedOutRequests"};
	        AttributeList beandata = mbsc.getAttributes(mbeanName, attributes);
	       /* for (int i = 0; i < beandata.size(); ++i) {
	            System.out.println(beandata.get(i).toString());
	           
	        }*/
	        serverMonitor.setTotSessnCount(returnCount(beandata.get(0).toString()));
	        serverMonitor.setTotReqCount(returnCount(beandata.get(1).toString()));
	        serverMonitor.setTotTimedOutReqCount(returnCount(beandata.get(2).toString()));
	        jmxc.close();
			} catch (MalformedObjectNameException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return serverMonitor;
	}
	
	public static Long returnCount(String serverCount){
		Long tempNo=null;
		try{
			String tempString=serverCount.split(" = ")[1];
			tempNo= Long.parseLong(tempString);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return tempNo;
	}
}
