<%-- 
    Document   : index
    Created on : Apr 5, 2018, 12:37:46 PM
    Author     : jithin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="#">

        <title>Scheduler Login</title>


        <script type="text/javascript"
        src="${contextPath}/resources/scripts/jquery-1.11.1.min.js"></script>
        <!-- Bootstrap core CSS -->
        <link href="${contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${contextPath}/resources/css/signin.css" rel="stylesheet">
    </head>

    <body>
        <form name='login' class="form-signin" action="<c:url value='j_spring_security_check' />" method='POST'>
            <div class="text-center mb-4">
                <h1 class="h3 mb-3 font-weight-normal">Scheduler Admin Login</h1>
                <c:if test="${not empty error}"><div>${error}</div></c:if>
                <c:if test="${not empty message}"><div>${message}</div></c:if>
            </div>

            <div class="form-label-group">
                <input type="email" name="username" id="inputEmail" class="form-control" placeholder="Email address" required autofocus/>
                <label for="inputEmail">Email address</label>
            </div>

            <div class="form-label-group">
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required/>
                <label for="inputPassword">Password</label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted text-center">TychExplore Scheduler&copy; 2017-2018</p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </body>
</html>
