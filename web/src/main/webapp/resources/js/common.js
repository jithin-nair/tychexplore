$(document).ready(function () {
    
    var url = "/restapi/getRecentBlocks";
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
            }],
        id: 'id',
        url: url,
        root: 'blockHeaders',
        beforeSend: function (data) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/restapi/getTotalPages?_=" + new Date().getTime(),
                dataType: 'json',
                success: function (data) {
                    source.totalrecords = data;
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                }
            });
        }
    };
    
    var linkrenderer = function (row, column, value) {
                if (value.indexOf('#') != -1) {
                    value = value.substring(0, value.indexOf('#'));
                }
                var format = { target: '"_self"' };
                var href = "block/tx/" + value;
            return "<a href='" + href + "'>" + value + "</a>";
            }
    
    var dataAdapter = new $.jqx.dataAdapter(source);

    $("#table").jqxDataTable({
        width: "100%",
        autoRowHeight: true,
        theme: 'metrodark',
        source: dataAdapter,
        filterable: false,
        pagerPosition: 'both',
        pageable: true,
        pagerMode: "advanced",
        pageSizeOptions: ['10', '15', '20'],
        pagerButtonsCount: 5,
        serverProcessing: true,
        altRows: true,
        columns: [{
                text: 'Height',
                editable: false,
                dataField: 'height',
                width: '15%'
            }, {
                text: 'Block Hash',
                editable: false,
                dataField: 'hash',
                width: '45%',
                cellsrenderer: linkrenderer
            }, {
                text: 'Difficulty',
                editable: false,
                dataField: 'difficulty',
                width: '15%'
            }, {
                text: 'Reward',
                dataField: 'reward',
                width: '15%',
                cellsalign: 'left'
            }, {
                text: 'Found Time',
                dataField: 'timestamp',
                cellsalign: 'center',
                width: '10%',
                cellsrenderer: function (row, column, value) {
                    return new Date(value * 1000).toGMTString();
                }
            }]
    });
    
        $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/restapi/totalMinedCoins?_=" + new Date().getTime(),
        dataType: 'json',
        success: function (data) {
            var json = data;
            $("#bTotalCoinsDiv").append("<input type='hidden' value='"+json.inWords.toString()+"' id='inWords'/>"
                    +"<input class='btn btn-success btn-xs pull-right' type='button' id='inWordsButton' value='🔊Play' />"
                    +"<span class='label label-warning pull-right' id='bTotalCoins'>"+json.totalCoins+"</span>"
                    +"<span class='label label-info pull-right'> Total Coins in Network : </span>");
            $("#inWordsButton").click(function () {
                var text = $("#inWords").val();
                responsiveVoice.speak(text);
            });
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
    
});