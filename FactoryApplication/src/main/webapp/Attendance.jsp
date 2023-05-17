<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head >
	<meta charset="UTF-8">
	<title>Products</title>
	<link rel="stylesheet" type="text/css" href="Style.css">
	<script type="text/javascript" src="/FactoryApplication/js/Payroll.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#filter-form').submit(function(event) {
        event.preventDefault(); // prevent default form submission behavior

        var formData = {
            empId: $('#empId').val(),
            empName: $('#empName').val(),
            team: $('#team').val(),
            date: $('#date').val(),
        };

        $.ajax({
            url: '/FactoryApplication/payroll/getEmployeeAttendance.action',
            type: 'GET',
            data: formData,
            headers: {
                Accept : "application/json;charset=utf-8",
                "Content-type" : "application/json;charset=utf-8"
            },
            success: function(response) {
                console.log("response - ",response);
                var tableData = document.getElementById("tableContent");
                var table = "<center><table><th>Emp Id</th><th>Emp Name</th><th>Team</th><th>Date</th><th>Status</th>";
                $.each(response,function(i,employee){
                    console.log("employee : ",employee);
                    table += "<tr>";
                    table += "<td>" + employee.empId + "</td>";
                    table += "<td>" + employee.empName + "</td>";
                    table += "<td>" + employee.team + "</td>";
                    table += "<td>" + employee.date + "</td>";
                    table += "<td>" + employee.status + "</td></tr>";
                })
                table += "</table></center>";
                tableData.innerHTML = table;
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
                var tableData = document.getElementById("tableContent");
                tableData.innerHTML="<center><h3>No Records Found</h3></center>"
            }
        });
    });
});

</script>

</head>
<body onload="loadAttendance()">
<tiles:insertAttribute name="header" /> 
<tiles:insertAttribute name="menu" /> 
	<div style="margin-left: 200px;"><center>
	<div id="bodyContent">
	<h3>Attendance</h3>
	<form id="filter-form">
		
  		<div class="form-group" style="margin-left: 280px;">
  			<h4 style="display: inline-block; margin-right: 10px;">Filter: </h4>
		    <input type="number" id="empId" name="empId" placeholder="Employee Id" style="width: 100px;margin-right: 10px;">
		    <input type="text" id="empName" name="name" placeholder="Employee Name" style="width: 100px;margin-right: 10px;">
		    <input type="text" id="team" name="team" placeholder="team" style="width: 100px;margin-right: 10px;">
		    <input type="date" id="date" name="date" placeholder="Date" style="width: 200px;margin-right: 10px;" required>
		    <button type="submit">Submit</button>
  		</div>
  	</form>
  	<div id="tableContent"></div>
	</div>
	</div>
 	</center>
</body>
</html>