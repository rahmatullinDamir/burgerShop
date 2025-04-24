<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Registration</title>
  <link rel="stylesheet" href="../css/signUp.css">
</head>
<body>
<div class="form-container">
  <h1 class="form-title">Registration</h1>

  <c:if test="${not empty param.error}">
    <div class="error-message">
      <c:choose>
        <c:when test="${param.error == 'emptyFields'}">
          All fields must be filled in.
        </c:when>
        <c:when test="${param.error == 'userExists'}">
          A user with this name already exists.
        </c:when>
        <c:when test="${param.error == 'shortPassword'}">
          The password must be at least 6 characters long.
        </c:when>
        <c:otherwise>
          An unknown error occurred. Please try again later.
        </c:otherwise>
      </c:choose>
    </div>
  </c:if>

  <form class="form" action="/signUp" method="post" id="signUpForm">
    <div class="form-group">
      <div class="form-field-container">
        <label class="form-label" for="username">Username</label>
        <input class="form-input" type="text" id="username" name="username" required>
      </div>
    </div>

    <div class="form-group">
      <div class="form-field-container">
        <label class="form-label" for="password">Password</label>
        <input class="form-input" type="password" id="password" name="password" required minlength="6">
      </div>
    </div>

    <div class="form-buttons">
      <button type="submit" class="form-button">Registration</button>
      <a href="${pageContext.request.contextPath}/signIn" class="form-button">Return</a>
    </div>
  </form>
</div>


<script>
  document.getElementById('signUpForm').addEventListener('submit', function(event) {
    const password = document.getElementById('password').value;
    const username = document.getElementById('username').value.trim();


    if (!username) {
      alert('Пожалуйста, заполните все поля.');
      event.preventDefault();
      return;
    }


    if (password.length < 6) {
      alert('Пароль должен содержать не менее 6 символов.');
      event.preventDefault();
      return;
    }
  });
</script>
</body>
</html>