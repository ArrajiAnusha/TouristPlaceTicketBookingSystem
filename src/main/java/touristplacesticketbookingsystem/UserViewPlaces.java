package touristplacesticketbookingsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserViewPlaces extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw= resp.getWriter();
		pw.println("<html>"
				+ "<body>"
				+ "<h1>Available Tourist Places</h1>"
				+ "<link rel='stylesheet' href='./userviewplace.css'>"
				+ "<table border='1' cellpadding='8'>"
				+ "<tr><th>place_id</th><th>Name</th><th>Location</th><th>Price</th><th>Date</th><th>Description</th></tr>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307?user=root && password=root");
			Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM touristplace.place_table");
            while (rs.next()) {
            	 int placeId = rs.getInt("place_id");
                  double price = rs.getDouble("price");
				pw.println( "<tr><td>" + rs.getInt("place_id") + "</td>"
						+ "<td>" + rs.getString("name") + "</td>"
						+ "<td>" + rs.getString("location") + "</td>"
						+ "<td>"+ rs.getDouble("price") +"</td>"
						+ "<td>"+ rs.getDate("date") +"</td>"
						+ "<td>"+ rs.getString("description") +"</td>"
						+ "<form method='post' action='book'>"
						+ "<input type='hidden' name='id' value='1'> "
						+ "<input type='hidden' name='place_id' value='" + placeId + "'>"
						+ "<input type='hidden' name='price' value='" + price + "'>"
						+ "<p>Tickets:</p> <input type='number' name='tickets' min='1' value='1' required>"
						+ "<button type='submit'>Book</button>"
						+ "</form>"
						+ "</tr>");
	}
            pw.println("</table>");
            pw.println("<button id='a1'><a href='login.html'>Back to Dashboard</a></buton>");
		}
		catch (ClassNotFoundException | SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}

}
