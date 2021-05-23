
(function(){
	
	angular.module("dashboard").config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise("/mainDashboard");
	
	
	  $stateProvider
      .state('rtdDashboard', {
          authenticate: false,
          url: "/rtdDashboard",
          templateUrl: "assets/view/rtdDashboard.html",
          controller: 'rtdController'
      }).state('edwDashboard', {
          authenticate: false,
          url: "/edwDashboard",
          templateUrl: "assets/view/edwDashboard.html",
          controller: 'edwController'
      }).state('contactDashboard', {
          authenticate: false,
          url: "/contactDashboard",
          templateUrl: "assets/view/contactDashboard.html",
          controller: 'contactController'
      }).state('transactionDashboard', {
          authenticate: false,
          url: "/transactionDashboard",
          templateUrl: "assets/view/transactionDashboard.html",
          controller: 'transactionController'
      }).state('mainDashboard', {
          authenticate: false,
          url: "/mainDashboard",
          templateUrl: "assets/view/mainDashboard.html",
          controller: 'mainController'
      }).state('attMonitor', {
          authenticate: false,
          url: "/attMonitor",
          templateUrl: "assets/view/attributeDashboard.html",
          controller: 'attributeController'
      }).state('mrdMonitor', {
          authenticate: false,
          url: "/mrdMonitor",
          templateUrl: "assets/view/mrdDashboard.html",
          controller: 'mrdController'
      }).state('reports', {
          authenticate: false,
          url: "/reports",
          templateUrl: "assets/view/reportDashboard.html",
          controller: 'reportController'
      });
	});
})();
