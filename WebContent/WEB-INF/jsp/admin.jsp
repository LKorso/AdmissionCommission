<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    
    <title>Administrator profile</title>

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
			<h3>${content.getString("admin.administrator")}</h3>
		</div>
	</div>
</header>
<body class="body">
	<div class="container">
		<div class="row">
			<div class="col-xs-3"><p>${user.getLastName()}</p></div>
			<div class="col-xs-3"><p>${user.getFirstName()}</p></div>
			<div class="col-xs-3"><p>${content.getString("email")}:</p></div>
			<div class="col-xs-3"><p>${user.getEmail()}</p></div>
		</div>
		<div class="row">
			<div class="col-xs-6"><p>${user.getDateOfBirth()}</p></div>
			<div class="col-xs-3"><p>${content.getString("phone")}:</p></div>
			<div class="col-xs-3"><p>${user.getPhone()}</p></div>
		</div>
		<div class="row">
			<div class="col-xs-6"><p>${user.getSex()}</p></div>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="admin" />
				<div class="col-xs-6"><button name="changeInformation" value="change">${content.getString("change_information")}</button></div>
			</form>
		</div>
		<div class="row">
			<h3>${content.getString("admin.applications_description")}</h3>
		</div>
		
		<!--Start of collapse panel-->
		<c:forEach  var="applications" items="${unreviewedApplications}">
			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <button class="btn" data-toggle="collapse" data-target="#collapse${applications.getApplicationId()}">${content.getString("admin.new_applicant")} - ${applications.getFaculty()}</button>
			      </h4>
			    </div>
			    <div id="collapse${applications.getApplicationId()}" class="panel-collapse collapse">
			      <div class="panel-body">
			      	<div class="col-xs-6">
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">${content.getString("last_name")}:</span>
			      				<span class="col-xs-3">${applications.getLastName()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">${content.getString("frist_name")}:</span>
			      				<span class="col-xs-3">${applications.getFirstName()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
				      		<p>
				      			<span class="col-xs-3">${content.getString("date_of_birth")}:</span>
				      			<span class="col-xs-3">${applications.getDateOfBirth()}</span>
				      		</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">${content.getString("sex")}:</span>
			      				<span class="col-xs-3">${applications.getSex()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">${content.getString("email")}:</span>
			      				<span class="col-xs-3">${applications.getEmail()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">${content.getString("phone")}:</span>
			      				<span class="col-xs-3">${applications.getPhone()}</span>
			      			</p>
			      		</div>
			      	</div>
			      	<div class="col-xs-6">
			      		<div class="row">
			      			<p>
			      				<span class="col-xs-3">${content.getString("faculty")}:</span>
			      				<span class="col-xs-3">${applications.getFaculty()}</span>
			      			</p>
			      		</div>
			      		<div class="row">
			      			<p class="col-xs-6">${content.getString("marks")}:</p>
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
			      		<div class="col-xs-12"><h3>${content.getString("admin.application_descriptions")}:</h3></div>
			      	</div>
			      	<div class="row">
			      		<textarea maxlength="500" rows="3" class="col-xs-8"></textarea>
			      	</div>
			      <div class="panel-footer">
				      <form action="Controller" method="POST">
						<input type="hidden" name="command" value="admin" />
				      	<div class="row">
				      		<button class="col-md-2" name="submit" value="${applications.getApplicationId()}">
				      			${content.getString("submit")}
				      		</button>
							<button class="col-md-2" name="cancel" value="${applications.getApplicationId()}">
								${content.getString("cancel")}
							</button>
				      	</div>
				      </form>
			      </div>
			    </div>
			  </div>
			</div>
			</div>
		</c:forEach>
	<!--End of collapse panel-->
	
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="admin"/>
		<div class="row">
			<h3 class="col-xs-10">${content.getString("admin.new_admin_description")}</h3>
				<button class="col-xs-2" name="newAdmininstrator" value="newAdmininstrator">
					${content.getString("admin.new_admin")}
				</button>
		</div>
		<div class="row">
			<h3 class="col-xs-10">${content.getString("admin.enroll_students_description")}</h3>
			<button class="col-xs-2" name="enrollStudents" value="enrollStudents">
					${content.getString("admin.enroll_students")}
			</button>
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