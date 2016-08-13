<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/WEB-INF/myTagLib.tld" prefix="mytag" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    
    <title>Change information</title>

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
			<h3>${content.getString("change.change_information")}</h3>
		</div>
	</div>
</header>
<body class="body">
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="changeInformation" />
		<div class="container">
			<div class="row">
				<h4>${content.getString("change.description")}: </h4>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("last_name")}: </p>
				<input class="col-xs-2" type="text" name="last_name" value="${user.getLastName()}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("frist_name")}: </p>
				<input class="col-xs-2" type="text" name="first_name" value="${user.getFirstName()}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("sex")}: </p>
				<div class="col-xs-2">
					<mytag:customselect values="${sex}" selectClass="form-control"
						criterionForSelected="${user.getSex()}" selectName="sex"/>
				</div>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("date_of_birth")}: </p>
				<p class="col-xs-1">${content.getString("year")}</p>
				<input class="col-xs-1" type="text" name="year" value="${yearOfBirth}"></input>
				<p class="col-xs-1">${content.getString("month")}</p>
				<div class="col-xs-2">
					<mytag:customselect values="${months}" selectClass="form-control"
						criterionForSelected="${monthOfBirth}" selectName="month"/>
				</div>
					<p class="col-xs-1" >${content.getString("day")}</p>
					<input class="col-xs-2" type="text" name="day" value="${dayOfBirth}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("email")}: </p>
				<input class="col-xs-2" type="text" name="email" value="${user.getEmail()}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">${content.getString("phone")}: </p>
				<input class="col-xs-2" type="text" name="phone" value="${user.getPhone()}"></input>
			</div>
			<input type="hidden" name="command" value="changeStudent" />
			<div class="row">
				<button name="submit" value="change">${content.getString("submit")}</button>
			</div>
		</div>
	</form>
	
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