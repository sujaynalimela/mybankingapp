<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import = "com.mybankingapp.controllers.AddTransactionServlet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction Details</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("form").submit(function(event) {
		event.preventDefault();
		var id = $("input[name=id]").val();

		$.get("AddTransaction", {id: id}, function(data) {
			// parse the JSON string response to a JavaScript object
			console.log(data);
			var data1=data[0], data2=data[1];
			var transactionList = data1;
            console.log(transactionList);
			// display the transaction details in a table
			var table = "<table border='1'><tr><th>ID</th><th>Amount</th><th>Type</th><th>Date</th></tr>";
			for (var i = 0; i < transactionList.length; i++) {
				console.log(transaction);
				var transaction = transactionList[i];
				table += "<tr><td>" + transaction.id + "</td><td>" + transaction.amount + "</td><td>" + transaction.type + "</td><td>" + transaction.date + "</td></tr>";
			}
			table += "</table>";
			$("#tableContainer").html(table);
			$("h3#balanceHeader").text("Total Balance : "+ data2["balance"]);
		});
	});
});
</script>
</head>
<body>
	<h2>Enter Transaction ID:</h2>
	<form>
		ID: <input type="text" name="id"><br>
		<input type="submit" value="Submit">
	</form>
	<div id="tableContainer"></div>
	<h3 id = "balanceHeader"></h3>
</body>
</html>