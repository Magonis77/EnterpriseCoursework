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

import dao.BranchDTO;
import models.Order;

/**
 * Servlet implementation class OrderProceed
 */
@WebServlet("/OrderProceed")
public class OrderProceed extends HttpServlet {
	private static final long serialVersionUID = 1L;
     @EJB 
     private BranchDTO bDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public OrderProceed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("code");
		int OrderNumber = Integer.parseInt(code);
		
		bDTO.processOrder(OrderNumber);
		
		List<Order> orderlist = bDTO.allProccessOrders();
		HttpSession session = request.getSession();
		session.setAttribute("orderlist", orderlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/BranchOrderList.jsp");
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
