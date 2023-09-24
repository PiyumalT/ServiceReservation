<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Make Reservation</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!-- Include the header -->
<%@ include file="header.jsp" %>

<!-- Main content - Make Reservation -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="border rounded p-4">
                <img src="img/new_reserve.png" width="100" height="100" alt="Login img" style="margin-left: 45%">
                <h2 class="text-center">Make a Reservation</h2>
                <!-- Reservation form -->
                <form action="AddReservations" method="POST" modelAttribute="reservation">
                    <!-- Reservation details -->
                    <div class="form-group">
                        <label for="date">Date of Reservation</label>
                        <input type="date" path="date" class="form-control" id="date" name="date" required>
                    </div>
                    <div class="form-group">
                        <label for="time">Preferred Time</label>
                        <select class="form-control" path="time" id="time" name="time" required>
                            <option value="10 AM">10 AM</option>
                            <option value="11 AM">11 AM</option>
                            <option value="12 PM">12 PM</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="location">Preferred Location</label>
                        <select class="form-control" path="location" id="location" name="location" required>
                            <option value="District 1">District 1</option>
                            <option value="District 2">District 2</option>
                            <!-- Add more options as needed -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="vehicle_no">Vehicle Registration Number</label>
                        <input type="text" class="form-control" path="vehicle_no" id="vehicle_no" name="vehicle_no" required>
                    </div>
                    <div class="form-group">
                        <label for="mileage">Current Mileage</label>
                        <input type="number" class="form-control" path="mileage" id="mileage" name="mileage" required min="0" max="1000000">
                    </div>
                    <div class="form-group">
                        <label for="message">Message (Optional)</label>
                        <textarea class="form-control" path="message" id="message" name="message"></textarea>
                    </div>

                    <!-- Submit button -->
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary btn-lg">Submit Reservation</button>
                    </div>
                </form>
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
