<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Fast Driving School</title>
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
            text-align: center;
        }

        h1 {
            color: #1e3a8a;
            margin-bottom: 2rem;
        }

        .admin-actions {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 2rem;
            margin-top: 2rem;
        }

        .action-card {
            background-color: white;
            border-radius: 8px;
            padding: 2rem;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .action-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 15px rgba(0,0,0,0.1);
        }

        .action-card h2 {
            margin-bottom: 1rem;
            color: #2563eb;
        }

        .action-card a {
            display: inline-block;
            margin-top: 1rem;
            padding: 0.5rem 1.5rem;
            background-color: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .action-card a:hover {
            background-color: #1e40af;
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
        <li><a href="${pageContext.request.contextPath}/admin">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
        <li><a href="${pageContext.request.contextPath}/services">Services</a></li>
        <li><a href="${pageContext.request.contextPath}/courses">Courses</a></li>
        <li><a href="${pageContext.request.contextPath}/instructors">Instructors</a></li>
        <li><a href="${pageContext.request.contextPath}/students">Students</a></li>
        <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
    </ul>
</nav>

<div class="container">
    <h1>Admin Dashboard</h1>

    <div class="admin-actions">
        <div class="action-card">
            <h2>Add New Student</h2>
            <a href="${pageContext.request.contextPath}/admin/students/add">Add Student</a>
        </div>
        <div class="action-card">
            <h2>Add New Instructor</h2>
            <a href="${pageContext.request.contextPath}/admin/instructors/add">Add Instructor</a>
        </div>
        <div class="action-card">
            <h2>Pending Bookings</h2>
            <a href="${pageContext.request.contextPath}/admin/bookings/pending">Pending Bookings</a>
        </div>
        <div class="action-card">
            <h2>View All Students</h2>
            <a href="${pageContext.request.contextPath}/students">View Students</a>
        </div>
        <div class="action-card">
            <h2>View All Instructors</h2>
            <a href="${pageContext.request.contextPath}/instructors">View Instructors</a>
        </div>
        <div class="action-card">
            <h2>Schedules</h2>
            <a href="${pageContext.request.contextPath}/admin/schedules">View Schedules</a>
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
    <div>(555) 123-4567 | info@driveright.com</div>
    <div class="copyright">&copy; 2025 DriveRight Driving School. All rights reserved.</div>
</footer>
</body>
</html>
