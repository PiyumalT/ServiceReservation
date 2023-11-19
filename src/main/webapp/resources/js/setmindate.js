document.addEventListener('DOMContentLoaded', function () {
    // Get the current date in the format YYYY-MM-DD
    const currentDate = new Date().toISOString().split('T')[0];

    // Set the min attribute of the date input
    document.getElementById('date').min = currentDate;
});
