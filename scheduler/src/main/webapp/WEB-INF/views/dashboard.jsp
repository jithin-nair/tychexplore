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

        <link href="${contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

        <!-- Custom styles for this template -->
        <link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/sidebar.css" rel="stylesheet">
    </head>

    <body>

        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>TychExplore Scheduler</h3>
                </div>

                <ul class="list-unstyled CTAs">
                    <c:if test="${not empty username}">
                        <div class="label label-info">
                            <span class="fa fa-user-secret"></span> ${username}
                        </div>
                    </c:if>
                    <br>
                    <c:url value="/logout" var="logoutUrl" />
                    <form action="${logoutUrl}" method="post" id="logoutForm">
                        <input type="hidden" 
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                        <button type="submit" class="btn btn-danger"><span class="fa fa-sign-out"></span>Sign out</button>
                    </form>
                </ul>

                <ul class="list-unstyled components">
                    <li>
                        <a href="${contextPath}/dashboard"><span class="fa fa-dashboard"></span> Dashboard <span class="sr-only">(current)</span></a>
                    </li>
                    <li>
                        <a href="${contextPath}/jobs/listjobs"><span class="fa fa-tasks"></span> Jobs</a>
                    </li>
                    <li>
                        <a href="${contextPath}/jobs/showLogs"><span class="fa fa-bell-o"></span> Logs</a>
                    </li>
                </ul>

            </nav>

            <!-- Page Content Holder -->
            <div id="content">

                <nav class="navbar navbar-default">
                    <div class="row">
                        <div class="col-md-2">
                            <div class="navbar-header">
                                <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                                    <i class="fa fa-expand"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </nav>

                <h2><span class="fa fa-dashboard"></span> Dashboard</h2>
                <div id="page">
                    <div id="poolStats" class="row">
                        <c:forEach items="${jobList}" var="job"> 
                            <div class="col-lg-3 col-sm-4">
                                <div class="infoBox hoverExpandEffect">
                                    <div class="icon">
                                        <span class="fa fa-history"></span>
                                    </div>
                                    <div class="content">
                                        <div class="text"><c:out value="${job.jobName}" /></div>
                                        <div class="value"><span id="blockSolvedTime"><c:out value="${job.jobStatus}" /></span> 
                                            <span class="smallText">
                                                <c:out value="${job.groupName}" />
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <!-- Last Hash -->
                        <div class="col-sm-12">
                            <div class="hashInfo hoverExpandEffect">
                                <div class="text">Last Hash</div>
                                <div class="content clearfix">
                                    <div class="value"><a id="lastHash" target="_blank" href="http://chainradar.com/tych/block/d17975fa4f225208ab78c085637835e8ee2d1d2492540638fcc73f318e43b9e6">d17975fa4f225208ab78c085637835e8ee2d1d2492540638fcc73f318e43b9e6</a></div>
                                    <div class="time">(<span id="networkLastBlockFound">6 minutes ago</span>)</div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
        </div>


        <script src="${contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });

                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content').toggleClass('active');
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });
            });
        </script>

    </body>
</html>

