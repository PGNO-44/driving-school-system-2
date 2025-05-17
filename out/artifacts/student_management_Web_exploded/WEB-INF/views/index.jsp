<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Welcome to Student Management System</h1>
    <div class="mt-4">
        <a href="/login" class="btn btn-primary me-2">Login</a>
        <a href="/students/create" class="btn btn-success">Register Student</a>
        <hr>
        <a href="/students" class="btn btn-outline-secondary">View Students</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>