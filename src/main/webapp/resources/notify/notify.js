function showNotify(message, type) {
    const notify = document.getElementById("custom-notify");
    const messageElement = document.getElementById("notify-message");
    const closeBtn = document.getElementById("close-notify");

    // Add the appropriate CSS class based on the message type
    if (type === "success") {
        notify.style.backgroundColor = "#4CAF50";
    } else if (type === "error") {
        notify.style.backgroundColor = "#f44336";
    }

    // Set the message text and display the notify
    messageElement.textContent = message;
    notify.style.display = "block";

    // Automatically hide the notify after a few seconds (adjust the duration as needed)
    let timeoutId;
    const duration = 5000; // 5 seconds
    const closeNotify = () => {
        clearTimeout(timeoutId);
        notify.style.display = "none";
        notify.style.backgroundColor = "#333"; // Reset background color
    };

    timeoutId = setTimeout(closeNotify, duration);

    // Pause the timeout when the mouse is over the notification
    notify.addEventListener("mouseenter", () => {
        clearTimeout(timeoutId);
    });

    // Resume the timeout when the mouse leaves the notification
    notify.addEventListener("mouseleave", () => {
        timeoutId = setTimeout(closeNotify, duration);
    });

    // Add an event listener to the close button
    closeBtn.addEventListener("click", () => {
        closeNotify();
    });
}
