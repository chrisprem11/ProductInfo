<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.0.0.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myTable').dataTable();
		$('#datatable').dataTable();
	});
</script>
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>Product Info</title>
</head>
<body>
	<div class="well">
		<h2 class="text-center text-primary">Digital Shopping</h2>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
				<h3 class="text-success text-center">Menu</h3>
				<div class="list-group">
					<a href="showItems" class="list-group-item  text-center"> Show
						All Items </a> <a href="addItem" class="list-group-item text-center">Add
						Item</a> <a href="#" class="list-group-item text-center">Reports</a> <a
						href="#" class="list-group-item text-center">Payments</a> <a
						href="invoiceList" class="list-group-item text-center">Invoice
						List</a> <a href="customers" class="list-group-item text-center">Customer
						List</a><a href="quickInvoice" class="list-group-item text-center">Quick
						Pay</a>
				</div>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-12"></div>
			<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
				<br /> <br /> <br />
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<a href="invoice" class="btn btn-lg btn-info">Create Invoice</a>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<a href="addCustomer" class="btn btn-lg btn-info">Create
							Customer</a>

					</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<a href="#" class="btn btn-lg btn-info">Report</a>
					</div>

				</div>
				<br />
				<hr />
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<div>
							<br />
							<h4 class="text-center text-success">Total Payment Received</h4>
							&nbsp;<br /> <span class="badge badge-warning pull-left">${totalPayment}</span>
							<span class="badge badge-danger pull-right">${totalPaymentPercent}
								%</span><br /> <br /> <br />
						</div>
						<div id="chart1" style="height: 100px; width: 100%;"></div>

					</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<div>
							<br />
							<h4 class="text-center text-success">Total Pending Amount</h4>
							&nbsp;<br /> <span class="badge badge-warning pull-left">${totalPendingAmount}</span>
							<span class="badge badge-danger pull-right">${totalPendingPercent}
								%</span><br /> <br /> <br />
						</div>
						<div id="chart2" style="height: 100px; width: 100%;"></div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<div>
							<h4 class="text-center text-success">Total Pending Invoice
								to Create</h4>
							&nbsp;<br /> <span class="badge badge-warning pull-left">5</span>
							<span class="badge badge-danger pull-right">10 %</span><br /> <br />
							<br />
						</div>
						<div id="chart3" style="height: 100px; width: 100%;"></div>
					</div>

				</div>
				<br />
				<hr />
				<div class="row">
					<h3 class="text-success">Invoices</h3>
					<table class="table table-bordered table-striped table-hover"
						id="myTable">
						<thead>
							<tr>
								<th class="bg-success">Invoice ID</th>
								<th class="bg-success">Invoice Number</th>
								<th class="bg-success">Status</th>
								<th class="bg-success">Amount</th>
								<th class="bg-success">Raised Date</th>
								<th class="bg-success">Customer</th>
								<th class="bg-success">Expected Amount</th>
								<th class="bg-success">Amount Deviation</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${invoiceList}">
								<tr>
									<c:url value="getInvoice" var="myURL">
										<c:param name="id" value="${item.id}" />
									</c:url>
									<td><a href="${myURL}"><c:out value="${item.id}"></c:out></a>
									</td>
									<td><c:out value="${item.invoiceNumber}"></c:out></td>
									<td><c:out value="${item.orderStatus}"></c:out></td>
									<td><c:out value="${item.finalAmount}"></c:out></td>
									<td><c:out value="${item.startDate}"></c:out></td>
									<td><c:out value="${item.customer.firstName}"></c:out></td>
									<td><c:out value="${item.expectedAmount}"></c:out></td>
									<td><c:out value="${item.amountDeviation}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br />
				<hr />
				<div class="row">
					<h3 class="text-success">Customers</h3>
					<table id="datatable"
						class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th class="bg-success">Customer Name</th>
								<th class="bg-success">Status</th>
								<th class="bg-success">Pending Amount</th>
								<th class="bg-success">Received Amount</th>
								<th class="bg-success">Last Invoice</th>
								<th class="bg-success">Number of items currently map</th>
								<th class="bg-success">Projected Items Map</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${abc}">
								<tr>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br />
			</div>

		</div>
		<div class="row">

			<div ng-controller="dataController">
				<div ng-repeat="(k,v) in userData">
					<!-- <p>{{ k }}</p>
					<p>{{ v }}</p> -->
				</div>
			</div>
			<h3>Chart Section</h3>
			<hr />
			<div id="chartContainer" style="height: 300px; width: 100%;"></div>
			<br /> <br />
		</div>
	</div>


	<script src="static/js/lib/bootstrap.js" type="text/javascript"></script>
	<script src="static/js/lib/jquery.canvasjs.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular.js"
		type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular-route.js"
		type="text/javascript"></script>
	<script src="static/js/app/application.js" type="text/javascript"></script>
	<script src="static/js/controller/dataController.js"
		type="text/javascript"></script>
</body>
</html>