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

import dao.BranchDTO;
import dao.CustomerDTO;
import dao.EmployeeDTO;
import dao.adminDTO;
import models.Client;
import models.Employee;
import models.Order;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CustomerDTO crDTO;
	@EJB
	private BranchDTO bDTO;
	@EJB
	private EmployeeDTO EDTO;
	@EJB
	private adminDTO aDTO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String param_action = request.getParameter("action");
		String tableStr = new String();
		
		switch(param_action) {
		//gets customer usage list from dto and sends to the jsp.
	case "GetUsersCustUsage" :
	{
		List<Client> clientlist = crDTO.allClients();
		
		HttpSession session = request.getSession();
		session.setAttribute("clientlist", clientlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Custusage.jsp");
		dispatcher.forward(request, response);
	}
	break;
	//shows customer usage list in jsp after getting them ffrom DTO
	case "CustUsagelist":
	{
		String idstring = request.getParameter("ClientID");
		
		try {
			int ClientID = Integer.parseInt(idstring);
			
			Client aut = EDTO.getCustomerUsageByClientID(ClientID);
			tableStr += "<table border='1'>";
			tableStr += "<tr><td>ID</td><td>Times Collection Made</td><td>Times Delivery Made</td></tr>";
			
			for(int i =0; i < aut.getCustomerusages().size(); i++)
			{
				tableStr += "<tr><td>" + String.valueOf(aut.getCustomerusages().get(i).getId()) + "</td>" + "<td>"
						+ aut.getCustomerusages().get(i).getTimes_collection_made()+ "</td>" + "<td>" + 
						aut.getCustomerusages().get(i).getTimes_delivery_made() + "</td></tr>";
			}
			
			tableStr += "</table>";
		}
		catch(Exception e)
		{
			tableStr += e.toString();
		}
		
	}
	break;
	//gets input from jsp and sends to DTO to create invoice.
	case "CreateInvoice":
	{
		String Client = request.getParameter("clientID");
	    int clientID = Integer.parseInt(Client);
		String Amount = request.getParameter("Amount");
	    int Ammount = Integer.parseInt(Amount);
		EDTO.CreateInvoice(clientID,Ammount);
		tableStr += " <br/> <strong> Invoice Created</strong></br><a href='index.html'>Home</a><br/>";	
		

	}
	break;
	//gets info from jsp to add to the database by sending to the DTO.
	case "loginEmployee":
	{
		
		String databaseUsername = "";
		String databasePassword = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee e = new Employee();
		List <Employee> Employeelist = aDTO.allEmployee();
		int is = 1;
		for(int i = 0; i < Employeelist.size(); i++)
		{
			databaseUsername = String.valueOf(Employeelist.get(i).getUsername());
			databasePassword = String.valueOf(Employeelist.get(i).getPassword());
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
	//Gets the data from the jsp and sends to DTO to assign crate a shelf and a warehouse.
	case "AmendCrate" :{
		String Crate = request.getParameter("CrateID");
	    int CrateID = Integer.parseInt(Crate);
		String Warehouse = request.getParameter("WarehouseID");
	    int WarehouseID = Integer.parseInt(Warehouse); 
	    String Shelf = request.getParameter("Shelf");
	    EDTO.AssignShelf(CrateID,WarehouseID,Shelf);
	}
	break;
	case "GetProccessedOrders": {
		List<Order> orderlist = EDTO.allProccessedOrders();
		HttpSession session = request.getSession();
		session.setAttribute("orderlist", orderlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/OrderListEmployee.jsp");
		dispatcher.forward(request, response);
	}
	break;
	case "AllOrders": {
		List<Order> orderlist = EDTO.allOrders();
		HttpSession session = request.getSession();
		session.setAttribute("orderlist", orderlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/AllOrdersList.jsp");
		dispatcher.forward(request, response);
	}
	break;
	default:
	break;
	}
	response.setContentType("text/html;charset=UTF-8");
	
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<title> Packfords Storage company Prototype </title>");
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
