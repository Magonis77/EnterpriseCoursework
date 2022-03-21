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
<title>Collection crate select type</title>
</head>
<body>

		<%
	@SuppressWarnings("unchecked")
	List<Crate> allcrates = (List<Crate>) session.getAttribute("allcrates");
	
	
	%>
<a href='index.html'>Home</a><br/>
	<form action="collectionselectorcrate.jsp" method = "post">
<h1>Select Existing crate to collect </h1>
         Select crate:&nbsp; <select name="cbxCrate" style="width: 200px">
			<c:forEach items="${allcrates}" var="crate">
				<option value="${crate.id}">${crate.item_Type}</option>
			</c:forEach>
		</select>
		<br>
	 Collection Date: <input type = "text" name="CollectionDate" value =${CollectionDate} readonly/></br>
	  Collection Time: <input type = "text" name="CollectionTime" value =${CollectionTime} readonly/></br>
	    Frequency: <input type = "text" name="Frequency" value =${Frequency} readonly/></br>
         <br />
         <input type = "submit" name="existing"  value = "Select" /></br>
         <h1>New Crate:</h1>
         	ClientID : <input type = "text" name="ClientID" value =${ClientID} readonly/></br>
         	Item Type: <input type = "text" name = "Itemtype"/></br>
         <input type = "submit" name="Newcrate"  value = "New Crate" /></br>
      </form>
<%
      if (request.getParameter("existing") != null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("CollectionServlet?action=ExistingCrate");
    		request.setAttribute("CollectionDate", request.getParameter("CollectionDate"));
    		request.setAttribute("cbxCrate", request.getParameter("cbxCrate"));
    		request.setAttribute("CollectionTime", request.getParameter("CollectionTime"));
    		request.setAttribute("Frequency", request.getParameter("Frequency"));
    		request.setAttribute("ClientID", request.getParameter("ClientID"));
    		rd.forward(request, response);

      } else if (request.getParameter("Newcrate") != null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("CollectionServlet?action=Newcrate");
    		request.setAttribute("CollectionDate", request.getParameter("CollectionDate"));
    		request.setAttribute("CollectionTime", request.getParameter("CollectionTime"));
    		request.setAttribute("Frequency", request.getParameter("Frequency"));
    		request.setAttribute("ClientID", request.getParameter("ClientID"));
    		request.setAttribute("Itemtype", request.getParameter("Itemtype"));
    		rd.forward(request, response);
      }
      %>
</body>
</html>