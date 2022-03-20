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
		if (request.getParameter("cbxCrate") != null) {
	
			response.sendRedirect("CustomerServlet?action=AddItem" + "&CrateID=" + 
									request.getParameter("cbxCrate")+ "&Item="+ request.getParameter("Item"));
	}
	%>
		<%
	@SuppressWarnings("unchecked")
	List<Crate> cratelist = (List<Crate>) session.getAttribute("cratelist");
	
	
	%>
<a href='index.html'>Home</a><br/>
	<form action="AddItems.jsp" method = "post">

         Select crate:&nbsp; <select name="cbxCrate" style="width: 200px">
			<c:forEach items="${cratelist}" var="crate">
				<option value="${crate.id}">${crate.item_Type}</option>
			</c:forEach>
		</select>
		<br>
	Item: <input type = "text" name = "Item"/>
         <br />
         <input type = "submit" value = "Select" />
      </form>

</body>
</html>