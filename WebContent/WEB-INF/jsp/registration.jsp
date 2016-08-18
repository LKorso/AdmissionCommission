<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/WEB-INF/myTagLib.tld" prefix="mytag" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${content.getString("registration")}</title>
	
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
			<h3>${content.getString("registration")}</h3>
		</div>
	</div>
</header>
<body class="body">
	<div class="container">
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="registration" />
			<h4>${content.getString("registration.description")}</h4>
			<div class="row">
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate" placeholder="Doe" type="text" name="lastName" id="form1" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$"></input>
					<label class="active" for="form1">${content.getString("last_name")}</label>
				</div>
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate" type="text" placeholder="John" name="firstName" id="form2" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$"></input>
					<label class="active" for="form2">${content.getString("frist_name")}</label>
				</div>
			</div>
			<h4>${content.getString("date_of_birth")}: </h4>
			<div class="row">
				<div class="col-xs-4 md-form">
					<input class="col-xs-1 form-control validate" placeholder="1999" type="number" min="1900" max="2016" step="1" name="year" id="form3" required></input>
					<label class="active" for="form3">${content.getString("year")}</label>
				</div>
				<div class="col-xs-4 descripted_select">
					<p class="description">${content.getString("month")}</p>
					<div>
						<mytag:customselect values="${months}" selectClass="form-control" selectName="month"/>
					</div>
				</div>
				<div class="col-xs-4  md-form">
					<input class="col-xs-2 form-control validate" placeholder="1" type="number" min="1" max="31" value="1" name="day" id="form4" required></input>
					<label class="active" for="form4">${content.getString("day")}</label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4 descripted_select">
					<p class="description">${content.getString("sex")}</p>
					<div>
						<mytag:customselect values="${sex}" selectClass="form-control" selectName="sex"/>
					</div>
				</div>
				<div class="md-form col-xs-4">
					<input class="col-xs-2 form-control validate" placeholder="+xx(xxx)xxx-xx-xx" type="text" name="phone" id="form5" required pattern="^((8|\+7|\+38)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$"></input>
					<label class="active" for="form5">${content.getString("phone")}</label>
				</div>
				<div class="md-form col-xs-4">
					<input class="col-xs-2 form-control validate" placeholder="user@email.com" type="email" name="email" id="form6" required></input>
					<label class="active" for="form6">${content.getString("email")}</label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate" type="password" name="password_one" id="form7" required></input>
					<label class="active" for="form7">${content.getString("password")}</label>
				</div>
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate" type="password" name="password_two" id="form8" required></input>
					<label class="active" for="form8">${content.getString("repeat_password")}</label>
				</div>
			</div>
			<h4>${content.getString("marks")}: </h4>
			<div class="row">
				<div class="col-xs-4">
					<mytag:customselect values="${subjects_list}" selectClass="form-control" selectName="subject_one"/>
				</div>
				<div class="col-xs-4">
					<mytag:customselect values="${subjects_list}" selectClass="form-control" selectName="subject_two"/>
				</div>
				<div class="col-xs-4">
					<mytag:customselect values="${subjects_list}" selectClass="form-control" selectName="subject_three"/>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4 md-form">
					<input class="col-xs-4 form-control validate" placeholder="200.00" type="text" name="mark_one" required pattern="(\d{3})+(\.\d{1,3})*"></input>
				</div>
				<div class="col-xs-4 md-form">
					<input class="col-xs-4 form-control validate" placeholder="200.00" type="text" name="mark_two" required pattern="(\d{3})+(\.\d{1,3})*"></input>
				</div>
				<div class="col-xs-4 md-form">
					<input class="col-xs-4 form-control validate" placeholder="200.00" type="text" name="mark_three" required pattern="(\d{3})+(\.\d{1,3})*"></input>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-8 md-form">
					<input class="form-control validate" placeholder="200.00" type="text" name="certificate" id="form9" required pattern="(\d{3})+(\.\d{1,3})*"></input>
					<label class="active" for="form9">${content.getString("certificate")}</label>
				</div>
				<button name="submit" id="reg_submit_btn" class="btn col-xs-4">${content.getString("submit")}</button>
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