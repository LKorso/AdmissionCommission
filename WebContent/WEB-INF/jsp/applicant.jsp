<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<h3 class="col-xs-3">Applicant</h3>
		</div>
	</div>
</header>
<body class="body">
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="applicant" />
	<div class="container">
		<div class="row">
			<div class="col-xs-3">
				<p>${user.getLastName()}</p>
			</div>
			<div class="col-xs-3">
				<p>${user.getFirstName()}</p>
			</div>
			<div class="col-xs-3">
				<p>email:</p>
			</div>
			<div class="col-xs-3">
				<p>${user.getEmail()}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<p>${user.getDateOfBirth()}</p>
			</div>
			<div class="col-xs-3">
				<p>phone:</p>
			</div>
			<div class="col-xs-3">
				<p>${user.getPhone()}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<p>${user.getSex()}</p>
			</div>
				<div class="col-xs-6">
					<button name="changeInformation" value="change">Change information</button>
				</div>
		</div>
		<div class="row">
			<h3>Marks: </h3>
		</div>
		<table class="table">
			<tr>
				<th>Subject</th>
				<th>mark</th>
			</tr>
				<c:forEach var="mark" items="${marks}">
					<tr>
						<td>${subjects.get(mark.getSubjectId())}</td>
						<td>${mark.getMark()}</td>
					</tr>
				</c:forEach>
		</table>
		<div class="row">
			<h3 class="col-xs-4">Submit a new application</h3>
			<select class="mdb-select col-xs-4" name="faculty_id">
    			<option value="" disabled selected>Choose faculty</option>
    			<c:forEach var="faculty" items="${faculties}">
    				<option value="${faculty.getId()}">${faculty.getName()}</option>
    			</c:forEach>
			</select>
			<button name="faculty" value="faculty" class="col-xs-2 btn btn-default">Choose</button>
		</div>
		<c:if test="${!applicationStatus}">
			<div class="row">
				<h3>Lists of applications: </h3>
			</div>
			<c:if test="${passed_applications != null}">
				<div class="row">
					<h4>Passed applications:</h4>
				</div>
				<c:forEach var="applications" items="${passed_applications}">
				<div class="row">
					<h4>${applications.get(0).getFaculty()}</h4>
				</div>
				<table class="table">
					<tr>
						<th>#</th>
						<th>Last name</th>
						<th>Frist name</th>
						<th>Average score</th>
					</tr>
						<c:forEach var="application" items="${applications}">
							<tr>
								<td>1</td>
								<td>${application.getLastName()}</td>
								<td>${application.getFirstName()}</td>
								<td>${rating.get(application.getApplicationId())}</td> 
							</tr>
						</c:forEach>
				</table>
				</c:forEach>
			</c:if>
			<c:if test="${unreviewed_applications != null}">
				<div class="row">
					<h4>Unreviewed applications: </h4>
				</div>
				<c:forEach var="unreviewed_application" items="${unreviewed_applications}">
					<div class="row">
						<div class="card">
							<div class="card-block">
			        			<h4 class="card-title">${unreviewed_application.getFaculty()}</h4>
			        			<p class="card-text">Your application filed ${unreviewed_application.getFillingDate()}, is not yet considered.</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${rejected_applications != null}">
				<div class="row">
					<h4>Rejected applications: </h4>
				</div>
				<c:forEach var="rejected_application" items="${rejected_applications}">
					<div class="row">
						<div class="card">
							<div class="card-block">
					       		<h4 class="card-title">${rejected_application.getFaculty()}</h4>
					        	<p class="card-text">Unfortunately your application has been rejected for the following reasons: ${rejected_application.getDescription()}.</p>
					        	<button name="remove" value="${rejected_application.getApplicationId()}" class="btn btn-primary">Remove</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</c:if>
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