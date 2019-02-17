<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Activity</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



</head>
<body>

	<!--------------------------------- form -->
	<h2 class="well text-center">Employee Rating</h2>
	<a class="btn btn-danger" style="margin-left: 80%" href="ManagerDash">Return
		to Dashboard</a>
	<div class="container"
		style="padding-left: 100px; padding-right: 100px; font-family: monospace; font-weight: bolder;">
		<div style="color: blue;">${Access }</div>
		<br>
		<br>
		<form method="get" action="Rating">
			<div class="form-group">
				Employee id:<br> <input class="form-control" type="number"
					name="employeeId">
			</div>

			<div class="form-group">
				Rating Period:<br> <input class="form-control" type="text"
					name="ratingPeriod">
			</div>


			<div class="form-group">
				Rating:<br> <select class="form-control" name="rating">
					<option></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>

				</select>

			</div>


			<div class="form-group">
				<input class="btn btn-success" type="submit"
					value="Update Accesssory Status">

			</div>
		</form>
	</div>




</body>
</html>