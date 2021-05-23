(function () {
	app.controller('mrdController', function($scope,$http,$rootScope) {
	//$scope.inputJSON="[{\"MRD_Object_Name\":\"X_EDW_RTD_INCLUD_PROD_MAS\",\"Last_Refresh_Time\":\"25/11/2016 08:27\",\"Last_Load_Time_In_RTD\":\"24/11/2016 09:07\",\"Current_Volume\":200,\"Previous_Volume\":180,\"Delta_Change\":11,\"Status\":\"red\"},{\"MRD_Object_Name\":\"X_EDW_RTD_INSIGHT_MSG\",\"Last_Refresh_Time\":\"25/11/2016 08:27\",\"Last_Load_Time_In_RTD\":\"24/11/2016 09:07\",\"Current_Volume\":200,\"Previous_Volume\":180,\"Delta_Change\":11,\"Status\":\"red\"},{\"MRD_Object_Name\":\"X_EDW_SEGMENT_BB_GRP_LKP\",\"Last_Refresh_Time\":\"25/11/2016 08:27\",\"Last_Load_Time_In_RTD\":\"24/11/2016 09:07\",\"Current_Volume\":200,\"Previous_Volume\":180,\"Delta_Change\":11,\"Status\":\"red\"}]";
	//$scope.inputData=JSON.parse($scope.inputJSON);
	 $rootScope.baseUrl="http://10.187.132.221:8080/RTDDashboard/api/";
	 
	 $scope.loadMrdDetails=function(mrdTable){
		 
		  //Loads transaction details based when a specific session Id is clicked. Displays them in a modal
		  for(var i=0;i<  $scope.mrdList.length;i++){
				if( $scope.mrdList[i].mrdTableName==mrdTable.currentTarget.innerHTML){
					 $scope.getMRDDetailByName($scope.mrdList[i].mrdTableName);
					 break;
				}
			 }	
		  }
	 
	  $scope.getAllMRDTablesStatus=function(){
		  var urlLink=$rootScope.baseUrl+"rtd/getAllMRDTablesStatus/";
		  $http.get(urlLink).then(function(response){
			 $scope.mrdList=response.data;
			 formDataTable($scope.mrdList);
		  });
	  }
	  
	  $scope.getMRDTablesByStatus=function(){
		  var urlLink=$rootScope.baseUrl+"rtd/getMRDTablesByStatus/RED";
		  $http.get(urlLink).then(function(response){
			 $scope.mrdRedList=response.data;
			 formDataTable($scope.mrdRedList);
		  });
	  }
	  
	  $scope.getMRDDetailByName=function(tableName){
		  var urlLink=$rootScope.baseUrl+"rtd/getMRDDetailByName/"+tableName;
		  $http.get(urlLink).then(function(response){
			 $scope.mrdDetail=response.data;
			  $('#mrdModal').modal('show');
		  });
	  }
	  
	  $scope.stateChanged = function(checkFlag) {
		  if(checkFlag==false){
			  $scope.getAllMRDTablesStatus();
		  }else{
			  $scope.getMRDTablesByStatus();
		  }
		    
		}
	  
	  $scope.getAllMRDTablesStatus();
	  
	  function formDataTable(tableData){
		  $('#mrdDash').dataTable().fnDestroy();
			 $('#mrdDash').dataTable({
			       "data": tableData,
			       "bDestroy": true,
			       "deferRender": true,
			       "columns": [
			            { 
			             "data": "mrdTableName",
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
			          { "data": "lastRefreshTime" }, 
			          { "data": "rtdLoadTime" }, 
			          { "data": "currentVolume" }, 
			          { "data": "previousVolume" }, 
			          { "data": "deltaChange" }, 
			          { "data": "status" }
			       ]
			    });
			  for(var i=0;i< tableData.length;i++){
				  var tempString='sessionLink'+tableData[i].mrdTableName;
				  var el = document.getElementById(tempString);
				 // el.onclick =  $scope.loadMrdDetails();
				  el.addEventListener("click", $scope.loadMrdDetails);
				 }
			  $('#mrdDash TR').each(function() {
				var lastCell= $(this).find("td:last").text();
				if(lastCell=="GREEN"){$(this).find("td:last").html('<div id="greenCircle">'); }
				else if(lastCell=="AMBER"){$(this).find("td:last").html('<div id="yellowCircle">'); }
				else if(lastCell=="RED"){$(this).find("td:last").html('<div id="redCircle">'); }
				  });
			}
	
	
	  $('#mrdToggle').change(function() {
	    	$scope.stateChanged($(this).prop('checked'));
	      })
	
	
	});
})();