<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 5/2/18
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>

<head>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.1/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.5/css/select.dataTables.min.css">
    <link rel="stylesheet" href="https://editor.datatables.net/extensions/Editor/css/editor.dataTables.min.css">
    <link rel="stylesheet" href="../../resources/css/agreement.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.2.5/js/dataTables.select.min.js"></script>
</head>

<div style="height: 100%; padding: 20px">

    <div class="container">
        <h2>Systemy</h2>
        <div class="row">
            <div class="column">
                <!-- Form for adding/updating/deleting entities -->

                <form class="form-horizontal" role="form" name="form1">

                    <!-- Name field -->
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">Nazwa:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" placeholder="nazwa">
                        </div>
                    </div>

                    <!-- description field -->
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="description">Opis:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="description" placeholder="opis">
                        </div>
                    </div>

                    <!-- owner field -->
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="owner">Właściciel:</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="owner">
                        </div>
                    </div>
                </form>
            </div> <!--column one end-->

        </div>
        <div class="form-group" style="clear: both">
            <button type="button" id="buttonInsert" class="btn btn-info">
                <span class="glyphicon glyphicon-search"></span> Dodaj
            </button>
            <button type="button" id="buttonUpdate" class="btn btn-success">
                <span class="glyphicon glyphicon-plus-sign"></span> Zaktualizuj
            </button>
            <button type="button" id="buttonDelete" class="btn btn-warning">
                <span class="glyphicon glyphicon-minus-sign"></span> Usuń
            </button>
        </div>

        <form style="margin: 5px" method="POST" enctype="multipart/form-data" action="/systemUpload" modelAttribute="file" >
            <table>
                <tr><td>Dodaj plik:</td><td><input type="file" name="file"/></td></tr>
            </table>
            <input type="submit" value="Dodaj plik" class="btn"/>
        </form>

        <div>
            <table id="tableSystems" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th class="col-sm-1" data-field="id">Id</th>
                    <th class="col-sm-1" data-field="name">Nazwa</th>
                    <th class="col-sm-3" data-field="description">Opis</th>
                    <th class="col-sm-3" data-field="owner">Właściciel</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<script>

    function checkIfAllFilled() {
        if($("#name").val().trim!="" && $("#owner").val().trim!=""){
            return true;
        }
        return false;
    }

    var rowData;
    $(document).ready(function(){

        $(".form-control").val("");
        // Activated the table
        var tableSystems = $('#tableSystems').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Polish.json"
            },
            "autoWidth": false,
            "columnDefs": [
                {"targets": [ 0 ],
                    "visible": false,
                    "searchable": false}
            ],
            "ajax": {
                "url": "/getSystems",
                "type": "POST",
                "success" :  function(data){
                    $.each(data, function(ind, obj){

                        tableSystems.row.add([
                            obj.id,
                            obj.name,
                            obj.description,
                            obj.owner,
                        ]).draw();
                    });
                }
            },
            select: true
        });


        $('#tableSystems tbody').on( 'click', 'tr', function () {
            rowData = tableSystems.row( this ).data();
            if(rowData!=null){
                $("#name").val(rowData[1]);
                $("#description").val(rowData[2]);
                $("#owner").val(rowData[3]);
            }
        } );

        $("#buttonDelete").click(function () {
            if(rowData==null) {
                alert("Wybierz element z listy");
            }else{
                $(this).callAjaxDel();
                $(".form-control").val("");
            }
        });

        $("#buttonUpdate").click(function() {

            if(rowData!=null){
                if(checkIfAllFilled()){
                    $(this).callAjax("updateSystem");
                    $(".form-control").val("");
                }else{
                    alert("Wszystkie pola muszą być wypełnione");
                }
            }else{
                alert("Wybierz element z listy");
            }
        });

        $("#buttonInsert").click(function() {
            if(checkIfAllFilled()) {
                $(this).callAjax("insertSystem");
                $(".form-control").val("");
            }else {
                alert("Wszystkie pola muszą być wypełnione");
            }

        });

        $(window).load(function() {

        });


        $.fn.callAjaxDel = function() {
            $.ajax({
                type: "POST",
                url: "/deleteSystem",
                dataType: "json",
                timeout : 100000,
                data: {"id": rowData[0]},

                success: function(data){
                    tableSystems.clear().draw();
                    tableSystems.ajax.reload();
                },
                error: function(e){
                    alert("ERROR: ", e);
                }
            });
        };


        $.fn.callAjax = function( method ){

            $.ajax({
                type: "POST",
                url: "/" + method,
                dataType: "json",
                timeout : 100000,
                data: { id: rowData[0],
                    name: $("#name").val(),
                    description: $("#description").val(),
                    owner: $("#owner").val()
                },
                success: function(data){
                    if(data==false){
                        alert("Błąd: sprawdż wypełnione dane");
                    }else {
                        tableSystems.clear().draw();
                        tableSystems.ajax.reload();
                    }
                },
                error: function(e){
                    alert("Błąd, być może taki numer umowy lub zapytania już jest w systemie");
                }
            });
        }

    });
</script>