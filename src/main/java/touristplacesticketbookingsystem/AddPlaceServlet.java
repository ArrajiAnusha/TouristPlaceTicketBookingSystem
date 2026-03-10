package touristplacesticketbookingsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPlaceServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
        String location = req.getParameter("location");
        double price = Double.parseDouble(req.getParameter("price"));
        String date = req.getParameter("date"); 
        String description = req.getParameter("description");
        String q1="insert into touristplace.place_table values(?,?,?,?,?,?)";
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307?user=root && password=root");
			PreparedStatement ps=con.prepareStatement(q1);
			ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, location);
            ps.setDouble(4, price);
            ps.setDate(5, Date.valueOf(date));
            ps.setString(6, description);
            ps.executeUpdate();
            
            PrintWriter pw= resp.getWriter();
			pw.println("<html>"
					+ "<body>"
					+ "<h3>Place added successfully!</h3>"
					+ "<a href='admin.html'>Back to Dashboard</a>"
					);
    	 }  catch (ClassNotFoundException | SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	

}
