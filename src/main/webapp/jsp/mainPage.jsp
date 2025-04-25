<%--
  Created by IntelliJ IDEA.
  User: moriarty
  Date: 27.01.2025
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="item">
    <c:forEach items="${burgers}" var="burger">
           <p>${burger.name}</p>
           <p>${burger.description}</p>
           <p>${burger.price}</p>
<%--           <p>${burger.image}</p>--%>
    </c:forEach>
</div>

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