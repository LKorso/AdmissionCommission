<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    
    <title>Material Design Bootstrap</title>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
	<title>Applicant page</title>
</head>
<header>
	<div class="container">
		<div class="row">
			<h3>Administrator</h3>
		</div>
	</div>
</header>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-3"><p>${user.getLastName()}</p></div>
			<div class="col-xs-3"><p>${user.getFirstName()}</p></div>
			<div class="col-xs-3"><p>email:</p></div>
			<div class="col-xs-3"><p>${user.getEmail()}</p></div>
		</div>
		<div class="row">
			<div class="col-xs-6"><p>${user.getDateOfBirth()}</p></div>
			<div class="col-xs-3"><p>phone:</p></div>
			<div class="col-xs-3"><p>${user.getPhone()}</p></div>
		</div>
		<div class="row">
			<div class="col-xs-6"><p>${user.getSex()}</p></div>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="admin" />
				<div class="col-xs-6"><button name="changeInformation" value="change">Change information</button></div>
			</form>
		</div>
		<div class="row">
			<h3>List of new applications</h3>
		</div>
		
		<!--Start of collapse panel-->
		<c:forEach  var="applications" items="${unreviewedApplications}">
			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <button class="btn" data-toggle="collapse" data-target="#collapse${applications.getApplicationId()}">New Applicant - ${applications.getFaculty()}</button>
			      </h4>
			    </div>
			    <div id="collapse${applications.getApplicationId()}" class="panel-collapse collapse">
			      <div class="panel-body">
			      	<div class="col-xs-6">
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">Last name:</span>
			      				<span class="col-xs-3">${applications.getLastName()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">First name</span>
			      				<span class="col-xs-3">${applications.getFirstName()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
				      		<p>
				      			<span class="col-xs-3">Date of birth:</span>
				      			<span class="col-xs-3">${applications.getDateOfBirth()}</span>
				      		</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">Sex:</span>
			      				<span class="col-xs-3">${applications.getSex()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">Email:</span>
			      				<span class="col-xs-3">${applications.getEmail()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">Phone:</span>
			      				<span class="col-xs-3">${applications.getPhone()}</span>
			      			</p>
			      		</div>
			      	</div>
			      	<div class="col-xs-6">
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">Faculty:</span>
			      				<span class="col-xs-3">${applications.getFaculty()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p class="col-xs-6">Marks:</p>
			      		</div>
			      		<c:forEach var="mark" items="${applicationMarks.get(applications.getApplicationId())}">
			      			<div class="row">
				      			<p>
				      				<span class="col-xs-3">${subjects.get(mark.getSubjectId()).getName()}:</span>
				      				<span class="col-xs-3">${mark.getMark()}</span>
				      			</p>
			      			</div>
			      		</c:forEach>
			      	</div>
			      	<div class="row">
			      		<div class="col-xs-12"><h3>Some descriptions:</h3></div>
			      	</div>
			      	<div class="row">
			      		<textarea maxlength="500" rows="3" class="col-xs-8"></textarea>
			      	</div>
			      <div class="panel-footer">
				      <form action="Controller" method="POST">
						<input type="hidden" name="command" value="admin" />
				      	<div class="row">
				      		<button class="col-md-2" name="submit" value="${applications.getApplicationId()}">Submit</button>
							<button class="col-md-2" name="cancel" value="${applications.getApplicationId()}">Cancel</button>
				      	</div>
				      </form>
			      </div>
			    </div>
			  </div>
			</div>
			</div>
		</c:forEach>
	<!--End of collapse panel-->

	<div class="row">
		<h3 class="col-xs-10">Here you can register a new administrator</h3>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="amin"/>
				<button class="col-xs-2">New Administrator</button>
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