<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Make Reservation</title>
    <link rel="icon" type="image/x-icon" href="<c:url value='/resources/img/icon.png' />">

    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="<c:url value="/resources/notify/notify.css" />" rel="stylesheet">

</head>
<body>
<!-- Include the header -->
<%@ include file="navBar.jsp" %>

<!-- Main content - Make Reservation -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="border rounded p-4">
                <img src="<c:url value="/resources/img/new_reserve.png" />" width="70" height="70" style="margin-left: 45%">
                <h2 class="text-center">Make a Reservation</h2>
                <!-- Reservation form -->
                <form action="AddReservation" method="GET" modelAttribute="reservation" id="reservationForm" >
                    <!-- Reservation details -->
                    <div class="form-group">
                        <label for="date">Date of Reservation</label>
                        <input type="date" path="date" class="form-control" id="date" name="date" required>
                        <div id="dateError" style="color: red;"></div>
                    </div>
                    <div class="form-group">
                        <label for="time">Preferred Time</label>
                        <select class="form-control" id="time" name="time" required>
                            <c:forEach var="time" items="${Times}">
                                <option value="${time}">${time}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="location">Preferred Location</label>
                        <select class="form-control" id="location" name="location" required>
                            <c:forEach var="location" items="${Locations}">
                                <option value="${location}">${location}</option>
                            </c:forEach>
                        </select>

                    </div>
                    <div class="form-group">
                        <label for="vehicle_no">Vehicle Registration Number</label>
                        <input type="text" class="form-control"  id="vehicle_no" name="vehicle_no" maxlength="10" placeholder="ABC-1234" required>
                    </div>
                    <div class="form-group">
                        <label for="mileage">Current Mileage</label>
                        <input type="number" class="form-control" id="mileage" name="mileage" required min="0" max="1000000" maxlength="7" value="0">
                    </div>
                    <div class="form-group">
                        <label for="message">Message (Optional)</label>
                        <textarea class="form-control" id="message" name="message" maxlength="250"></textarea>
                    </div>

                    <!-- Submit button -->
                    <div id="submitError" style="color: red; font-weight: bold;" class="my-3 mx-3"></div>
                    <div class="text-center">
                        <button type="button" id ="submitBTN" class="btn btn-primary btn-lg" onclick="validateForm()">Submit Reservation</button>
                        <a href="home" class="btn btn-danger btn-lg mx-3">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/form-validation.js" />"></script>
<script src="<c:url value="/resources/js/newReservationInputs.js" />"></script>
<script src="<c:url value="/resources/js/setmindate.js" />"></script>





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

        if (msg === "Failed") {
            showNotify("Reservation failed. Please try again.", "error");
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
