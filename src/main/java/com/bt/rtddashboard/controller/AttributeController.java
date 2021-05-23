/**
 * 
 */
package com.bt.rtddashboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.rtddashboard.model.MrdDetailMaster;
import com.bt.rtddashboard.model.MrdSnapshotMaster;
import com.bt.rtddashboard.model.ProspectAttributeMaster;
import com.bt.rtddashboard.model.StandardAttributeMaster;
import com.bt.rtddashboard.service.AttributeService;

/*
 * @author 609669080
 *
 */
@RestController
public class AttributeController {

	@Autowired
	private AttributeService service;

	@RequestMapping(value="/rtd/getStandardAttributesByJobType/{jobType}", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String,Set<String>>> getStandardAttributesByJobType(@PathVariable(value="jobType")String jobType){
		System.out.println("Inside Attribute Service" +jobType);
		List<StandardAttributeMaster> attList=new ArrayList<StandardAttributeMaster>();
		ResponseEntity<HashMap<String,Set<String>>> response=null;
		try{
			attList=service.getStandardAttributesByJobType(jobType);
			HashMap<String,Set<String>> finalMap =new HashMap<String,Set<String>>();
			finalMap=formStandardAttributeMap(attList);
			response=new ResponseEntity<HashMap<String,Set<String>>>(finalMap,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getProspectAttributesByJobType/{jobType}", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String,Set<String>>> getProspectAttributesByJobType(@PathVariable(value="jobType")String jobType){
		System.out.println("Inside Attribute Service" +jobType);
		List<ProspectAttributeMaster> attList=new ArrayList<ProspectAttributeMaster>();
		ResponseEntity<HashMap<String,Set<String>>> response=null;
		try{
			attList=service.getProspectAttributesByJobType(jobType);
			HashMap<String,Set<String>> finalMap =new HashMap<String,Set<String>>();
			finalMap=formProspectAttributeMap(attList);
			response=new ResponseEntity<HashMap<String,Set<String>>>(finalMap,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getStandardAttributeByName/{jobType}/{attName}", method=RequestMethod.GET)
	public ResponseEntity<List<StandardAttributeMaster>> getStandardAttributeByName(@PathVariable(value="jobType")String jobType,@PathVariable(value="attName")String attName){
		System.out.println("Inside Attribute Service" +attName);
		List<StandardAttributeMaster> attList=new ArrayList<StandardAttributeMaster>();
		ResponseEntity<List<StandardAttributeMaster>> response=null;
		try{
			attList=service.getStandardAttributeByName(jobType,attName);
			response=new ResponseEntity<List<StandardAttributeMaster>>(attList,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getProspectAttributeByName/{jobType}/{attName}", method=RequestMethod.GET)
	public ResponseEntity<List<ProspectAttributeMaster>> getProspectAttributeByName(@PathVariable(value="jobType")String jobType,@PathVariable(value="attName")String attName){
		System.out.println("Inside Attribute Service" +attName);
		List<ProspectAttributeMaster> attList=new ArrayList<ProspectAttributeMaster>();
		ResponseEntity<List<ProspectAttributeMaster>> response=null;
		try{
			attList=service.getProspectAttributeByName(jobType,attName);
			response=new ResponseEntity<List<ProspectAttributeMaster>>(attList,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getAllMRDTablesStatus", method=RequestMethod.GET)
	public ResponseEntity<List<MrdSnapshotMaster>> getAllMRDTablesStatus(){
		System.out.println("Inside getMRDTablesStatus");
		List<MrdSnapshotMaster> mrdList=new ArrayList<MrdSnapshotMaster>();
		ResponseEntity<List<MrdSnapshotMaster>> response=null;
		try{
			mrdList=service.getAllMRDTablesStatus();
			response=new ResponseEntity<List<MrdSnapshotMaster>>(mrdList,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getMRDTablesByStatus/{status}", method=RequestMethod.GET)
	public ResponseEntity<List<MrdSnapshotMaster>> getMRDTablesByStatus(@PathVariable(value="status")String status){
		System.out.println("Inside getMRDTablesStatus");
		List<MrdSnapshotMaster> mrdList=new ArrayList<MrdSnapshotMaster>();
		ResponseEntity<List<MrdSnapshotMaster>> response=null;
		try{
			mrdList=service.getMRDTablesByStatus(status);
			response=new ResponseEntity<List<MrdSnapshotMaster>>(mrdList,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/rtd/getMRDDetailByName/{tableName}", method=RequestMethod.GET)
	public ResponseEntity<List<MrdDetailMaster>> getMRDDetailByName(@PathVariable(value="tableName")String tableName){
		System.out.println("Inside getMRDTablesStatus");
		List<MrdDetailMaster> mrdList=new ArrayList<MrdDetailMaster>();
		ResponseEntity<List<MrdDetailMaster>> response=null;
		try{
			mrdList=service.getMRDDetailByName(tableName);
			response=new ResponseEntity<List<MrdDetailMaster>>(mrdList,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	public static HashMap<String,Set<String>> formStandardAttributeMap(List<StandardAttributeMaster> attributeList){
		HashMap<String,Set<String>> finalMap =new HashMap<String,Set<String>>();
		Set<String> redSet=new HashSet<String>();
		Set<String> amberSet=new HashSet<String>();
		Set<String> greenSet=new HashSet<String>();
		
		for(StandardAttributeMaster stMaster:attributeList){
			if(stMaster.getInsightStatus().equalsIgnoreCase("RED")){
				redSet.add(stMaster.getAttName());
			}else if(stMaster.getInsightStatus().equalsIgnoreCase("AMBER")){
				amberSet.add(stMaster.getAttName());
			}else if(stMaster.getInsightStatus().equalsIgnoreCase("GREEN")){
				greenSet.add(stMaster.getAttName());
			}
		}
		finalMap.put("RED",redSet);
		finalMap.put("AMBER",amberSet);
		finalMap.put("GREEN",greenSet);
		
		return finalMap;
	}
	
	public static HashMap<String,Set<String>> formProspectAttributeMap(List<ProspectAttributeMaster> attributeList){
		HashMap<String,Set<String>> finalMap =new HashMap<String,Set<String>>();
		Set<String> redSet=new HashSet<String>();
		Set<String> amberSet=new HashSet<String>();
		Set<String> greenSet=new HashSet<String>();
		
		for(ProspectAttributeMaster stMaster:attributeList){
			if(stMaster.getInsightStatus().equalsIgnoreCase("RED")){
				redSet.add(stMaster.getAttName());
			}else if(stMaster.getInsightStatus().equalsIgnoreCase("AMBER")){
				amberSet.add(stMaster.getAttName());
			}else if(stMaster.getInsightStatus().equalsIgnoreCase("GREEN")){
				greenSet.add(stMaster.getAttName());
			}
		}
		finalMap.put("RED",redSet);
		finalMap.put("AMBER",amberSet);
		finalMap.put("GREEN",greenSet);
		
		return finalMap;
	}
	
	
	
}

