

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.*;
import java.sql.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinevotingsystem", "root", "shouvik");
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM user WHERE username=? AND password=?")) {
               pst.setString(1, username);
               pst.setString(2, password);
               ResultSet rs = pst.executeQuery();

               if (rs.next()) {
                   HttpSession session = request.getSession();
                   session.setAttribute("username", username);
                   session.setAttribute("userId", rs.getInt("user_id"));
                   response.sendRedirect("welcome.jsp");
               } else {
                   request.setAttribute("errorMessage", "Invalid username or password");
                   RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                   dispatcher.forward(request, response);
               }
         } catch (SQLException e) {
              e.printStackTrace();
               response.sendRedirect("login.jsp");
         }
	}

}
