<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	
	<title>Student profile</title>
	
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    
    <link rel="stylesheet" href="css/style.css">

    <link rel="stylesheet" href="css/fonts.css">
	
</head>
<header>
	<%@include file="header.jsp" %>
		
	<div class="container">
		<div class="row">
			<h3 class="col-xs-3">${content.getString("student")} ${content.getString("faculty")}: </h3>
			<h3>${faculty}</h3>
		</div>
	</div>
</header>
<body class="body">
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<p>${user.getLastName()}</p>
			</div>
			<div class="col-xs-3">
				<p>${user.getFirstName()}</p>
			</div>
			<div class="col-xs-3">
				<p>${content.getString("email")}:</p>
			</div>
			<div class="col-xs-3">
				<p>${user.getEmail()}</p>
			</div>
		</div>
		<dir class="row">
			<div class="col-xs-6">
				<p>${user.getDateOfBirth()}</p>
			</div>
			<div class="col-xs-3">
				<p>${content.getString("phone")}:</p>
			</div>
			<div class="col-xs-3">
				<p>${user.getPhone()}</p>
			</div>
		</dir>
		<div class="row">
			<div class="col-xs-6">
				<p>${user.getSex()}</p>
			</div>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="student" />
				<div class="col-xs-6">
					<button>${content.getString("change_information")}</button>
				</div>
			</form>
		</div>
		
	<!-- SCRIPTS -->

    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>

    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/tether.min.js"></script>

    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
	
</body>
</html>