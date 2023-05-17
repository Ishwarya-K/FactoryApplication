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
	    $('#employee-form').submit(function(event) {
	      event.preventDefault();
	      var formData = $(this).serialize();
	      $.ajax({
	        type: 'POST',
	        url: '/FactoryApplication/payroll/addEmployee.action',
	        data: formData,
	        success: function(result) {
	        	console.log("result : ",result);
	        	document.getElementById("employee-form").reset();
	        	var status = document.getElementById("formStatus");
	        	if(result=="error"){
	        		status.innerHTML="<center><p style='color:red;'>Failed to add employee !</p><center>"
	        	}
	        	else{
	        		status.innerHTML="<center><p style='color:green;'>Employee added successfully !</p><center>";
	        	}
	        	
	        },
	        error: function() {
	            alert('Error occurred while adding product');
	        	//var status = document.getElementById("formStatus");
	        	//status.innerHTML="<center><p style='color:red;'>Failed to add employee !</p><center>"
	        }
	      });
	    });
	  });

	</script>
</head>
<body >
<tiles:insertAttribute name="header" /> 
<tiles:insertAttribute name="menu" /> 
<br><br>
<div style="margin-left: 200px;">
	<div id="bodyContent" >
<form id="employee-form" class="form" style='align-items: center;'>
  <div class="form-group">
    <label for="empName">Employee Name:</label>
    <input type="text" id="empName" name="empName" required>
  </div>
  
  <div class="form-group">
    <label for="dob">DOB:</label>
    <input type="date" id="dob" name="dob" required>
  </div>
  
  <div class="form-group">
    <label for="phoneNo">Phone Number:</label>
    <input type="tel" id="phoneNo" name="phoneNo" required>
  </div>
  
  <div class="form-group">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
  </div>
  
  <div class="form-group">
    <label for="degree">Degree:</label>
    <select id="degree" name="degree" required>
      <option value="">--Select Degree--</option>
      <option value="BE">BE</option>
      <option value="BTech">BTech</option>
      <option value="ME">ME</option>
      <option value="MTech">MTech</option>
      <option value="BCA">BCA</option>
      <option value="MCA">MCA</option>
      <option value="BBA">BBA</option>
      <option value="MBA">MBA</option>
    </select>
  </div>
  
  <div class="form-group">
    <label for="teamName">Team Name:</label>
    <input type="text" id="teamName" name="teamName" required>
  </div>
  
  <div class="form-group">
    <label for="skills">Skills:</label>
    <input type="text" id="skills" name="skills" required>
  </div>
  
  <div class="form-group">
    <label for="joiningDate">Date of Joining:</label>
    <input type="date" id="joiningDate" name="joiningDate" required>
  </div>
  
  <div class="form-group">
    <label for="payGrade">Pay Grade:</label>
    <select id="payGrade" name="payGrade" required>
      <option value="">--Select Pay Grade--</option>
      <option value="A">A</option>
      <option value="B">B</option>
      <option value="C">C</option>
      <option value="D">D</option>
      <option value="E">E</option>
      <option value="F">F</option>
      <option value="G">G</option>
    </select>
  </div>
  
  <div class="form-group">
    <label for="role">Role:</label>
    <select id="role" name="role" required>
      <option value="">--Select Role--</option>
      <option value="Manager">Manager</option>
      <option value="Operator">Operator</option>
      <option value="QA">QA</option>
      <option value="Assembler">Assembler</option>
    </select>
  </div>
  <div class="form-group">
    <label for="mentor">Reporting To:</label>
    <input type="text" id="mentor" name="mentor" required>
  </div>
  
  <button type="submit">Submit</button>
</form>

<div id="formStatus"></div>
</div>
</div>
</body>
</html>