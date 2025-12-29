<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style7.css">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<% String message= (String)request.getAttribute("Message"); %>
<h1><%= message%></h1>
</body>
</html>