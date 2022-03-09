<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Login Page</title>
</head>
<body>
<h1>
Employee Login:
</h1> 
<hr/>  
  
<h3>Login Form</h3>  
	<form method="GET">
		Username: <input type="text" name="username"> <br />
		Password: <input type="password" name="password"> <br /> 
		<input type="submit" value="Add" />
	</form>

	<%
		if (request.getParameter("username") != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("EmployeeServlet?action=login");
			request.setAttribute("username", request.getParameter("username"));
			request.setAttribute("password", request.getParameter("password"));
			
			rd.forward(request, response);
		}
	%>
</form>  
</body>
</html>