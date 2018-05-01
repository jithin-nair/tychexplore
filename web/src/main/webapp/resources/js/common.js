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
                name: 'orphan_status',
                type: 'string',
                map: 'orphan_status'
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
    
    var dataAdapter = new $.jqx.dataAdapter(source);

    $("#table").jqxDataTable({
        width: "100%",
        autoRowHeight: true,
        theme: 'darkblue',
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
                width: '45%'
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
                text: 'Orphan Status',
                dataField: 'orphan_status',
                cellsalign: 'center',
                width: '10%',
                cellsrenderer: function (row, column, value) {
                    if(value===true){
                        return "Orphaned";
                    }
                    else{
                        return "Not Orphaned";
                    }
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