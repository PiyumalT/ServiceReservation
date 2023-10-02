<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://java.sun.com/jsp/jstl/core">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Reservations</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%--    <link rel="stylesheet" th:href="@{/style.css}" />--%>
    <link href="<c:url value="/resources/notify/notify.css" />" rel="stylesheet">

</head>
<body>
<!-- Include the header -->
<%@ include file="navBar.jsp" %>

<!-- Main content - View Reservations -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="border rounded p-4">
                <h2 class="text-center">Upcoming Reservations</h2>
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

                    <c:forEach items="${serviceRecords}" var="reservation">
                        <tr>
                            <td>${reservation.date}</td>
                            <td>${reservation.time}</td>
                            <td>${reservation.location}</td>
                            <td>${reservation.vehicle_no}</td>
                            <td>${reservation.mileage}</td>
                            <td style="max-width: 17rem;">${reservation.message}</td>
                            <td>
                                <a href="view-reservation-details/${reservation.id}" class="btn btn-info btn-sm">View More</a>
                                <a href="#" class="btn btn-danger btn-sm" onclick="confirmDelete(${reservation.id})">Cancel Reservation</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <!-- Add more reservation rows as needed -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="custom-notify" class="custom-notify">
    <span id="notify-message" class="notify-message"></span>
    <button id="close-notify" class="close-notify" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>

<script src="<c:url value="/resources/notify/notify.js" />"></script>

<script th:inline="javascript">
    window.onload = function() {

        const msg = "${message}";
        if (msg === "Deleted") {
            showNotify("Reservation Deleted Successfully", "success");
        } else if (msg === "Failed") {
            showNotify("Operation Failed", "error");
        }else if (msg === "error"){
            showNotify("Unknown Error Occurred", "error");
        }
    }
</script>

<script>
    function confirmDelete(reservationId) {
        const confirmation = confirm("Are you sure you want to cancel this reservation?");
        if (confirmation) {
            // If the user confirms, navigate to the delete URL
            window.location.href = `/deleteServiceRecord/`+reservationId;
        }
    }
</script>



<%@ include file="footer.jsp" %>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
