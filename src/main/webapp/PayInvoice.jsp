<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice payment</title>
</head>
<body>
<a href='index.html'>Home</a><br/>
<form method = "GET">
         Invoice Number(ID): <input type = "text" name = "InvoiceID"></br>
         Amount: <input type = "text" name = "Amount"></br>
        Card Number:<input type = "text" name = "CardNumber"></br>
         Exp date:<input type = "text" name = "ExpDate"><input type = "text" name = "ExpDate"></br>
           CVV: <input type = "text" name = "CVV"></br>
         <br />
         <input type = "submit" value = "Add" />
      </form>
      
      <%
      	if (request.getParameter("InvoiceID") != null)
      	{
      		RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet?action=PayInvoice");
      		request.setAttribute("InvoiceID", request.getParameter("InvoiceID"));
      		request.setAttribute("Amount", request.getParameter("Amount"));
      		rd.forward(request, response);
      	}
      %>
</body>
</html>