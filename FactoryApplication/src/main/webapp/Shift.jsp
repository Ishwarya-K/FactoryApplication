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
	<script type="text/javascript" src="/FactoryApplication/js/Shift.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body onload="loadShiftDetails()">
<tiles:insertAttribute name="header" /> 
   <tiles:insertAttribute name="menu" />  
	<div style="margin-left: 200px;"><center>
	<div id="bodyContent">
	<h3>Shift Details</h3>
  	<div id="tableContent"></div>
	</div>
	</div>
 	</center>
</body>
</html>