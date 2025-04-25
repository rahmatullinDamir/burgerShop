<%--
  Created by IntelliJ IDEA.
  User: damirrakhmatullin
  Date: 25.04.25
  Time: 04:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>

<h1>Your Cart</h1>
<div class="cart_container">
    <c:forEach items="${cardDtos}" var="card">
        <form action="/cart" method="POST" class="cart_item">
            <input type="hidden" value="${card.orderId}" name="orderId">
            <div class="post-image">
                <img src="${pageContext.request.contextPath}/uploaded/files?id=${card.burger.image.id}"
                     alt="Burger Image">
            </div>
            <div class="info-block">
                <p>Burger Name: ${card.burger.burger.name}</p>
                <p>Burger Description: ${card.burger.burger.description}</p>
                <p>Burger Price: ${card.burger.burger.price} rub</p>
                <p>Quantity of Burger: ${card.quantity}</p>
            </div>
            <button name="action" value="Delete">Delete burger!</button>
        </form>
    </c:forEach>
</div>

<form action="/cart" method="POST">
    <button name="action" value="Order">Order</button>
</form>

<%@ include file="footer.jsp" %>
