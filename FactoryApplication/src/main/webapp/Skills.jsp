<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head >
	<meta charset="UTF-8">
	<title>Products</title>
</head>
<body >
<tiles:insertAttribute name="header" /> 
   <tiles:insertAttribute name="menu" />    
	<div style="margin-left: 200px;">
	<div id="menuChoice">
	<ul>
	  <s:iterator value="skills">
	    <li><s:property /></li>
	  </s:iterator>
	</ul>
	</div>
	</div>
	
</body>
</html>