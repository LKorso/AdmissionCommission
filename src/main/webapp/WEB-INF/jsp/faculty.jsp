<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/myTagLib.tld" prefix="mytag" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    
    <title>Faculty</title>

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
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="faculty" />

			<h2>${faculty.getName()}</h2>
			<div class="row">
				<h4 class="col-xs-3">${content.getString("faculty.positions")}: </h4> 
				<p class="col-xs-2">${faculty.getStudentsNumber()}</p>
			</div>
			<h2>${content.getString("faculty.marks")}:</h2>
			<div class="row">
				<c:forEach var="subject" items="${faculty_subjects}">
				<div class="col-xs-3">
					<c:if test="${current_marks.get(subject.getId()).getMark() == null}">
					<blockquote class="blockquote  bq-danger">
						<p class="bq-title">${subject.getName()}</p>
						<p>${content.getString("faculty.no_mark")}</p>
					</blockquote>
				</c:if>
				<c:if test="${current_marks.get(subject.getId()).getMark() != null  && current_marks.get(subject.getId()).getMark() >= min_marks.get(subject.getId()).getMinMark()}">
				<blockquote class="blockquote  bq-success">
					<p class="bq-title">${subject.getName()}</p>
					<p>${current_marks.get(subject.getId()).getMark()}</p>
				</blockquote>
			</c:if>
			<c:if test="${current_marks.get(subject.getId()).getMark() < min_marks.get(subject.getId()).getMinMark()}">
			<blockquote class="blockquote  bq-warning">
				<p class="bq-title">${subject.getName()}</p>
				<p>${current_marks.get(subject.getId()).getMark()}</p>
			</blockquote>
		</c:if>
	</div> 
</c:forEach>
<div class="col-xs-3">
	<blockquote class="blockquote bq-success">
		<p class="bq-title">${content.getString("certificate")}</p>
		<p>${certificate.getMark()}</p>
	</blockquote>
</div> 
</div> 
<div class="row">
	<div class="col-xs-8">
		<p class="descripted_select">${content.getString("priority_description")}</p>
	</div>
	<div class="md-form col-xs-4">
		<select class="form-control" id="priority_select" name="priority_id">
	    	<c:forEach var="priority" items="${priorities}">
	    		<option value="${priority.getId()}">${priority.getPriority()}</option>
	    	</c:forEach>
		</select>
	</div>
</div>
<c:if test="${!(missing_marks || low_rating)}">
<div class="row">
	<button class="btn btn-success btn-lg" name="applay" value="applay">${content.getString("applay")}</button>
</div>
</c:if>
<!--Modal-->
<div class="modal" id="modal-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="card">
				<div class="card-header danger-color-dark white-text">
					${content.getString("faculty.header")}
				</div>
				<c:if test="${(missing_marks && !low_rating)}">
					<div class="card-block">
						<h4 class="card-title">${content.getString("faculty.no_mark.hedear")}</h4>
						<p class="card-text">${content.getString("faculty.no_mark.message")}</p>
						<button class="btn btn-danger-outline waves-effect" name="modal_button" value="add_mark">
							${content.getString("faculty.no_mark.add_marks")}
						</button>
						<button class="btn btn-danger-outline waves-effect" name="modal_button" value="to_profile">
							${content.getString("faculty.to_profile")}
						</button>
					</div>
				</c:if>
				<c:if test="${low_rating}">
					<div class="card-block">
						<h4 class="card-title">${content.getString("faculty.low_rating.header")}</h4>
						<p class="card-text">${content.getString("faculty.low_rating.message")}</p>
						<button class="btn btn-danger-outline waves-effect" name="modal_button" value="to_profile">
							${content.getString("faculty.to_profile")}
						</button>
					</div>
				</c:if>
			</div>
		</div> 
	</div>
</div>
</form>

<!--/Modal-->
<c:if test="${missing_marks || low_rating}">
<div class="row">
	<button class="btn btn-success btn-lg" data-toggle="modal" data-target="#modal-1" >${content.getString("applay")}</button>
</div>
</c:if>
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