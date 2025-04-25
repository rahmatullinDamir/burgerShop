<%--
  Created by IntelliJ IDEA.
  User: damirrakhmatullin
  Date: 25.04.25
  Time: 02:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BurgerShop</title>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>
<header class="header">
    <div class="header__container">
        <div class="header__logo">
            <img src="/static/logo.png" alt="Логотип Бургерного рая">
            <h1 class="header__title">Burger Shop</h1>
        </div>
        <nav class="header__nav">
            <ul class="header__menu">
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li><a href="/admin">Admin</a></li>
                </c:if>
                <li><a href="/cart">Cart</a></li>
                <li><a href="/profile">Profile</a></li>
            </ul>
        </nav>
    </div>
</header>