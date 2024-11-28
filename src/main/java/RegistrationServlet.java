

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
	    String password = request.getParameter("password");  // Consider hashing the password
	    String voterName = request.getParameter("voter_name");
	    String aadhar = request.getParameter("aadhar");
	    String state = request.getParameter("state");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinevotingsystem", "root", "shouvik");
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user (username, password, voter_name, aadhar, state) VALUES (?, ?, ?, ?, ?)")) {
               
               pstmt.setString(1, username);
               pstmt.setString(2, password);
               pstmt.setString(3, voterName);
               pstmt.setString(4, aadhar);
               pstmt.setString(5, state);
               pstmt.executeUpdate();
               
               response.sendRedirect("login.jsp");
           } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp");
        }
	}

}
