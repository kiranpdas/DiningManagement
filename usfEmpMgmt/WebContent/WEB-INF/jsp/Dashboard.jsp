<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
body{
background:  url('https://static.pexels.com/photos/349610/pexels-photo-349610.jpeg') no-repeat center center fixed;
-webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
color:  navy;
font-family: monospace;

}
</style>
</head>
<body >
	<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	%>
	<div style="padding-bottom: 6%">
		<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand " href="#">USF DINING</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="Dashboard">DASHBOARD</a></li>

				<li><a href="Leaves">LEAVES</a></li>
				<li><a href="Availability">AVAILABILITY</a></li>
				<li><a href="Training">TRAINING</a></li>
				<li><a href="Timesheet">TIMESHEET</a></li>

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">+++<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="Payments">PAYMENTS</a></li>
						<li><a href="Incentives">INCENTIVES</a></li>
						<li><a href="Shift">SHIFT</a></li>
						<li><a href="Activity">ACTIVITY</a></li>
						<li><a href="Accessories">ACCESSORIES</a></li>
						<li><a href="Performance">PERFORMANCE</a></li>
					</ul></li>

				<li class="dropdown" style="margin-left: 400px"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"> <b
						style="text-transform: capitalize;color:yellow; ">Hi
							${userName} </b><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="ViewProfile">Profile</a></li>
						<li><a href="home">Logout</a></li>
					</ul></li>

			</ul>
		</div>
		</nav>
	</div>
	<div class="container ">
		<div class="row">
			<div class="col-md-8 ">
				<div class="row">


					<div class="table-responsive col-md-8 " >
						<h2>Trainings to be completed</h2>
						<table id="trainingTbl" class="table">

							<thead>
								<tr>
									<th>Training Name</th>
									<th>Assigned Date</th>
									<th>Status</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${training}" var="temp">
									<tr>
										<td>${temp.trainingName}</td>
										<td>${temp.assignedDate}</td>
										<td>${temp.status}</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>




					



				</div>

				<div class="row">

					<div class="table-responsive col-md-8" style="" >
						<h2>Upcoming Schedule</h2>

						<table class="table ">
							<thead>
								<tr>
									<th>Schedule Date</th>
									<th>Start Time</th>
									<th>End Time</th>
									<th>Activity</th>
									<th>Location</th>
									<th>Supervisor</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${act}" var="temp">
									<tr>
										<td>${temp.shdDate}</td>
										<td>${temp.startTime}</td>
										<td>${temp.endTime}</td>
										<td>${temp.activity}</td>
										<td>${temp.location}</td>
										<td>${temp.supervisor}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
			</div>
			
			<div class="col-sm-4" style="">
				<h2 >Incentives</h2>
				<table id="incentivesTbl" class="table ">
					<thead>
						<tr>
							<th>Coupon Id</th>
							<th>Description</th>
							<th>Redeem Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${incentive}" var="temp">
							<tr>
								<td>${temp.couponId}</td>
								<td>${temp.couponDesc}</td>
								<td>${temp.redeemStatus}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="table-responsive col-md-8" style="font-size: 17px;color:yellow">
				<br>
						<div class="btn btn-lg" style="background:threeddarkshadow; 
						width: 150px; height: 150px;  font-size: 20px">
							<p style="font-size: 20px; font-family:cursive; ">
							Leaves<br> Remaining:</p>
							<p style="font-size: 40px; font-family:fantasy;">${leaves}</p>
							
						</div>
					</div>
			</div>
		</div>


	</div>
</body>
</html>