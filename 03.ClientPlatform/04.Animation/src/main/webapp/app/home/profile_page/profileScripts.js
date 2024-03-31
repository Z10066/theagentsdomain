
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('button').forEach(function(button) {
        button.addEventListener('click', function() {
            var field = this.previousElementSibling;
            if (field && field.tagName === 'INPUT') {
                // Perform save operation
                console.log('Saving', field.id, field.value);
            } else {
                // Perform other button-specific actions
                console.log(this.textContent);
            }
        });
    });
});
