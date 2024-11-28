<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    
    <div class="login-container">
    	<h1>Online Voting Portal made by Shouvik</h1>
        <h2>Login to Your Account</h2>
        <form action="LoginServlet" method="post">
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>
        <p>Not registered yet? <a href="register.jsp">Register Here</a></p>
        <button onclick="location.href='adminLogin.jsp'">Admin Login</button>
    </div>
</body>
</html>