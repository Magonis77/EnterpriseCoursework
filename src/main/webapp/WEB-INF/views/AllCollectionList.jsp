<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>All Collection List</title>
 </head>
 <body>
<a href='index.html'>Home</a><br/>
    <h3>All Collection List</h3>


    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
      	  <th>Collection ID</th>
      	  <th>Client First Name</th>
      	  <th>Client Last Name</th>
      	  <th>Client Phone Number</th>
      	  <th>Client Email</th>
      	  <th>Client Address Line 1</th>
      	  <th>Client Address Line 2</th>
      	  <th>Client Address Line 3</th>
      	  <th>Client Address Line 4</th>
      	  <th>Client City</th>
      	  <th>Client Post Code</th>
          <th>Date</th>
          <th>Time</th>
          <th>Frequency</th>
          <th>Journey</th>
          <th>Items</th>
          <tr>
       </tr>
          <tr>
       <c:forEach items="${collectionlist}" var="collection" >
             <td>${collection.id}</td>
             <td>${collection.client.firstName}</td>
             <td>${collection.client.lastName}</td>
             <td>${collection.client.phoneNumber}</td>
             <td>${collection.client.emailaddress}</td>
             <td>${collection.client.address.addressLine1}</td>
             <td>${collection.client.address.addressLine2}</td>
             <td>${collection.client.address.addressLine3}</td>
             <td>${collection.client.address.addressLine4}</td>
             <td>${collection.client.address.city}</td>
             <td>${collection.client.address.postCode}</td>
             <td>${collection.date}</td>
             <td>${collection.time}</td>
             <td>${collection.frequency}</td>
             <td>${collection.journey}</td>
              <td>
                <a href="SeeItemsServlet?code=${collection.id}">Show Items</a>
             </td>
          </tr>
       </c:forEach>
    </table>

 </body>
</html>
