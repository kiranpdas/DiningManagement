<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

</head>
<body>


	<h2 class="well text-center">Employee Profile</h2>
	<a href="Dashboard" class="btn btn-warning" style="margin-left: 85%">
		Return to Dashboard</a>
	<br>
	<br>
	<table class="table">
		<tbody>
			<tr>
				<th>FULL NAME</th>
				<td>${profile.employeeName}</td>
			</tr>
			<tr>
				<th>EMPLOYEE ID</th>
				<td>${profile.employeeId}</td>
			</tr>
			<tr>
				<th>USER NAME</th>
				<td>${profile.empUserName}</td>
			</tr>
			<tr>
				<th>CELL NUMBER</th>
				<td>${profile.employeeCell}</td>
			</tr>
			<tr>
				<th>EMAIL</th>
				<td>${profile.employeeEmail}</td>
			</tr>
			<tr>
				<th>ADDRESS</th>
				<td>${profile.employeeAddr}</td>
			</tr>
			<tr>
				<th>GENDER</th>
				<td>${profile.employeeGender}</td>
			</tr>
			<tr>
				<th>DATE OF BIRTH</th>
				<td>${profile.employeeDOB}</td>
			</tr>
			<tr>
				<th>SOCIAL SECURITY NUMBER</th>
				<td>${profile.employeeSSN}</td>
			</tr>
			<tr>
				<th>VISA TYPE</th>
				<td>${profile.employeeVisa}</td>
			</tr>
			<tr>
				<th>MANAGER NAME</th>
				<td>${profile.empManagerName}</td>
			</tr>
			<tr>
				<th>EMPLOYEE LEVEL</th>
				<td>${profile.empLevel}</td>
			</tr>
			<tr>
				<th>LIST OF ALLERGIES</th>
				<td>${profile.allergies}</td>
			</tr>

		</tbody>
	</table>

</body>
</html>