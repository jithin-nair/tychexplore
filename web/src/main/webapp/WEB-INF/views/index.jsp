<%-- 
    Document   : index
    Created on : 15 Feb, 2018, 9:16:57 PM
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

        <script src=${contextPath}"/resources/scripts/responsivevoice.js"></script>
        
        <!-- add the jQWidgets framework -->
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxcore.js"></script>
        <!-- add one or more widgets -->
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxdata.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript"
        src="resources/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxdatatable.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript"
        src="${contextPath}/resources/jqwidgets/jqxradiobutton.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxchart.core.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxdraw.js"></script>

        <script src="${contextPath}/resources/js/common.js"></script>
        <script src="${contextPath}/resources/js/main.js"></script>

        <!-- VENDOR CSS -->
        <link rel="stylesheet" href="${contextPath}/resources/styles/bootstrap.min.css">
        <link rel="stylesheet"
              href="${contextPath}/resources/font-awesome/css/font-awesome.min.css">

        <!-- add the jQWidgets base styles and one of the theme stylesheets -->
        <link rel="stylesheet" href="${contextPath}/resources/jqwidgets/styles/jqx.base.css"
              type="text/css" />
        <link rel="stylesheet"
              href="${contextPath}/resources/jqwidgets/styles/jqx.darkblue.css" type="text/css" />

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
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#divCollapse" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar bg-success"></span>
                            <span class="icon-bar bg-success"></span>
                            <span class="icon-bar bg-success"></span>
                        </button>
                        <a class="navbar-brand" href="/${contextPath}"> <img
                                src="resources/images/logo.png" alt="TychExplore Logo">
                        </a>
                    </div>
                    <div class="collapse navbar-collapse" id="divCollapse">
                        <ul class="nav navbar-nav">
                            <li><a href="/${contextPath}" class="custom-link-color"><i class="fa fa-home"></i> Home </a></li>
                            <li><a href="http://tychecash.net/#network" class="custom-link-color" target="_blank">
                                    <i class="fa fa-cloud"></i> Pools </a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <div class="navbar-form navbar-left">
                                    <div class="form-group">
                                        <span class="label label-warning" id="bWarning"></span>
                                        <input class="form-control mr-sm-2" type="text" name="query" id="query"
                                               placeholder="Search by block hash/height" style="width: 600px;">
                                    </div>
                                    <button class="btn btn-success" id="search" name="search" type="button">
                                        <i class="fa fa-search"></i> Search
                                    </button>
                                </div>
                            </li>
                        </ul>
                    </div>

                </div>
            </nav>


        </div>
        <!-- END WRAPPER -->
        <div class="container" style="margin-top:60px;margin-bottom:30px;">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-primary" style="height:300px">
                        <div class="panel-body">
                            <span class="label label-info" id="bSearchLabel">Latest Block Status</span>
                            <span id="bTotalCoinsDiv">
                                
                            </span>
                            <table class="table table-bordered" style="width:100%">
                                <tr>
                                    <th style="width: 30%;color: #336a80;">Height</th>
                                    <td style="color: #2d5768;"><b><span id="bHeight"></span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;color: #336a80;">Hash</th>
                                    <td style="color: #2d5768;word-break: break-all;">
                                        <span id="bHash" class="label label-success"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;color: #336a80;">Found</th>
                                    <td style="color: #2d5768;"><b><span id="bFound"></span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;color: #336a80;">Difficulty</th>
                                    <td style="color: #2d5768;"><b><span id="bDifficulty"></span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;color: #336a80;">Reward</th>
                                    <td style="color: #2d5768;"><b><span id="bReward"></span></b>
                                        &nbsp;<small class="label label-warning">TYCH</small></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;color: #336a80;">Status</th>
                                    <td style="color: #2d5768;"><b><span id="bStatus"></span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;color: #336a80;">Previous</th>
                                    <td style="color: #2d5768;word-break: break-all;">
                                        <i>
                                            <a id="bPrevious" class="label label-info" href="#"></a> 
                                        </i>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div id='chartContainer' style="height:300px;width:100%;"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="table"></div>
                </div>
            </div>
        </div>

        <footer class="footer navbar-fixed-bottom navbar-inverse" style="color: whitesmoke;">
            <div class="container-fluid">
                <span class="pull-left">
                    <i class="fa fa-copyright"></i> 2018 <a href="http://tyche.cash" target="_blank"><strong>TycheCash</strong></a>
                </span>
                <span class="pull-right"><i class="fa fa-github"></i>
                    <a href="https://github.com/jithin-nair/crypto-dev" target="_blank"><strong>TychExplore</strong></a>
                </span>
            </div>
        </footer>

    </body>
</html>
