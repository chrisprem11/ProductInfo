app.controller("dataController",['$scope','$http',function($scope,$http){
	
	$scope.chartData = [];
	$http.get("http://localhost:8080/ProductInfo/getDataChart").then(
			
			function(response) {
				$scope.userData =response.data;
				
				 $.each($scope.userData, function(key, value) {
					    console.log( key + " , " + value );
					    $scope.chartData.push({label: key,'y':value});
					  });
				 console.log($scope.chartData);
				 $(function () {
						//Better to construct options first and then pass it as a parameter
						var options = {
							title: {
								text: "Monthly Sales Amount"
							},
					                animationEnabled: true,
							data: [
							{
								type: "pie", //change it to line, area, column, pie, etc
								dataPoints: $scope.chartData
							}
							]
						};

						$("#chartContainer").CanvasJSChart(options);
						$("#chart1").CanvasJSChart(options);
						$("#chart2").CanvasJSChart(options);
						$("#chart3").CanvasJSChart(options);

					});

			});
	
	
	}]);