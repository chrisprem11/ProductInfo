<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />

<title>Quick Pay</title>
</head>
<body>
	<div class="well">
		<h2 class="text-center text-primary">Quick Payment</h2>
	</div>
	<div class="container">
		<div class="row">
			<div>
				<form action="findItem" method="get">
					<div class="form-group">
						<h3 class="text-danger">Item Name:</h3>
						<input class="form-control" placeholder="Enter the item name"
							name="itemName" required />
					</div>
					<div>
						<input type="submit" value="Add"
							class="btn btn-sm btn-primary pull-right">
					</div>
				</form>
			</div>
		</div>
	
	<div class="row">
		<h3 class="text-danger">Selected Items:</h3>
		<div>
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th class="bg-warning">Item Name</th>
						<th class="bg-warning">Quantity</th>
						<th class="bg-warning">Item Cost</th>
						<th class="bg-warning">Additional Cost</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${itemList}">
						<tr>
							<td><c:out value="${item.itemName}"></c:out></td>
							<td><c:out value="${item.quantity}"></c:out></td>
							<td><c:out value="${item.itemCost}"></c:out></td>
							<td><c:out value="${item.additionalCost}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<br />

		<div class="row">
			<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
				<h3 class="text-danger">Invoice:</h3>
				<br />
				<form action="invoice" modelAttribute="invoiceDetails" method="post">
					<div class="form-group">
						<label>Customer :</label> <input class="form-control" type="text"
							placeholder="Enter customer contact number" name="contact"
							required />
					</div>
					<div class="form-group">
						<label>Discount on Item Cost:</label> <input class="form-control"
							placeholder="Enter discount % or 0" name="discountItemCost"
							required />
					</div>
					<div class="form-group">
						<label>Discount on Net Amount:</label> <input class="form-control"
							placeholder="Enter discount % or 0" name="discountNetAmount"
							required />
					</div>
					<div class="form-group">
						<label>Discount on Additional Cost:</label> <input
							class="form-control" placeholder="Enter discount % or 0"
							name="discountAdditionalCost" required />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-sm btn-primary pull-right"
							value="Create Invoice" />
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