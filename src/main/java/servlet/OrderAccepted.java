package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDTO;
import models.Order;

/**
 * Servlet implementation class OrderAccepted
 */
@WebServlet("/OrderAccepted")
public class OrderAccepted extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB 
    private EmployeeDTO EDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAccepted() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("code");
		int OrderNumber = Integer.parseInt(code);
		
		EDTO.AcceptOrder(OrderNumber);
		
		List<Order> orderlist = EDTO.allProccessedOrders();
		HttpSession session = request.getSession();
		session.setAttribute("orderlist", orderlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/OrderListEmployee.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
