<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>Items</title>
</head>
<body>
	<div class="well">
		<h2 class="text-center text-primary">Items</h2>
	</div>
	<div class="container">
		<br />
		<h3 class="text-danger">List of Items:</h3>
		<br />
		<hr />
		<table id="myTable" class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th class="bg-primary">ID</th>
					<th class="bg-primary">Name</th>
					<th class="bg-primary">Cost</th>
					<th class="bg-primary">Additional Cost</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${allItems}">
					<tr>
						<td><c:out value="${item.id}"></c:out></td>
						<td><c:out value="${item.itemName}"></c:out></td>
						<td><c:out value="${item.itemCost}"></c:out></td>
						<td><c:out value="${item.additionalCost}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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