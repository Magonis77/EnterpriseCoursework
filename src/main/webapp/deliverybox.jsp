<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<%@ page import="models.Crate"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	@SuppressWarnings("unchecked")
	List<Crate> allcrates = (List<Crate>) session.getAttribute("allcrates");
	
	
	%>
<a href='index.html'>Home</a><br/>
	<form action="deliverybox.jsp" method = "post">
         Select crate:&nbsp; <select name="cbxCrate" style="width: 200px">
			<c:forEach items="${allcrates}" var="crate">
				<option value="${crate.id}">${crate.item_Type}</option>
			</c:forEach>
		</select>
		<br>
	 Collection Date: <input type = "text" name="DeliveryDate" value =${DeliveryDate} readonly/></br>
	  Collection Time: <input type = "text" name="DeliveryTime" value =${DeliveryTime} readonly/></br>
	    Frequency: <input type = "text" name="Frequency" value =${Frequency} readonly/></br>
         	ClientID : <input type = "text" name="ClientID" value =${ClientID} readonly/></br>
         <input type = "submit" name="wholecrate"  value = "Deliver whole Crate" /></br>
         </br>
          <input type = "submit" name="Specificitems"  value = "Deliver specific item from this crate" /></br>
      </form>
      <%
      if (request.getParameter("wholecrate") != null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("DeliveryServlet?action=WholeCrate");
    		request.setAttribute("DeliveryDate", request.getParameter("DeliveryDate"));
    		request.setAttribute("cbxCrate", request.getParameter("cbxCrate"));
    		request.setAttribute("DeliveryTime", request.getParameter("DeliveryTime"));
    		request.setAttribute("Frequency", request.getParameter("Frequency"));
    		request.setAttribute("ClientID", request.getParameter("ClientID"));
    		rd.forward(request, response);

      } else if (request.getParameter("Specificitems") != null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("DeliveryServlet?action=SpecificItems");
    		request.setAttribute("DeliveryDate", request.getParameter("DeliveryDate"));
    		request.setAttribute("DeliveryTime", request.getParameter("DeliveryTime"));
    		request.setAttribute("Frequency", request.getParameter("Frequency"));
    		request.setAttribute("ClientID", request.getParameter("ClientID"));
    		rd.forward(request, response);
      }
      %>
</body>
</html>