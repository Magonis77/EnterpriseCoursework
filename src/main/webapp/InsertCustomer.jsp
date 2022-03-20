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
<a href='index.html'>Home</a><br/>
         First Name: <input type = "text" name = "firstName"></br>
         Last Name: <input type = "text" name = "lastName"></br>
         Email: <input type = "text" name = "email"></br>
         Phone Number: <input type = "text" name = "phonenumber"></br>
         Password: <input type = "password" name = "Password">       <br />
         Address Line 1: <input type = "text" name = "Address1"/>	<br/>
		Address Line 2: <input type = "text" name = "Address2"/>	<br/>
		Address Line 3: <input type = "text" name = "Address3"/>	<br/>
		Address Line 4: <input type = "text" name = "Address4"/>	<br/>
		Post Code: <input type = "text" name = "PostCode"/>	<br/>
		City: <input type = "text" name = "City"/>	<br/>
         <input type = "submit" value = "Create" />
      </form>
      
      <%
      	if (request.getParameter("firstName") != null)
      	{
      		RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet?action=insertUser");
      		request.setAttribute("firstName", request.getParameter("firstName"));
      		request.setAttribute("lastName", request.getParameter("lastName"));
      		request.setAttribute("email", request.getParameter("email"));
      		request.setAttribute("phonenumber", request.getParameter("phonenumber"));
      		request.setAttribute("Password", request.getParameter("Password") + 
					"&Address1=" + request.getParameter("Address1")+ 
					"&Address2=" + request.getParameter("Address2")+ 
"&Address3=" + request.getParameter("Address3")+ 
"&Address4=" + request.getParameter("Address4")+ 
"&PostCode=" + request.getParameter("PostCode")
+ 
"&City=" + request.getParameter("City"));
      		rd.forward(request, response);
      	}
      %>
</body>
</html>