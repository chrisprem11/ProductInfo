<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />

<title>Customers</title>
</head>
<body>
	<div class="well">
		<h2 class="text-center text-primary">Customers</h2>
	</div>
	<div class="container">
		<div class="row">
			<h3 class="text-danger">Create Customer:</h3>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12"></div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
				<form action="addCustomer" modelAttribute="customerDetails"
					method="post">
					<div class="form-group">
						<label>First Name :</label> <input class="form-control"
							type="text" placeholder="Enter item name" name="firstName"
							required />
					</div>
					<div class="form-group">
						<label>Last Name :</label> <input class="form-control" type="text"
							placeholder="Enter cost of the item" name="lastName" required />
					</div>
					<div class="form-group">
						<label>Contact :</label> <input class="form-control" type="text"
							placeholder="Enter additional cost if any" name="contact"
							required />
					</div>
					<div class="form-group">
						<label>Address :</label>
						<textarea class="form-control" rows="5" cols="8"
							placeholder="Enter customer's address" name="address" required></textarea>
					</div>
					<div class="form-group">
						<label>Additional Detail One :</label> <input class="form-control"
							type="text" placeholder="Enter additional field"
							name="additonalDetailOne" required />
					</div>
					<div class="form-group">
						<label>Additional Detail Two :</label> <input class="form-control"
							type="text" placeholder="Enter additional field"
							name="additonalDetailTwo" required />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-sm btn-primary pull-right"
							value="Create Customer" />
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