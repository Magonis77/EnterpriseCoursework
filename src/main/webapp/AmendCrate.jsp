<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method = "GET">
         Crate ID (ID): <input type = "text" name = "CrateID"></br>
         Shelf: <input type = "text" name = "Shelf"></br>
        WarehouseID:<input type = "text" name = "WarehouseID"></br>
         <br />
         <input type = "submit" value = "Add" />
      </form>
      
      <%
      	if (request.getParameter("CrateID") != null)
      	{
      		RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet?action=AmendCrate");
      		request.setAttribute("CrateID", request.getParameter("CrateID"));
      		request.setAttribute("Shelf", request.getParameter("Shelf"));
      		request.setAttribute("WarehouseID", request.getParameter("WarehouseID"));
      		rd.forward(request, response);
      	}
      %>
</body>
</html>