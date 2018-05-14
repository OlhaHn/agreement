<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 5/1/18
  Time: 12:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>


<meta charset="UTF-8">
<head>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.1/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.5/css/select.dataTables.min.css">
    <link rel="stylesheet" href="https://editor.datatables.net/extensions/Editor/css/editor.dataTables.min.css">
    <link rel="stylesheet" href="../../resources/css/agreement.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.2.5/js/dataTables.select.min.js"></script>
    <script src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js"></script>

</head>
<div style="height: 100%; padding: 20px">

    <div class="container">
        <h2>${typeOfAgreements} umowy</h2>
        <div class="row">
            <div class="column">
        <!-- Form for adding/updating/deleting entities -->

            <form class="form-horizontal" role="form" name="form1">

                <!-- order number field, check if is number -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Numer:</label>
                    <div class="col-sm-10">
                        <input type="text" onkeyup="this.value=this.value.replace(/[^\d]/,'')" class="form-control" id="number" placeholder="numer">
                    </div>
                </div>

                <!-- Date from field -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="dateFrom">Od:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control otherclass" id="dateFrom" min="1900-01-01" max="2050-01-01">
                    </div>
                </div>

                <!-- Date to field -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="dateTo">Do:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="dateTo" min="1900-01-01" max="2050-01-01">
                    </div>
                </div>

                <!-- Amount field -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="amount">Wartość:</label>
                    <div class="col-sm-10">
                        <input type="number" step="0.01" class="form-control" id="amount" min=0 placeholder="wartość">
                    </div>
                </div>

                <!-- Amount type field -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="type">Typ wartości:</label>
                    <div class="col-sm-10">
                        <select name="combobox" id="type">
                            <option selected value="NET">Netto</option>
                            <option value="BRU">Brutto</option>
                        </select>
                    </div>
                </div>

            </form>
            </div> <!--column one end-->

            <div class="column">
            <form class="form-horizontal" role="form" name="form2">

                <!-- Period field -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="period">Period umowy:</label>
                    <div class="col-sm-10">
                        <select name="combobox1" id="period">
                            <option selected value="MONTH">Miesiąc</option>
                            <option value="QUARTER">Kwartał</option>
                            <option value="YEAR">Rok</option>
                        </select>
                    </div>
                </div>


                <!-- Authorization percent field-->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="percent">Procent:</label>
                    <div class="col-sm-10">
                        <input type="number" step="0.01" class="form-control" id="percent" min=0 placeholder="procent">
                    </div>
                </div>

                <!-- System field -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="system">System:</label>
                    <div class="col-sm-10">
                        <select name="combobox2" id="system">
                        </select>
                    </div>
                </div>

                <!-- Request field-->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="request">Numer zapytania:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="request" onkeyup="this.value=this.value.replace(/[^\d]/,'')" placeholder="numer zapytania">
                    </div>
                </div>

                <!-- Active field -->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="active">Aktywna:</label>
                    <div class="col-sm-10">
                        <input type="checkbox" checked class="form-control" id="active">
                    </div>
                </div>


            </form>
            </div>
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

        <form style="margin: 5px" method="POST" enctype="multipart/form-data" action="${fileUploadAdress}" modelAttribute="file" >
            <table>
                <tr><td>Dodaj plik:</td><td><input type="file" name="file"/></td></tr>
            </table>
            <input type="submit" value="Dodaj plik" class="btn"/>
        </form>


        <div>
            <table id="tableAgreements" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th class="col-sm-1" data-field="id">Id</th>
                    <th class="col-sm-1" data-field="number">Numer</th>
                    <th class="col-sm-3" data-field="from">Od</th>
                    <th class="col-sm-3" data-field="to">Do</th>
                    <th class="col-sm-2" data-field="value">Wartość</th>
                    <th class="col-sm-2" data-field="value_type">Typ wartości</th>
                    <th class="col-sm-2" data-field="period">Period umowy</th>
                    <th class="col-sm-2" data-field="percent">Procent</th>
                    <th class="col-sm-2" data-field="system">System</th>
                    <th class="col-sm-2" data-field="question">Numer zapytania</th>
                    <th class="col-sm-2" data-field="aktywna">Aktywna</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>

