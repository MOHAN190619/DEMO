

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import demo.connection;

/**
 * Servlet implementation class order
 */
public class order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String productname=request.getParameter("proname");
		 int price=Integer.parseInt(request.getParameter("price"));
		int quntity=Integer.parseInt(request.getParameter("qunt"));
		
		int total=price*quntity;
	
		System.out.println(total);
		PreparedStatement ps;
String q="INSERT INTO demo (`PRODUCT NAME`,`QUNTITY`,`PRICE`,`TOTAL`)values(?,?,?,?)";
		try {
			ps=connection.getConnection().prepareStatement(q);
			ps.setString(1,productname );
			ps.setInt(2,price);
			ps.setInt(3, quntity);
			ps.setDouble(4,total);
			if(ps.executeUpdate()>0) {
				response.sendRedirect("search.html");
		out.println(total);
			}
			else {
				out.println("error please try again");
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
