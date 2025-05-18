<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bookings - Driving School</title>
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

        .bookings-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 2rem;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .bookings-table th, .bookings-table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .bookings-table th {
            background-color: #1e3a8a;
            color: white;
        }

        .bookings-table tr:hover {
            background-color: #f5f5f5;
        }

        .btn-cancel {
            background-color: #dc2626;
            color: white;
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-cancel:hover {
            background-color: #b91c1c;
        }

        .no-bookings {
            text-align: center;
            padding: 2rem;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .status-pending {
            color: #f59e0b; /* Amber */
            font-weight: bold;
        }
        .status-approved {
            color: #10b981; /* Emerald */
            font-weight: bold;
        }
        .status-rejected {
            color: #ef4444; /* Red */
            font-weight: bold;
        }

        .admin-comments {
            font-size: 0.9rem;
            color: #64748b; /* Slate */
            margin-top: 0.25rem;
            font-style: italic;
        }

        /* Adjust table columns */
        .bookings-table th:nth-child(4),
        .bookings-table td:nth-child(4) {
            width: 120px;
        }
    </style>
</head>
<body>
<header>
    <div class="logo">Fast Driving School</div>
</header>

<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/student/bookings/new">New Booking</a></li>
        <li><a href="${pageContext.request.contextPath}/student/bookings/my">My Bookings</a></li>
    </ul>
</nav>

<div class="container">
    <h1>My Bookings</h1>

    <c:choose>
        <c:when test="${not empty bookings}">
            <table class="bookings-table">
                <thead>
                <tr>
                    <th>Instructor</th>
                    <th>Date</th>
                    <th>Time Slot</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bookings}" var="booking">
                    <tr>
                        <td>${booking.instructorName}</td>
                        <td>${booking.date}</td>
                        <td>${booking.timeSlot}</td>
                        <td>
                                <span class="status-${booking.status.toLowerCase()}">
                                        ${booking.status}
                                </span>
                            <c:if test="${not empty booking.adminComments}">
                                <div class="admin-comments">
                                        ${booking.adminComments}
                                </div>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${booking.status == 'PENDING' || booking.status == 'APPROVED'}">
                                <form method="post" action="${pageContext.request.contextPath}/student/bookings/cancel" style="display: inline;">
                                    <input type="hidden" name="instructorName" value="${booking.instructorName}">
                                    <input type="hidden" name="date" value="${booking.date}">
                                    <input type="hidden" name="timeSlot" value="${booking.timeSlot}">
                                    <input type="hidden" name="status" value="${booking.status}">
                                    <button type="submit" class="btn-cancel">Cancel</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="no-bookings">
                <p>You don't have any bookings yet.</p>
                <a href="${pageContext.request.contextPath}/student/bookings/new" class="btn">Book a Lesson</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>