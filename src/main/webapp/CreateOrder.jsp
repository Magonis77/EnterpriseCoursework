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
         Client Number(ID): <input type = "text" name = "Client"></br>
         Item Type: <input type = "text" name = "ItemType"></br>
         Preferred Collection Date:<input type = "Date" name = "CollectionDate"></br>
         Preferred Collection Date:<input type = "Time" name = "CollectionTime"></br>
         <br />
         <input type = "submit" value = "Add" />
      </form>
      
      <%
      	if (request.getParameter("Client") != null)
      	{
      		RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet?action=CreateOrder");
      		request.setAttribute("Client", request.getParameter("Client"));
      		request.setAttribute("ItemType", request.getParameter("ItemType"));
      		request.setAttribute("CollectionDate", request.getParameter("CollectionDate"));
      		request.setAttribute("CollectionTime", request.getParameter("CollectionTime"));
      		rd.forward(request, response);
      	}
      %>
</body>
</html>