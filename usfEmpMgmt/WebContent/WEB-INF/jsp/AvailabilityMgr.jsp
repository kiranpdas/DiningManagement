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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

<script type="text/javascript">

$(function(){
	var $actTableHead=$('#actTableHead')
	var $actTable=$('#actTable');
	var $submitAct=$('#submitAct');
	
	$actTableHead.hide();
	

	$("#availForm").submit(function (e){
	e.preventDefault();
	var $actTable=$('#actTable');
	var html='';
	
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/reqAvailabilityMgr",
			data:{dayofWeek:$("#dayofWeek").val()},
			success : function(data) {
				console.log("SUCCESS:dddd11");
				data=JSON.parse(data);
				console.log("SUCCESS: ",data);
				for(i=0;i<data.length;i++){
			        html+='<tr><td>'
			        +data[i].empId
			        +'</td><td>'
			        +data[i].empName
			        +'</td><td>'
			        +data[i].startTime
			        +'</td><td>'
			        +data[i].endTime
			        +'</td></tr>';
			    }
				console.log(html);
			    $actTable.html(html);
			    $actTableHead.show();
			    $('#activityTbl').DataTable();
				
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
	<h2 class="well text-center">Availability</h2>
	<a class="btn btn-danger" style="margin-left: 80%" href="ManagerDash">Return
		to Dashboard</a>
	<div  >
		<form id="availForm" action="updAvailSubmit" method="post">
		
			<div class="form-group">
				<span class="glyphicon glyphicon-calendar"></span> <label
					for="dayofWeek">Day of Week: </label> <select id="dayofWeek" 
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
				<input type="submit" class="btn btn-success">
			
			</div>
			
		</form>
	</div>



	<div id="actTableHead" class="table-responsive ">

		<table id="activityTbl" class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>Emp Id</th>
					<th>Emp Name</th>
					<th>Start Time</th>
					<th>End Time</th>

				</tr>
			</thead>
			<tbody id="actTable">
			</tbody>
		</table>
	</div>
</body>
</html>