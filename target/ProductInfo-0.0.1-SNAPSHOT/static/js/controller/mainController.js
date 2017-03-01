app.controller("mainController", [ '$scope', '$http', function(scope, http) {
	scope.message = "Hello World !";

	$http.get("http://localhost:8080/ProductInfo/getDataChart").then(function(response) {
		var userData = response.data;
		console.log(userData);
	});
} ]);