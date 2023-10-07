<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://java.sun.com/jsp/jstl/core">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View All Reservations</title>
    <link rel="icon" type="image/x-icon" href="<c:url value='/resources/img/icon.png' />">
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
                <h2 class="text-center">View Reservations</h2>
                <div style="width: 100%" class="text-center">
                    <img src="<c:url value="/resources/img/reservations.png" />" width="60" height="60">
                </div>
                <!-- Reservation table -->
                <c:if test="${not empty futureServiceRecords or not empty pastServiceRecords}">
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

                        <c:forEach items="${futureServiceRecords}" var="reservation">
                            <tr>
                                <td>${reservation.date}</td>
                                <td>${reservation.time}</td>
                                <td>${reservation.location}</td>
                                <td>${reservation.vehicle_no}</td>
                                <td>${reservation.mileage}</td>
                                <td style="max-width: 17rem;">${reservation.message}</td>
                                <td>
                                    <a href="view-reservation-details/${reservation.booking_id}" class="btn btn-info btn-sm">View More</a>
                                    <a href="#" class="btn btn-danger btn-sm" onclick="confirmDelete(${reservation.booking_id})">Cancel Reservation</a>
                                </td>
                            </tr>

                        </c:forEach>

                        <c:forEach items="${pastServiceRecords}" var="reservation">
                            <tr>
                                <td>${reservation.date}</td>
                                <td>${reservation.time}</td>
                                <td>${reservation.location}</td>
                                <td>${reservation.vehicle_no}</td>
                                <td>${reservation.mileage}</td>
                                <td style="max-width: 17rem;">${reservation.message}</td>
                                <td>
                                    <a href="view-reservation-details/${reservation.booking_id }" class="btn btn-info btn-sm">View More</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <%-- If futureServiceRecords is null or empty, display a message --%>
                <c:if test="${empty futureServiceRecords and empty pastServiceRecords}">
                    <div class="text-center">
                        <h4 class="my-3" style="color: #a80404">No Reservations to show.</h4>
                    </div>
                </c:if>
                    <div class="text-center">
                        <a href="home" class="btn btn-primary btn-lg mx-3">Go to Home</a>
                        <a href="AddReservations" class="btn btn-success btn-lg mx-3">New Reservation</a>
                    </div>
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
        else if (msg === "Reservation not found"){
            showNotify("Reservation not found", "error");
        }
    }
</script>

<script>
    function confirmDelete(reservationId) {
        const confirmation = confirm("Are you sure you want to cancel this reservation?");
        if (confirmation) {
            // If the user confirms, navigate to the delete URL
            window.location.href = `deleteServiceRecord/`+reservationId;
        }
    }
</script>


<!-- Include the footer -->
<%@ include file="footer.jsp" %>

<!-- Bootstrap JavaScript (jQuery and Popper.js required) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
