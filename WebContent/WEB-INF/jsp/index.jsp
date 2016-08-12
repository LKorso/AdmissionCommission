<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test="${user == null}">
		<jsp:forward page="/login"></jsp:forward>
	</c:if>
	<c:if test="${userType.equals(\"Administrator\")}">
		<jsp:forward page="/admin"></jsp:forward>
	</c:if>
	<c:if test="${userType.equals(\"Applicant\")}">
		<jsp:forward page="/applicant"></jsp:forward>
	</c:if>
	<c:if test="${userType.equals(\"Student\")}">
		<jsp:forward page="/student"></jsp:forward>
	</c:if>
</body>
</html>