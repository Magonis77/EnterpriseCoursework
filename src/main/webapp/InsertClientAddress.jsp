<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<%@ page import="models.Client"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		if (request.getParameter("cbxClient") != null) {
	
			response.sendRedirect("CustomerServlet?action=insertuseraddress" + "&ClientID=" + 
									request.getParameter("cbxClient") + 
									"&Address1=" + request.getParameter("Address1")+ 
									"&Address2=" + request.getParameter("Address2")+ 
			"&Address3=" + request.getParameter("Address3")+ 
"&Address4=" + request.getParameter("Address4")+ 
"&PostCode=" + request.getParameter("PostCode")
+ 
"&City=" + request.getParameter("City"));
	}
	%>

	<%
	@SuppressWarnings("unchecked")
	List<Client> clientlist = (List<Client>) session.getAttribute("clientlist");
	
	%>

	<form action="InsertClientAddress.jsp" method = "post">
         Select an author:&nbsp; <select name="cbxClient" style="width: 200px">
			<c:forEach items="${clientlist}" var="client">
				<option value="${client.id}">${client.firstName}</option>
			</c:forEach>
			
		</select>
		<br/>
		Address Line 1: <input type = "text" name = "Address1"/>	<br/>
		Address Line 2: <input type = "text" name = "Address2"/>	<br/>
		Address Line 3: <input type = "text" name = "Address3"/>	<br/>
		Address Line 4: <input type = "text" name = "Address4"/>	<br/>
		Post Code: <input type = "text" name = "PostCode"/>	<br/>
		City: <input type = "text" name = "City"/>	<br/>
         <br />
		
         <input type = "submit" value = "Add" />
      </form>
</body>
</html>