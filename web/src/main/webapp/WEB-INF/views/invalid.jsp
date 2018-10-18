<%-- 
    Document   : invalid
    Created on : 18 Mar, 2018, 8:50:41 PM
    Author     : Jithin
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
                        <li><a href="http://tychecash.net/snapshot.tar.gz" class="custom-link-color">
                                <i class="fa fa-download"></i> Snapshot </a></li>
                        <li><a href="http://wallet.tychecash.net" class="custom-link-color">
                                <i class="fa fa-id-card"></i> Web Wallet </a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <div class="navbar-form navbar-left">
                                <div class="form-group">
                                    <span class="label label-warning" id="bWarning"></span>
                                    <input class="form-control mr-sm-2" type="text" name="query" id="query"
                                           placeholder="Search by block hash/height/tx hash" style="width: 500px;">
                                </div>
                                <button class="btn btn-success" id="search" name="search" type="button">
                                    <i class="fa fa-search"></i> Search
                                </button>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>


        </div>
        <!-- END WRAPPER -->
        <div class="container" style="margin-top:60px">
            <c:if test="${not empty message}">
                <div class="jumbotron">      
                    <div class="alert alert-danger" id="error-alert">
                        <div class="text-center">
                            <i class="fa fa-exclamation-triangle" aria-hidden="true" style="font-size: 75px;"></i>
                            <h1>Oops...</h1>
                            <p>${message}</p>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>

        <footer class="footer navbar-fixed-bottom navbar-inverse" style="color: whitesmoke;">
            <div class="container-fluid">
                <span class="pull-left">
                    <i class="fa fa-copyright"></i> 2018 <a href="http://tyche.cash" target="_blank"><strong>BlockchainNeXT</strong></a>
                </span>
                <span class="pull-right"><i class="fa fa-github"></i>
                    <a href="https://github.com/jithin-nair/tychexplore" target="_blank"><strong>TychExplore</strong></a>
                </span>
            </div>
        </footer>
    </body>
</html>


