<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/admin.css">
    <title>Admin Dashboard</title>
</head>
<body>
<h1>Admin Dashboard</h1>
<%@ page import="java.sql.*" %>
<%
ResultSet rs = null;
Connection conn = null;
PreparedStatement ps = null;
String query = "select * from parties";
try {
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinevotingsystem", "root", "shouvik");
    ps = conn.prepareStatement(query);
    rs = ps.executeQuery();
%>
<table>
    <tr>
        <th>Party ID</th>
        <th>Party Name</th>
        <th>Vote Count</th>
    </tr>
<%
    while(rs.next()) {
%>
    <tr>
        <td><%= rs.getInt(1) %></td>
        <td><%= rs.getString(2) %></td>
        <td><%= rs.getInt(3) %></td>
    </tr>
<%
    }
} catch(Exception e) {
    out.print(e);
} finally {
    if (rs != null) try { rs.close(); } catch(SQLException e) {}
    if (ps != null) try { ps.close(); } catch(SQLException e) {}
    if (conn != null) try { conn.close(); } catch(SQLException e) {}
}
%>
</table>
</body>
</html>