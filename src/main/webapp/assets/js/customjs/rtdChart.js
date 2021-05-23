function getChart(title,xData,yTitle,series){
	
	var chart = {
		      type: 'column'
		   };
	var title = {
		      text: title   
		   };
	var xAxis={
			categories:xData
	};
	var yAxis={
			title:{
				text:yTitle
			},
			plotLines:[{
				value: 0,
		        width: 1,
		        color: '#808080'
			}]
	};
	var legend={
			 layout: 'vertical',
		     align: 'right',
		     verticalAlign: 'middle',
		     borderWidth: 0
	};
	var credits= {
	      enabled: false
	  };
	var json={}
	json.title = title;
	 json.chart = chart; 
	  // json.subtitle = subtitle;
	 json.credits=credits;
	   json.xAxis = xAxis;
	   json.yAxis = yAxis;
	 //  json.tooltip = tooltip;
	   json.legend = legend;
	   json.series = series;
	   return json;
}