<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>All Orders List</title>
 </head>
 <body>
<a href='index.html'>Home</a><br/>
    <h3>All Orders List</h3>


    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
      	  <th>Order Number</th>
          <th>Order Date</th>
          <th>Status</th>
          <th>Client ID</th>
          <th>Collection Date</th>
          <tr>
       </tr>
          <tr>
       <c:forEach items="${orderlist}" var="order" >
             <td>${order.number}</td>
             <td>${order.date}</td>
             <td>${order.status}</td>
             <td>${order.clientID}</td>
             <td>${order.collectionDate}</td>
          </tr>
       </c:forEach>
    </table>

 </body>
</html>
