<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myTable').dataTable();
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>Invoice</title>
</head>
<body>
	<div class="well container-fluid">
		<h2 class="text-center text-primary">Invoice History</h2>
	</div>
	<div class="container-fluid">
		<br />
		<h3 class="text-danger">Registered Invoices :</h3>
		<br />
		<hr />
		<div class="row">
			<table id="myTable"
				class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="bg-primary">Invoice ID</th>
						<th class="bg-primary">Item Details</th>
						<th class="bg-primary">Net Amount</th>
						<th class="bg-primary">Expected Amount</th>
						<th class="bg-primary">Customer's Name</th>
						<th class="bg-primary">Start Date</th>
						<th class="bg-primary">Additional Cost</th>
						<th class="bg-primary">Discount on itemCost</th>
						<th class="bg-primary">Discount on AdditionalCost</th>
						<th class="bg-primary">Discount on net amount</th>
						<th class="bg-primary">Final Amount</th>
						<th class="bg-primary">End Date</th>
						<th class="bg-primary">Order Status</th>
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
							<td><c:out value="${item.orderDetails}"></c:out></td>
							<td><c:out value="${item.netAmount}"></c:out></td>
							<td><c:out value="${item.expectedAmount}"></c:out></td>
							<td><c:out value="${item.customer.firstName}"></c:out></td>
							<td><c:out value="${item.startDate}"></c:out></td>
							<td><c:out value="${item.additionalCost}"></c:out></td>
							<td><c:out value="${item.discountItemCost}"></c:out></td>
							<td><c:out value="${item.discountAdditionalCost}"></c:out></td>
							<td><c:out value="${item.discountNetAmount}"></c:out></td>
							<td><c:out value="${item.finalAmount}"></c:out></td>
							<td><c:out value="${item.endDate}"></c:out></td>
							<td><c:out value="${item.orderStatus}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="static/js/bootstrap.js" type="text/javascript"></script>
	<script src="static/js/angular.js" type="text/javascript"></script>
	<script src="static/js/angular-route.js" type="text/javascript"></script>
	<script src="static/js/app/application.js" type="text/javascript"></script>
	<script src="static/js/controller/mainController.js"
		type="text/javascript"></script>
</body>
</html>