package touristplacesticketbookingsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingTicketServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	        resp.setContentType("text/html");
	        PrintWriter pw = resp.getWriter();

	        HttpSession session = req.getSession(false);
	        if (session == null || session.getAttribute("user_id") == null) {
	            resp.sendRedirect("login.html");
	            return;
	        }

	        try {
//	        	int id=Integer.parseInt(req.getParameter("id"));
	            int userId = (int) session.getAttribute("user_id");
	            int placeId = Integer.parseInt(req.getParameter("place_id"));
	            int tickets = Integer.parseInt(req.getParameter("tickets"));
	            double price = Double.parseDouble(req.getParameter("price"));
	            double total = tickets * price;

	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3307/touristplace", "root", "root");

	            String query = "INSERT INTO touristplace.ticket_book VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setInt(1, userId);
	            ps.setInt(2, placeId);
	            ps.setInt(3, tickets);
	            ps.setDouble(4, price);
	            ps.setDouble(5, total);

	            int rows = ps.executeUpdate();

	            if (rows > 0) {
	                pw.println("<html><head><link rel='stylesheet' href='./total.css'></head><body>"
	                        + "<h1>Booking Successful!</h1>"
	                        + "<p>Tickets Booked: " + tickets + "</p>"
	                        + "<p>Total Amount: " + total + "</p>"
	                        + "<a href='login.html'><button>Back to Dashboard</button></a>"
	                        + "</body></html>");
	            } else {
	                pw.println("<html><body><h3>❌ Booking Failed.</h3></body></html>");
	            }

	            ps.close();
	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<html><body><h3>❌ Error: " + e.getMessage() + "</h3></body></html>");
	        }
	}
	

}
