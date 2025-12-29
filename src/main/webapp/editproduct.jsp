<%@page import="com.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style6.css">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>Edit Product details</h1>
<% Product p = (Product)request.getAttribute("existingproduct");%>

<form action="editproduct" method="post">
<label>PId</label>
<input type="number" name="pid" value="<%=p.getPid()%>"><br><br>

<label>Pname</label>
<input type="text" name="pname" value="<%=p.getPname()%>"><br><br>

<label>PQty</label>
<input type="number" name="pqty" value="<%=p.getPqty()%>"><br><br>

<label>Price</label>
<input type="text" name="price" value="<%=p.getPrice()%>"><br><br>

<input type="submit" value="Edit Product">
</form>

</body>
</html>