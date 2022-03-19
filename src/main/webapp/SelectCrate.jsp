<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<%@ page import="models.Crate"%>
<%@ page import="models.Client"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crate Select</title>
</head>
<body>
<a href='index.html'>Home</a><br/>

	<%
		if (request.getParameter("cbxCrate") != null) {
	
			response.sendRedirect("CustomerServlet?action=SelectCrate" + "&CrateID=" + 
									request.getParameter("cbxCrate") + "&ClientID=" + request.getParameter("ClientID"));
	}
	%>

	<%
	@SuppressWarnings("unchecked")
	List<Crate> cratelist = (List<Crate>) session.getAttribute("cratelist");
	
	
	%>
<a href='index.html'>Home</a><br/>
	<form action="SelectCrate.jsp" method = "post">

         Select crate:&nbsp; <select name="cbxCrate" style="width: 200px">
			<c:forEach items="${cratelist}" var="crate">
				<option value="${crate.id}">${crate.item_Type}</option>
			</c:forEach>
		</select>
		<br>
		Client ID =  <input type="text" id="ClientID" name="ClientID" value=${clientID} readonly></br>
         <input type = "submit" value = "Select" />
      </form>

</body>
</html>