<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deliver Order</title>
</head>
<body>
<a href='index.html'>Home</a><br/>
<h1>Request Collection:</h1>
<form action="Deliveryorder.jsp" method = "post">
 Client ID :<input type = "text" name = "ClientID"></br>
         Preferred Delivery Date:<input type = "Date" name = "DeliveryDate"></br>
         Preferred Delivery time:<input type = "Time" name = "DeliveryTime"></br>
         Frequency :<input type = "text" name = "Frequency"></br>
         For frequency please write if you need to have it Delivered every week or month or it is one time.</br>
         For example: One time for one time Delivery.
                  <br />
         <input type = "submit" name="Next" value = "Next" />
                  <br />
      </form>
      <%
      if (request.getParameter("Next") != null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("DeliveryServlet?action=getclientcrates");
    		request.setAttribute("DeliveryDate", request.getParameter("DeliveryDate"));
    		request.setAttribute("DeliveryTime", request.getParameter("DeliveryTime"));
    		request.setAttribute("Frequency", request.getParameter("Frequency"));
    		request.setAttribute("ClientID", request.getParameter("ClientID"));
    		rd.forward(request, response);

      }
      %>
</body>
</html>