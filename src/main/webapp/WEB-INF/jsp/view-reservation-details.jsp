<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Details</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .col-md-6{
            padding-left: 5px !important;
            padding-right: 5px !important;
            border-bottom: solid 1px #0000001a;
        }
    </style>
</head>
<body>
<!-- Include the header -->
<%@ include file="header.jsp" %>

<!-- Main content - Reservation Details -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="border rounded p-4">
                <h2 class="text-center">Reservation Details</h2>
                <!-- Display full details of the reservation here -->
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>ID:</strong>
                    </div>
                    <div class="col-md-6">
                        ${reservationDetails.id}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Date:</strong>
                    </div>
                    <div class="col-md-6">
                        ${reservationDetails.date}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Time:</strong>
                    </div>
                    <div class="col-md-6">
                        ${reservationDetails.time}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Location:</strong>
                    </div>
                    <div class="col-md-6">
                        ${reservationDetails.location}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Vehicle Num:</strong>
                    </div>
                    <div class="col-md-6">
                        ${reservationDetails.vehicle_no}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Mileage:</strong>
                    </div>
                    <div class="col-md-6">
                        ${reservationDetails.mileage}
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6 text-md-right">
                        <strong>Message:</strong>
                    </div>
                    <div class="col-md-6">
                        ${reservationDetails.message}
                    </div>
                </div>

                <!-- Add more rows as needed -->
                <div class="text-center">
                    <a href="ViewReservations" class="btn btn-primary btn-lg mt-3">Back to Reservations</a>
                    <a href="view-reservations.jsp" class="btn btn-danger btn-lg mt-3">Cancel Reservation</a>
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
