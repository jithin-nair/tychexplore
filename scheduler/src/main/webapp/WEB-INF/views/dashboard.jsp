<%-- 
    Document   : dashboard
    Created on : Apr 6, 2018, 2:24:58 PM
    Author     : jithin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="#">

        <title>Scheduler Dashboard</title>

        <script type="text/javascript"
        src="${contextPath}/resources/scripts/jquery-1.11.1.min.js"></script>
        <!-- Bootstrap core CSS -->
        <link href="${contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet">
    </head>

    <body>
        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">TychExplore Scheduler</a>
            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="#"><span class="glyphicon glyphicon-log-out"></span>Sign out</a>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <span class="glyphicon glyphicon-dashboard"></span>
                                <a class="nav-link" href="#">
                                    Dashboard <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <span class="glyphicon glyphicon-tasks"></span>
                                <a class="nav-link" href="#">
                                    Jobs
                                </a>
                            </li>
                            <li class="nav-item">
                                <span class="glyphicon glyphicon-bell"></span>
                                <a class="nav-link" href="#">
                                    Logs
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>


            </div>
        </div>


        <script src="${contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

    </body>
</html>

