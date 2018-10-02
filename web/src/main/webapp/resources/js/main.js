/**
 * 
 */
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
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/restapi/getBlockBySearch?query=" + totalPages + "&_=" + new Date().getTime(),
                dataType: 'json',
                success: function (data) {
                    var json = data;
                    $("#bHeight").text(json.result.block_header.height);
                    $("#bHash").text(json.result.block_header.hash);
                    $("#bFound").text(new Date(json.result.block_header.timestamp * 1000).toGMTString());
                    $("#bDifficulty").text(json.result.block_header.difficulty);
                    $("#bReward").text(json.result.block_header.reward);
                    $("#bStatus").text((json.result.block_header.orphan_status === true) ? 'Orphaned' : 'Not Orphaned');
                    $("#bPrevious").text(json.result.block_header.prev_hash);
                    $("#bPrevious").attr("href", "block/tx/" + json.result.block_header.prev_hash);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                }
            });

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
            url: "/restapi/getBlockBySearch?query=" + $("#query").val() + "&_=" + new Date().getTime(),
            dataType: 'json',
            success: function (data) {
                var json = data;
                if (json.result == null) {
                    $("#bWarning").text("No Result Found");
                    window.setTimeout(function () {
                        $("#bWarning").text("");
                    }, 3000);
                } else {
                    $("#bHeight").text(json.result.block_header.height);
                    $("#bHash").text(json.result.block_header.hash);
                    $("#bFound").text(new Date(json.result.block_header.timestamp * 1000).toGMTString());
                    $("#bDifficulty").text(json.result.block_header.difficulty);
                    $("#bReward").text(json.result.block_header.reward);
                    $("#bStatus").text((json.result.block_header.orphan_status === true) ? 'Orphaned' : 'Not Orphaned');
                    $("#bPrevious").text(json.result.block_header.prev_hash);
                    $("#bPrevious").attr("href", "block/tx/" + json.result.block_header.prev_hash);
                    $("#bSearchLabel").text("Search Result");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    });


});
