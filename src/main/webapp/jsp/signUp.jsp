<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Registration</title>
  <link rel="stylesheet" href="/static/signUp.css">
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
    <div class="form-group">
      <div class="form-field-container">
        <label class="form-label" for="street">Street</label>
        <input class="form-input" type="text" id="street" name="street" required>
      </div>
    </div>
    <div class="form-group">
      <div class="form-field-container">
        <label class="form-label" for="city">City</label>
        <input class="form-input" type="text" id="city" name="city" required>
      </div>
    </div>
    <div class="form-group">
      <div class="form-field-container">
        <label class="form-label" for="house">House</label>
        <input class="form-input" type="text" id="house" name="house" required>
      </div>
    </div>
    <div class="form-group">
      <div class="form-field-container">
        <label class="form-label" for="flat">Flat</label>
        <input class="form-input" type="text" id="flat" name="flat" required>
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
    event.preventDefault();

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;
    const street = document.getElementById('street').value.trim();
    const city = document.getElementById('city').value.trim();
    const house = document.getElementById('house').value.trim();
    const flat = document.getElementById('flat').value.trim();

    let isValid = true;

    document.querySelectorAll('.error-message').forEach(el => el.remove());

    if (!username) {
      showError('username', 'Username is required.');
      isValid = false;
    }

    if (password.length < 6) {
      showError('password', 'Password must be at least 6 characters long.');
      isValid = false;
    }

    if (!street) {
      showError('street', 'Street is required.');
      isValid = false;
    }

    if (!city) {
      showError('city', 'City is required.');
      isValid = false;
    }

    if (!house || isNaN(house)) {
      showError('house', 'House number must be a number.');
      isValid = false;
    }

    if (!flat || isNaN(flat)) {
      showError('flat', 'Flat number must be a number.');
      isValid = false;
    }

    if (isValid) {
      this.submit();
    }
  });

  function showError(fieldId, message) {
    const field = document.getElementById(fieldId);
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;
    field.parentElement.appendChild(errorDiv);
  }
</script>
</body>
</html>