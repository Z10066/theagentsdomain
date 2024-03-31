
// Script to simulate progress
document.addEventListener('DOMContentLoaded', function() {
    const progressBar = document.querySelector('.progress-bar');
    const progressPercent = document.querySelector('.progress-percent');
    let progress = 0;

    const interval = setInterval(() => {
        if(progress < 100) {
            progress++;
            progressBar.style.width = progress + '%';
            progressPercent.textContent = progress + '%';
        } else {
            clearInterval(interval);
            // Do something when the process is complete
        }
    }, 100); // Adjust time as needed
});
