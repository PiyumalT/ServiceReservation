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
    <style>
        /* Add CSS styles to style your custom notify messages */
        .custom-notify {
            position: fixed;
            top: 0;
            right: 0;
            margin: 20px;
            padding: 10px 20px;
            z-index: 9999;
            display: none;
            border-radius: 5px;
        }

        .custom-notify.success {
            background-color: #4CAF50;
            color: #fff;
        }

        .custom-notify.error {
            background-color: #f44336;
            color: #fff;
        }

    </style>
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

                    <c:forEach items="${serviceRecords}" var="reservation">
                        <tr>
                            <td>${reservation.date}</td>
                            <td>${reservation.time}</td>
                            <td>${reservation.location}</td>
                            <td>${reservation.vehicle_no}</td>
                            <td>${reservation.mileage}</td>
                            <td>${reservation.message}</td>
                            <td>
                                <a href="view-reservation-details/${reservation.id}" class="btn btn-info btn-sm">View More</a>
                                <a href="/deleteServiceRecord/${reservation.id}" class="btn btn-danger btn-sm">Cancel Reservation</a>
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

<!-- Include the following HTML structure in your page where you want to display messages -->
<div id="custom-notify" class="custom-notify"></div>
<script src="notify.js"></script>
<script>
    function showNotify(message, type) {
        const notify = document.getElementById("custom-notify");

        // Add the appropriate CSS class based on the message type
        if (type === "success") {
            notify.classList.add("success");
        } else if (type === "error") {
            notify.classList.add("error");
        }

        // Set the message text and display the notify
        notify.textContent = message;
        notify.style.display = "block";

        // Automatically hide the notify after a few seconds (adjust the duration as needed)
        setTimeout(() => {
            notify.style.display = "none";
            notify.classList.remove("success", "error");
        }, 5000); // 5000 milliseconds (5 seconds)
    }
</script>


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





<!-- Include the footer -->
<%@ include file="footer.jsp" %>

<!-- Bootstrap JavaScript (jQuery and Popper.js required) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
