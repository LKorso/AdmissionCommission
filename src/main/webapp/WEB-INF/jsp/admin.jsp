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
	</div>
</header>
<body class="body">
	<div class="container">
		<div class="card">
			<div class="card-block">
				<h3 class="card-title">${content.getString("admin.administrator")}</h3>
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
				<form action="Controller" method="POST">
					<input type="hidden" name="command" value="admin" />
					<div class="row btn_row">
						<button class="btn col-xs-6" name="changeInformation" value="changeInformation">${content.getString("change_information")}</button>
					</div>
				</form>
				</div>
		</div>
		<div class="card">
			<div class="card-block">
				<h3 class="card-title">${content.getString("admin.applications_description")}</h3>
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
					      	<form action="Controller" method="POST">
								<input type="hidden" name="command" value="admin" />
					      	<div class="row">
					      		<textarea maxlength="500" rows="3" class="col-xs-8" name="description"></textarea>
					      	</div>
					      	<div class="panel-footer">
						      	<div class="row">
						      		<button class="col-md-2" name="submit" value="${applications.getApplicationId()}">
						      			${content.getString("submit")}
						      		</button>
									<button class="col-md-2" name="cancel" value="${applications.getApplicationId()}">
										${content.getString("cancel")}
									</button>
						      	</div>
					      	</div>
					      </form>
					    </div>
					  </div>
					</div>
					</div>
				</c:forEach>
			<!--End of collapse panel-->
		</div>
	</div>
	
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="admin"/>
			<div class="card">
				<div class="card-block">
					<div class="row">
						<h3 class="col-xs-9">${content.getString("admin.new_admin_description")}</h3>
							<button class="col-xs-3 btn" name="newAdmininstrator" value="newAdmininstrator">
								${content.getString("admin.new_admin")}
							</button>
					</div>
				</div>
			</div>
			</form>
			<form action="Controller" method="POST">
			<input type="hidden" name="command" value="admin"/>
			<div class="card">
				<div class="card-block">
					<div class="row">
						<h3 class="col-xs-9">${content.getString("admin.rejected_applications.description")}</h3>
							<button class="col-xs-3 btn" name="rejectedApplications" value="rejectedApplications">
								${content.getString("show")}
							</button>
					</div>
				</div>
			</div>
			</form>
			<div class="card">
				<div class="card-block">
				<div class="row">
					<h3 class="col-xs-9">${content.getString("admin.enroll_students_description")}</h3>
					<c:if test="${!unreviewedApplications.isEmpty()}">
						<button class="col-xs-3 btn" name="enrollStudents" value="enrollStudents"  data-toggle="modal" data-target="#modal-1" >
								${content.getString("admin.enroll_students")}
						</button>
					</c:if>
					<c:if test="${unreviewedApplications.isEmpty()}">
						<form action="Controller" method="POST">
							<input type="hidden" name="command" value="admin"/>
							<button class="col-xs-3 btn" name="enrollStudents" value="enrollStudents">
									${content.getString("admin.enroll_students")}
							</button>
						</form>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
<!--Modal-->
<div class="modal" id="modal-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="card">
				<div class="card-header danger-color-dark white-text">
					${content.getString("admin.unreviewed.header")}
				</div>
				<div class="card-block">
					<p class="card-text">${content.getString("admin.unreviewed.message")}</p>
					<button class="btn btn-danger-outline waves-effect" data-dismiss="modal"">
						${content.getString("add")}
					</button>
				</div>
			</div>
		</div> 
	</div>
</div>
</form>

<!--/Modal-->
	
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