package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDTO;
import models.Client;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CustomerDTO crDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String param_action = request.getParameter("action");
		String tableStr = new String();
		
		switch(param_action) {
		case "insertUser":
		{
			String firstname = request.getParameter("firstName");
			String lastname = request.getParameter("lastName");
		    String email = request.getParameter("email");
		    String phone = request.getParameter("phonenumber");
		    int phonenumber = Integer.parseInt(phone);
			crDTO.insertClient(firstname,lastname,email,phonenumber);
			
			tableStr += "<a href='CustomerServlet?action=getusers'>Insert Customers Address</a><br/>";
		}
		break;
		case "getusers":
		{
			List<Client> clientlist = crDTO.allClients();
			
			HttpSession session = request.getSession();
			session.setAttribute("clientlist", clientlist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("InsertClientAddress.jsp");
			dispatcher.forward(request, response);
			
		}
		case "insertuseraddress":
		{
			String cbxClient = request.getParameter("ClientID");
			int idClient = Integer.parseInt(cbxClient);
			String addressline1 = request.getParameter("Address1");
			String addressline2 = request.getParameter("Address2");
			String addressline3 = request.getParameter("Address3");
			String addressline4 = request.getParameter("Address4");
			String postcode = request.getParameter("PostCode");
			String city = request.getParameter("City");
			
			crDTO.insertuseraddress(idClient, addressline1,addressline2,addressline3,addressline4,postcode,city);

			tableStr += " <br/><strong>Client address added! </strong>";
		}
		break;
		case "login":
		{
			
			String databaseUsername = "";
			String databasePassword = "";
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Client c = new Client();
			List <Client> clientlist = crDTO.allClients();
			int is = 1;
			for(int i = 0; i < clientlist.size(); i++)
			{
				databaseUsername = String.valueOf(clientlist.get(i).getUsername());
				databasePassword = String.valueOf(clientlist.get(i).getPassword());
				if (username.equals(databaseUsername) && password.equals(databasePassword)) {
					request.setAttribute("users", username);
					response.sendRedirect("index.jsp");
					is = 1;
					break;
			    }
				else {
					is = 0;
				}
			}

			if (is == 0) {	

				tableStr += "<br/><strong>Incorrect password!!</strong>";
				tableStr += "</table>";
				tableStr += "<a href=login.jsp>Try again!</a>";
		    }
			
		}
		break;
		default:
		break;
		}
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Book Store App </title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println(tableStr);
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
