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
</head>
<body >
<div style="background-color:#F88379;height:50px;padding-top: 1px;padding-bottom: 25px;">
	<center><h1 >Factory</h1></center>
</div>
<br><br>
<div class='flex-container' style="background-color:#303030;"><div class='flex-row'>
<div class='flex-item' style="height:100px;">
<center><a href="/FactoryApplication/productHome" style=" font-size: 30px;color:white">Products</a></center>
</div>
</div>
<div class='flex-row'><div class='flex-item' style="height:100px;">
<center><a href="/FactoryApplication/payrollHome" style=" font-size: 30px;color:white;padding-top: 100px;">Payroll</a></center>
</div></div>
<div class='flex-row'><div class='flex-item' style="height:100px;">
<center><a href="/FactoryApplication/shift" style=" font-size: 30px;color:white">Shift</a></center>
</div></div>
</div>
</body>
</html>