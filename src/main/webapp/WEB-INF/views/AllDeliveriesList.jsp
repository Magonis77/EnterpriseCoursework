<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>All Deliveries List</title>
 </head>
 <body>
<a href='index.html'>Home</a><br/>
    <h3>All Deliveries List</h3>


    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
      	  <th>Delivery ID</th>
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
                    <th>Status</th>
          <th>Journey</th>
          <tr>
       </tr>
          <tr>
       <c:forEach items="${deliverylist}" var="delivery" >
             <td>${delivery.id}</td>
             <td>${delivery.client.firstName}</td>
             <td>${delivery.client.lastName}</td>
             <td>${delivery.client.phoneNumber}</td>
             <td>${delivery.client.emailaddress}</td>
             <td>${delivery.client.address.addressLine1}</td>
             <td>${delivery.client.address.addressLine2}</td>
             <td>${delivery.client.address.addressLine3}</td>
             <td>${delivery.client.address.addressLine4}</td>
             <td>${delivery.client.address.city}</td>
             <td>${delivery.client.address.postCode}</td>
             <td>${delivery.date}</td>
             <td>${delivery.time}</td>
             <td>${delivery.frequency}</td>
             <td>${delivery.status}</td>
             <td>${delivery.journey}</td>
          </tr>
       </c:forEach>
    </table>

 </body>
</html>
