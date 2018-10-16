<%-- 
    Document   : search
    Created on : 8 Mar, 2018, 9:13:15 PM
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

        <script src=${contextPath}"/resources/scripts/responsivevoice.js"></script>

        <!-- add the jQWidgets framework -->
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxcore.js"></script>
        <!-- add one or more widgets -->
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxdata.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript"
        src="${contextPath}/resources/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxdatatable.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript"
        src="${contextPath}/resources/jqwidgets/jqxradiobutton.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxchart.core.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/jqwidgets/jqxdraw.js"></script>


        <!-- VENDOR CSS -->
        <link rel="stylesheet" href="${contextPath}/resources/styles/bootstrap.min.css">
        <link rel="stylesheet"
              href="${contextPath}/resources/font-awesome/css/font-awesome.min.css">

        <!-- add the jQWidgets base styles and one of the theme stylesheets -->
        <link rel="stylesheet" href="${contextPath}/resources/jqwidgets/styles/jqx.base.css"
              type="text/css" />
        <link rel="stylesheet"
              href="${contextPath}/resources/jqwidgets/styles/jqx.metrodark.css" type="text/css" />

        <link rel="stylesheet"
              href="${contextPath}/resources/css/style.css" type="text/css" />
        
        <style type="text/css">
            .custom-link-color{
                color:whitesmoke;
            }
            th {
                background-color: #616161;
                color: white;
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
        <div class="container" style="margin-top:60px;margin-bottom:30px;">
            <c:if test="${not empty message}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-danger" id="error-alert">
                            <button type="button" class="close" data-dismiss="alert">x</button>
                            <strong>Oops!!</strong> ${message}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-primary" style="height:300px">
                        <div class="panel-body">
                            <span class="label label-large label-yellow arrowed-in-right arrowed-in" id="bSearchLabel">Latest Block Status</span>
                            <span id="bTotalCoinsDiv">

                            </span>
                            <table class="table table-bordered" style="width:100%">
                                <tr>
                                    <th style="width: 30%;">Height</th>
                                    <td style="color: #2d5768;"><b><span id="bHeight">${bHeight}</span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;">Hash</th>
                                    <td style="color: #2d5768;word-break: break-all;">
                                        <i>
                                            <a id="bHash" class="label label-success" href="#">${bHash}</a> 
                                        </i>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;">Found</th>
                                    <td style="color: #2d5768;"><b><span id="bFound">${bFound}</span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;">Difficulty</th>
                                    <td style="color: #2d5768;"><b><span id="bDifficulty">${bDifficulty}</span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;">Reward</th>
                                    <td style="color: #2d5768;"><b><span id="bReward">${bReward}</span></b>
                                        &nbsp;<small class="label label-warning">TYCH</small></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;">Status</th>
                                    <td style="color: #2d5768;"><b><span id="bStatus">${bStatus}</span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 30%;">Previous</th>
                                    <td style="color: #2d5768;word-break: break-all;">
                                        <i>
                                            <a id="bPrevious" class="label label-info" href="${contextPath}/block/tx/${bPrevious}">${bPrevious}</a> 
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
                    <div class="panel panel-primary">
                        <div class="panel-body">
                            <span class="label label-large label-yellow arrowed-in-right arrowed-in" id="bSearchLabel">Block Transaction Details</span>
                            <span id="bTotalCoinsDiv">

                            </span>
                            <table class="table table-bordered" style="width:100%">
                                <tr>
                                    <th style="width: 10%;">Major Version</th>
                                    <td style="color: #2d5768;"><b><span id="majorVersion">${blockTransactionResponse.majorVersion}</span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 10%;">Minor Version</th>
                                    <td style="color: #2d5768;">
                                        <b><span id="prevId">${blockTransactionResponse.minorVersion}</span></b>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width: 10%;">Nonce</th>
                                    <td style="color: #2d5768;"><b><span id="nonce">${blockTransactionResponse.nonce}</span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 10%;word-break: break-all;">Prev Block Id</th>
                                    <td style="color: #2d5768;">
                                        <i>
                                            <a id="bPrevious" class="label label-info" href="${contextPath}/block/tx/${blockTransactionResponse.prevId}">${blockTransactionResponse.prevId}</a> 
                                        </i>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width: 10%;">Block Found</th>
                                    <td style="color: #2d5768;"><b><span id="btxFound">${blockTransactionResponse.timestamp}</span></b></td>
                                </tr>
                                <tr>
                                    <th style="width: 10%;">Tx hashes</th>
                                    <td style="color: #2d5768;">
                                        <b>
                                            <table class="table table-bordered" style="width:100%">
                                                <c:forEach var="hashes" items="${blockTransactionResponse.txHashes}" varStatus="loop">
                                                    <tr>
                                                        <th style="width: 12%;">Transaction ${loop.index+1}</th>
                                                        <td style="color: #2d5768;"><b>
                                                                <span id="txHash_${loop.index+1}">
                                                                    <i>
                                                                        <a id="bPrevious" class="label label-pink" href="${contextPath}/tx/<c:out value="${hashes}" />"><c:out value="${hashes}" /></a> 
                                                                    </i>
                                                                </span></b>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </b>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width: 10%;">Miner Tx</th>
                                    <td style="color: #2d5768;word-break: break-all;">
                                        <b>
                                            <table class="table table-bordered" style="width:100%">
                                                <tr>
                                                    <th style="width: 12%;">Extra</th>
                                                    <td style="color: #2d5768;">
                                                        <b><span id="extra">${blockTransactionResponse.minerTx.extra}</span></b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th style="width: 12%;">Block Unlock Height</th>
                                                    <td style="color: #2d5768;">
                                                        <b><span id="unlockTime">${blockTransactionResponse.minerTx.unlockTime}</span></b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th style="width: 12%;">Version</th>
                                                    <td style="color: #2d5768;">
                                                        <b><span id="version">${blockTransactionResponse.minerTx.version}</span></b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th style="width: 12%;">Vin</th>
                                                    <td style="color: #2d5768;">
                                                        <b>
                                                            <table class="table table-bordered" style="width:100%">
                                                                <c:forEach var="vin" items="${blockTransactionResponse.minerTx.vin}" varStatus="loop">
                                                                    <tr>
                                                                        <th style="width: 12%;">Type</th>
                                                                        <td style="color: #2d5768;"><b>
                                                                                <span id="vType"><c:out value="${vin.type}" />
                                                                                </span></b>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th style="width: 12%;">Height</th>
                                                                        <td style="color: #2d5768;"><b>
                                                                                <span id="vHeight"><c:out value="${vin.value.height}" />
                                                                                </span></b>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </table>
                                                        </b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th style="width: 12%;">VOut</th>
                                                    <td style="color: #2d5768;">
                                                        <b>
                                                            <table class="table table-bordered" style="width:100%">
                                                                <tr>
                                                                    <th style="width: 10%;">Type</th>
                                                                    <th style="width: 70%;">Key</th>
                                                                    <th style="width: 20%;">Amount</th>
                                                                </tr>
                                                                <c:forEach var="transactionVO" items="${transactionVOs}" varStatus="loop">
                                                                    <tr>
                                                                        <td style="color: #2d5768;"><b>
                                                                                <span id="txType"><c:out value="${transactionVO.type}" />
                                                                                </span></b>
                                                                        </td>
                                                                        <td style="color: #2d5768;"><b>
                                                                                <span id="txKey"><c:out value="${transactionVO.key}" />
                                                                                </span></b>
                                                                        </td>
                                                                        <td style="color: #2d5768;"><b>
                                                                                <span id="txAmount"><c:out value="${transactionVO.amountValue}" />
                                                                                </span></b>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </table>
                                                        </b>
                                                    </td>
                                                </tr>
                                            </table>
                                        </b>
                                    </td>
                                </tr>
                            </table>
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
    <script>
        $(document).ready(function () {

            var totalPages = 0;

            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/restapi/getTotalPages?_=" + new Date().getTime(),
                dataType: 'json',
                success: function (data) {
                    totalPages = data;
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                complete: function (jqXHR, textStatus) {

                    var url = "/restapi/getGraphData";
                    // prepare the data
                    var source = {
                        datatype: "json",
                        datafields: [{
                                name: 'height',
                                type: 'int',
                                map: 'height'
                            }, {
                                name: 'hash',
                                type: 'string',
                                map: 'hash'
                            }, {
                                name: 'difficulty',
                                type: 'int',
                                map: 'difficulty'
                            }, {
                                name: 'reward',
                                type: 'string',
                                map: 'reward'
                            }, {
                                name: 'timestamp',
                                type: 'string',
                                map: 'timestamp'
                            }, {
                                name: 'orphan_status',
                                type: 'string',
                                map: 'orphan_status'
                            }],
                        id: 'id',
                        url: url,
                        root: 'blockHeaders'
                    };

                    var toolTipCustomFormatFn = function (dataSource) {
                        return function (value, itemIndex, serieGroup, group, categoryValue, categoryAxis) {
                            var timestamp = dataSource.records[itemIndex].timestamp;
                            var height = dataSource.records[itemIndex].height;
                            var difficulty = dataSource.records[itemIndex].difficulty;
                            return "Height: " + height + "</br>Difficulty: " + difficulty + "</br>Found: " + new Date(timestamp * 1000).toLocaleDateString();
                        }
                    };

                    var dataAdapter = new $.jqx.dataAdapter(source);

                    // prepare jqxChart settings
                    var settings = {
                        title: "Difficulty Graph",
                        description: "Difficulty vs Height",
                        toolTipFormatFunction: toolTipCustomFormatFn(dataAdapter),
                        enableAnimations: true,
                        showLegend: true,
                        padding: {left: 15, top: 5, right: 20, bottom: 5},
                        titlePadding: {left: 10, top: 0, right: 0, bottom: 10},
                        source: dataAdapter,
                        xAxis:
                                {
                                    dataField: 'height',
                                    valuesOnTicks: true
                                },
                        colorScheme: 'scheme02',
                        seriesGroups:
                                [
                                    {
                                        alignEndPointsWithIntervals: false,
                                        type: 'splinearea',
                                        series: [
                                            {dataField: 'difficulty', displayText: 'difficulty', opacity: 0.7}
                                        ]
                                    }
                                ]
                    };
                    // setup the chart
                    $('#chartContainer').jqxChart(settings);

                }
            });
            
            $("#search").click(function () {
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "/restapi/getBySearch?query=" + $("#query").val() + "&_=" + new Date().getTime(),
                    dataType: 'json',
                    success: function (data) {
                        var json = data;
                        if (json.status == "FAILED") {
                            $("#bWarning").text("No Result Found");
                            window.setTimeout(function () {
                                $("#bWarning").text("");
                            }, 5000);
                        } else {
                            if (json.type == "block") {
                                window.open("/block/tx/" + json.query, "_self")
                            } else {
                                window.open("/tx/" + json.query, "_self")
                            }
                        }
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                    }
                });
            });

        });

        //Converts timestamp from server side to GMT String equivalent
        $("#bFound").text(new Date($("#bFound").text() * 1000).toGMTString());
        $("#btxFound").text(new Date($("#btxFound").text() * 1000).toGMTString());

    </script>
</html>

