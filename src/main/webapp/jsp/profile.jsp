<%--
  Created by IntelliJ IDEA.
  User: kim85
  Date: 24.04.2025
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>

<%@include file="header.jsp"%>

<div class="profile-header">
    <div class="profile-info">
        <img src="/static/avatar.jpg" alt="avatar" class="profile-avatar">
        <p class="profile-username">${sessionScope.username}</p>
    </div>
</div>

<form action="/profile" method="POST" class="profile-form">
    <div class="form-group">
        <div class="form-field-container">
            <label class="form-label" for="street">Street</label>
            <input class="form-input" type="text" id="street" name="street" value="${address.street}" required>
        </div>
    </div>
    <div class="form-group">
        <div class="form-field-container">
            <label class="form-label" for="city">City</label>
            <input class="form-input" type="text" id="city" name="city" value="${address.city}" required>
        </div>
    </div>
    <div class="form-group">
        <div class="form-field-container">
            <label class="form-label" for="house">House</label>
            <input class="form-input" type="text" id="house" name="house" value="${address.house}" required>
        </div>
    </div>
    <div class="form-group">
        <div class="form-field-container">
            <label class="form-label" for="flat">Flat</label>
            <input class="form-input" type="text" id="flat" name="flat" value="${address.flat}" required>
        </div>
    </div>

    <button type="submit" class="form-input">Change address</button>
</form>
<%@include file="footer.jsp"%>
