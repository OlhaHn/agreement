var rowData;
$(document).ready(function(){


    // Activated the table
    var tableClient = $('#tableClient').DataTable({
        "autoWidth": false,
        "columnDefs": [
            {"targets": [ 0 ],
                "visible": false,
                "searchable": false}
        ],
        "ajax": {
            "url": "/getAllClients",
            "type": "POST",
            "success" :  function(data){
                $.each(data, function(ind, obj){

                    tableClient.row.add([
                        obj.id,
                        obj.amount,
                        obj.amountType,
                        obj.period,
                        obj.request,
                        obj.period
                    ]).draw();
                });
            }
        },
        select: true
    });


    $('#tableClient tbody').on( 'click', 'tr', function () {
        rowData = tableClient.row( this ).data();
        if(rowData!=null){
            $("#name").val(rowData[1]);
        }
    } );

    $("#buttonDelete").click(function () {
        console.log( rowData );
        $(this).callAjaxDel();
    });

    $("#buttonSearch").click(function(){

        tableClient.clear().draw();
        tableClient.ajax.reload();

    });

    $("#buttonInsert").click(function(){
        $(this).callAjax("insertClient", "");

        $(".form-control").val("");

    });

    $(window).load(function() {

    });


    $.fn.callAjaxDel = function() {
        $.ajax({
            type: "POST",
            url: "/deleteActive",
            dataType: "json",
            timeout : 100000,
            data: {"id": rowData[0]},

            success: function(data){
                tableClient.clear().draw();
                tableClient.ajax.reload();
            },
            error: function(e){
                alert("ERROR: ", e);
            }
        });
    };

    $.fn.callAjax = function( method, checkeds ){
        $.ajax({
            type: "POST",
            url: "/" + method,
            dataType: "json",
            timeout : 100000,
            data: { name: $("#name").val(), lastname: $("#lastname").val(), dateBirth: $("#dateBirth").val(),
                register: $("#register").val(), checked: checkeds },

            success: function(data){
                tableClient.clear().draw();
                tableClient.ajax.reload();
            },
            error: function(e){
                alert("ERROR: ", e);
            }
        });
    }

});
