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
import models.Order;

/**
 * Servlet implementation class BranchServlet
 */
@WebServlet("/BranchServlet")
public class BranchServlet extends HttpServlet {
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
    public BranchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param_action = request.getParameter("action");
		String tableStr = new String();
		
		switch(param_action) {
		case "GetOrders": {
			List<Order> orderlist = bDTO.allProccessOrders();
			HttpSession session = request.getSession();
			session.setAttribute("orderlist", orderlist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/BranchOrderList.jsp");
			dispatcher.forward(request, response);
		}
		break;
		case "revieworder":
		{
			
		}
		break;
		case "sendorder":{
			
			
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
