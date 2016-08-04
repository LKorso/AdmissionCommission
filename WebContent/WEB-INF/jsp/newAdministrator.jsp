<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/WEB-INF/myTagLib.tld" prefix="mytag" %>
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
			<h3>Registration</h3>
		</div>
	</div>
</header>
<body>
	<div class="container">
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="newAdministrator" />
			<div class="row">
				<h4>Fill in all these fields</h4>
			</div>
			<div class="row">
				<p class="col-xs-2">Last name: </p>
				<input class="col-xs-2" type="text" name="lastName"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">First name: </p>
				<input class="col-xs-2" type="text" name="firstName"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Sex: </p>
				<div class="col-xs-2">
					<mytag:customselect values="${sex}" selectClass="form-control" selectName="sex"/>
				</div>
			</div>
			<div class="row">
				<p class="col-xs-2">Date of birth: </p>
				<p class="col-xs-1">Year</p>
				<input class="col-xs-1" type="text" name="year"></input>
				<p class="col-xs-1">Month</p>
				<div class="col-xs-2">
					<mytag:customselect values="${months}" selectClass="form-control" selectName="month"/>
				</div>
				<p class="col-xs-1">Day</p>
				<input class="col-xs-2" type="text" name="day"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Phone: </p>
				<input class="col-xs-2" type="text" name="phone"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Email: </p>
				<input class="col-xs-2" type="email" name="email"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Password: </p>
				<input class="col-xs-2" type="password" name="password_one"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Repeat your password: </p>
				<input class="col-xs-2" type="password" name="password_two"></input>
			</div>
			<div class="row">
				<button name="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>