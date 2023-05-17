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
	<script>
	$(document).ready(function() {
	    $('#shift-form').submit(function(event) {
	      event.preventDefault();
	      var formData = $(this).serialize();
	      $.ajax({
	        type: 'POST',
	        url: '/FactoryApplication/shift/addShift.action',
	        data: formData,
	        success: function(result) {
	        	document.getElementById("shift-form").reset();
	        	var status = document.getElementById("formStatus");
	        	status.innerHTML="<center><p style='color:green;'>Shift added successfully !</p><center>"
	        },
	        error: function() {
	          //alert('Error occurred while adding product');
	        	document.getElementById("shift-form").reset();
	        	var status = document.getElementById("formStatus");
	        	status.innerHTML="<center><p style='color:red;'>Failed to add product !</p><center>"
	        
	        }
	      });
	    });
	  });

	</script>
</head>
<body>
<tiles:insertAttribute name="header" /> 
   <tiles:insertAttribute name="menu" />  
<div style="margin-left: 200px;"><center>
	<div id="bodyContent">
	<h3>Shift Details</h3>
  	<form id="shift-form" class="form" style='align-items: center;'>
  <div class="form-group">
    <label for="empId">Employee Id:</label>
    <input type="text" id="empId" name="empId" required>
  </div>
  
  <div class="form-group">
    <label for="shiftDate">Shift Date :</label>
    <input type="date" id="shiftDate" name="shiftDate" required>
  </div>
  
  <div class="form-group">
    <label for="numOfHrs">Number of Hours :</label>
    <input type="number" id="numOfHrs" name="numOfHrs" required>
  </div>
  
  <div class="form-group">
    <label for="startTime">Start Time:</label>
    <input type="time" id="startTime" name="startTime" required>
  </div>
  <div class="form-group">
    <label for="endTime">End Time:</label>
    <input type="time" id="endTime" name="endTime" required>
  </div>
  <button type="submit">Submit</button>
</form>
<div id="formStatus"></div>
	</div>
 	</center>
</body>
</html>