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

            <div class="quality">
                <label for="quantity-${burger.burger.id}">Quantity:</label>
                <input type="number" id="quantity-${burger.burger.id}" value="1" min="1" max="10">
            </div>
            <button onclick="updateCart(${burger.burger.id})">Add to Cart</button>
        </div>
    </c:forEach>
</div>

<script>
    function updateCart(burgerId) {
        const quantityInput = document.getElementById('quantity-'+ burgerId);
        const quantity = parseInt(quantityInput.value);

        if (isNaN(quantity) || quantity < 1) {
            alert("Please enter a valid quantity.");
            return;
        }

        fetch("${pageContext.request.contextPath}/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ burgerId, quantity })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                console.log("Success:", data);
                up();
                alert("Added to cart!");
            })
            .catch(error => {
                console.error("There was a problem with the fetch operation:", error);
            });
    }
    async function up() {
        try {
            const response = await fetch('/count', {
                method: 'GET',
                credentials: 'include'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            console.log('Количество бургеров в корзине:', data.count);
            const cartCountElement = document.getElementById("cart-count");
            cartCountElement.textContent = data.count;
        } catch (error) {
            console.error('Ошибка при получении количества бургеров:', error);
        }
    }
</script>
<%@include file="footer.jsp"%>