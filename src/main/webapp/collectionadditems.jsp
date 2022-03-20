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
<title>Add Items</title>
</head>
<body>

	<%
		if (request.getParameter("add") != null) {
	
			response.sendRedirect("CollectionServlet?action=AddItem" 
			+ "&collectionID=" + 
					request.getParameter("collID") + "&Item="+ request.getParameter("Item"));
	}
	%>


<a href='index.html'>Home</a><br/>
	<form action="collectionadditems.jsp" method = "post">
		<br>
	Item: <input type = "text" name = "Item"/>
         <br />
         <input type = "submit" name="add" value = "Add" /></br>
    Collection ID: <input type = "text" name="collID" value =${collectionID} readonly/></br>
      </form>

</body>
</html>