(function () {	
app.controller('attributeController', function($scope,$http,$rootScope) {
		
	
		  $scope.stFlag=true;
		  $scope.prospectFlag=false;
		  $scope.incFlag=true;
		  $scope.mileFlag=false;
		  $scope.realFlag=false;
		  $scope.delta1_inc=false;
		  $scope.delta2_inc=true;
		  $scope.delta3_inc=true;
		  $scope.delta1_real=false;
		  $scope.delta2_real=true;
		  $scope.delta3_real=true;
		  $scope.delta1_mile=false;
		  $scope.delta2_mile=true;
		  $scope.delta3_mile=true;
		  
		  //Prospect Data members
		  $scope.proIncFlag=true;
		  $scope.proMileFlag=false;
		  $scope.proRealFlag=false;
		  $scope.proDelta1_inc=false;
		  $scope.proDelta2_inc=true;
		  $scope.proDelta3_inc=true;
		  $scope.proDelta1_real=false;
		  $scope.proDelta2_real=true;
		  $scope.proDelta3_real=true;
		  $scope.proDelta1_mile=false;
		  $scope.proDelta2_mile=true;
		  $scope.proDelta3_mile=true;
		  
		  $scope.attType="INCREMENTAL";
		  $rootScope.baseUrl="http://10.187.132.221:8080/RTDDashboard/api/";
		  
		  $scope.selectMenu=function(selectFlag){
			  $scope.resetAll();
			  if(selectFlag=="STANDARD")  {
				  $scope.stFlag=true;
				  $scope.getStandardAttributesByJobType('INCREMENTAL');
			  }
			  else if(selectFlag=="PROSPECT"){
				  $scope.prospectFlag=true;
				  $scope.getProspectAttributesByJobType('INCREMENTAL');
			  }
		
		  }
		  
		  $scope.selectMenu1=function(selectFlag){
			  $scope.resetTabs();
			  if(selectFlag=="INCREMENT"){
				  $scope.incFlag=true;
				  $scope.getStandardAttributesByJobType('INCREMENTAL');
				  $scope.attType="INCREMENTAL";
			  }else  if(selectFlag=="MILESTONE"){
				  $scope.mileFlag=true;
				  $scope.getStandardAttributesByJobType('MILESTONE');
				  $scope.attType="MILESTONE";
			  }else if(selectFlag=="REALTIME"){
				  $scope.realFlag=true;
				  $scope.getStandardAttributesByJobType('REALTIME');
				  $scope.attType="REALTIME";
			  }
		
		  }
		  
		  $scope.resetAll=function(){
			  $scope.stFlag=false;
			  $scope.prospectFlag=false;
		  }
		  
		  $scope.resetTabs=function(){
			  $scope.incFlag=false;
			  $scope.mileFlag=false;
			  $scope.realFlag=false;
		//	  $scope.delta1=false;
			//  $scope.delta2=true;
			 // $scope.delta3=true;
		  }
		  $scope.showdelta1_INC=function(){
			  if($scope.delta1_inc_inc==true) $scope.delta1_inc=false;
			  else $scope.delta1_inc=true;
		  }
		  $scope.showdelta2_INC=function(){
			  if($scope.delta2_inc==true) $scope.delta2_inc=false;
			  else $scope.delta2_inc=true;
		  }
		  $scope.showDelta3_INC=function(){
			  if($scope.delta3_inc==true) $scope.delta3_inc=false;
			  else $scope.delta3_inc=true;
		  }
		  
		  $scope.showDelta1_REAL=function(){
			  if($scope.delta1_real==true) $scope.delta1_real=false;
			  else $scope.delta1_real=true;
		  }
		  $scope.showDelta2_REAL=function(){
			  if($scope.delta2_real==true) $scope.delta2_real=false;
			  else $scope.delta2_real=true;
		  }
		  $scope.showDelta3_REAL=function(){
			  if($scope.delta3_real==true) $scope.delta3_real=false;
			  else $scope.delta3_real=true;
		  }
		  
		  $scope.showDelta1_MILE=function(){
			  if($scope.delta1_mile==true) $scope.delta1_mile=false;
			  else $scope.delta1_mile=true;
		  }
		  $scope.showDelta2_MILE=function(){
			  if($scope.delta2_mile==true) $scope.delta2_mile=false;
			  else $scope.delta2_mile=true;
		  }
		  $scope.showDelta3_MILE=function(){
			  if($scope.delta3_mile==true) $scope.delta3_mile=false;
			  else $scope.delta3_mile=true;
		  }
		  
		  $scope.showStandardAttributePopup=function(attrData){
			//  alert(attrData.attName);
			  $scope.getStandardAttributeByName($scope.attType,attrData);
			 
		  }
		  
		  $scope.getStandardAttributeByName=function(jobType,attributeName){
			  var urlLink=$rootScope.baseUrl+"rtd/getStandardAttributeByName/"+jobType+"/"+attributeName;
			  $http.get(urlLink).then(function(response){
				 $scope.attList=response.data;
				 $("#attModal").modal('show');
			  });
		  }
		  
		  $scope.getStandardAttributesByJobType=function(jobType){
			  var urlLink=$rootScope.baseUrl+"rtd/getStandardAttributesByJobType/"+jobType;
			  $http.get(urlLink).then(function(response){
				 $scope.redList=response.data.RED;
				 $scope.amberList=response.data.AMBER;
				 $scope.greenList=response.data.GREEN;
			  });
		  }
		  
		  // Prospect Logic Starts Here
		  
		  $scope.selectMenu2=function(selectFlag){
			  $scope.proResetTabs();
			  if(selectFlag=="INCREMENT"){
				  $scope.proIncFlag=true;
				  $scope.getProspectAttributesByJobType('INCREMENTAL');
				  $scope.attType="INCREMENTAL";
			  }else  if(selectFlag=="MILESTONE"){
				  $scope.proMileFlag=true;
				  $scope.getProspectAttributesByJobType('MILESTONE');
				  $scope.attType="MILESTONE";
			  }else if(selectFlag=="REALTIME"){
				  $scope.proRealFlag=true;
				  $scope.getProspectAttributesByJobType('REALTIME');
				  $scope.attType="REALTIME";
			  }
		
		  }
		  
		  $scope.proResetTabs=function(){
			  $scope.proIncFlag=false;
			  $scope.proMileFlag=false;
			  $scope.proRealFlag=false;
		//	  $scope.delta1=false;
			//  $scope.delta2=true;
			 // $scope.delta3=true;
		  }
		  $scope.proShowdelta1_INC=function(){
			  if($scope.proDelta1_inc_inc==true) $scope.proDelta1_inc=false;
			  else $scope.proDelta1_inc=true;
		  }
		  $scope.proShowdelta2_INC=function(){
			  if($scope.proDelta2_inc==true) $scope.proDelta2_inc=false;
			  else $scope.proDelta2_inc=true;
		  }
		  $scope.proShowDelta3_INC=function(){
			  if($scope.proDelta3_inc==true) $scope.proDelta3_inc=false;
			  else $scope.proDelta3_inc=true;
		  }
		  
		  $scope.proShowDelta1_REAL=function(){
			  if($scope.proDelta1_real==true) $scope.proDelta1_real=false;
			  else $scope.proDelta1_real=true;
		  }
		  $scope.proShowDelta2_REAL=function(){
			  if($scope.proDelta2_real==true) $scope.proDelta2_real=false;
			  else $scope.proDelta2_real=true;
		  }
		  $scope.proShowDelta3_REAL=function(){
			  if($scope.proDelta3_real==true) $scope.proDelta3_real=false;
			  else $scope.proDelta3_real=true;
		  }
		  
		  $scope.proShowDelta1_MILE=function(){
			  if($scope.proDelta1_mile==true) $scope.proDelta1_mile=false;
			  else $scope.proDelta1_mile=true;
		  }
		  $scope.proShowDelta2_MILE=function(){
			  if($scope.proDelta2_mile==true) $scope.proDelta2_mile=false;
			  else $scope.proDelta2_mile=true;
		  }
		  $scope.proShowDelta3_MILE=function(){
			  if($scope.proDelta3_mile==true) $scope.proDelta3_mile=false;
			  else $scope.proDelta3_mile=true;
		  }
		  
		  $scope.showProspectAttributePopup=function(attrData){
				//  alert(attrData.attName);
				  $scope.getProspectAttributeByName($scope.attType,attrData);
		  }
		  
		  $scope.getProspectAttributeByName=function(jobType,attributeName){
			  var urlLink=$rootScope.baseUrl+"rtd/getProspectAttributeByName/"+jobType+"/"+attributeName;
			  $http.get(urlLink).then(function(response){
				 $scope.attList=response.data;
				 $("#attModal").modal('show');
			  });
		  }
		  
		  $scope.getProspectAttributesByJobType=function(jobType){
			  var urlLink=$rootScope.baseUrl+"rtd/getProspectAttributesByJobType/"+jobType;
			  $http.get(urlLink).then(function(response){
				  $scope.redList=response.data.RED;
				  $scope.amberList=response.data.AMBER;
				  $scope.greenList=response.data.GREEN;
				
			  });
		  }
		  
		  $scope.getStandardAttributesByJobType('INCREMENTAL');
		  //$scope.getProspectAttributesByJobType('INCREMENTAL');
	 });
})();