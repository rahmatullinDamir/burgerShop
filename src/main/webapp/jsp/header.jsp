<%--
  Created by IntelliJ IDEA.
  User: damirrakhmatullin
  Date: 25.04.25
  Time: 02:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BurgerShop</title>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<script>
    document.addEventListener("DOMContentLoaded", async function () {
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
    });
</script>
<body>
<header class="header">
    <div class="header__container">

        <div class="header__logo">
            <a href="/"><img src="/static/logo.png" alt="Логотип Бургерного рая"></a>
            <h1 class="header__title">Burger Shop</h1>
        </div>
        <nav class="header__nav">
            <ul class="header__menu">
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li><a href="/admin">Admin</a></li>
                </c:if>
                <li>
                    <a href="/cart">
                        Cart(<span id="cart-count">0</span>)
                    </a>
                </li>
                <li><a href="/profile">Profile</a></li>
            </ul>
        </nav>
    </div>
</header>
<div class="container">