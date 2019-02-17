<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>USF Dining</title>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<style>
body{
background:  url('https://static.pexels.com/photos/326278/pexels-photo-326278.jpeg') no-repeat center center fixed;
-webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
color:white;

}
</style>
</head>
<body >

<div class="container">
  <h1>USF Dining</h1>
  
  <form action="Login" method="post">
  	<div style="padding:300px 600px 0 100px">
  	<div>${Fail}</div>
    <div class="form-group input-group">
      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
      <input type="text" class="form-control" name="username" placeholder="Enter user name">
    </div>
    <div class="form-group input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>      
      <input type="password" class="form-control" name="password" placeholder="Enter password">
    </div>
    <div class="checkbox">
      <label><input type="checkbox"> Remember me</label>
    </div>
    <input type="submit" class="btn btn-success" value="Login"/>
    </div>
  </form>
</div>

</body> 
</html>

</body>
</html>