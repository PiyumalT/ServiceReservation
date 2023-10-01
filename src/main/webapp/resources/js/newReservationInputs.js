document.getElementById('date').onchange = function() {
    const selectedDate = new Date(this.value);
    const currentDate = new Date();
    const dateError = document.getElementById('dateError');

    // Check if the selected date is after the current date
    if (selectedDate <= currentDate) {
        dateError.textContent = 'Please select a date after the current date.';
    } else {
        dateError.textContent = '';

        // Check if the selected date is a Sunday (day 0)
        if (selectedDate.getDay() === 0) {
            dateError.textContent = 'Please select a date that is not a Sunday.';
        }

    }
};

