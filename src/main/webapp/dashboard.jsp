<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ArtBox</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="general_container">
		<h1 class="title">ArtBox</h1>

		<div class="menu">
			<%@include file="menu.jsp"%>
		</div>

			<form action="find"  method="get" class="form_search">
				<input type="text" name="name" value="" placeholder="Search field by name..."> 
				<input type="submit" value="Search" class="pointer">
			</form>
			
			<p class="${type} message_dashboard">${message}</p>

			<table class="table_search">
				<tr>
					<td>#</td>
					<td>Title</td>
					<td>Age, from</td>
					<td>Price</td>
					<td></td>
				</tr>
				<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.getId()}</td>
					<td class="table_name">${product.getName()}</td>
					<td>${product.getAge()}</td>
					<td>${product.getCost()}</td>
					<td>
						<form action="remove" method="GET">
							<input type="hidden" name="id" value="${product.getId()}"> 
							<input type="submit" value="delete" class="btn_delete">
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
	</div>
	<div class="footer">
		<p class="copyright">&#169; GM ArtBoxSystems (Brovary)</p>
	</div>
</body>
</html>