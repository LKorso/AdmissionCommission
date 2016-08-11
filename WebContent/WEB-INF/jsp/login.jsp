<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">

    <link rel="stylesheet" href="css/style.css">

    <link rel="stylesheet" href="css/fonts.css">

    <title>Admission Commission</title>
</head>
<body>
    <header class="main-head" style="background-image: url(img/Golovnijj_korpus_KPI.jpg);">
        <jsp:include page="header.jsp"/>

        <div class="container header_text">
            <h1>Admission commission</h1>
            <h2 id="top_line">National Technical University of Ukraine</h2>
            <h2>"Kyiv Polytechnic Institute"</h2>
        </div>
        
        <div class="container">
            <div class="row">
                <button class="btn z-depth-2" id="log_btn" data-toggle="modal" data-target=".modal">LOG IN</button>
            </div>
        </div>

    </header>

    <div class="modal fade" id="login_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Sign in with your Account</p>
                    <form action="Controller" method="POST">
                        <input type="hidden" name="command" value="login" />
                        <div class="md-form">
                             <input type="email" name="email" value="" id="form9" class="form-control validate" name="email">
                             <label for="form9" data-error="wrong" data-success="right">Email:</label>
                        </div>
                        <div class="md-form">
                            <input type="password" name="password" value="" id="form10" class="form-control validate">
                            <label for="form10" data-error="wrong" data-success="right">Password:</label>
                        </div>   
                        <button name="signIn" value="signIn" class="btn">Sign in</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body" id="sign_up_body">
                    <form action="Controller" method="POST">
                        <input type="hidden" name="command" value="login" />
                        <button name="signUp" value="signUp" class="btn">Sign up</button>
                    </form>
                </div>
            </div>
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