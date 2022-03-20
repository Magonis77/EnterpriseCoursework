package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CollectionDTO;
import dao.CustomerDTO;
import dao.EmployeeDTO;
import models.Collection;
import models.Crate;
import models.Order;

/**
 * Servlet implementation class CollectionServlet
 */
@WebServlet("/CollectionServlet")
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      @EJB
      private CollectionDTO cDTO;
      @EJB
      private EmployeeDTO eDTO;
      @EJB
      private CustomerDTO crDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionServlet() {
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
		case "CollectItems": {

			String date = request.getParameter("CollectionDate");
			String Time = request.getParameter("CollectionTime");
			String Address = request.getParameter("Collectionaddress");
			String Frequency = request.getParameter("Frequency");
			String Client = request.getParameter("ClientID");
			int ClientID = Integer.parseInt(Client);
			cDTO.createCollection(date, Time, Address, Frequency, ClientID);
			int i = cDTO.getlatestcollectionadd();
				HttpSession session = request.getSession();
				session.setAttribute("Address", Address);
				session.setAttribute("collectionID", i);
				RequestDispatcher dispatcher = request.getRequestDispatcher("collectionadditems.jsp");
				dispatcher.forward(request, response);
			
			
			
		}
		break;
		case "Collectcrate":
		{
			String date = request.getParameter("CollectionDate");
			String Time = request.getParameter("CollectionTime");
			String Address = request.getParameter("Collectionaddress");
			String Frequency = request.getParameter("Frequency");
			String Client = request.getParameter("ClientID");
			int ClientID = Integer.parseInt(Client);
			List<Crate> allcrates = crDTO.allCratesbyClientID(ClientID);
				HttpSession session = request.getSession();
				session.setAttribute("CollectionDate",date);
				session.setAttribute("CollectionTime",Time);
				session.setAttribute("Collectionaddress",Address);
				session.setAttribute("Frequency",Frequency);
				session.setAttribute("allcrates", allcrates);
				session.setAttribute("ClientID", ClientID);
				RequestDispatcher dispatcher = request.getRequestDispatcher("collectionselectorcrate.jsp");
				dispatcher.forward(request, response);

			
			
		}
		break;
		case "AddItem":{
			String Item = request.getParameter("Item");
			String Address = request.getParameter( "Address");
			String Coll = request.getParameter("collectionID");
			int CollectionID = Integer.parseInt(Coll);
			cDTO.AddItem(Item, CollectionID);
			HttpSession session = request.getSession();

			RequestDispatcher dispatcher = request.getRequestDispatcher("collectionadditems.jsp");
			dispatcher.forward(request, response);
			
		}
		break;
		case "ExistingCrate" :{
			String date = request.getParameter("CollectionDate");
			String Time = request.getParameter("CollectionTime");
			String Address = request.getParameter("Collectionaddress");
			String Frequency = request.getParameter("Frequency");
			String Client = request.getParameter("ClientID");
			int ClientID = Integer.parseInt(Client);
			String cbcrate = request.getParameter("cbxCrate");
			int Crate = Integer.parseInt(cbcrate);
			cDTO.createCollection(date, Time, Address, Frequency, ClientID);
			cDTO.assigncratecollection(ClientID, Crate, date);
			tableStr += "<a href='index.html'>Home</a><br/>";
			
		}
		break;
		
		case"Newcrate": {
			String date = request.getParameter("CollectionDate");
			String Time = request.getParameter("CollectionTime");
			String Address = request.getParameter("Collectionaddress");
			String Frequency = request.getParameter("Frequency");
			String ItemType = request.getParameter("Itemtype");
			String Client = request.getParameter("ClientID");
			int ClientID = Integer.parseInt(Client);
			cDTO.createCollection(date, Time, Address, Frequency, ClientID);
			cDTO.createCrate(ClientID, ItemType, date);
			List<Crate> cratelist = crDTO.allCratesbyClientID(ClientID);
			HttpSession session = request.getSession();
			session.setAttribute("cratelist", cratelist);
			session.setAttribute("clientID", ClientID);

			RequestDispatcher dispatcher = request.getRequestDispatcher("AddItems.jsp");
			dispatcher.forward(request, response);
			
		}
		break;
		case "allCollections":{
			List<Collection> collectionlist = cDTO.allCollections();
			HttpSession session = request.getSession();
			session.setAttribute("collectionlist", collectionlist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/AllCollectionList.jsp");
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
