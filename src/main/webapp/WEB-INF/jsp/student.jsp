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
</header>
<body class="body">
	<div class="container">
		<div class="card">
			<div class="card-block">
				<h3 class="card-title">${faculty}</h3>
			</div>
		</div>
		<div class="card">
			<div class="card-block">
				<h3 class="card-title">${content.getString("student")}</h3>
				<div class="row col-xs-6">
					<div class="row">
						<div class="col-xs-6">
							<p class="main-info">${user.getLastName()}</p>
						</div>
						<div class="col-xs-6">
							<p class="main-info">${user.getFirstName()}</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<p class="main-info">${user.getSex()}</p>
						</div>
						<div class="col-xs-6">
							<p class="main-info">${user.getDateOfBirth()}</p>
						</div>
					</div>
				</div>
				<div class="row col-xs-6">
					<div class="row">
						<div class="col-xs-6">
							<p class="main-info">${content.getString("email")}:</p>
						</div>
						<div class="col-xs-6">
							<p class="main-info">${user.getEmail()}</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<p class="main-info">${content.getString("phone")}:</p>
						</div>
						<div class="col-xs-6">
							<p class="main-info">${user.getPhone()}</p>
						</div>
					</div>
				</div>
				<form action="Controller" method="POST">
					<input type="hidden" name="command" value="student" />
					<div class="row btn_row">
						<button class="btn col-xs-3">${content.getString("change_information")}</button>
					</div>
				</form>
				</div>
			</div>
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