<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mybankingapp.controllers.AddCustomerServlet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Details</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("form").submit(function(event) {
		event.preventDefault();
		var id = $("input[name=id]").val();
		var name = $("input[name=name]").val();
		var email = $("input[name=email]").val();
		var address = $("input[name=address]").val();
		var phone = $("input[name=phone]").val();

		var jsonString = "{\"id\": \"" + id + "\", "
					+ "\"name\": \"" + name + "\", "
					+ "\"email\": \"" + email + "\", "
					+ "\"address\": \"" + address + "\", "
					+ "\"phone\": \"" + phone + "\"}";

		$.post("AddCustomer", {jsonString: jsonString}, function(data) {
			alert(data);
			window.location.href = "index.jsp";
		});
	});
});
</script>
</head>
<body>
	<h2>Enter Customer Details:</h2>
	<form>
		Id: <input type="text" name="id"><br>
		Name: <input type="text" name="name"><br>
		Email: <input type="text" name="email"><br>
		Address: <input type="text" name="address"><br>
		Phone: <input type="text" name="phone"><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>
