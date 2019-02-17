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
	<h2 class="well text-center">Performance</h2>
	<a class="btn btn-danger" style="margin-left: 80%" href="ManagerDash">Return
		to Dashboard</a>
		<div class="table-responsive" >
			<table id="performTbl" class="table table-striped table-hover ">
				<thead>
					<tr>				
					<th>Employee Id</th>
					<th>Employee Name</th>		
						<th>Rating Period</th>
						<th>Reviewer</th>
						<th>Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${perform}" var="temp">
						<tr>
							<td>${temp.empId}</td>
							<td>${temp.empName}</td>
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