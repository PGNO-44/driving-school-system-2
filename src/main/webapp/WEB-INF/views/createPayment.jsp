<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Payment</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="form-container">
    <h1>Create a New Payment</h1>
    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/api/payments/new" method="post">
        <label for="studentId">Student ID :</label>
        <input type="text" id="studentId" name="studentId" placeholder="e.g., S001" required>

        <label for="lessonId">Lesson ID :</label>
        <input type="text" id="lessonId" name="lessonId" placeholder="e.g., L001" required>

        <label for="paymentDate">Payment Date :</label>
        <input type="date" id="paymentDate" name="paymentDate" value="${payment.paymentDate}" required>

        <label for="amount">Amount (Rs:) :</label>
        <input type="number" id="amount" name="amount" step="0.01" placeholder="e.g., Rs:50.00" required>

        <label for="paymentStatus">Payment Status:</label>
        <select id="paymentStatus" name="paymentStatus" required>
            <option value="">Select Status</option>
            <option value="Paid" ${payment.paymentStatus == 'Paid' ? 'selected' : ''}>Paid</option>
            <option value="Pending" ${payment.paymentStatus == 'Pending' ? 'selected' : ''}>Pending</option>
            <option value="Refunded" ${payment.paymentStatus == 'Refunded' ? 'selected' : ''}>Refunded</option>
            <option value="Failed" ${payment.paymentStatus == 'Failed' ? 'selected' : ''}>Failed</option>
        </select>

        <input type="submit" value="Record Payment" class="btn-primary">
    </form>

    <div class="right-actions">
        <a href="${pageContext.request.contextPath}/api/payments" class="btn-secondary">View Payment</a>
    </div>
</div>
</body>
</html>