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
           <p>${burger.burger.name}</p>
           <p>${burger.burger.description}</p>
           <p>${burger.burger.price}</p>
           <p><div class="post-image">
        <img src="${pageContext.request.contextPath}/uploaded/files?id=${burger.image.id}"
             alt="Post Image">
        </div>
        <button onclick="updateCart(${burger.burger.id}, 'add')">Add to Cart</button>
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
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--    <head>--%>
<%--        <title>Burgers</title>--%>
<%--    </head>--%>
<%--    <body>--%>
<%--    <div class="container">--%>
<%--        <div class="item">Header</div>--%>
<%--        <div class="item">Content</div>--%>
<%--        <div class="item">Footer</div>--%>
<%--    </div>--%>
<%--    <c:forEach items="${burgers}" var="burger">--%>
<%--        <p>${burger.price}</p>--%>
<%--        <p>${burger.description}</p>--%>
<%--        <p>${burger.name}</p>--%>
<%--    </c:forEach>--%>
<%--    </body>--%>
<%--</html>--%>