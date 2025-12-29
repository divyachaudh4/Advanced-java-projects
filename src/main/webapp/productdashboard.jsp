<%@page import="com.bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style5.css">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>Welcome to product Application</h1>

 <% User user = (User)request.getAttribute("loggeduser"); %>
 
 <% if(user != null){ %>
 <span>Welcome<%=user.getName() %></span><br><br>
 <%} %>
 
<button><a href="addnewproduct.jsp">Add New Product</a></button><br><br>
<table style="border: 1px solid black">
<tr>
<th>PId</th>
<th>PName</th>
<th>PQty</th>
<th>Price</th>
<th>Operations</th>
</tr>

<% ArrayList<Product> allproduct =(ArrayList)request.getAttribute("allproduct");%>
<% for(Product p :allproduct){ %>

<tr>
<td><%=p.getPid() %></td>
<td><%=p.getPname() %></td>
<td><%=p.getPqty() %></td>
<td><%=p.getPrice() %></td>
<td>
<a href="getproductbyid?pid=<%=p.getPid() %>"><button>Edit</button></a>
<a href="deleteproduct?pid=<%=p.getPid() %>" onclick="return confirm('Are you sure to delete this record' );"><button>Delete</button></a>
</td>
</tr>
<%} %>
</table>
</body>
</html>