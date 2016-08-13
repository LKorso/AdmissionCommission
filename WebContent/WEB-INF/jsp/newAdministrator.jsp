<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/WEB-INF/myTagLib.tld" prefix="mytag" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>New Administrator</title>
	
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
			<h3>${content.getString("newadmin.registration")}</h3>
		</div>
	</div>
</header>
<body class="body">
	<div class="container">
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="newAdministrator" />
			<div class="row">
				<h4>${content.getString("registration.description")}</h4>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("last_name")}: </p>
				<input class="col-xs-2" type="text" name="lastName"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("frist_name")}: </p>
				<input class="col-xs-2" type="text" name="firstName"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("sex")}: </p>
				<div class="col-xs-2">
					<mytag:customselect values="${sex}" selectClass="form-control" selectName="sex"/>
				</div>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("date_of_birth")}: </p>
				<p class="col-xs-1">${content.getString("year")}</p>
				<input class="col-xs-1" type="text" name="year"></input>
				<p class="col-xs-1">${content.getString("month")}</p>
				<div class="col-xs-2">
					<mytag:customselect values="${months}" selectClass="form-control" selectName="month"/>
				</div>
				<p class="col-xs-1">${content.getString("day")}</p>
				<input class="col-xs-2" type="text" name="day"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("phone")}: </p>
				<input class="col-xs-2" type="text" name="phone"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("email")}: </p>
				<input class="col-xs-2" type="email" name="email"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("password")}: </p>
				<input class="col-xs-2" type="password" name="password_one"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("repeat_password")}: </p>
				<input class="col-xs-2" type="password" name="password_two"></input>
			</div>
			<div class="row">
				<button name="submit" class="btn btn-primary">${content.getString("submit")}</button>
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