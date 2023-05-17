<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head >
	<meta charset="UTF-8">
	<title>Products</title>
	<!-- <link rel="stylesheet" type="text/css" href="Style.css"> -->
</head>
<body >
<tiles:insertAttribute name="header" />  
<center><table>
		<thead>
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Category</th>
				<th>Details</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="productList">
		<tr>
		<td><s:property value="productId"/></td>
		<td><s:property value="productName"/></td>
		<td><s:property value="category"/></td>
		<td><a href="<s:url action='viewProduct'><s:param name='productId' value='%{productId}'/></s:url>">view</a></td>
		</tr>
		</s:iterator>
		</tbody>
		
	</table>	
	</center>
</body>
</html>
