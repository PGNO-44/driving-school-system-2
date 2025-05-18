<!-- update-payment.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Payment</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="update-container">
    <h1>Update Payment</h1>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/api/payments/update/${payment.paymentId}" method="post">

        <label for="studentId">Student ID:</label>
        <input type="text" id="studentId" name="studentId" value="${payment.studentId}" placeholder="e.g., S001" required>

        <label for="lessonID">Lesson ID:</label>
        <input type="text" id="lessonID" name="lessonId" value="${payment.lessonId}" placeholder="e.g., L001" required>

        <label for="paymentDate">Payment Date:</label>
        <input type="date" id="paymentDate" name="paymentDate" value="${payment.paymentDate}" required>

        <label for="amount">Amount (Rs.):</label>
        <input type="number" id="amount" name="amount" value="${payment.amount}" step="0.01" placeholder="e.g., 2500.00" required>

        <label for="paymentStatus">Payment Status:</label>
        <select id="paymentStatus" name="paymentStatus" required>
            <option value="">Select Status</option>
            <option value="Paid" ${payment.paymentStatus == 'Paid' ? 'selected' : ''}>Paid</option>
            <option value="Pending" ${payment.paymentStatus == 'Pending' ? 'selected' : ''}>Pending</option>
            <option value="Refunded" ${payment.paymentStatus == 'Refunded' ? 'selected' : ''}>Refunded</option>
            <option value="Failed" ${payment.paymentStatus == 'Failed' ? 'selected' : ''}>Failed</option>
        </select>

        <input type="submit" value="Update Payment" style="margin-top: 20px;">
    </form>

    <p style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/api/payments">Back to Payment History</a>
    </p>
</div>
</body>
</html>
