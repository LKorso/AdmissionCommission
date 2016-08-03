<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

	<title>Admission Commission</title>
</head>
<body>
	<div class="container">
        <h2>National Technical University of Ukraine</h2>
        <h2>"Kyiv Polytechnic Institute"</h2>
        <h3>Admission commission</h3>
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
            <button name="signIn" value="signIn" class="btn btn-primary">Sign in</button>
            
        </form>
        <form action="Controller" method="POST">
            <input type="hidden" name="command" value="login" />
            <button name="signUp" value="signUp" class="btn btn-info">Sign up</button>
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