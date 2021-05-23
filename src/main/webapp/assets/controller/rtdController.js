(function () {
	app.controller('rtdController', function($scope,$http,$rootScope) {
	 
	  $scope.serverName="";
	  $scope.serverType="";
	  $scope.currentServer="";
	  $rootScope.baseUrl="http://10.187.132.221:8080/RTDDashboard/api/";
	  
	   $scope.selectServer=function(server){
		   //select a server
		   $scope.serverName=server.serverName;
		   $scope.serverType=server.serverState;
		   //Get inline services for that server
		  
		   var urlLink=$rootScope.baseUrl+"rtd/getAllInlineByServer/" + server.serverId;
		   $http.get(urlLink).then(function(response){
			   $scope.serverDetails=response.data;
			   $scope.inlineServices=$scope.serverDetails.ilsList;
			   $scope.currentServer=$scope.serverDetails;
			   $scope.serverName=$scope.serverDetails.serverName;
			   $scope.serverType=$scope.serverDetails.serverState;
			   $scope.getServerStats($scope.serverDetails.serverId);
			   $scope.refreshTime=getDate($scope.inlineServices[0].refreshTime);
			});
		   /*for(var i=0;i<$scope.serverDetails.length;i++ ){
			   if( $scope.serverName===$scope.serverDetails[i].serverName){
				   $scope.inlineServices=$scope.serverDetails[i].ilsList;
				   $scope.currentServer=$scope.serverDetails[i];
					$scope.getServerStats($scope.serverDetails[i].serverId);
			   }
		   }*/
		   
	   }
	   
	   $scope.getAllServerDetails=function(){
		   //Get all server names
		   var urlLink=$rootScope.baseUrl+"rtd/getAllServers";
		   $http.get(urlLink).then(function(response){
				  $scope.serverList=response.data;
				  var serverData=$scope.serverList[0];
				  $scope.selectServer(serverData);
				/*//  $scope.currentServer=$scope.serverDetails[0];
				//  $scope.inlineServices=$scope.serverDetails[0].ilsList;
				  $scope.serverName=$scope.serverDetails[0].serverName;
				  $scope.serverType=$scope.serverDetails[0].serverState;
				  $scope.getServerStats($scope.serverDetails[0].serverId);*/
			});
	   }
	   
	  /* $scope.getILSByServer=function(){
		   //get all ILS based on serverIP
		   $http.get("assets/data/ils.json").then(function(response){
				  $scope.inlineServices=response.data;
			});
	   }*/
	   
	 /*  $scope.getServer=function(){
		   //get server details based on server id
		   $http.get("assets/data/ils.json").then(function(response){
				  $scope.inlineServices=response.data;
				  
			});
	   }*/
	   
	   $scope.getServerStats=function(serverId){
		   //get server status based on server id
		   var urlLink=$rootScope.baseUrl+"rtd/getServerStats/" + serverId;
		   $http.get(urlLink).then(function(response){
				  $scope.serverStats=response.data;
				 
			});
	   }
	   
	   $scope.getILSStatus=function(ils){
		   var urlLink=$rootScope.baseUrl+"rtd/checkILSHealth/" + ils.ilsName;
		   $http.get(urlLink,{
			   headers:{'serverIP':$scope.currentServer.serverIp}
		   }).then(function(response){
			//alert(response.data);
			if(response.data===true){
				ils.status='true';
				alert('Ping Successful');
			}else{
				ils.status='false';
				alert('Ping Failure. Please check the ILS on this server');
			}
			}, function errorCallback(response) {
			   alert(response);
			  });
	   }
	   
	   $scope.getServerStatus=function(server){
		   var urlLink=$rootScope.baseUrl+"rtd/getServerStatus/" + server.serverId;
		   $http.get(urlLink).then(function(response){
			//alert(response.data);
			   //$scope.serverStats=response.data;
			   $scope.serverStats.totReqCount=response.data.totReqCount;
				  $scope.serverStats.totSessnCount=response.data.totSessnCount;
				  $scope.serverStats.totTimedOutReqCount=response.data.totTimedOutReqCount;
			}, function errorCallback(response) {
			   alert(response);
			  });
	   }
	   
	   function getDate(date){
		   var monthNames = new Array("January", "February", "March", 
				   "April", "May", "June", "July", "August", "September", 
				   "October", "November", "December");

				   var today = new Date(date);
				   var cDate = today.getDate();
				   var cMonth = today.getMonth();
				   var cYear = today.getFullYear();

				   var cHour = today.getHours();
				   var cMin = today.getMinutes();
				   var cSec = today.getSeconds();

				   return monthNames[cMonth] + " " +cDate  + "," +cYear + " " +cHour+ ":" + cMin+ ":" +cSec ;
				   
	   }
	   //initial functions to call
	   $scope.getAllServerDetails();
	   
	 
	});
})();