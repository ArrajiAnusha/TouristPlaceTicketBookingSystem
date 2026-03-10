package touristplacesticketbookingsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllPlacesServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
		   PrintWriter pw= resp.getWriter();
					pw.println("<html>"
							+ "<head><meta charset='UTF-8'><title>Admin Dashboard</title></head>"
							+ "<link rel='stylesheet' href='./availtourplace.css'>");
							pw.println( "<body>");
							pw.println( "<h1>Available Tourist Places</h1>");
							pw.println( "<table border='1' cellpadding='18'>");
							pw.println( "<tr><th>place_id</th><th>Name</th><th>Location</th><th>Price</th><th>Date</th><th>Description</th></tr>");
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307?user=root && password=root");
						Statement stmt = con.createStatement();
			            ResultSet rs = stmt.executeQuery("SELECT * FROM touristplace.place_table");
			            while (rs.next()) {
							pw.println( "<tr><td>" + rs.getInt("place_id") + "</td>"
									+ "<td>" + rs.getString("name") + "</td>"
									+ "<td>" + rs.getString("location") + "</td>"
									+ "<td>"+ rs.getDouble("price") +"</td>"
									+ "<td>"+ rs.getDate("date") +"</td>"
									+ "<td>"+ rs.getString("description") +"</td></tr>"
									);
							
			            }
			            pw.println("</table>");
			            pw.println("<a href='admin.html'><button>Back to Dashboard</button></a>");
			            pw.println( "<a href='adminbook.html'><button>All User Booking Data</button></a>");
					}
					 catch (ClassNotFoundException | SQLException e) {
				 			// TODO Auto-generated catch block
				 			e.printStackTrace();
				 		}
									
			            }
						
	}

	


