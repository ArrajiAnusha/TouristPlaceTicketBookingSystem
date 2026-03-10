package touristplacesticketbookingsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	        String email = req.getParameter("em");
	        String password = req.getParameter("pass");

	        String query = "SELECT * FROM touristplace.user_table WHERE email=? AND password=?";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307?user=root&password=root");

	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, email);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                int userId = rs.getInt("user_id");  

	           
	                HttpSession session = req.getSession();
	                session.setAttribute("user_id", userId);

	
	                PrintWriter pw = resp.getWriter();
	                pw.println("<html>"
	                        + "<head><meta charset='UTF-8'><title>User Dashboard</title></head>"
	                        + "<link rel='stylesheet' href='./user.css'>"
	                        + "<body>"
	                        + "<h1>Welcome, User!</h1>"
	                        + "<a href='userviewplace.html'><button>View & Book Tourist Places</button></a><br><br>"
	                        + "<a href='booking.html'><button>My Bookings</button></a>"
	                        + "</body></html>");
	            } else {
	                resp.setContentType("text/html");
	                PrintWriter pw = resp.getWriter();
	                pw.println("<html><body><h3>Invalid credentials or not a user.</h3></body></html>");
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	


