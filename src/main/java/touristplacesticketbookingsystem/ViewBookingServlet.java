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

public class ViewBookingServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            resp.sendRedirect("login.html");
            return;
        }

        int userId = (int) session.getAttribute("user_id");

        PrintWriter pw = resp.getWriter();

        pw.println("<html><head><link rel='stylesheet'href='./ticket.css'></head><body><h1>My Bookings</h1>"
       +"<table border='1' cellpadding='10'><tr><th>Place</th><th>Location</th><th>Tickets</th><th>Total</th><th>Date</th></tr>");

        try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3307/touristplace", "root", "root");
            PreparedStatement ps = con.prepareStatement(
                "SELECT p.name, p.location, b.tickets, b.total, p.date FROM touristplace.ticket_book b JOIN touristplace.place_table p ON b.place_id = p.place_id WHERE b.user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pw.println("<tr><td>" + rs.getString("name") + "</td>"
               +"<td>" + rs.getString("location") + "</td>"
               +"<td>" + rs.getInt("tickets") + "</td>"
               +"<td>" + rs.getDouble("total") + "</td>"
               +"<td>" + rs.getDate("date") + "</td></tr>");
            }
            pw.println("</table><body></html>");
            pw.println("<br><button><a href='login.html'>Back to Dashboard</a></button>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<html><body><h3> Error: " + e.getMessage() + "</h3></body></html>");
        }
	}
	

}
