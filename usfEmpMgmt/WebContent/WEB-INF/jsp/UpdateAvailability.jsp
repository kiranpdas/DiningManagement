<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Availability</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	
<script>
$(function(){
	
	$("#availForm").submit(function(){
		alert("Successfully updated Availability")
// 		$("#msg").show();
	});
});


</script>
</head>
<body>
<div>
<h2 class="text-center well">Update Availability</h2>
<div>

			<a href="Availability"class="btn btn-info"
			style="margin-left:75%; width: 90px; height: 90px; border-radius: 90px; font-size:15px;text-align: center;">
			<br>Return to <br>Availability</a>
		
<!-- </div> -->
<!-- <div id="msg" style="display:none"> -->

<!-- Updated Availability successfully -->
<!-- </div> -->
</div>
	<div class="container" style="padding:50px 150px 100px 150px;">
		<form id="availForm" action="updAvailSubmit" method="get">
		
			<div class="form-group">
				<span class="glyphicon glyphicon-calendar"></span> <label
					for="dayofWeek">Day of Week: </label> <select name="dayofWeek"
					class="form-control" required="required">
					<option value="Sunday">Sunday</option>
					<option value="Monday">Monday</option>
					<option value="Tuesday">Tuesday</option>
					<option value="Wednesday">Wednesday</option>
					<option value="Thurday">Thursday</option>
					<option value="Friday">Friday</option>
					<option value="Saturday">Saturday</option>
				</select>
			</div>

			<div class="form-group">
				<span class="glyphicon glyphicon-time"></span><label
					for="startTime">Start Time:</label> <input name="startTime" required="required"
					type="time" class="form-control" placeholder="Enter Start Time" >
			</div>
			
			<div class="form-group">
				<span class="glyphicon  glyphicon-time"></span><label for="endTime">End
					Time:</label> <input name="endTime" required="required" type="time" class="form-control"
					placeholder="Enter End Time">
			</div>
			
			<div class="form-group">
				<input type="submit" class="btn btn-success">
				<input type="reset" class="btn btn-danger">
			</div>
			
		</form>
	</div>





</body>
</html>