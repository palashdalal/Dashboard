(function () {
	app.controller('transactionController', function($scope,$http,$rootScope) {
		$scope.startDate=getCurrentDate();
		$scope.endDate=getCurrentDate();
		$scope.transaction="";
		$scope.requestAttr="";
		$scope.idName="Session Id";
		$scope.paramId="";
		$rootScope.baseUrl="http://10.187.132.221:8080/RTDDashboard/api/";
		  
		  $scope.selectServer=function(server){
			   //select a server
			   $scope.serverName=server.serverName;
			   $scope.serverType=server.serverState;
			   $scope.currentServer=server;
		  }
		  $scope.getAllServerDetails=function(){
			   //Get all server names
			   var urlLink=$rootScope.baseUrl+"rtd/getAllServers";
			   $http.get(urlLink).then(function(response){
					  $scope.serverList=response.data;
					  var serverData=$scope.serverList[0];
					  $scope.selectServer(serverData);
					
				});
		   }
		  
		  $scope.loadTransactionDetails=function(sessionId){
			  //Loads transaction details based when a specific session Id is clicked. Displays them in a modal
			  for(var i=0;i< $scope.transactionList.length;i++){
					if($scope.transactionList[i].sessionId==sessionId.currentTarget.innerHTML){
						$scope.transaction=$scope.transactionList[i];
						$scope.requestAttr=transformTransactionReqRes($scope.transactionList[i].request);
						$scope.responseAttr=transformTransactionReqRes($scope.transactionList[i].response);
						break;
					}
				 }	
			  $('#myModal').modal('show');
		  }
		  
		  $scope.getTransactionByDate=function(){
			  //Gets Transaction Details based on Start Date and End Date. Here session Id/Customer Id fields are optional and may or may not be entered
			  if($scope.startDate==getCurrentDate() && $scope.endDate==getCurrentDate()){}
			  else{
				  $scope.startDate=document.getElementById("startDate").value;
				  $scope.endDate=document.getElementById("endDate").value;
			  }
			  var urlLink;
			  if( $scope.startDate==""|| $scope.endDate==""){
				  alert("Dates cannot be empty");
				  }else{
					  if($scope.paramId!=""){
						  if($scope.idName="Session Id"){
							  urlLink=$rootScope.baseUrl+"rtd/getTransactionMonitoringByDate/sessionId/"+$scope.paramId+ "?startDate=" + $scope.startDate + "&endDate="+$scope.endDate; 
						  }else urlLink=$rootScope.baseUrl+"rtd/getTransactionMonitoringByDate/customerId/"+$scope.paramId+ "?startDate=" + $scope.startDate + "&endDate="+$scope.endDate; 
					  }else urlLink=$rootScope.baseUrl+"rtd/getTransactionMonitoringByDate?startDate=" + $scope.startDate + "&endDate="+$scope.endDate;
					  $http.get(urlLink).then(function(response){
						 var data=response.data;
						 $scope.transactionList=data;
						 $('#trnsctionReports').dataTable().fnDestroy();
						 $('#trnsctionReports').dataTable({
					       "data": data,
					       "bDestroy": true,
					       "deferRender": true,
					       "columns": [
					            { 
					             "data": "sessionId",
					             "render" : function(data, type, row, meta){
					                if(type === 'display'){
					                   return $('<a id="sessionLink' +data+'">')
					                      .text(data)
					                      .wrap('<div></div>')
					                      .parent()
					                      .html();
		
					                } else {
					                   return data;
					                }
					             }
					          },
					          { "data": "customerId" }, 
					          { "data": "accountId" }, 
					          { "data": "advisorName" }, 
					          { "data": "requestStartTime" }, 
					          { "data": "requestEndTime" }, 
					       ]
					    });
					  for(var i=0;i< $scope.transactionList.length;i++){
						  var tempString='sessionLink'+$scope.transactionList[i].sessionId;
						  var el = document.getElementById(tempString);
						  el.onclick =  $scope.loadTransactionDetails;
						 }	
				  });
					  //Reset Date Values
					  $scope.startDate="";
						$scope.endDate="";
			  }
		  }
		
		  $scope.selectId=function(idName){
			  //selects customer Id/session Id from dropdown
			  if(idName=="sessionId"){
				  $scope.idName="Session Id";
			  }else   $scope.idName="Customer Id";
			 
		  }
		  
		  function transformTransactionReqRes(data){
			  //Transform Request Param values so that they can be displayed properly in modal
			  $scope.element="";
			  $scope.elementList=[];
			  if(data!=null){
				  var res=data.split(", ");
				  for(var i=0;i<res.length;i++){
					  if(i%2==0){
						  $scope.param1=res[i].split(":")[0];
						  $scope.param2=res[i].split(":")[1];
					  }else{
						  $scope.param3=res[i].split(":")[0];
						  $scope.param4=res[i].split(":")[1];
					  }
					  if(i%2!==0 || i==res.length-1){
						  $scope.element={param1:$scope.param1,param2:$scope.param2,param3:$scope.param3,param4:$scope.param4}
						  $scope.elementList.push($scope.element);
					  }
				  }
				  return $scope.elementList;
			  }else
				  return null;
		  }
		  
		  function getCurrentDate(){
			  //Returns present Date
			  var today = new Date();
			  var dd = today.getDate();
			  var mm = today.getMonth()+1; //January is 0!

			  var yyyy = today.getFullYear();
			  if(dd<10){
			      dd='0'+dd
			  } 
			  if(mm<10){
			      mm='0'+mm
			  } 
			  var today = dd+'/'+mm+'/'+yyyy;
			  return today;
		  }
		  
		  //Initialise Code
		  $scope.getAllServerDetails();
		  $scope.getTransactionByDate();
	});
})();