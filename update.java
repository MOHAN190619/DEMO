

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import demo.connection;

/**
 * Servlet implementation class update
 */
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
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
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
String name=request.getParameter("user");
		String phonenumber=request.getParameter("ph");
		String address=request.getParameter("add");
		String productname=request.getParameter("proname");
		 int price=Integer.parseInt(request.getParameter("price"));
		int quntity=Integer.parseInt(request.getParameter("qunt"));
		
		int total=price*quntity;
				
		try     
		{                
			String query = "update products set `PHONE NUMBER`=?, `ADDRESS`=?, `PRODUCT NAME`=?, `QUNTITY`=?, `PRICE`=?, `TOTAL`=? where `NAME`=?";

		
         PreparedStatement ps=connection.getConnection().prepareStatement(query);
         
         
         ps.setString(1, phonenumber);
         ps.setString(2, address);
         ps.setString(3,productname);
			ps.setInt(4, quntity);
			ps.setInt(5,price);
			ps.setDouble(6,total);
			ps.setString(7, name);         
         
           if(ps.executeUpdate()>0)
           {
 	          RequestDispatcher rd=request.getRequestDispatcher("search.html");
 	          rd.include(request,response);
 	          out.print("<script>alert(\"order placed sucessfully\")</script>");
 	         
           }
           else {
        	   RequestDispatcher rd=request.getRequestDispatcher("orderdetails.html");
  	          rd.include(request,response);
  	        out.print("<p style=text-align:center;color:red;>New Details not  Updated</p>");
  	          
           }
		}
           catch(Exception ex)
   		{
   	      System.out.println(ex);
   		}
	

		}
}
		

