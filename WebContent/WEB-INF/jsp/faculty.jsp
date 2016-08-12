<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h4 class="col-xs-3">Number of positions: </h4> 
				<p class="col-xs-2">${faculty.getStudentsNumber()}</p>
			</div>
			<h2>Your marks:</h2>
			<div class="row">
				<c:forEach var="subject" items="${faculty_subjects}">
				<div class="col-xs-3">
					<c:if test="${current_marks.get(subject.getId()).getMark() == null}">
					<blockquote class="blockquote  bq-danger">
						<p class="bq-title">${subject.getName()}</p>
						<p>No mark</p>
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
		<p class="bq-title">Certificate</p>
		<p>${certificate.getMark()}</p>
	</blockquote>
</div> 
</div> 
<c:if test="${!(missing_marks || low_rating)}">
<div class="row">
	<button class="btn btn-success btn-lg" name="applay" value="applay">Applay</button>
</div>
</c:if>
<!--Modal-->
<div class="modal" id="modal-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="card">
				<div class="card-header danger-color-dark white-text">
					No correct marks
				</div>
				<c:if test="${(missing_marks && !low_rating)}">
					<div class="card-block">
						<h4 class="card-title">Unfortunately you don't have all necessary marks!</h4>
						<p class="card-text">You can add the necessary assessment to your profile unless you specify them during the registration.</p>
						<button class="btn btn-danger-outline waves-effect" name="modal_button" value="add_mark">Add marks</button>
						<button class="btn btn-danger-outline waves-effect" name="modal_button" value="to_profile">Back to profile</button>
					</div>
				</c:if>
				<c:if test="${low_rating}">
					<div class="card-block">
						<h4 class="card-title">Unfortunately your rating is not enough!</h4>
						<p class="card-text">You can go to your profile and try to join the other faculty.</p>
						<button class="btn btn-danger-outline waves-effect" name="modal_button" value="to_profile">Back to profile</button>
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
	<button class="btn btn-success btn-lg" data-toggle="modal" data-target="#modal-1" >Applay</button>
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