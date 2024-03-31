
// JavaScript to handle option button click to toggle 'selected' class
document.addEventListener('DOMContentLoaded', function() {
    var optionButtons = document.querySelectorAll('.option-button');
    optionButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            // Deselect other buttons in the same group
            this.parentNode.querySelectorAll('.option-button').forEach(function(btn) {
                btn.classList.remove('selected');
            });
            // Select clicked button
            this.classList.add('selected');
        });
    });

    // Handlers for edit and continue buttons
    document.querySelector('.edit-button').addEventListener('click', function() {
        console.log('Edit prompt clicked');
        // Implement the edit functionality
    });
    
    document.querySelector('.continue-button').addEventListener('click', function() {
        console.log('Continue clicked');
        // Implement the continue functionality
    });
});
