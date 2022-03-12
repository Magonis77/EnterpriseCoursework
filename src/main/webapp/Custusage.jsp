<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<%@ page import="models.Customerusage"%>
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
	
			response.sendRedirect("CustomerServlet?action=CustUsagelist" + "&ClientID=" + 
									request.getParameter("cbxClient"));
											
											
}	%>
<%
	@SuppressWarnings("unchecked")
	List<Customerusage> clientlist = (List<Customerusage>) session.getAttribute("custusagelist");
	
	%>

	<form action="Custusage.jsp" method = "post">
         Select an author:&nbsp; <select name="cbxClient" style="width: 200px">
			<c:forEach items="${clientlist}" var="client">
				<option value="${client.id}">${client.firstName}</option>
			</c:forEach>
			
		</select>
		 <input type = "submit" value = "Select" />
      </form>
</body>
</html>