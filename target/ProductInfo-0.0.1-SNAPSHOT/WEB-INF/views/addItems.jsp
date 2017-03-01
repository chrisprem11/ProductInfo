<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />

<title>Items</title>
</head>
<body>
	<div class="well">
		<h2 class="text-center text-primary">Items</h2>
	</div>
	<div class="container">
		<div class="row">
			<h3 class="text-danger">Add Items:</h3>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12"></div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
				<form action="addItem" modelAttribute="ItemDetails" method="post">
					<div class="form-group">
						<label>Item Name :</label> <input class="form-control" type="text"
							placeholder="Enter item name" name="itemName" required />
					</div>
					<div class="form-group">
						<label>Item Cost :</label> <input class="form-control" type="text"
							placeholder="Enter cost of the item" name="itemCost" required />
					</div>
					<div class="form-group">
						<label>Additional Cost :</label> <input class="form-control"
							type="text" placeholder="Enter additional cost if any"
							name="additionalCost" required />
					</div>
					<div class="form-group">
						<label>Additional Field :</label> <input class="form-control"
							type="text" placeholder="Enter additional field"
							name="additonalField" required />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-sm btn-primary pull-right" value="Add Item" />
					</div>
				</form>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12"></div>
		</div>
	</div>

	<script src="static/js/jquery-2.0.0.js" type="text/javascript"></script>
	<script src="static/js/bootstrap.js" type="text/javascript"></script>
	<script src="static/js/angular.js" type="text/javascript"></script>
	<script src="static/js/angular-route.js" type="text/javascript"></script>
	<script src="static/js/app/application.js" type="text/javascript"></script>
	<script src="static/js/controller/mainController.js"
		type="text/javascript"></script>
</body>
</html>