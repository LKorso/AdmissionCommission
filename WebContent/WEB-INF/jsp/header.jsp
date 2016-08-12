<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
   	<form action="Controller" method="POST">
    	<input type="hidden" name="command" value="header" />
        <nav class="navbar navbar-dark navbar-fixed-top" id="navbar">
            <!-- Collapse button-->
        <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#header">
            <i class="fa fa-bars"></i>
        </button>

        <div class="container" id="nav_container">

            <!--Collapse content-->
            <div class="collapse navbar-toggleable-xs" id="header">
                <!--Links-->
                <span class="icon-kpi-logo navbar-brand">
                    
                </span>
                <ul class="nav navbar-nav" id="navigation">
                    <li class="nav-item active">
                        <button name="home" value=""><a class="nav-link">${content.getString("header_home")}</a></button>
                    </li>
                    <li class="nav-item active">
                        <button name="log_out" value=""><a class="nav-link">${content.getString("header_log_out")}</a></button>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${content.getString("header_language")}</a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <a class="dropdown-item" href="#"><img src="img/eng-flag.svg" class="flag"/>English</a>
                            <a class="dropdown-item" href="#"><img src="img/ru-flag.svg" class="flag"/>Русский</a>
                            <a class="dropdown-item" href="#"><img src="img/ua-flag.svg" class="flag"/>Українська</a>
                        </div>
                    </li>
                </ul>
            </div>
            <!--/.Collapse content-->

        </div>
        </nav>
	</form>