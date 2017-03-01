<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />

<title>Invoice Details</title>
</head>
<body>
	<div class="well">
		<h2 class="text-center text-primary">Invoice particulars</h2>
	</div>
	<div class="container">
		<hr />
		<div class="row">
			<table class="table table-striped table-hover table-bordered">
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
					<tr>

						<td><c:out value="${invoice.id}"></c:out></td>
						<td><c:out value="${invoice.orderDetails}"></c:out></td>
						<td><c:out value="${invoice.netAmount}"></c:out></td>
						<td><c:out value="${invoice.expectedAmount}"></c:out></td>
						<td><c:out value="${invoice.customer.firstName}"></c:out></td>
						<td><c:out value="${invoice.startDate}"></c:out></td>
						<td><c:out value="${invoice.additionalCost}"></c:out></td>
						<td><c:out value="${invoice.discountItemCost}"></c:out></td>
						<td><c:out value="${invoice.discountAdditionalCost}"></c:out></td>
						<td><c:out value="${invoice.discountNetAmount}"></c:out></td>
						<td><c:out value="${invoice.finalAmount}"></c:out></td>
						<td><c:out value="${invoice.endDate}"></c:out></td>
						<td><c:out value="${invoice.orderStatus}"></c:out></td>
					</tr>

				</tbody>
			</table>
		</div>
		<br /> <br />
		<hr />
		<c:choose>
			<c:when test="${invoice.orderStatus == 'Pending'}">
				<div class="row">
					<form action="completeOrder" method="post"
						modelAttribute="completeOrder">
						<div class="form-group">
							<label>Amount Received :</label> <input type="text"
								name="customerAmount" />
						</div>
						<input type="hidden" value="${invoice.id}" name="invoiceId" />
						<div class="form-group">
							<input type="submit" class="btn btn-success btn-md"
								value="Complete Order" />
						</div>
					</form>
				</div>
				<div class="row">
					<c:url value="invoicePdf" var="myURL">
						<c:param name="id" value="${invoice.id}" />
					</c:url>
					<a href="${myURL}" class="btn btn-md btn-success pull-right">Download
						Invoice</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<!-- <a href="pdf/cover.pdf" class="btn btn-md btn-success pull-right">Download
						Invoice</a> -->
					<c:url value="invoicePdf" var="myURL">
						<c:param name="id" value="${invoice.id}" />
					</c:url>
					<a href="${myURL}" class="btn btn-md btn-success pull-right">Download
						Invoice</a>
				</div>
			</c:otherwise>
		</c:choose>
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