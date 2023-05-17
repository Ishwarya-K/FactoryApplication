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
	<script type="text/javascript" src="/FactoryApplication/js/Product.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body >
<tiles:insertAttribute name="header" /> 
   <tiles:insertAttribute name="menu" />
	<center>
	<div style="margin-left: 200px;">
	<div id="bodyContent" >
	<h3>Our Products</h3>
	<div class="image-row">
		<img src="/FactoryApplication/images/phone.jpg" alt="phone">
		<img src="/FactoryApplication/images/laptop.jpg" alt="laptop">
		<img src="/FactoryApplication/images/smartWatch.jpg" alt="smartWatch">
	</div>
	</div>
	</div></center>
</body>
</html>