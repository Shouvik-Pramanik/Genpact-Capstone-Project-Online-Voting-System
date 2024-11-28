

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String partyName = request.getParameter("partyName");
        if (partyName == null || partyName.trim().isEmpty()) {
            response.sendRedirect("voteError.jsp");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinevotingsystem", "root", "shouvik");
            
            pstmt = conn.prepareStatement("SELECT has_voted FROM user WHERE user_id = ?");
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next() && rs.getBoolean("has_voted")) {
                response.sendRedirect("alreadyVoted.jsp");
                return;
            }

            pstmt = conn.prepareStatement("UPDATE parties SET vote_count = vote_count + 1 WHERE party_name = ?");
            pstmt.setString(1, partyName);
            int updatedRows = pstmt.executeUpdate();

            if (updatedRows > 0) {
                pstmt = conn.prepareStatement("UPDATE user SET has_voted = true WHERE user_id = ?");
                pstmt.setInt(1, userId);
                pstmt.executeUpdate();
                response.sendRedirect("voteConfirmation.jsp");
            } else {
                System.out.println("No party updated, check party name accuracy and case sensitivity.");
                response.sendRedirect("voteError.jsp");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            response.sendRedirect("voteError.jsp");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("SQLException on close: " + ex.getMessage());
            }
        }
	}

}
