(function () {
	app.controller('reportController', function($scope,$http,$rootScope) {
		  
		
		//  $('#startDate').val(getCurrentDate());
		//  $('#endDate').val(getCurrentDate());
		
		  $rootScope.baseUrl="http://10.187.132.221:8080/RTDDashboard/api/";
		  $scope.displaytableFlag=false;
		  $scope.displayGraphFlag=false;
		  $scope.serverParams=["Request Count","Session Count", "Timeout Count"];
		  
		  $scope.selectServer=function(server){
			   //select a server
			   $scope.serverName=server.serverName;
			   $scope.serverType=server.serverState;
			   $scope.currentServer=server;
			  // $scope.getServerStatsByServer($scope.currentServer);
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
		  
		  $scope.getAllStats=function(){
			  var urlLink=$rootScope.baseUrl+"rtd/getAllStatsHistory";
			  $http.get(urlLink).then(function(response){
				 var data=response.data;
				 $('#rtdReports').DataTable( {
					    data: data
					} );
				
			  });
		  }
		  
		  $scope.getServerStatsByServer=function(server){
			  if($('#startDate').val()!=null && $('#endDate').val()!=null && $('#startDate').val()!="" && $('#endDate').val()!=""){
			  var urlLink=$rootScope.baseUrl+"rtd/getServerStatsTableByDate/" + server.serverId+"?startDate="+$('#startDate').val()+"&endDate="+$('#endDate').val();;
			  $http.get(urlLink).then(function(response){
				 var data=response.data;
				 $('#rtdReports').dataTable().fnDestroy();
				 $('#rtdReports').DataTable( {
					    data: data
					} );
				 $scope.displaytableFlag=true;
				 $scope.getServerStatsByDate();
			  });
			  }else  $scope.displaytableFlag=false;
		  }
		  
		  $scope.getServerStatsByDate=function(){
				//return the hourly Server Stats by date
			  	if($('#startDate').val()!=null && $('#endDate').val()!=null && $('#startDate').val()!="" && $('#endDate').val()!=""){
				   var urlLink=$rootScope.baseUrl+"rtd/getServerStatsByDate/server/"+  $scope.currentServer.serverId+"?startDate="+$('#startDate').val()+"&endDate="+$('#endDate').val();
				  //alert(urlLink);
				   $http.get(urlLink).then(function(response){
						  $scope.serverchartData=response.data;
						  $scope.series=[];
						  $scope.series.push($scope.serverList[0].serverName);
						  $scope.linechartValues=[];
						//  $scope.allLinechartValues=[];
						  $scope.lineLabels=[];
						  $scope.reqVal=[];
						  $scope.sesVal=[];
						  $scope.timeoutVal=[];
						  $scope.keys=Object.keys($scope.serverchartData);
						  for(var i=0;i< $scope.keys.length;i++){
								 // $scope.series.push($scope.serverList[i].serverName);
								  $scope.reqVal.push($scope.serverchartData[$scope.keys[i]].reqCountAfterRefresh);
								  $scope.sesVal.push($scope.serverchartData[$scope.keys[i]].sessnCountAfterRefresh);
								  $scope.timeoutVal.push($scope.serverchartData[$scope.keys[i]].timedOutReqCountAfterRefresh);
								  $scope.lineLabels.push($scope.serverchartData[$scope.keys[i]].refreshTime);
								  
							  }
						/*  for(var i=0;i< $scope.serverchartData.length;i++){
							 // $scope.series.push($scope.serverList[i].serverName);
							  $scope.reqVal.push($scope.serverchartData[i].reqCountAfterRefresh);
							  $scope.sesVal.push($scope.serverchartData[i].sessnCountAfterRefresh);
							  $scope.timeoutVal.push($scope.serverchartData[i].timedOutReqCountAfterRefresh);
							  $scope.lineLabels.push($scope.tempVar[j].refreshTime);
							  
						  }*/
							  $scope.linechartValues.push($scope.reqVal);
							  $scope.linechartValues.push($scope.sesVal);
							  $scope.linechartValues.push($scope.timeoutVal);
							  $scope.displayGraphFlag=true
					});
			  	}else  $scope.displayGraphFlag=false;
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
	   
		  
		  $scope.getAllServerDetails();
		 
		 
		  
		 // $scope.getAllStats();
		  //chart
		  $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
		   $scope.options = {
		     scales: {
		       yAxes: [
		         {
		           id: 'y-axis-1',
		           type: 'linear',
		           display: true,
		           position: 'left'
		         }
		         
		       ]
		     }
		   };
	});
})();