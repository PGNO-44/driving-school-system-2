<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard - Fast Driving School</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #333;
        }

        header {
            background-color: #1e3a8a;
            color: white;
            padding: 1rem 0;
            text-align: center;
        }

        .logo {
            font-size: 2rem;
            font-weight: bold;
            margin-bottom: 0.5rem;
        }

        nav {
            background-color: #2563eb;
            padding: 1rem 0;
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            gap: 2rem;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        nav a:hover {
            background-color: #1e40af;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem;
        }

        h1 {
            color: #1e3a8a;
            margin-bottom: 2rem;
            text-align: center;
        }

        .profile-card {
            background-color: white;
            border-radius: 8px;
            padding: 2rem;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 0 auto;
        }

        .profile-info {
            margin-bottom: 1.5rem;
        }

        .profile-info p {
            margin: 0.5rem 0;
            font-size: 1.1rem;
        }

        .profile-info strong {
            color: #1e3a8a;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 1rem;
            margin-top: 2rem;
        }

        .btn {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background-color: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
            border: none;
            cursor: pointer;
            font-size: 1rem;
            font-weight: bold;
        }

        .btn:hover {
            background-color: #1e40af;
        }

        .btn-logout {
            background-color: #dc2626;
        }

        .btn-logout:hover {
            background-color: #b91c1c;
        }

        footer {
            background-color: #0f172a;
            color: white;
            text-align: center;
            padding: 2rem;
            margin-top: 3rem;
        }

        .footer-links {
            display: flex;
            justify-content: center;
            gap: 2rem;
            margin-bottom: 1rem;
        }

        .footer-links a {
            color: white;
            text-decoration: none;
        }

        .footer-links a:hover {
            text-decoration: underline;
        }

        .copyright {
            margin-top: 1rem;
            font-size: 0.9rem;
            color: #94a3b8;
        }
    </style>
</head>
<body>
<header>
    <div class="logo">Fast Driving School</div>
</header>

<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/student/dashboard">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
        <li><a href="${pageContext.request.contextPath}/services">Services</a></li>
        <li><a href="${pageContext.request.contextPath}/courses">Courses</a></li>
        <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
    </ul>
</nav>

<div class="container">
    <h1>Welcome, ${student.firstName} ${student.lastName}!</h1>

    <div class="profile-card">
        <div class="profile-info">
            <p><strong>Email:</strong> ${student.email}</p>
            <p><strong>Phone:</strong> ${student.phone}</p>
            <p><strong>Address:</strong> ${student.address}</p>
        </div>

        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/student/bookings/new" class="btn">New Booking</a>
            <a href="${pageContext.request.contextPath}/student/bookings/my" class="btn">View My Bookings</a>
            <a href="/logout" class="btn btn-logout">Logout</a>
        </div>
    </div>
</div>

<footer>
    <div class="footer-links">
        <a href="${pageContext.request.contextPath}/about">About Us</a>
        <a href="${pageContext.request.contextPath}/services">Services</a>
        <a href="${pageContext.request.contextPath}/courses">Courses</a>
        <a href="${pageContext.request.contextPath}/contact">Contact</a>
    </div>
    <div>123 Driving Lane, Highway City, HC 12345</div>
    <div>(555) 123-4567 | info@fastdriving.com</div>
    <div class="copyright">&copy; 2025 Fast Driving School. All rights reserved.</div>
</footer>
</body>
</html>