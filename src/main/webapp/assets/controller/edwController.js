(function () {
	app.controller('edwController', function($scope,$http,$rootScope) {
	  $scope.message = 'Hello from edw Controller';
	  $rootScope.baseUrl="http://10.187.132.221:8080/RTDDashboard/api/";
	  $scope.getAllContacts=function(){
		  var urlLink=$rootScope.baseUrl+"rtd/getAllContacts";
		  $http.get(urlLink).then(function(response){
			 var data=response.data;
			 $('#contactTable').DataTable( {
				    data: data
				} );
		  });
	  }
	  $scope.overAllFlag=true;
	  $scope.incFlag=false;
	  $scope.mileFlag=false;
	  $scope.realFlag=false;
	  $scope.incThreshold=25;
	  
	  $scope.selectMenu=function(selectFlag){
		  $scope.resetAll();
		  if(selectFlag=="OVERALL")   $scope.overAllFlag=true;
		  else if(selectFlag=="INCREMENT")   $scope.incFlag=true;
		  else  if(selectFlag=="MILESTONE")   $scope.mileFlag=true;
		  else if(selectFlag=="REALTIME")   $scope.realFlag=true;
	  }
	  
	  $scope.resetAll=function(){
		  $scope.overAllFlag=false;
		  $scope.incFlag=false;
		  $scope.mileFlag=false;
		  $scope.realFlag=false;
	  }
	//  $scope.getAllContacts();
	 });
})();