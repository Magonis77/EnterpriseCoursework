<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collection</title>
</head>
<body>
<form method = "GET">
<a href='index.html'>Home</a><br/>
<h1>Request Collection:</h1>
 Client ID :<input type = "text" name = "ClientID"></br>
         Preferred Collection Date:<input type = "Date" name = "CollectionDate"></br>
         Preferred Collection time:<input type = "Time" name = "CollectionTime"></br>
         Address:<input type = "text" name = "Collectionaddress"></br>
         Frequency :<input type = "text" name = "Frequency"></br>
         For frequency please write if you need to have it collected every week or month or it is one time.</br>
         For example: One time for one time collection.
                  <br />
         <input type = "submit" name="items" value = "Collect Separate Items" />
                  <br />
         <input type = "submit" name="crate" value = "Collect whole crate" />
      </form>
      <%
      if (request.getParameter("items") != null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("CollectionServlet?action=CollectItems");
    		request.setAttribute("CollectionDate", request.getParameter("CollectionDate"));
    		request.setAttribute("CollectionTime", request.getParameter("CollectionTime"));
    		request.setAttribute("Collectionaddress", request.getParameter("Collectionaddress"));
    		request.setAttribute("Collectionaddress", request.getParameter("Frequency"));
    		request.setAttribute("Collectionaddress", request.getParameter("ClientID"));
    		rd.forward(request, response);

      } else if (request.getParameter("crate") != null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("CollectionServlet?action=Collectcrate");
    		request.setAttribute("CollectionDate", request.getParameter("CollectionDate"));
    		request.setAttribute("CollectionTime", request.getParameter("CollectionTime"));
    		request.setAttribute("Collectionaddress", request.getParameter("Collectionaddress"));
    		request.setAttribute("Collectionaddress", request.getParameter("Frequency"));
    		request.setAttribute("Collectionaddress", request.getParameter("ClientID"));
    		rd.forward(request, response);
      }
      %>
</body>
</html>