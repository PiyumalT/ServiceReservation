<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!-- Include the header -->

<jsp:include page="header.jsp" />
<!-- Main content - Login Box -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="border rounded p-4 text-center">
                <!-- Add your login form here -->
                <h2>Login</h2>
                <p>Log in using a third-party authentication service:</p>
                <img src="img/login.png" width="100" height="100" alt="Login img">
                <a href="home.jsp" class="btn btn-primary btn-lg btn-block mt-5">Login with Third-Party Service</a>
            </div>
            <!-- Error Message Div -->
            <%
                String error = (String) request.getAttribute("error");
                if (error != null && error.equals("login service error")) {
            %>
            <div class="alert alert-danger mt-3" role="alert">
                Auth Service error, Try again later.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
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
