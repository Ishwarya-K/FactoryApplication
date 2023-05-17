<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
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
	    $('#login-form').submit(function(event) {
	      event.preventDefault();
	      var formData = $(this).serialize();
	      $.ajax({
	        type: 'POST',
	        url: '/FactoryApplication/validateLogin.action',
	        data: formData,
	        success: function(response) {
	        	console.log("response : ",response);
	        	console.log("success : ",response.status);
	        	var jsonResponse = JSON.parse(JSON.stringify(response));
	        	const validLogin = jsonResponse.validLogin;
	        	if(validLogin){
	        		console.log("success");
	        		window.location.href = "/FactoryApplication/Home.jsp";
	        	}
	        	else{
	        		console.log("failure");
	        		//window.location.href = "/FactoryApplication/login.jsp";
	        		var errorMsg=document.getElementById("errorMsg");
	        		console.log(errorMsg);
	        		errorMsg.innerHTML="<center><h3 style='color:red'>Login failed</h3></center>";
	        	}
	        	
	        },
	        error: function() {
	          //alert('Error occurred while adding product');
	          var errorMsg = document.getElementById("errorMsg");
	          errorMsg.innerHTML="<center><p style='color:red;'>Incorrect username or password !</p></center>"
	        }
	      });
	    });
	  });
	
	function validateUserId() {
		  var userIdInput = document.getElementById("userId");
		  var userIdValue = userIdInput.value.trim();
		  var userIdError = document.getElementById("userIdError");

		  if (!/^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*@gmail\.com$/.test(userIdValue)) {
		    userIdError.innerHTML = "<p>Please enter a valid Gmail address</p>";
		  } else {
		    userIdError.innerHTML = "";
		  }
		}

	</script>
</head>
<body >
<div style="background-color:#F88379;height:50px;padding-top: 1px;padding-bottom: 25px;">
	<center><h1 >Factory</h1></center>
</div>
<br><br>
<div class='flex-container'><div class='flex-row'><div class='flex-item'>
<center><h2>Login</h2></center>
	<form id="login-form" style='align-items: center;'>
  		<div class="form-group">
		    <label for="userId">User ID : </label><br>
		    <input type="text" id="userId" name="userId" required onblur="validateUserId()">
  		</div>
  		
  	   <div class="form-group">
		    <label for="passwd">Password : </label><br>
		    <input type="password" id="passwd" name="passwd" required>
  	   </div>
  	   <center><div class="form-group" id="userIdError" style="color:red;"></div></center>
  		<button type="submit">Submit</button>
	</form>
	
	<div id="errorMsg"></div>
</div></div></div>
</body>
</html>