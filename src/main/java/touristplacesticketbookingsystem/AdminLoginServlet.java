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

public class AdminLoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
//		String role=req.getParameter("role");
		String q1="select * from touristplace.user_table where email=? AND password=? AND role='admin'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307?user=root && password=root");
			PreparedStatement ps=con.prepareStatement(q1);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				PrintWriter pw= resp.getWriter();
				pw.println("<html>"
						+ "<head><meta charset='UTF-8'><title>Admin Dashboard</title></head>"
						+ "<link rel='stylesheet' href='./welcomeadmin.css'>"
						+ "<body>"
						+ "<h1>Welcome, Admin!</h1>"
						+ "<div><a href='addplace.html'><button>Add Tourist Place</button></a>"
						+ "<a href='viewplaces.html'><button id='a1'>View All Places</button></a></div>"
						+ "</body></html>");
			} else {
				resp.setContentType("text/html");
				PrintWriter pw = resp.getWriter();
				pw.println("<html><body><h3>Invalid credentials or not an admin.</h3></body></html>");
			}

				
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
