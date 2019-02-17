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
	

	$submitAct.submit(function (e){
	e.preventDefault();
	var $actTable=$('#actTable');
	var $startDate =  $("#startDate").val();
	var $endDate =  $("#endDate").val();
	var html='';
	
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/reqLeaveMgr",
			data:{startDate:$startDate,	endDate:$endDate},
			success : function(data) {
				console.log("SUCCESS:abcabcabc");
				data=JSON.parse(data);
				console.log("SUCCESS: ",data);
				for(i=0;i<data.length;i++){
			        html+='<tr><td>'
			        +data[i].empId
			        +'</td><td>'
			        +data[i].empName
			        +'</td><td>'
			        +data[i].startDate
			        +'</td><td>'
			        +data[i].endDate
			        +'</td><td>'
			        +data[i].reason
			        +'</td><td>'
			        +data[i].approvalStatus
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
	<h2 class="well text-center">Leaves</h2>
	<a class="btn btn-danger" style="margin-left: 80%" href="ManagerDash">Return
		to Dashboard</a>
	<div >
		<form id="submitAct" class="form-inline" method="post">
			Start Date:<input id="startDate" required="required" type="date"
				name="startDate" /> End Date:<input id="endDate"
				required="required" type="date" name="endDate" /> <input
				type="submit" class="btn " />
		</form>
	</div>



	<div id="actTableHead" class="table-responsive ">

		<table id="activityTbl" class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>Emp Id</th>
					<th>Emp Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Reason</th>
					<th>Approval Status</th>

				</tr>
			</thead>
			<tbody id="actTable">
			</tbody>
		</table>
	</div>
</body>
</html>