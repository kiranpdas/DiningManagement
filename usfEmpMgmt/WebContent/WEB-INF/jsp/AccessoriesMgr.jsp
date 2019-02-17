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
	<h2 class="well text-center">Issue Accessories</h2>
	<a class="btn btn-danger" style="margin-left: 80%" href="ManagerDash">Return
		to Dashboard</a>
	<div class="container" style="padding-left:100px;padding-right:100px;font-family:monospace; font-weight: bolder;">
		<div style="color:blue; ">${Access }</div><br><br>
		<form method="get" action="ManageAccessory" >
			<div class="form-group">
				Employee id:<br> <input class="form-control" type="number" name="employeeId">
			</div>

			<div class="form-group">
				Accessory Id:<br> <input class="form-control" type="number" name="accessoryId">
			</div>

			<div class="form-group">
				Accessory Name:<br> <input class="form-control" type="text" name="accessoryName">
			</div>

			<div class="form-group">
				Issue Status:<br> <select class="form-control" name="issueStatus">
				<option > </option>
				<option value="Not issued">Not issued</option>
				<option value="Issued">Issued</option>
				</select>
				
			</div>

			<div class="form-group">
				Issue Date: <br><input class="form-control"  name="issueDate" type="date" required="required"
					name="issuedate" />
			</div>
			<div class="form-group">
				<input class="btn btn-success"  type="submit" value="Update Accesssory Status">

			</div>
		</form>
	</div>




</body>
</html>