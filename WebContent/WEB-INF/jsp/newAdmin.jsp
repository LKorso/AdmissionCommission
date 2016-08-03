<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<script src="js/jquery-1.11.3.js"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<title>New Administrator</title>
</head>
<header>
	<div class="container">
		<div class="row">
			<h3>New Administrator</h3>
		</div>
	</div>
</header>
<body>
	<div class="container">
		<div class="row">
			<h4>Fill in all these fields</h4>
		</div>
		<div class="row">
			<p class="col-xs-2">Last name: </p>
			<input class="col-xs-2" type="text"></input>
		</div>
		<div class="row">
			<p class="col-xs-2">First name: </p>
			<input class="col-xs-2" type="text"></input>
		</div>
		<div class="row">
			<p class="col-xs-2">Sex: </p>
			<div class="col-xs-2">
				<select class="form-control">
			  	<option>M</option>
			  	<option>W</option>
				</select>
			</div>
		</div>
		<div class="row">
			<p class="col-xs-2">Date of birth: </p>
			<p class="col-xs-1">Year</p>
			<input class="col-xs-1" type="text"></input>
			<p class="col-xs-1">Month</p>
			<div class="col-xs-2">
				<select class="form-control">
				  <option value="January">January</option>
				  <option value="February">February</option>
				  <option value="Martch">Martch</option>
				  <option value="April">April</option>
				  <option value="May">May</option>
				  <option value="June">June</option>
				  <option value="July">July</option>
				  <option value="August">August</option>
				  <option value="September">September</option>
				  <option value="October">October</option>
				  <option value="November">November</option>
				  <option value="December">December</option>
				</select>
			</div>
			<p class="col-xs-1">Day</p>
			<input class="col-xs-2" type="text"></input>
		</div>
		<div class="row">
			<p class="col-xs-2">Email: </p>
			<input class="col-xs-2" type="text"></input>
		</div>
		<div class="row">
			<p class="col-xs-2">Phone: </p>
			<input class="col-xs-2" type="text"></input>
		</div>
		<div class="row">
			<button>Submit</button>
		</div>
	</div>
</body>
</html>