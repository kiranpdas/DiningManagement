<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Performance</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
<script>
$(document).ready(function(){
    $('#performTbl').DataTable();
});
</script>	


</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<ul class="nav navbar-nav" >
		<li><a href="Dashboard">DASHBOARD</a></li>
		<li><a href="Shift">SHIFT</a></li>
		<li><a href="Activity">ACTIVITY</a></li>
		<li><a href="Leaves">LEAVES</a></li>
		<li><a href="Availability">AVAILABILITY</a></li>
		<li><a href="Training">TRAINING</a></li>
		<li><a href="Timesheet">TIMESHEET</a></li>
		<li><a href="Payments">PAYMENTS</a></li>
		<li><a href="Incentives">INCENTIVES</a></li>
		<li><a href="Accessories">ACCESSORIES</a></li>
		<li class="active"><a href="Performance">PERFORMANCE</a></li>

	</ul>
	</nav>	
		<div class="table-responsive" style="margin-top:40px">
			<h2 class="well text-center">Performance</h2>
			<table id="performTbl" class="table table-striped table-hover ">
				<thead>
					<tr>						
						<th>Rating Period</th>
						<th>Reviewer</th>
						<th>Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${perform}" var="temp">
						<tr>
							<td>${temp.ratingPeriod}</td>
							<td>${temp.appraiser}</td>
							<td>${temp.empRating}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</body>
</html>