package touristplacesticketbookingsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateAccount extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String role=req.getParameter("role");
		String p1="insert into touristplace.user_table values(?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307?user=root && password=root");
			PreparedStatement ps=con.prepareStatement(p1);
			ps.setInt(1, id);
			ps.setString(2,name);
			ps.setString(3,email);
			ps.setString(4, password);
			ps.setString(5,role);
			ps.executeUpdate();
			RequestDispatcher rd =req.getRequestDispatcher("login.html");
			rd.forward(req, resp);
            HttpSession session = req.getSession();
            session.setAttribute("user_id", id); 
	}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