</div>
<script>

    function resetForms() {
        document.forms['form1'].reset();
        document.forms['form2'].reset();
    }

    function checkIfAllFilled() {
        var isValid=true;
        $(".form-group input").each(function() {
            var element = $(this);
            console.log(element);
            console.log("val:"+element.val());
            if (element.val().trim() == "") {
                isValid = false;
            }
        });
        return isValid;

    }

    var rowData;
    $(document).ready(function(){

        resetForms();
        // Activated the table
        var tableAgreements = $('#tableAgreements').DataTable({
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
                "url": "${getAgreements}",
                "type": "POST",
                "success" :  function(data){
                    $.each(data, function(ind, obj){

                        var amountType = obj.amountType=="NET"?"netto":"brutto";
                        var period = obj.period == "MONTH" ? "miesiąc" : obj.period == "YEAR" ? "rok" : "kwartał";
                        var active = obj.active ? "TAK" : "NIE";

                        tableAgreements.row.add([
                            obj.id,
                            obj.number,
                            obj.dateFrom,
                            obj.dateTo,
                            obj.amount,
                            amountType,
                            period,
                            obj.percent,
                            obj.system.name,
                            obj.request,
                            active
                        ]).draw();
                    });
                }
            },
            select: true
        });


        $('#tableAgreements tbody').on( 'click', 'tr', function () {
            rowData = tableAgreements.row( this ).data();
            if(rowData!=null){
                console.log(rowData);
                $("#number").val(rowData[1]);
                $("#dateFrom").val(rowData[2].substring(6, 10)+"-"+rowData[2].substring(0, 2)+"-"+rowData[2].substring(3, 5));
                $("#dateTo").val(rowData[3].substring(6, 10)+"-"+rowData[3].substring(0, 2)+"-"+rowData[3].substring(3, 5));
                $("#amount").val(rowData[4]);
                if(rowData[5]=="netto"){
                    $("#type").val("NET");
                }else{
                    $("#type").val("BRU");
                }

                if(rowData[6]=="rok"){
                    $("#period").val("YEAR");
                }else if(rowData[6]=="kwartał"){
                    $("#period").val("QUARTER");
                }else{
                    $("#period").val("MONTH");
                }
                $("#percent").val(rowData[7]);
                $("#system").val(rowData[8]);
                if(rowData[10]=="TAK") {
                    $("#active").prop('checked',true);
                    $("#active").val("on");
                }else {
                    $("#active").val("off");
                    $("#active").prop('checked', false);
                }

                $("#request").val(rowData[9]);

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
                    $(this).callAjax("updateAgreement", "");
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
                $(this).callAjax("insertAgreement", "");
                $(".form-control").val("");
            }else {
                alert("Wszystkie pola muszą być wypełnione");
            }

        });


        $(window).load(function() {
            $(this).callAjaxGetSystems();
        });


        $.fn.callAjaxDel = function() {
            $.ajax({
                type: "POST",
                url: "/deleteActive",
                dataType: "json",
                timeout : 100000,
                data: {"id": rowData[0]},

                success: function(data){
                    tableAgreements.clear().draw();
                    tableAgreements.ajax.reload();
                },
                error: function(e){
                    alert("ERROR: ", e);
                }
            });
        };

        $.fn.callAjaxGetSystems = function(){
            $.ajax({
                type: "POST",
                url: "/getSystems",
                dataType: "json",
                timeout : 100000,

                success: function(data){
                    var select = document.getElementById("system");
                    $.each(data, function(ind, obj){
                        select.options[select.options.length] = new Option(obj.name, obj.name);
                    });

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
                data: { id: rowData[0],
                    number: $("#number").val(),
                    dateFrom: $("#dateFrom").val(),
                    dateTo: $("#dateTo").val(),
                    amount: $("#amount").val(),
                    amountType: $("#type").val(),
                    period: $("#period").val(),
                    percent: $("#percent").val(),
                    system: $("#system").val(),
                    request: $("#request").val(),
                    active: $("#active").val()
                },
                success: function(data){
                    if(data==false){
                        alert("Błąd: sprawdż wypełnione dane");
                    }else {
                        tableAgreements.clear().draw();
                        tableAgreements.ajax.reload();
                    }
                },
                error: function(e){
                    alert("Błąd, być może taki numer umowy lub zapytania już jest w systemie");
                }
            });
        }

    });



</script>