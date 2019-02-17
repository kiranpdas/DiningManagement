<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leaves Approval</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

<script type="text/javascript">
	$(document).ready(function() {

		$('#activityTbl').DataTable();
		$("#Approve").click(function() {
			var leaveApproveId = [];
			$.each($("input[name='leaveApprove']:checked"), function() {
				leaveApproveId.push($(this).val());
			});
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/reqLeaveApprove",
				data : {
					leaveApproveId : leaveApproveId
				},
				success : function(data) {
					console.log("SUCCESS:abcabcabc");
					alert("Approved leaves successfully");
					window.location.reload();

				},
				error : function(e) {
					console.log("ERROR: ", e);

				}

			});
		});
		
		$("#Approve").click(function() {
			var leaveApproveId = [];
			$.each($("input[name='leaveApprove']:checked"), function() {
				leaveApproveId.push($(this).val());
			});
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/reqLeaveApprove",
				data : {
					leaveApproveId : leaveApproveId, action:"approve"
				},
				success : function(data) {
					console.log("SUCCESS:abcabcabc");
					alert("Approved leaves successfully");
					window.location.reload();

				},
				error : function(e) {
					console.log("ERROR: ", e);

				}

			});
		});
		
		$("#Reject").click(function() {
			var leaveApproveId = [];
			$.each($("input[name='leaveApprove']:checked"), function() {
				leaveApproveId.push($(this).val());
			});
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/reqLeaveApprove",
				data : {
					leaveApproveId : leaveApproveId, action:"reject"
				},
				success : function(data) {
					console.log("SUCCESS:abcabcabc");
					alert("Approved Rejected ");
					window.location.reload();

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

	<!--------------------------------- form -->
	<h2 class="well text-center">Leaves</h2>
	<a class="btn btn-danger" style="margin-left: 80%" href="ManagerDash">Return
		to Dashboard</a>



	<div id="actTableHead" class="table-responsive ">
		<button id="Approve" class="btn btn-success" type="button">Approve</button>
		<button id="Reject" class="btn btn-danger" type="button">Reject</button>
		<table id="activityTbl" class="table table-striped table-hover ">
			<thead>
				<tr>
					<th></th>
					<th>Emp Id</th>
					<th>Emp Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Reason</th>
					<th>Approval Status</th>

				</tr>
			</thead>
			<tbody id="actTable">
				<c:forEach items="${leaves}" var="temp">
					<tr>
						<td><input type="checkbox" name="leaveApprove"
							value="${temp.leaveId}" /></td>
						<td>${temp.empId}</td>
						<td>${temp.empName}</td>
						<td>${temp.startDate}</td>
						<td>${temp.endDate}</td>
						<td>${temp.reason}</td>
						<td>${temp.approvalStatus}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>