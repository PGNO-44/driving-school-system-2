<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Bookings</title>
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
        .status-pending { color: orange; font-weight: bold; }
        .booking-card {
            background: white;
            padding: 1rem;
            margin-bottom: 1rem;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .btn-approve {
            background-color: #10b981; /* Emerald */
            color: white;
        }

        .btn-approve:hover {
            background-color: #0d9f6e;
        }

        .btn-reject {
            background-color: #ef4444; /* Red */
            color: white;
        }

        .btn-reject:hover {
            background-color: #dc2626;
        }

        .booking-card {
            background: white;
            padding: 1.5rem;
            margin-bottom: 1.5rem;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
            text-align: left;
        }

        .booking-card p {
            margin: 0.5rem 0;
        }

        .booking-actions {
            margin-top: 1rem;
            display: flex;
            gap: 1rem;
        }

        .comment-input {
            width: 100%;
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .status-pending {
            color: #f59e0b; /* Amber */
            font-weight: bold;
            margin: 0.5rem 0;
        }
        .action-form {
            display: flex;
            gap: 1rem;
            align-items: center;
            margin-top: 1rem;
        }

        .comment-input {
            width: 100%;
            padding: 0.75rem;
            margin-bottom: 0;
            border: 1px solid #d1d5db;
            border-radius: 6px;
            font-family: inherit;
            font-size: 1rem;
            transition: border-color 0.3s, box-shadow 0.3s;
            flex-grow: 1;
        }

        .comment-input:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
        }

        .btn-approve, .btn-reject {
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 6px;
            font-weight: 600;
            font-size: 1rem;
            cursor: pointer;
            transition: all 0.3s ease;
            white-space: nowrap;
        }

        .btn-approve {
            background-color: #10b981;
            color: white;
        }

        .btn-approve:hover {
            background-color: #0d9f6e;
            transform: translateY(-1px);
        }

        .btn-reject {
            background-color: #ef4444;
            color: white;
        }

        .btn-reject:hover {
            background-color: #dc2626;
            transform: translateY(-1px);
        }

        .booking-actions {
            display: flex;
            gap: 1rem;
            margin-top: 1.5rem;
        }

        .booking-card {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .queue-booking {
            background-color: #fffde7;
        }
        .action-form {
            display: inline-block;
            margin-right: 10px;
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
    <h1>Pending Bookings</h1>

    <c:choose>
        <c:when test="${not empty pendingBookings}">
            <c:forEach items="${pendingBookings}" var="booking">
                <div class="booking-card">
                    <p><strong>Student:</strong> ${booking.studentName}</p>
                    <p><strong>Instructor:</strong> ${booking.instructorName}</p>
                    <p><strong>Date:</strong> ${booking.date}</p>
                    <p><strong>Time:</strong> ${booking.timeSlot}</p>
                    <p class="status-pending">Status: ${booking.status}</p>

                    <div class="booking-actions">
                        <form action="/admin/bookings/approve/${booking.bookingId}" method="post" class="action-form">
                            <input type="text" name="comments" class="comment-input" placeholder="Add comments (optional)">
                            <button type="submit" class="btn-approve">Approve</button>
                        </form>

                        <form action="/admin/bookings/reject/${booking.bookingId}" method="post" class="action-form">
                            <input type="text" name="comments" class="comment-input" placeholder="Add comments (optional)">
                            <button type="submit" class="btn-reject">Reject</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No pending bookings at this time.</p>
        </c:otherwise>
    </c:choose>
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