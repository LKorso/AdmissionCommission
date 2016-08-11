<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<h3>Changing Information</h3>
		</div>
	</div>
</header>
<body class="body">
	<div class="container">
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="changeInformation" />
			<div class="row">
				<h4>Here you can change some information about yourself: </h4>
			</div>
			<div class="row">
				<p class="col-xs-2">Last name: </p>
				<input class="col-xs-2" type="text" name="last_name" value="${user.getLastName()}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">First name: </p>
				<input class="col-xs-2" type="text" name="first_name" value="${user.getFirstName()}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Sex: </p>
				<div class="col-xs-2">
					<mytag:customselect values="${sex}" selectClass="form-control"
						criterionForSelected="${user.getSex()}" selectName="sex"/>
				</div>
			</div>
			<div class="row">
				<p class="col-xs-2">Date of birth: </p>
				<p class="col-xs-1">Year</p>
				<input class="col-xs-1" type="text" name="year" value="${yearOfBirth}"></input>
				<p class="col-xs-1">Month</p>
				<div class="col-xs-2">
					<mytag:customselect values="${months}" selectClass="form-control"
						criterionForSelected="${monthOfBirth}" selectName="month"/>
				</div>
					<p class="col-xs-1" >Day</p>
					<input class="col-xs-2" type="text" name="day" value="${dayOfBirth}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Email: </p>
				<input class="col-xs-2" type="text" name="email" value="${user.getEmail()}"></input>
			</div>
			<div class="row">
				<p class="col-xs-2">Phone: </p>
				<input class="col-xs-2" type="text" name="phone" value="${user.getPhone()}"></input>
			</div>
			<input type="hidden" name="command" value="changeStudent" />
			<div class="row">
				<button name="submit" value="change">Submit</button>
			</div>
			</form>
			<div class="row">
				<h3>Marks: </h3>
			</div>
			<table class="table">
				<tr>
					<th>Subject</th>
					<th>Mark</th>
					<th>Change</th>
					<c:if test="${applicationStatus}">
						<th>Delete</th>
					</c:if>
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
								<td><input type="text" name="mark" value="${mark.getMark()}"></input></td>
								<td><button name="changeMarkId" value="${mark.getId()}">Change</button></td>
								<c:if test="${applicationStatus}">
									<td><button name="deleteMark" value="${mark.getId()}">Delete</button></td>
								</c:if>
							</form>
							</tr>
					</c:forEach>
			</table>
				<div class="row">
					<h3>Add your mark if there aren't all of them!</h3>
				</div>
				<div class="row">
					<form action="Controller" method="POST">
						<input type="hidden" name="command" value="changeInformation" />
						<div class="col-xs-5">
							<mytag:customselect values="${vacant_subjects}" selectClass="form-control" selectName="subject"/>
						</div>
						<input type="text" name="mark" value="" class="col-xs-4">
						<button class="col-xs-3" name="addMark">Add</button>
					</form>
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