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
			<%@ include file="menu.jsp" %>
		</div>
		
		<div>
	
		<p class="${type} message_add">${message}</p>
		
			<form action="add" method="POST" class="form_add">
			
				<table>
					<tr>
						<td>Title : </td>
						<td><input type="text" name="name" value="" class="input_field" placeholder="Please, input title..."></td>
					</tr>
					<tr>
						<td>Age, from : </td>
						<td><input type="text" name="age" value="" class="input_field" placeholder="Please, input age..."></td>
					</tr>
					<tr>
						<td>Price : </td>
						<td><input type="text" name="cost" value="" class="input_field" placeholder="Please, input price..."></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Save" class="button pointer"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="footer">
		<p class="copyright">&#169; GM ArtBoxSystems (Brovary)</p>
	</div>
</body>
</html>