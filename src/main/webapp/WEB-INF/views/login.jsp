<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Login</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background-color: #f6f9fc;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            padding: 40px;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
            font-weight: 500;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #555;
        }

        input[type="email"], input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        input[type="email"]:focus, input[type="password"]:focus {
            border-color: #4a90e2;
            outline: none;
        }

        button {
            background-color: #2563eb;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 12px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #173f9c;
        }

        .error {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 4px;
            color: white;
            background-color: #f44336;
        }

        .user-types {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .user-type {
            margin: 0 10px;
            font-size: 14px;
            color: #777;
        }

        .divider {
            margin: 30px 0;
            text-align: center;
            position: relative;
        }

        .divider:before {
            content: "";
            position: absolute;
            top: 50%;
            left: 0;
            right: 0;
            border-top: 1px solid #ddd;
            z-index: 1;
        }

        .divider span {
            background-color: #fff;
            padding: 0 15px;
            position: relative;
            z-index: 5;
            color: #777;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h1>Student Login</h1>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post" action="/login">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit">Login</button>
    </form>

    <div class="divider">
        <span>OR</span>
    </div>

    <div class="user-types">
        <div class="user-type">Forgot Password?</div>
        <div class="user-type">Register</div>
    </div>
</div>
</body>
</html>