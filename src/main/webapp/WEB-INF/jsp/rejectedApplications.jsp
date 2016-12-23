<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<h3>${content.getString("admin.rejected_applications.description")}</h3>
		</div>
	</div>
</header>
<body class="body">
	<table class="table">
				<tr>
					<th>${content.getString("faculty")}</th>
					<th>${content.getString("last_name")}</th>
					<th>${content.getString("frist_name")}</th>
					<th>${content.getString("email")}</th>
					<th>${content.getString("phone")}</th>
					<th>P</th>
					<th>${content.getString("marks")}</th>
					<th>${content.getString("admin.application_descriptions")}</th>
					<th>${content.getString("submit")}</th>
					<th>${content.getString("cancel")}</th>
				</tr>
		<c:forEach var="applications" items="${rejectedApplications}">
			<tr>
				<form action="Controller" method="POST">
					<input type="hidden" name="command" value="rejectedApplications" />
						<td>${applications.getFaculty()}</td>
						<td>${applications.getLastName()}</td>
						<td>${applications.getFirstName()}</td>
						<td>${applications.getEmail()}</td>
						<td>${applications.getPhone()}</td>
						<td>${applications.getPriority()}</td>
						<td>
							<select>
								<c:forEach var="mark" items="${applicationMarks.get(applications.getApplicationId())}">
									<option>${subjects.get(mark.getSubjectId()).getName()} : ${mark.getMark()}</option>
								</c:forEach>
							</select>
						</td>
						<td><textarea name="description" value="${applications.getDescription()}">${applications.getDescription()}</textarea></td>
						<td><button name="submit" value="${applications.getApplicationId()}" class="btn flat-btn">${content.getString("submit")}</button></td>
						<td><button name="cancel" value="${applications.getApplicationId()}" class="btn flat-btn">${content.getString("cancel")}</button></td>
				</form>
			</tr>
		</c:forEach>
	</table>
	
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