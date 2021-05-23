(function () {
	app.controller('contactController', function($scope,$http,$rootScope) {
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
	  
	  $scope.getAllContacts();
	 });
})();