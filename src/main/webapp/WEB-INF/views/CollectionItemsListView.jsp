<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>All Collection Items List</title>
 </head>
 <body>
<a href='index.html'>Home</a><br/>
    <h3>All Collection Items List</h3>

<a href='CollectionServlet?action=allCollections'>Back</a>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
      	  <th>ID</th>
      	  <th>Item</th>
          <tr>
       </tr>
          <tr>
       <c:forEach items="${itemslist}" var="items" >
             <td>${items.id}</td>
             <td>${items.item}</td>
          </tr>
       </c:forEach>
    </table>

 </body>
</html>
