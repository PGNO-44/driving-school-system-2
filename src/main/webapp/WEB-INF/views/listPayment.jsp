<!-- payment-history.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment History</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="table-container">
    <h1>Payment History</h1>

    <table border="1" style="margin: auto; width: 80%; text-align: center;">
        <thead>
        <tr>
            <th>Payment ID</th>
            <th>Student ID</th>
            <th>Lesson ID</th>
            <th>Payment Date</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="payment" items="${payments}">
            <tr>
                <td>${payment.paymentId}</td>
                <td>${payment.studentId}</td>
                <td>${payment.lessonId}</td>
                <td>${payment.paymentDate}</td>
                <td>${payment.amount}</td>
                <td>${payment.paymentStatus}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/api/payments/update/${payment.paymentId}">Update</a> |
                    <a href="${pageContext.request.contextPath}/api/payments/delete/${payment.paymentId}" onclick="return confirm('Are you sure you want to delete this payment?')">Delete</a>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty payments}">
            <tr>
                <td colspan="7" style="text-align: center;">No payments found.</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <p style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/api/payments/new">Record a New Payment</a>
    </p>
</div>
</body>
</html>