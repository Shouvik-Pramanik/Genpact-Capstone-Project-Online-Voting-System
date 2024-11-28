<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register New Account</title>
<link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="register-container">
        <h2>Register New Account</h2>
        <form action="RegistrationServlet" method="post">
            Name: <input type="text" name="voter_name" required><br>
            Aadhar: <input type="number" name="aadhar" required><br>
            State: <input type="text" name="state" required><br>
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>