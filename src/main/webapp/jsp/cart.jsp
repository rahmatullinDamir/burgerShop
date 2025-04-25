<%--
  Created by IntelliJ IDEA.
  User: damirrakhmatullin
  Date: 25.04.25
  Time: 04:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>

<h1>Your Cart</h1>
<c:choose>
    <c:when test="${empty burgersInCart}">
        <p>Your cart is empty.</p>
    </c:when>
    <c:otherwise>
        <table class="cart-table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="burger" items="${burgersInCart}">
                <tr>
                    <td>${burger.name}</td>
                    <td>${burger.price} ₽</td>
                    <td>${burger.quantity}</td>
                    <td>${burger.price * burger.quantity} ₽</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cart">
                            <input type="hidden" name="burgerId" value="${burger.id}">
                            <input type="hidden" name="action" value="remove">
                            <button type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="cart-total">
            <strong>Total:</strong>
            <span>
                    ${burgersInCart.stream().mapToInt(burger -> burger.price * burger.quantity).sum()} ₽
            </span>
        </div>
    </c:otherwise>
</c:choose>

<div class="cart-actions">
    <a href="${pageContext.request.contextPath}/">Continue Shopping</a>
    <a href="${pageContext.request.contextPath}/checkout">Checkout</a>
</div>
<%@ include file="footer.jsp" %>
