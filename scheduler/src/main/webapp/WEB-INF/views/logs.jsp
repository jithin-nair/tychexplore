<%-- 
    Document   : jobs
    Created on : 8 Apr, 2018, 12:36:43 PM
    Author     : Jithin
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

        <script src="${contextPath}/resources/scripts/sockjs.js"></script>
        <script src="${contextPath}/resources/scripts/stomp.js"></script>
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

                <h2><span class="fa fa-tasks"></span> Logs</h2>

                <div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-inline">
                                <div class="form-group">
                                    <label for="connect">WebSocket connection: &nbsp;</label>
                                    <button id="connect" class="btn btn-success" type="button">Connect</button>&nbsp;
                                    <button id="disconnect" class="btn btn-warning" type="button" disabled="disabled">Disconnect
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <form class="form-inline">
                                <div class="form-group">
                                    <label for="name">Websocket echo test:</label>
                                    <input type="text" id="name" class="form-control" placeholder="Type something here...">&nbsp;
                                </div>
                                <button id="send" class="btn btn-dark" type="button">Send</button>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table id="conversation" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Logs</th>
                                    </tr>
                                </thead>
                                <tbody id="greetings">
                                </tbody>
                            </table>
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

            var stompClient = null;

            function setConnected(connected) {
                $("#connect").prop("disabled", connected);
                $("#disconnect").prop("disabled", !connected);
                if (connected) {
                    $("#conversation").show();
                } else {
                    $("#conversation").hide();
                }
                $("#greetings").html("");
            }

            function connect() {
                var socket = new SockJS('http://localhost:8084/scheduler/gs-guide-websocket');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function (frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/greetings', function (greeting) {
                        showGreeting(JSON.parse(greeting.body).content);
                    });
                    stompClient.subscribe('/log', function (greeting) {
                        showGreeting(JSON.parse(greeting.body).content);
                    });
                });
            }

            function disconnect() {
                if (stompClient !== null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }

            function sendName() {
                stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
            }

            function showGreeting(message) {
                $("#greetings").append("<tr><td>" + message + "</td></tr>");
            }

            $(function () {
                $("#connect").click(function () {
                    connect();
                });
                $("#disconnect").click(function () {
                    disconnect();
                });
                $("#send").click(function () {
                    sendName();
                });
            });

        </script>

    </body>
</html>
