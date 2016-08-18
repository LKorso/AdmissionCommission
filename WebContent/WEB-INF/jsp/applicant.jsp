<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
        
	<title>Applicant page</title>

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
			<h3 class="col-xs-3">${content.getString("applicant.aplicant")}</h3>
		</div>
	</div>
</header>
<body class="body">
<div class="container">
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="applicant" />
	
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
			<div class="row btn_row">
				<button class="btn col-xs-6" name="changeInformation" value="change">${content.getString("change_information")}</button>
			</div>
		<div class="row">
			<h3>${content.getString("marks")}: </h3>
		</div>
		<table class="table">
			<tr>
				<th>${content.getString("applicant.subject")}</th>
				<th>${content.getString("applicant.mark")}</th>
			</tr>
				<c:forEach var="mark" items="${marks}">
					<tr>
						<td>${subjects.get(mark.getSubjectId())}</td>
						<td>${mark.getMark()}</td>
					</tr>
				</c:forEach>
		</table>
		<h3>${content.getString("applicant.newapplication_description")}</h3>
		<div class="row">
			<div class="md-form col-xs-8">
				<select class="form-control" id="faculty_select" name="faculty_id">
	    			<option value="" disabled selected>${content.getString("applicant.choose_faculty")}</option>
	    			<c:forEach var="faculty" items="${faculties}">
	    				<option value="${faculty.getId()}">${faculty.getName()}</option>
	    			</c:forEach>
				</select>
			</div>
			<div>
				<button name="faculty" value="faculty" class="col-xs-4 btn">
					${content.getString("choose")}
				</button>
			</div>
		</div>
		<c:if test="${!applicationStatus}">
			<div class="row">
				<h3>${content.getString("applicant.applications_list")}: </h3>
			</div>
			<c:if test="${passed_applications != null}">
				<div class="row">
					<h4>${content.getString("applicant.passed_applications")}:</h4>
				</div>
				<c:forEach var="applications" items="${passed_applications}">
				<div class="row">
					<h4>${applications.get(0).getFaculty()}</h4>
				</div>
				<table class="table numbered">
					<tr>
						<th>#</th>
						<th>${content.getString("last_name")}</th>
						<th>${content.getString("frist_name")}</th>
						<th>${content.getString("applicant.average")}</th>
						<th>${content.getString("priority")}</th>
					</tr>
						<c:forEach var="application" items="${applications}">
							<tr>
								<td class="counter"></td>
								<td>${application.getLastName()}</td>
								<td>${application.getFirstName()}</td>
								<td>${application.getRating()}</td> 
								<td>${application.getPriority()}</td>
							</tr>
						</c:forEach>
				</table>
				</c:forEach>
			</c:if>
			<c:if test="${unreviewed_applications != null}">
				<div class="row">
					<h4>${content.getString("applicant.unreviewed_applications")}: </h4>
				</div>
				<c:forEach var="unreviewed_application" items="${unreviewed_applications}">
					<div class="row">
						<div class="card">
							<div class="card-block">
			        			<h4 class="card-title">${unreviewed_application.getFaculty()}</h4>
			        			<p class="card-text">
			        				${content.getString("applicant.un_app_description_one")}${unreviewed_application.getFillingDate()}${content.getString("applicant.un_app_description_two")}
			        			</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${rejected_applications != null}">
				<div class="row">
					<h4>${content.getString("applicant.rejected_applications")}: </h4>
				</div>
				<c:forEach var="rejected_application" items="${rejected_applications}">
					<div class="row">
						<div class="card">
							<div class="card-block">
					       		<h4 class="card-title">${rejected_application.getFaculty()}</h4>
					        	<p class="card-text">${content.getString("applicant.rejected_description")}: ${rejected_application.getDescription()}.</p>
					        	<button name="remove" value="${rejected_application.getApplicationId()}" class="btn btn-primary">
					        		${content.getString("remove")}
					        	</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</c:if>
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