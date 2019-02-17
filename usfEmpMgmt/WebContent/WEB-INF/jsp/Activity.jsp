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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
	

	$submitAct.submit(function (e){
	e.preventDefault();
	var $actTable=$('#actTable');
	var $startDate =  $("#startDate").val();
	var $endDate =  $("#endDate").val();
	var html='';
	
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/activityReq",
			data:{startDate:$startDate,	endDate:$endDate},
			success : function(data) {
				console.log("SUCCESS:jksdhfjksdhfkj");
				data=JSON.parse(data);
				console.log("SUCCESS: ",data);
				for(i=0;i<data.length;i++){
			        html+='<tr><td>'
			        +data[i].shdDate
			        +'</td><td>'
			        +data[i].startTime
			        +'</td><td>'
			        +data[i].endTime
			        +'</td><td>'
			        +data[i].activity
			        +'</td><td>'
			        +data[i].location
			        +'</td><td>'
			        +data[i].supervisor
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
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<ul class="nav navbar-nav" >
			<li><a href="Dashboard">DASHBOARD</a></li>
			<li><a href="Shift">SHIFT</a></li>
			<li class="active"><a href="Activity">ACTIVITY</a></li>
			<li><a href="Leaves">LEAVES</a></li>
			<li><a href="Availability">AVAILABILITY</a></li>
			<li><a href="Training">TRAINING</a></li>
			<li><a href="Timesheet">TIMESHEET</a></li>
			<li><a href="Payments">PAYMENTS</a></li>
			<li><a href="Incentives">INCENTIVES</a></li>
			<li><a href="Accessories">ACCESSORIES</a></li>
			<li><a href="Performance">PERFORMANCE</a></li>
		</ul>
	</nav>
	
<!--------------------------------- form -->

	<div style="margin-top:80px">
		<form id="submitAct" class="form-inline" method="post" >
		Start Date:<input id="startDate" required="required" type="date" name="startDate" />
		End Date:<input id="endDate"  required="required" type="date" name="endDate" />
		 <input  type="submit"  class="btn "/>
		</form>
	</div>
	
	
	
		<div  id="actTableHead" class="table-responsive ">
			<h2 class="well text-center">Activity</h2>
			<table id="activityTbl" class="table table-striped table-hover ">
				<thead>
					<tr>
						<th>DATE</th>
						<th>START TIME</th>
						<th>END TIME</th>
						<th>Activity</th>
						<th>Location</th>
						<th>Supervisor</th>
						
					</tr>
				</thead>
				<tbody id="actTable">
				</tbody>
			</table>
		</div>
</body>
</html>