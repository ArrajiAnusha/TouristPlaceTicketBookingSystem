package touristplacesticketbookingsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AllUserView extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 PrintWriter pw = resp.getWriter();
	        HttpSession session = req.getSession(false);

	        if (session == null || session.getAttribute("user_id") == null) {
	            resp.sendRedirect("admin.html");
	            return;
	        }

	        pw.println("<html><head><title>All Bookings</title></head><link rel='stylesheet' href='./alluserview.css'><body>");
	        pw.println("<h1> All User Bookings</h1>");
	        pw.println("<table border='1' cellpadding='10'>");
	        pw.println("<tr><th>User Email</th><th>Place Name</th><th>Tickets</th><th>Price</th><th>Total</th></tr>");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/touristplace", "root", "root");

	            String sql = "SELECT  u.email, p.name, b.tickets, b.price, b.total " +
	                         "FROM touristplace.ticket_book b " +
	                         "JOIN touristplace.user_table u ON b.user_id = u.user_id " +
	                         "JOIN touristplace.place_table p ON b.place_id = p.place_id";

	            PreparedStatement ps = con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
//	                int bookingId = rs.getInt("id");
	                String userEmail = rs.getString("email");
	                String placeName = rs.getString("name");
	                int tickets = rs.getInt("tickets");
	                double price = rs.getDouble("price");
	                double total = rs.getDouble("total");

	                pw.println("<tr>"
//	                pw.println("<td>" + bookingId + "</td>");
	                +"<td>" + userEmail + "</td>"
	                +"<td>" + placeName + "</td>"
	                +"<td>" + tickets + "</td>"
	                +"<td>₹" + price + "</td>"
	                +"<td>₹" + total + "</td>"
	                +"</tr>");
	            }

	            pw.println("</table>");
	            pw.println("<br><a href='admin.html'><button>Back to Admin Dashboard</button></a>");
	            pw.println("</body></html>");
	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<p style='color:red;'>❌ Error while fetching bookings.</p>");
	        }
	}
	

}
