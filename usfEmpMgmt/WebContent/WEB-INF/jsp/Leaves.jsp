<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leaves</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

<script type="text/javascript">
	$(function() {

		$('#requestLeave').click(function() {
			$('#leaveHistBody').hide();
			$("#leaveAppBody").hide();
			$("#leaveHistForm").hide();
			$('#leaveRequestBody').show();
		});

		$('#leaveHistory').click(function() {
			$("#leaveAppBody").hide();
			$('#leaveRequestBody').hide();
			$('#leaveHistForm').show();
		});

		$("#submitHist").submit(
				function(e) {
					e.preventDefault();
					var html = "";
					$.ajax({
						type : "GET",
						url : "${pageContext.request.contextPath}/leaveHist",

						data : {
							startDate : $("#leaveStart").val(),
							endDate : $("#leaveEnd").val()
						},

						success : function(data) {
							data = JSON.parse(data);
							console.log("SUCCESS: ", data);
							for (i = 0; i < data.length; i++) {
								html += '<tr><td>' + data[i].leaveStart
										+ '</td><td>' + data[i].leaveEnd
										+ '</td><td>' + data[i].reason
										+ '</td><td>' + data[i].status
										+ '</td></tr>';
							}

							$("#leaveTable").html(html);
							$('#leaveHistoryTbl').DataTable();
							$("#leaveHistBody").show();
							

						},
						error : function(e) {
							console.log("ERROR: ", e);

						}

					});
				});

		$('#requestLeaveform').submit(function(e) {
			e.preventDefault();
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/requestLeave",
				data : {
					startDate : $('#startDate').val(),
					endDate : $('#endDate').val(),
					reason : $('#reason').val()
				},
				success : function(data) {
					console.log("SUCCESS: ", data);
					$('#leaveRequestBody').hide();
					alert('Leaves Applied');
					document.location.reload()
				},
				error : function(e) {
					console.log("ERROR: ", e);

				}

			});

		});

	});
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<ul class="nav navbar-nav">
		<li><a href="Dashboard">DASHBOARD</a></li>
		<li><a href="Shift">SHIFT</a></li>
		<li><a href="Activity">ACTIVITY</a></li>
		<li class="active"><a href="Leaves">LEAVES</a></li>
		<li><a href="Availability">AVAILABILITY</a></li>
		<li><a href="Training">TRAINING</a></li>
		<li><a href="Timesheet">TIMESHEET</a></li>
		<li><a href="Payments">PAYMENTS</a></li>
		<li><a href="Incentives">INCENTIVES</a></li>
		<li><a href="Accessories">ACCESSORIES</a></li>
		<li><a href="Performance">PERFORMANCE</a></li>
	</ul>
	</nav>

	<div class="container" style="margin-top: 70px">

		<button id="requestLeave" type="submit" class="btn ">Request
			Leave</button>
		<button id="leaveHistory" type="submit" class="btn ">Leave
			History</button>
		<button class="btn btn-warning active btn-sm"
			style="margin-left: 550px; width: 115px; height: 115px; border-radius: 115px; font-size: 9px">
			<h2>${leavesRemain}</h2>
			Leaves<br> Remaining
		</button>
	</div>

	<div id=leaveAppBody style="display: none;">Leaves applied</div>

	<div id="leaveRequestBody" style="margin-left: 20px; display: none;">
		<br>
		<br>
		<br>
		<form id="requestLeaveform" class="form-inline" method="post">
			Start Date:<input id="startDate" type="date" required="required"
				name="startDate" class="input-sm" /> End Date:<input id="endDate"
				type="date" required="required" name="endDate" class="input-sm" /><br>
			<br> Reason: <input id="reason" type="text" required="required"
				class="input-lg" /><br>
			<br> <input id="submitLeave" type="submit" class="btn " />
		</form>
	</div>


	<div id="leaveHistForm" style="display: none">
		<form id="submitHist" class="form-inline" method="post">
			Start Date:<input id="leaveStart" type="date" required="required"
				name="startDate" /> End Date:<input id="leaveEnd" type="date"
				required="required" name="endDate" />
			<input  type="submit" class="btn "/>
		</form>
	</div>

	<div id=leaveHistBody style="display: none; padding: 50px">

		<h2 class="well text-center">Leave History</h2>
		<table id="leaveHistoryTbl" class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>START DATE</th>
					<th>END DATE</th>
					<th>REASON</th>
					<th>STATUS</th>

				</tr>
			</thead>
			<tbody id="leaveTable">
			</tbody>
		</table>
	</div>


</body>
</html>