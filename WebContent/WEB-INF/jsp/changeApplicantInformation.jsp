<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div class="container">
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="changeInformation" />
			<h4>${content.getString("change.description")}</h4>
			<div class="row">
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate"  type="text" name="lastName" id="form1" value="${user.getLastName()}" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$"></input>
					<label class="active" for="form1">${content.getString("last_name")}</label>
				</div>
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate" type="text" name="firstName" id="form2" value="${user.getFirstName()}" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$"></input>
					<label class="active" for="form2">${content.getString("frist_name")}</label>
				</div>
			</div>
			<h4>${content.getString("date_of_birth")}: </h4>
			<div class="row">
				<div class="col-xs-4 md-form">
					<input class="col-xs-1 form-control validate" value="${yearOfBirth}" type="number" min="1900" max="2016" step="1" name="year" id="form3" required></input>
					<label class="active" for="form3">${content.getString("year")}</label>
				</div>
				<div class="col-xs-4 descripted_select">
					<p class="description">${content.getString("month")}</p>
					<div>
						<mytag:customselect values="${months}" criterionForSelected="${monthOfBirth}" selectClass="form-control" selectName="month"/>
					</div>
				</div>
				<div class="col-xs-4  md-form">
					<input class="col-xs-2 form-control validate"  value="${dayOfBirth}" type="number" min="1" max="31" value="1" name="day" id="form4" required></input>
					<label class="active" for="form4">${content.getString("day")}</label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4 descripted_select">
					<p class="description">${content.getString("sex")}</p>
					<div>
						<mytag:customselect values="${sex}" criterionForSelected="${user.getSex()}" selectClass="form-control" selectName="sex"/>
					</div>
				</div>
				<div class="md-form col-xs-4">
					<input class="col-xs-2 form-control validate" value="${user.getPhone()}" type="text" name="phone" id="form5" required pattern="^((8|\+7|\+38)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$"></input>
					<label class="active" for="form5">${content.getString("phone")}</label>
				</div>
				<div class="md-form col-xs-4">
					<input class="col-xs-2 form-control validate" value="${user.getEmail()}" type="email" name="email" id="form6" required></input>
					<label class="active" for="form6">${content.getString("email")}</label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate" type="password" name="password_one" id="form7"></input>
					<label class="active" for="form7">${content.getString("password")}</label>
				</div>
				<div class="col-xs-6 md-form">
					<input class="col-xs-2 form-control validate" type="password" name="password_two" id="form8"></input>
					<label class="active" for="form8">${content.getString("repeat_password")}</label>
				</div>
			</div>
			<div class="row btn_row" >
				<button name="submit" id="reg_submit_btn" class="btn col-xs-6">${content.getString("submit")}</button>
			</div>
			</form>
			<h3>${content.getString("marks")}: </h3>
			<table class="table">
				<tr>
					<th>${content.getString("applicant.subject")}</th>
					<th>${content.getString("applicant.mark")}</th>
				</tr>
					<c:forEach var="mark" items="${marks}">
							<tr>
							<form action="Controller" method="POST">
								<input type="hidden" name="command" value="changeInformation" />
								<c:if test="${applicationStatus}">
									<td><mytag:customselect values="${subjects_list}" selectClass="form-control"
											criterionForSelected="${subjects.get(mark.getSubjectId())}" selectName="subject"/></td>
								</c:if>
								<c:if test="${!applicationStatus}">
									<td>${subjects.get(mark.getSubjectId())}</td>
								</c:if>
								<c:if test="${!applicationStatus}">
									<td>${mark.getMark()}</td>
								</c:if>
								<c:if test="${applicationStatus}">
									<td><input type="text" name="mark" value="${mark.getMark()}" required pattern="(\d{3})+(\.\d{1,3})*"></input></td>
									<td><button name="changeMarkId" value="${mark.getId()}" class="btn flat-btn">${content.getString("change")}</button></td>
									<td><button name="deleteMark" value="${mark.getId()}" class="btn flat-btn">${content.getString("delete")}</button></td>
								</c:if>
							</form>
							</tr>
					</c:forEach>
			</table>
				<h3>${content.getString("add_marks_description")}</h3>
				<form action="Controller" method="POST">
					<input type="hidden" name="command" value="changeInformation" />
					<div class="row">
						<div class="md-form col-xs-4">
							<mytag:customselect values="${vacant_subjects}" selectClass="form-control" selectId="subject_select" selectName="subject"/>
						</div>
						<div class="md-form col-xs-4">
							<input type="text" name="mark" value="" placeholder="200.00" class="col-xs-6 form-control validate" required pattern="(\d{3})+(\.\d{1,3})*">
						</div>
						<div>
							<button name="addMark" id="reg_submit_btn" class="btn col-xs-4">${content.getString("add")}</button>
						</div>
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