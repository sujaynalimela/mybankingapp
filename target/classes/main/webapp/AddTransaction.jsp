<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mybankingapp.controllers.AddTransactionServlet" %>
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
		var amount = $("input[name=amount]").val();
		var type = $("input[name=type]").val();
		var date = $("input[name=date]").val();

		var jsonString = "{\"id\": \"" + id + "\", "
					+ "\"amount\": \"" + amount + "\", "
					+ "\"type\": \"" + type + "\", "
					+ "\"date\": \"" + date + "\"}";

		$.post("AddTransaction", {jsonString: jsonString}, function(data) {
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
		Amount: <input type="text" name="amount"><br>
		Account Type: <input type="text" name="type"><br>
		Date: <input type="text" name="date" placeholder = "yyyy-mm-dd"><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>
