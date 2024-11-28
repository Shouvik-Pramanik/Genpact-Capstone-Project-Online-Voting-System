<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/vote.css">
</head>
<body>
	<form action="VoteServlet" method="post">
    <label for="partyName">Choose a party to vote for:</label>
    <select name="partyName" id="partyName">
        <option value="Indian National Congress">Indian National Congress</option>
        <option value="Bharatiya Janta Party">Bharatiya Janta Party</option>
        <option value="Communist Party of India (Marxist)">Communist Party of India (Marxist)</option>
        <option value="Nationalist Congress Party">Nationalist Congress Party</option>
        <option value="All India Trinamool Congress">All India Trinamool Congress</option>
        <option value="Bahujan Samaj Party">Bahujan Samaj Party</option>
    </select>
    <button type="submit">Vote</button>
</form>
</body>
</html>