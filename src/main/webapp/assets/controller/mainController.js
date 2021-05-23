(function () {
	app.controller('mainController', function($scope,$http,$rootScope) {
		  $scope.serverName="";
		  $scope.serverType="";
		  $scope.currentServer="";
		  $rootScope.baseUrl="http://10.187.132.221:8080/RTDDashboard/api/";
		  $scope.serverIlsList=[];
		  $('#alertDate').val(getCurrentDate());
		  $('#serverChartStatDate').val(getCurrentDate());
		  $scope.serverType="Production";
		  $scope.chartFlag=true;
		  $scope.serverTypeFlag=true;
		  $scope.serverParams=["Request Count","Session Count", "Timeout Count"];
		   
		  $scope.selectServer=function(server){
			   //select a server
			  $scope.server=server;
			   $scope.serverName=server.serverName;
			
		   }
		   
		  $scope.selectServerType=function(serverType){
			   //select server Type
			  $scope.serverType=serverType;
			  $scope.getAllServersByState($scope.serverType);
		   }
		  
		   function getServerByServerId(serverId){
			//Get server details based on serverId
			   for(var i=0;i<$scope.serverList.length;i++){
				   if($scope.serverList[i].serverId==serverId){
					   return $scope.serverList[i];
				   }
			   }
		   }
		   
		   $scope.getAllServersByState=function(serverType){
			   var urlLink=$rootScope.baseUrl+"rtd/getAllServersByState/serverState/" + serverType;
			   $http.get(urlLink).then(function(response){
					  $scope.modifiedServerList=response.data;
					  if($scope.modifiedServerList==null || $scope.modifiedServerList.length<=0) $scope.serverTypeFlag=false;
					  else $scope.serverTypeFlag=true;
					 
				});
		   }
		   
		   $scope.getAllServerDetails=function(){
			   //Get all server Details
			   var urlLink=$rootScope.baseUrl+"rtd/getAllServers";
			   $http.get(urlLink).then(function(response){
					  $scope.serverList=response.data;
					  $scope.server= $scope.serverList[0];
					  $scope.serverName=  $scope.serverList[0].serverName;
					   $scope.serverType=  $scope.serverList[0].serverState;
					   //$scope.getDailyServerStatus($scope.server);
					  /*for(var i=0;i<)*/
					/*  $("#ilsStatsTable").tableHeadFixer({
							head: false,
							foot: false,
							left: 1
							
							}); 
					  var table = $('#ilsStatsTable').DataTable( {
					        scrollX:        true,
					        scrollCollapse: true,
					        paging:         false,
					        fixedColumns:   {
					            leftColumns: 1,
					            rightColumns: 1
					        }
					    } );*/
				});
		   }
		   
		   $scope.getAllInlineStats=function(){
			//return all the inline service present status, inline service id,last refresh time and corresponding serice id  
			   var urlLink=$rootScope.baseUrl+"rtd/getAllInlineStats";
			   var urlLink1=$rootScope.baseUrl+"rtd/getCurrentInlineStatusForAllServers";
			   $http.get(urlLink).then(function(response){
					  var temp=response.data;
					  var date=new Date(temp[0].refreshTime);
					 // alert(date.toString("dd-MMM-yyyy hh:mm:ss"));
					  var date=new Date(temp[0].refreshTime).toISOString();
					  var date1=date.substring(0,10) +" " +date.substring(11,20) + " BST";
					  //alert(date1);
					  $scope.refreshTime= date1;
				});
			   $http.get(urlLink1).then(function(response){
					  $scope.allInlineStatList=response.data;
				});
		   }
		   
		   $scope.getCurrentInlineStatusForAllServers=function(){
			//
			   var urlLink=$rootScope.baseUrl+"rtd/getCurrentInlineStatusForAllServers";
			   $http.get(urlLink).then(function(response){
					  $scope.allInlineRemoteStatList=response.data;
					  $scope.allInlineStatList=response.data;
					  $("#ilsStateModal").modal('show');
				});
		   }
		   
		   $scope.checkILSStatusRemote=function(ils,serverId){
			   //checks ILS status for a particular server
			   var flag=0;
			   var status;
			   try{
				   for(var i=0;i<$scope.allInlineRemoteStatList.length;i++){
					   if($scope.allInlineStatList[i].serverId==serverId && $scope.allInlineRemoteStatList[i].ilsid==ils.ilsId){
								flag=1;
								status= $scope.allInlineRemoteStatList[i].status;
								break;
						}
					}
				   if(flag==1){
						return status;
					}else{
						return 'NA';
					}
			   }catch(e){
				   
			   }
			} 
		   
		   $scope.getAllServerStatsByDate=function(){
				//return the hourly Server Stats by date
				   var urlLink=$rootScope.baseUrl+"rtd/getAllServerStatsByDate"+"?startDate="+getCurrentDate();
				   var urlLink=$rootScope.baseUrl+"rtd/getAllServerStatsByDate"+"?startDate="+getCurrentDate();
				   $http.get(urlLink).then(function(response){
						  $scope.allServerStatList=response.data;
						  $scope.series=[];
						//  $scope.linechartValues=[];
						  $scope.allLinechartValues=[];
						  $scope.lineLabels=[];
						  $scope.reqValList=[];
						  $scope.sesValList=[];
						  $scope.timeoutValList=[];
						  for(var i=0;i< $scope.serverList.length;i++){
						//	  $scope.series.push($scope.serverList[i].serverName);
							  $scope.reqVal=[];
							  $scope.sesVal=[];
							  $scope.timeoutVal=[];
							  $scope.linechartValues=[];
							  $scope.serverStatList=$scope.allServerStatList[$scope.serverList[i].serverId];
							  $scope.reqSeries={};
							  $scope.sesSeries={};
							  $scope.timeOutSeries={};
							  for(var j =0;j< $scope.serverStatList.length;j++){
								  $scope.reqVal.push($scope.serverStatList[j].reqCountAfterRefresh);
								  $scope.sesVal.push($scope.serverStatList[j].sessnCountAfterRefresh);
								  $scope.timeoutVal.push($scope.serverStatList[j].timedOutReqCountAfterRefresh);
								  if(i==0){
									  $scope.lineLabels.push($scope.serverStatList[j].refreshTime);
								  }
							  }
							  $scope.temp=[1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24];
							  $scope.reqSeries["name"]=$scope.serverList[i].serverName;
								  $scope.reqSeries["data"]=$scope.reqVal;
								  $scope.sesSeries["name"]=$scope.serverList[i].serverName;
								  $scope.sesSeries["data"]=$scope.sesVal;
								  $scope.timeOutSeries["name"]=$scope.serverList[i].serverName;
								  $scope.timeOutSeries["data"]=$scope.timeoutVal;
							  $scope.reqValList.push($scope.reqSeries);
							  $scope.sesValList.push($scope.sesSeries);
							  $scope.timeoutValList.push($scope.timeOutSeries);
							  $scope.allLinechartValues.push($scope.linechartValues);
							  
						  }
						 // $scope.temp=[1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24];
						  
							$scope.requestChart=getChart("Hourly Request Count", $scope.lineLabels,"Req Count", $scope.reqValList);
							$scope.sessionChart=getChart("Hourly Session Count", $scope.lineLabels,"Session Count", $scope.sesValList);
							$scope.timeOutChart=getChart("Hourly TimeOut Count", $scope.lineLabels,"Timeouts", $scope.timeoutValList);
							 $('#reqContainer').highcharts($scope.requestChart);
							 $('#sesContainer').highcharts($scope.sessionChart);
							 $('#timeOutContainer').highcharts($scope.timeOutChart);
						 
					});
			}
		   
		   $scope.getServerStatusByServerId=function(serverId){
			   //Get server and its ILs details plus ils status by serverId
			   var urlLink=$rootScope.baseUrl+"rtd/getAllInlineByServer/" + server.serverId;
			   $http.get(urlLink).then(function(response){
				   $scope.serverDetails=response.data;
				});
		   }
		   
		   $scope.getAllInlineSevices=function(){
			   //Get all inline service Details
			   var urlLink=$rootScope.baseUrl+"rtd/getAllInline";
			   $http.get(urlLink).then(function(response){
					  $scope.inlineList=response.data;
					  $scope.inlineMap=new Object();
					  for(var i=0;i<$scope.inlineList.length;i++){
						  $scope.inlineMap[$scope.inlineList[i].ilsId]=$scope.inlineList[i];
					  }
				});
		   }
		   
		   $scope.showServerDetails=function(serverId){
			   //Opens up server Details in a modal
			for(var i=0;i<$scope.serverList.length;i++){
				if($scope.serverList[i].serverId==serverId){
					$scope.server=$scope.serverList[i];
					break;
				}
			} 
			 $('#serverModal').modal('show');
		   }
		   
		   $scope.showILSDetails=function(serverId,ils){
			   //Opens up server Details in a modal
			for(var i=0;i<$scope.serverList.length;i++){
				if($scope.serverList[i].serverId==serverId){
					$scope.ilsLis=$scope.serverList[i].teams;
					for(var j=0;j<$scope.ilsLis.length;j++){
						if($scope.ilsLis[j].ilsName==ils.ilsName){
							return 
						}
					}
					break;
				}
			} 
		   }
		   
		   $scope.checkILSStatus=function(ils,serverId){
			   //checks ILS status for a particular server
			   var flag=0;
			   var status;
			   try{
				   for(var i=0;i<$scope.allInlineStatList.length;i++){
					   if($scope.allInlineStatList[i].serverId==serverId && $scope.allInlineStatList[i].ilsid==ils.ilsId){
								flag=1;
								status= $scope.allInlineStatList[i].status;
								break;
						}
					}
				   if(flag==1){
						return status;
					}else{
						return 'NA';
					}
			   }catch(e){
				   
			   }
			} 
			
		   
		   $scope.getServerStats=function(serverId){
			   //get server status based on server id
			   var urlLink=$rootScope.baseUrl+"rtd/getServerStats/" + serverId;
			   $http.get(urlLink).then(function(response){
					  $scope.serverStats=response.data;
				});
		   }
		   
		   $scope.getAllServersStatusFromRemote=function(){
			   var urlLink=$rootScope.baseUrl+"rtd/getAllServersStatusFromRemote";
			   $http.get(urlLink).then(function(response){
					  $scope.remoteStatList=response.data;
					  $("#serverCurrentStatModal").modal('show');
				});
		   }
		   
		   $scope.getMaxRecomStats=function(){
			   var urlLink=$rootScope.baseUrl+"rtd/getMaxRecomStats";
			   $http.get(urlLink).then(function(response){
					  $scope.maxRecomList=response.data;
					  $("#maxRecomModal").modal('show');
				},function errorCallback(response) {
					$scope.errorMsg="Unable To Max Recommendation Stats";
					$("#genErrorModal").modal('show');
				  });
		   }
		   
		   $scope.getAlertsByDate=function(){
			   $scope.dailyDate=document.getElementById("alertDate").value;
			   var urlLink=$rootScope.baseUrl+"rtd/getAlertsByDate?date=" + $scope.dailyDate;
			   $http.get(urlLink).then(function(response){
				   $scope.alertList=response.data;
				   $scope.custAlertList=[];
				   for(var i=0;i<3;i++){
					   $scope.custAlertList.push($scope.alertList[i]);
				   }
			   });
		   }
		   
		   $scope.getILSStatus=function(ils,serverId){
			   var urlLink=$rootScope.baseUrl+"rtd/checkILSHealth/" + ils.ilsName;
			   var currentServer=getServerByServerId(serverId);
			   var serverIP=currentServer.serverIp+":"+ils.serverPort;
			   $http.get(urlLink,{
				   headers:{'serverIP':serverIP}
			   }).then(function(response){
				//alert(response.data);
				if(response.data===true){
					ils.status='true';
					//alert('Ping Successful');
					$("#successModal").modal('show');
				}else{
					ils.status='false';
					//alert('Ping Failure. Please check the ILS on this server');
					$("#errorModal").modal('show');
				}
				}, function errorCallback(response) {
					$scope.errorMsg="Unable To fetch Inline Statistics";
					$("#genErrorModal").modal('show');
				  });
		   }
		   
		   $scope.getAllAlerts=function(){
			   $('#allAlertTable').dataTable().fnDestroy();
				 $('#allAlertTable').dataTable({
			       "data": $scope.alertList,
			       "bDestroy": true,
			       "deferRender": true,
			       "columns": [
			          { "data": "alertType" }, 
			          { "data": "alertDesc" }, 
			          { "data": "alertDate" }, 
			       ]
			    });
			   $("#alertModal").modal('show');
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
					$scope.errorMsg="Unable To Load Server Statistics";
					$("#genErrorModal").modal('show');
				  });
		   }
		   
		   $scope.getDailyServerStatus=function(server){
			 //  $scope.dailyDate=document.getElementById("serverStatDate").value;
			   var urlLink=$rootScope.baseUrl+"rtd/getServerStatusByDate/" + server.serverId +"?startDate="+ $scope.dailyDate;
			   $http.get(urlLink).then(function(response){
				   $scope.totReqCount=response.data.reqCountAfterRefresh;
					  $scope.totSessnCount=response.data.sessnCountAfterRefresh;
					  $scope.totTimedOutReqCount=response.data.timedOutReqCountAfterRefresh;
					  if($scope.totReqCount!=null || $scope.totTimedOutReqCount!=null ){
						  $scope.chartFlag=false;
						  $scope.labels = ["Request Count", "Timeouts Count"];
						  $scope.data = [$scope.totReqCount,$scope.totTimedOutReqCount];
					  }else{
						  $scope.chartFlag=true;
						  $scope.totReqCount=0;
						  $scope.totSessnCount=0;
						  $scope.totTimedOutReqCount=0;
					  }
				   
				}, function errorCallback(response) {
					$scope.errorMsg="Unable To Load Daily Server Statistics. Charts will not work";
					$("#genErrorModal").modal('show');
				  });
		   }
		   
		   $scope.showServerStats=function(server,countFlag){
			   var tempServer;
			   var servFlag=0;
			   for(var i=0;i<$scope.allServerStats.length;i++){
				  if($scope.allServerStats[i].serverId==server.serverId){
					  tempServer=$scope.allServerStats[i];
					  servFlag=1;
					  break;
				  }
			   }
			   if(servFlag==1){
				   if(countFlag=="REQCOUNT") return tempServer.reqCountAfterRefresh;
				   else if(countFlag=="SESSNCOUNT") return tempServer.sessnCountAfterRefresh;
				   else if(countFlag=="TIMOUTREQCOUNT") return tempServer.timedOutReqCountAfterRefresh;
			   }else{
				   return 'NA';
			   }
		   }
		   
		   $scope.getServerName=function(serverID){
			   for(var i=0;i<$scope.serverList.length;i++){
					  if($scope.serverList[i].serverId==serverID){
						  return $scope.serverList[i].serverName;
					  }
				   }
		   }
		   
		   	$scope.getDailyServerStatusForAllServers=function(){
			   
			   $scope.dailyDateForTable=document.getElementById("serverChartStatDate").value;
			   var urlLink=$rootScope.baseUrl+"rtd/getAllServerStatusByDate/?startDate="+ $scope.dailyDateForTable;
			   $http.get(urlLink).then(function(response){
				   $scope.allServerStats=response.data;
				}, function errorCallback(response) {
					$scope.errorMsg="Unable To Load Daily Server Statistics. Charts will not work";
					$("#genErrorModal").modal('show');
				  });
		   }
		   	
		   $scope.getRecomStats=function(){
			   //alert(getYesterdayDate());
			   var urlLink=$rootScope.baseUrl+"rtd/getRecomStats?date="+ getYesterdayDate();
			   $http.get(urlLink).then(function(response){
				   $scope.recomStatList=response.data;
				}, function errorCallback(response) {
					$scope.errorMsg="Unable To Load Recommendation Statistics";
					$("#genErrorModal").modal('show');
				  });
		   }
		   
		   $scope.getInlineServiceName=function(ilsId){
			 //  alert($scope.inlineMap[ilsId].ilsName);
			   return $scope.inlineMap[ilsId].ilsName;
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
		   
		   function getYesterdayDate(){
				  //Returns present Date
				  var today = new Date();
				  var dd = today.getDate()-1;
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
		  
		   //initial functions to call
		   $scope.getAllServerDetails();
		   $scope.getAllServersByState($scope.serverType);
		   $scope.getAllInlineSevices();
		   $scope.getAllInlineStats();
		   //$scope.getCurrentInlineStatusForAllServers();
		   $scope.getDailyServerStatusForAllServers();
		   $scope.getAllServerStatsByDate();
		   $scope.getAlertsByDate();
		   $scope.getRecomStats();
		  // $scope.getDailyServerStatus($scope.server);
		 //  $scope.getDailyServerStatus();
		   
		   //Chart
		   
		//   $scope.lineLabels = ["January", "February", "March", "April", "May", "June", "July"];
		//   $scope.series = ['Series A', 'Series B'];
		/*   $scope.lineData = [
		     [65, 59, 80, 81, 56, 55, 40],
		     [28, 48, 40, 19, 86, 27, 90]
		   ];*/
		   /*$scope.onClick = function (points, evt) {
		     console.log(points, evt);
		   };*/
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