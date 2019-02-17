<!DOCTYPE html>
<html lang="en">
<head>
<title>Manager Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: black; color: white">
	<div class="row" style="margin-left:1%">
		<div class="col-md-10">
		<h2>
			Welcome <b style="text-transform: capitalize;">${userName}!</b><br>
			What would you like to do Today?
		</h2>
		</div>
		<div align="right" class="col-lg-1" style="margin-top:1%;margin-left:6%">
			<a href="home" style="text-decoration: none; font-family: cursive;"
				class="btn btn-success">Logoff</a>
		</div>
		<!-- Trigger the modal with a button -->
		<br>
		<br>
		<br>

	</div>
<br><br>

	<div class="container row">
		<div class="container col-md-6">

			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModal">Need to view schedules and more?</button>

			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">View</h4>
						</div>
						<div class="modal-body">
							<nav class="navbar">
								<ul class="nav navbar-nav">
									<li><a href="ActivityMgr">Activity</a></li>
									<br>
									<li><a href="LeaveMgr">Leaves</a></li>
									<br>
									<li><a href="PerformMgr">Peformance</a></li>
									<br>
									<li><a href="AvailabilityMgr">Availability</a></li>
									<br>
								</ul>
							</nav>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModal2">Please look into these tasks...</button>
			<div class="modal fade" id="myModal2" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Approvals</h4>
						</div>
						<div class="modal-body">
							<nav class="navbar ">
								<ul class="nav navbar-nav" >
									<li><a href="ApproveLeavesForm" >Approve Leaves</a></li><br>
									<li><a href="PerformaneForm">Rate Employees</a></li><br>
									<li><a href="AccessoriesIssueForm">Issue Accessories</a></li><br>
								</ul>
							</nav>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

		</div>
		<div class="container col-md-4" style="font-size:x-large; ; ;font-family:Garamond body copy ;color:yellow">
			<br><br>You have ${leavesPending } pending leave approvals!<br><br><br>
			${accessoriesPending } accessories need to be issued!
		</div>

	</div>

</body>
</html>


</body>
</html>