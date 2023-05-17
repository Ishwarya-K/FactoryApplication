<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head >
	<meta charset="UTF-8">
	<title>Products</title>
	<link rel="stylesheet" type="text/css" href="Style.css">
	<script type="text/javascript" src="/FactoryApplication/js/Product.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	$(document).ready(function() {
	    $('#product-form').submit(function(event) {
	      event.preventDefault();
	      var formData = $(this).serialize();
	      $.ajax({
	        type: 'POST',
	        url: '/FactoryApplication/products/addProduct.action',
	        data: formData,
	        success: function(result) {
	        	console.log("result : "+result);
	        	document.getElementById("product-form").reset();
	        	var status = document.getElementById("formStatus");
	        	if(result=="error"){
	        		status.innerHTML="<center><p style='color:red;'>Failed to add product !</p><center>"
	        	}
	        	else{
	        		status.innerHTML="<center><p style='color:green;'>Product added successfully !</p><center>"
	        	}
	        },
	        error: function() {
	            alert('Error occurred while adding product');
	        	//var status = document.getElementById("formStatus");
	        	//status.innerHTML="<center><p style='color:red;'>Failed to add product !</p><center>"
	      
	        }
	      });
	    });
	  });

	</script>
</head>
<body >
<tiles:insertAttribute name="header" /> 
   <tiles:insertAttribute name="menu" />
<div style="margin-left: 200px;">
	<div id="bodyContent" >
		<form id="product-form" style='align-items: center;'>
  		<div class="form-group">
		    <label for="productName">Product Name : </label>
		    <input type="text" id="productName" name="productName" required>
  		</div>
	   <div class="form-group">
	    	<label for="category">Category : </label>
		    <select id="category" name="category" required>
		      <option value="Mobile devices">Mobile Devices</option>
		      <option value="Desktop">Desktop</option>
		      <option value="Battery">Battery</option>
		    </select>
	   </div>
  	   <div class="form-group">
		    <label for="availableQuantity">Available Quantity : </label>
		    <input type="number" id="availableQuantity" name="availableQuantity" required>
  	   </div>
  	   <div class="form-group">
		    <label for="price">Price : </label>
		    <input type="number" id="price" name="price" required>
  		</div>
  		<div class="form-group">
		    <label for="workingHrs">Working Hours : </label>
		    <input type="number" id="workingHrs" name="workingHrs" required>
  		</div>
	  	<div class="form-group">
		    <label for="managerId">Manager ID : </label>
		    <input type="number" id="managerId" name="managerId" required>
	  	</div>
	  	<div class="form-group">
		    <label for="skillString">Skills : </label>
		    <input type="text" id="skillString" name="skillString" required>
	  	</div>
	  	<div class="form-group">
		    <label for="spares">Spare : </label>
		    <input type="text" id="spares" name="spares" required>
	  	</div>
	  	<div class="form-group">
		    <label for="spareQuantity">Spare Quantity : </label> 
		    <input type="text" id="spareQuantity" name="spareQuantity" required>
	  	</div>
  		<button type="submit">Submit</button>
	</form>
<div id="formStatus"></div>
</div>
</div>
</body>
</html>