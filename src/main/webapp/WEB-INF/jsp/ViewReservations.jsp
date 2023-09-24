<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Reservations</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!-- Include the header -->
<%@ include file="header.jsp" %>

<!-- Main content - View Reservations -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="border rounded p-4">
                <h2 class="text-center">View Reservations</h2>
                <!-- Reservation table -->
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Location</th>
                        <th>Vehicle Num</th>
                        <th>Mileage</th>
                        <th>Message</th>
                        <th>Options</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Add reservation rows dynamically here -->
                    <tr>
                        <td>2023-09-30</td>
                        <td>10 AM</td>
                        <td>District 1</td>
                        <td>ABC 123</td>
                        <td>50000 miles</td>
                        <td>Additional notes</td>
                        <td>
                            <a href="view-reservation-details.jsp" class="btn btn-info btn-sm">View More</a>
                            <a href="#" class="btn btn-danger btn-sm">Cancel</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2023-09-31</td>
                        <td>10 AM</td>
                        <td>District 2</td>
                        <td>ABC 123</td>
                        <td>50000 miles</td>
                        <td>Additional notes</td>
                        <td>
                            <a href="view-reservation-details.jsp" class="btn btn-info btn-sm">View More</a>
                            <a href="#" class="btn btn-danger btn-sm">Cancel</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2023-09-30</td>
                        <td>10 AM</td>
                        <td>District 1</td>
                        <td>ABC 123</td>
                        <td>50000 miles</td>
                        <td>Additional notes</td>
                        <td>
                            <a href="view-reservation-details.jsp?id=1" class="btn btn-info btn-sm">View More</a>
                            <a href="#" class="btn btn-danger btn-sm">Cancel</a>
                        </td>
                    </tr>
                    <!-- Add more reservation rows as needed -->
                    </tbody>
                </table>
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
