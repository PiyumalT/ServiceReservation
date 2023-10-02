<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!-- Include the header -->
<%@ include file="navBar.jsp" %>

<!-- Main content - Home Page -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="border rounded p-4 text-center">
                <h2>Welcome ${name}</h2>
                <img src="<c:url value="/resources/img/home.png" />" width="100" height="100" alt="Login img">
                <p>What would you like to do?</p>
                <div class="row justify-content-center"> <!-- Center align the buttons -->
                    <div class="col-md-5 mb-3">
                        <a href="view-profile" class="btn btn-info btn-lg btn-block p-3">View Profile info</a>
                    </div>
                    <div class="col-md-5 mb-3">
                        <a href="AddReservations" class="btn btn-success btn-lg btn-block p-3">Make a Reservation</a>
                    </div>
                    <div class="col-md-5 mb-3">
                        <a href="ViewReservations" class="btn btn-primary btn-lg btn-block p-3">View Reservations</a>
                    </div>
                    <div class="col-md-5 mb-3">
                        <a href="futureReservations" class="btn btn-warning btn-lg btn-block p-3">Upcoming Reservations</a>
                    </div>
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
