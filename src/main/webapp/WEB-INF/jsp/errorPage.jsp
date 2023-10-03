<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error</title>
    <link rel="icon" type="image/x-icon" href="<c:url value='/resources/img/icon.png' />">

    <!-- Add Bootstrap CSS link -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<%@ include file="navBar.jsp" %>
<body>
<div class="container mt-5">
    <div class="error-container text-center">
        <img src="<c:url value="/resources/img/error.png" />" width="100" height="100">
        <h1 class="mt-3">Oops! Something went wrong.</h1>
        <p class="lead">We apologize for the inconvenience. The error details are as follows:</p>
        <p class="font-weight-bold">Error Code: ${statusCode}</p>
        <%-- Check if errorMessage is not null or empty before displaying --%>
        <c:if test="${not empty errorMessage}">
            <p class="font-weight-bold">Error Message: ${errorMessage}</p>
        </c:if>
        <a href="/home" class="btn btn-primary btn-lg mx-3">Go to Home</a>
    </div>
</div>
<%@ include file="footer.jsp" %>

<!-- Add Bootstrap JS scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
