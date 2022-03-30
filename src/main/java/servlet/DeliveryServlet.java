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
import dao.DeliveryDTO;
import models.Collection;
import models.Crate;
import models.Delivery;
import models.Itemslist;

/**
 * Servlet implementation class DeliveryServlet
 */
@WebServlet("/DeliveryServlet")
public class DeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private DeliveryDTO dDTO;
    @EJB
    private CustomerDTO crDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryServlet() {
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
		//gets client crates by the client ID from DTO and then sends to the jsp.
	case "getclientcrates" :
	{
			String date = request.getParameter("DeliveryDate");
			String Time = request.getParameter("DeliveryTime");
			String Frequency = request.getParameter("Frequency");
			String Client = request.getParameter("ClientID");
			int ClientID = Integer.parseInt(Client);
			List<Crate> cratelist = crDTO.allCratesbyClientID(ClientID);
			HttpSession session = request.getSession();
			session.setAttribute("allcrates", cratelist);
			session.setAttribute("DeliveryDate",date);
			session.setAttribute("DeliveryTime",Time);
			session.setAttribute("Frequency",Frequency);
			session.setAttribute("ClientID", ClientID);

			RequestDispatcher dispatcher = request.getRequestDispatcher("deliverybox.jsp");
			dispatcher.forward(request, response);
	}
	break;
	//gets the action wholecrate meaning that the delivery needs to be of the whole create so sends info to DTO to create the delivery request.
	case "WholeCrate" :
	{
		String date = request.getParameter("DeliveryDate");
		String Time = request.getParameter("DeliveryTime");
		String Frequency = request.getParameter("Frequency");
		String Client = request.getParameter("ClientID");
		int ClientID = Integer.parseInt(Client);
		String cbcrate = request.getParameter("cbxCrate");
		int Crate = Integer.parseInt(cbcrate);
		dDTO.createDeliveycrate(date, Time, Frequency, ClientID);
		dDTO.assigncratedelivery(ClientID, Crate, date);
		tableStr += "<a href='index.html'>Home</a><br/>";
	}
	break;
	//makes the delivery request of the specific items and sends the client to the UI(jsp) where he will be able to select what items to deliver.
	case "SpecificItems" :
	{
		String date = request.getParameter("DeliveryDate");
		String Time = request.getParameter("DeliveryTime");
		String Frequency = request.getParameter("Frequency");
		String Client = request.getParameter("ClientID");
		int ClientID = Integer.parseInt(Client);
		String cbcrate = request.getParameter("cbxCrate");
		int Crate = Integer.parseInt(cbcrate);
		dDTO.createDeliveyspecificitems(date, Time, Frequency, ClientID);
		dDTO.assigncratedelivery(ClientID, Crate, date);
		List<Itemslist> itemslist = dDTO.getItemsbyCrateID(Crate);
		int DeliveryID = dDTO.getlatestdeliveryadd();
		HttpSession session = request.getSession();
		session.setAttribute("DeliveryID", DeliveryID);
		session.setAttribute("itemslist", itemslist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("deliveryitem.jsp");
		dispatcher.forward(request, response);
		tableStr += "<a href='index.html'>Home</a><br/>";
	}
	break;
	//adds the selected item from the UI to the delivery request.
	case "deliverthisitem":{
		String code = (String) request.getParameter("code");
		int ItemID = Integer.parseInt(code);
		int DeliveryID = dDTO.getlatestdeliveryadd();
		dDTO.adddeliveryitems(ItemID, DeliveryID);
		tableStr += "<a href='index.html'>Home</a><br/>";
	}
	break;
	//shows all deliveries (gets the deliveries from DTO and sends them to the jsp where they will be added to the table one by one.
	case "ShowallDeliveries":{
		List<Delivery> deliverylist = dDTO.allDeliveries();
		HttpSession session = request.getSession();
		session.setAttribute("deliverylist", deliverylist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/AllDeliveriesList.jsp");
		dispatcher.forward(request, response);
	}
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
