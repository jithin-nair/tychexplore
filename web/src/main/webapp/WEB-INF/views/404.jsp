<%-- 
    Document   : 404
    Created on : 11 Mar, 2018, 8:31:00 PM
    Author     : jithin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>TychExplore : TycheCash Block Explorer</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <!-- add the jQuery script -->
        <script type="text/javascript"
        src="${contextPath}/resources/scripts/jquery-1.11.1.min.js"></script>
        <!-- add the bootstrap script -->
        <script src="${contextPath}/resources/scripts/bootstrap.min.js"></script>


        <!-- VENDOR CSS -->
        <link rel="stylesheet" href="${contextPath}/resources/styles/bootstrap.min.css">
        <link rel="stylesheet"
              href="${contextPath}/resources/font-awesome/css/font-awesome.min.css">
        <style type="text/css">
            .custom-link-color{
                color:whitesmoke;
            }
        </style>
    </head>
    <body>

        <!-- WRAPPER -->
        <div id="wrapper">

            <nav class="navbar navbar-fixed-top bg-primary" >
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="http://explorer.tychecash.net/"> <img
                                src="${contextPath}/resources/images/logo.png" alt="TychExplore Logo">
                        </a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li><a href="http://explorer.tychecash.net/" class="custom-link-color"><i class="fa fa-home"></i> Home </a></li>
                        <li><a href="${contextPath}/api" class="custom-link-color" target="_blank"><i class="fa fa-code"></i> API </a></li>
                        <li><a href="http://tychecash.net/#network" class="custom-link-color" target="_blank">
                                <i class="fa fa-cloud"></i> Pools </a></li>
                    </ul>
                </div>
            </nav>


        </div>
        <!-- END WRAPPER -->
        <div class="container" style="margin-top:60px">
            <div class="jumbotron">      
                <div class="alert alert-danger" id="error-alert">
                    <div class="text-center">
                        <i class="fa fa-exclamation-triangle" aria-hidden="true" style="font-size: 75px;"></i>
                        <h1>404</h1>
                        <h2>Requested Resource Not Found</h2>
                        <div class="error-details">
                            Sorry, an error has occured, Requested page not found!
                        </div>
                        <div class="error-actions">
                            <a href="/${contextPath}" class="btn btn-primary btn-lg">
                                <span class="glyphicon glyphicon-home"></span>Take Me Home </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <footer class="footer navbar-fixed-bottom navbar-inverse" style="color: whitesmoke;">
            <div class="container-fluid">
                <span class="pull-left">
                    <i class="fa fa-copyright"></i> 2018 <a href="http://tyche.cash" target="_blank"><strong>TycheCash</strong></a>
                </span>
                <span class="pull-right"><i class="fa fa-github"></i>
                    <a href="https://github.com/jithin-nair/tychexplore" target="_blank"><strong>TychExplore</strong></a>
                </span>
            </div>
        </footer>
    </body>
</html>



