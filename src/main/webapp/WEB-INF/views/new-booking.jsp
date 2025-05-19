<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Booking - Fast Driving School</title>
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
        .booking-form {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: bold;
            color: #1e3a8a;
        }

        input, select {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        .btn-submit {
            background-color: #2563eb;
            color: white;
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            font-weight: bold;
        }

        .btn-submit:hover {
            background-color: #1e40af;
        }
    </style>
</head>
<body>
<header>
    <div class="logo">Fast Driving School</div>
</header>

<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/student/bookings/my">My Bookings</a></li>
    </ul>
</nav>

<div class="container">
    <h1>New Booking</h1>

    <div class="booking-form">
        <form method="post" action="${pageContext.request.contextPath}/student/bookings/create">
            <div class="form-group">
                <label for="instructorName">Instructor:</label>
                <select id="instructorName" name="instructorName" required>
                    <option value="">Select Instructor</option>
                    <c:forEach items="${instructors}" var="instructor">
                        <option value="${instructor.firstName} ${instructor.lastName}">
                                ${instructor.firstName} ${instructor.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="date">Date:</label>
                <input type="date" id="date" name="date" required>
            </div>

            <div class="form-group">
                <label for="timeSlot">Time Slot:</label>
                <select id="timeSlot" name="timeSlot" required>
                    <option value="">Select Time Slot</option>
                    <option value="09:00-10:00">09:00-10:00</option>
                    <option value="10:00-11:00">10:00-11:00</option>
                    <option value="11:00-12:00">11:00-12:00</option>
                    <option value="13:00-14:00">13:00-14:00</option>
                    <option value="14:00-15:00">14:00-15:00</option>
                    <option value="15:00-16:00">15:00-16:00</option>
                </select>
            </div>

            <button type="submit" class="btn-submit">Book Lesson</button>
        </form>
    </div>
</div>
</body>
</html>