package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.EmployeeDTO;
import models.Employee;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/Employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private EmployeeDTO emDTO;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String param_action = request.getParameter("action");
		String tableStr = new String();
		
		switch(param_action) {
		case "login":
		{
			
			String databaseUsername = "";
			String databasePassword = "";
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Employee e = new Employee();
			List <Employee> Employeelist = emDTO.allEmployee();
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
		default:
			break;
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
