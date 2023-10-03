function validateForm() {
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;
    const location = document.getElementById("location").value;
    const vehicleNo = document.getElementById("vehicle_no").value;
    const mileage = document.getElementById("mileage").value;
    const message = document.getElementById("message").value;

    // Validation logic
    let isValid = true;
    const submitError = document.getElementById("submitError");
    submitError.innerHTML = ""; // Clear previous error messages

    if (!date) {
        submitError.innerHTML += "Date is required.<br>";
        isValid = false;
    }
    const selectedDate = new Date(date);
    const currentDate = new Date();
    if (selectedDate <= currentDate) {
        submitError.innerHTML += "Please select a date after the current date.<br>";
        isValid = false;
    } else if (selectedDate.getDay() === 0) {
        submitError.innerHTML += "Please select a date that is not a Sunday.<br>";
        isValid = false;
    }

    if (!time) {
        submitError.innerHTML += "Time is required.<br>";
        isValid = false;
    }
    // const validTimes = ["10 AM", "11 AM", "12 PM"];
    // if (!validTimes.includes(time)) {
    //     submitError.innerHTML += "Please select a valid time (10 AM, 11 AM, or 12 PM).<br>";
    //     isValid = false;
    // }

    if (!location) {
        submitError.innerHTML += "Location is required.<br>";
        isValid = false;
    }
    // const validDistricts = ["District 1", "District 2"];
    // if (!validDistricts.includes(location)) {
    //     submitError.innerHTML += "Please select a valid location (District 1, District 2, etc.).<br>";
    //     isValid = false;
    // }

    if (!vehicleNo) {
        submitError.innerHTML += "Vehicle Registration Number is required.<br>";
        isValid = false;
    }
    if (vehicleNo.length > 10) {
        submitError.innerHTML += "Vehicle Registration Number not exceed 10 characters.<br>";
        isValid = false;
    }

    if (!mileage) {
        submitError.innerHTML += "Mileage is required.<br>";
        isValid = false;
    } else if (isNaN(mileage) || mileage < 0 || mileage > 1000000) {
        submitError.innerHTML += "Mileage must be a number between 0 and 1,000,000.<br>";
        isValid = false;
    }

    if (message.length > 250) {
        submitError.innerHTML += "Message should not exceed 250 characters.<br>";
        isValid = false;
    }

    // If all validations pass, submit the form
    if (isValid) {
        document.getElementById('reservationForm').submit();
    } else {
        return false; // Form submission will be prevented
    }
}
