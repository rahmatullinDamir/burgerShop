<%--
  Created by IntelliJ IDEA.
  User: kim85
  Date: 25.04.2025
  Time: 5:09
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <title>Admin Panel</title>
</head>
<body>
<div class="admin-container">
  <h1>Burger Admin Panel</h1>

  <!-- Add Burger Form -->
  <div class="add-burger-form">
    <h2>Add New Burger</h2>
    <form action="/admin" method="post" enctype="multipart/form-data">
      <div class="form-group">
        <label for="name">Burger Name:</label>
        <input type="text" id="name" name="name" required>
      </div>

      <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" min="0" step="0.01" required>
      </div>

      <div class="form-group">
        <label for="text">Description:</label>
        <textarea id="text" name="text" rows="3" required></textarea>
      </div>

      <input type="file" name="image">

      <button type="submit" class="submit-btn">Add Burger</button>
    </form>
  </div>

  </div>
</body>
</html>
<%@include file="footer.jsp"%>