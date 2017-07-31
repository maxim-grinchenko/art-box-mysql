<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ArtBox</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="general_container">
		<h1 class="title">ArtBox</h1>

		<div class="menu">
			<%@include file="menu.jsp"%>
		</div>

		<div class="discription">
		<p class="block_before_register ${hidden}"><b>Before using this resource you need to login or register</b></p>
		<p class="${block_message_register} ${green}"><b>${success_message_register}</b></p>
		
			<p>Description ArtBox web-service:</p>
			<p>The service is used for create, delete and search positions goods (further positions).</p>
			<p>The page "<span class="uppercase">add</span>" is used to added positions.</p>

			<ul>
				<li>field "<b>Title</b>" - name positions, can compose of letters, numbers, signs;</li>
				<li>field "<b>Age</b>" - recommended age of use, should compose only of numbers;</li>
				<li>field "<b>Cost</b>" - price, should compose only of numbers;</li>
			</ul>
			
			<p>Button "<b>Save</b>" - saves the inputed positions to the database. </p>

			<p>The page "<span class="uppercase">dashboard</span>" is used to search for positions by title (name). </p>

			<p>When the search query matches, the result is displayed under the search panel, where the sequence number in the
				database, name, age, cost is displayed.</p>

			<p>The "<b>delete</b>" button (last column) removes the position from the database.</p>

			<hr>
			<p>Terms and conditions of use:</p>

			<p class="price">
				Trial period - 14 days <br> The full version - $ 650.00
			</p>
			<hr>
			<p>If you want to purchase this web-service, you need to send your money by email: 
				<a href="mailto:maxima@artbox.com?subject=Purchase ArtBox" class="mail">maxima@artbox.com</a><br> 
				<span class="note">*All money from the sale goes to charity </span></p>
		</div>
	</div>
	


	<div class="footer">
		<p class="copyright">&#169; GM ArtBoxSystems (Brovary)</p>
	</div>
	

</body>
</html>