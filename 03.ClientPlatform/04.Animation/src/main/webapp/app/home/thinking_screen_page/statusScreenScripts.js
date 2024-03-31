
// JavaScript code, if needed for dynamic status updates
document.addEventListener('DOMContentLoaded', function() {
    // Example: dynamically update status after a certain event or timeout
    setTimeout(() => {
        const stillThinkingText = document.querySelector('.status-item .status-text:nth-child(2)');
        stillThinkingText.textContent = 'Processing complete'; // Update text
        const spinner = document.querySelector('.loading-spinner');
        spinner.style.display = 'none'; // Hide spinner
    }, 5000); // After 5 seconds for example
});
