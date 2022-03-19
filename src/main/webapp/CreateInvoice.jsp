<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Invoice</title>
</head>
<body>
<form method = "GET">
<a href='index.html'>Home</a><br/>
         Client ID <input type = "text" name = "clientID"></br>
         Amount: <input type = "text" name = "Amount"></br>
         <br />
         <input type = "submit" value = "Create" />
      </form>
      
      <%
      	if (request.getParameter("clientID") != null)
      	{
      		RequestDispatcher rd = request.getRequestDispatcher("EmployeeServlet?action=CreateInvoice");
      		request.setAttribute("clientID", request.getParameter("clientID"));
      		request.setAttribute("Amount", request.getParameter("Amount"));
      		rd.forward(request, response);
      	}
      %>
</body>
</html>