<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Customer</title>
</head>
<body>
<form method = "GET">
         First Name: <input type = "text" name = "firstName"></br>
         Last Name: <input type = "text" name = "lastName"></br>
         Email: <input type = "text" name = "email"></br>
         Phone Number: <input type = "text" name = "phonenumber">
         <br />
         <input type = "submit" value = "Add" />
      </form>
      
      <%
      	if (request.getParameter("firstName") != null)
      	{
      		RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet?action=insertUser");
      		request.setAttribute("action", "insertBook");
      		request.setAttribute("firstName", request.getParameter("firstName"));
      		request.setAttribute("lastName", request.getParameter("lastName"));
      		request.setAttribute("email", request.getParameter("email"));
      		request.setAttribute("phonenumber", request.getParameter("phonenumber"));
      		rd.forward(request, response);
      	}
      %>
</body>
</html>