<%--
  Created by IntelliJ IDEA.
  User: moriarty
  Date: 27.01.2025
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="item">
    <c:forEach items="${burgerWithImages}" var="burger">
        <div>
            <div class="post-image">
                <img src="${pageContext.request.contextPath}/uploaded/files?id=${burger.image.id}"
                     alt="Burger Image">
            </div>
            <p>${burger.burger.name}</p>
            <p>${burger.burger.description}</p>
            <p>${burger.burger.price} rub</p>
            <button onclick="updateCart(${burger.burger.id}, 'add')">Add to Cart</button>
        </div>
    </c:forEach>
</div>

<script>
    function updateCart(burgerId, action) {
        fetch("${pageContext.request.contextPath}/cart", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `burgerId=${burgerId}&action=${action}`
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                const cartCountElement = document.getElementById("cart-count");
                cartCountElement.textContent = data.totalItems;
            })
            .catch(error => {
                console.error("There was a problem with the fetch operation:", error);
            });
    }
</script>
<%@include file="footer.jsp"%>