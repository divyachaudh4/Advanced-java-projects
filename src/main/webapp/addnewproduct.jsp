<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style2.css">
</head>
<body>
<div class="container">
<h1>Add New Product</h1>
<form action="addnewproduct" method="post">
<label>PId</label>
<input type="number" name="pid"><br><br>

<label>Pname</label>
<input type="text" name="pname"><br><br>

<label>PQty</label>
<input type="number" name="pqty"><br><br>

<label>Price</label>
<input type="text" name="price"><br><br>

<input type="submit" value="Add New Product">
</form>
</body>
</html>