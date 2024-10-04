

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import demo.connection;

/**
 * Servlet implementation class search
 */
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
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
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("user");
		try {
			String query="select * from products where NAME=?";
			PreparedStatement ps;
			ps=connection.getConnection().prepareStatement(query);
			
		ps.setString(1, name);
			 ResultSet rs=ps.executeQuery();
			 if(rs.next())
	            {	 
				
				 
		          String phonenumber=rs.getString(2);
		          String address=rs.getString(3);
		          String product=rs.getString(4);
		          String quntity=rs.getString(5);
		          String price=rs.getString(6);
		          String total=rs.getString(7);
			         
		          
		          
		          RequestDispatcher rd=request.getRequestDispatcher("search.html");
		         
		          rd.include(request,response);
		         
		pw.print("<form action=\"update\" method=\"post\">"
				+"	name<input type=\"text\" name=\"user\" value="+name+">	<br>"  	
		
		+ "phone number<input type=\"text\" name=\"ph\" value="+phonenumber+"> <br>");
		
 
		pw.print(" Address<input type=\"text\" name=\"add\" value="+address+"> <br>"
		);
		pw.print(" PRODUCT NAME<input type=\"text\" name=\"proname\" value="+product+"> <br>"
				);
		pw.print(" QUNTITY<input type=\"number\" name=\"qunt\" value="+quntity+"> <br>"
				);
		pw.print(" PRICE<input type=\"number\" name=\"price\" value="+price+"> <br>"
				);
		pw.print(" TOTAL<input type=\"Double\" name=\"total\" value="+total+"> <br>"
				
				);
		
	        
		pw.print(" <input type=\"submit\" value=\"bill\"formaction=\"update\" method=\"post\">"
				);   
		pw.print(" <input type=\"button\" value=\"view order summary\"onclick=\"window.location.href='order.html'\">"
				);   
	            }
		
			 
			 
	 		 else
	         {
	        RequestDispatcher rd=request.getRequestDispatcher("orderdetails.html");
		          rd.include(request,response);
		         pw.print("<p style=text-align:center;color:red;>name doesnot exist</p>");
	         }
			}
		
			
			catch(Exception ex)
			{
		      System.out.println(ex);
			}
	}



}