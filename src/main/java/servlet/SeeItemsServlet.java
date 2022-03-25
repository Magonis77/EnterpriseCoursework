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
import models.Collection;
import models.CollectionItem;
import models.Crate;
import models.Itemslist;
import models.Order;

/**
 * Servlet implementation class SeeItemsServlet
 */
@WebServlet("/SeeItemsServlet")
public class SeeItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private EmployeeDTO EDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//gets the id of the crate and gets the itemslist.
		String code = (String) request.getParameter("code");
		int CollectionNumber = Integer.parseInt(code);
		List<CollectionItem> itemslistcolection = EDTO.getItemsList(CollectionNumber);
		if(itemslistcolection != null) {
		HttpSession session = request.getSession();
		session.setAttribute("itemslist", itemslistcolection);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/CollectionItemsListView.jsp");
		dispatcher.forward(request, response);
		}
		else {
			List<Itemslist> itemslistcrate = EDTO.getcrateItemsList(CollectionNumber);
			HttpSession session = request.getSession();
			session.setAttribute("itemslist", itemslistcrate);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/CollectionItemsListView.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
