<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="icon" type="image/x-icon" href="<c:url value='/resources/img/icon.png' />">
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!-- Include the header -->
<%@ include file="navBar.jsp" %>

<!-- Main content - User Profile -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="border rounded p-4 text-center">
                <h2 class="text-center">User Profile</h2>
                <img src="<c:url value="/resources/img/profile.png" />" width="100" height="100">

                <!-- Display user profile information -->
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Username:</strong>
                    </div>
                    <div class="col-md-6 text-md-left">
                        ${userProfile.username}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Name:</strong>
                    </div>
                    <div class="col-md-6 text-md-left">
                        ${userProfile.name}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Email:</strong>
                    </div>
                    <div class="col-md-6 text-md-left">
                        ${userProfile.email}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Contact Number:</strong>
                    </div>
                    <div class="col-md-6 text-md-left">
                        ${userProfile.phoneNumber}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Country:</strong>
                    </div>
                    <div class="col-md-6 text-md-left">
                        ${userProfile.country}
                    </div>
                </div>

                <!-- Add more user profile information as needed -->

                <div class="text-center mt-3">
                    <a href="home" class="btn btn-primary">Go Back</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Include the footer -->
<%@ include file="footer.jsp" %>

<!-- Bootstrap JavaScript (jQuery and Popper.js required) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
