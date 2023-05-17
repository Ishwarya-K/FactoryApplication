<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
	<meta charset="UTF-8">
	<title>Products</title>
	<link rel="stylesheet" type="text/css" href="Style.css">
	<script type="text/javascript" src="/FactoryApplication/js/Product.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
</head>
<body >
<div style="background-color:#F88379;height:75px;padding-top: 1px;padding-bottom: 25px;">
	<center><h1 >Products</h1></center>
	<a href="/FactoryApplication/Home.jsp" style='margin-left:20px;'>Back To Home</a>
	
	</div>
<br><br>
<nav style='margin-top:24px;'>
	<a href="/FactoryApplication/index.jsp">Home</a>
	<a href="javascript:loadProducts()">View Products</a>
	<a href="/FactoryApplication/AddProduct.jsp">Add Product</a>
	<a href="/FactoryApplication/logout.action">Logout</a>
</nav>
<center><h3>No Records found!</h3></center>
</body>
</html>