
// Assuming you need functionality for handling the delete actions
document.addEventListener('DOMContentLoaded', function() {
    var deleteButtons = document.querySelectorAll('.delete-workspace-button');
    deleteButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            // Confirm and handle the workspace deletion process
            var workspaceName = this.parentNode.previousSibling.previousSibling.textContent;
            if (confirm('Are you sure you want to delete ' + workspaceName + '?')) {
                console.log('Deleting workspace:', workspaceName);
                // Call to backend to delete workspace
            }
        });
    });

    document.querySelector('.delete-account-button').addEventListener('click', function() {
        // Handle account deletion process
        if (confirm('Are you sure you want to delete your account? This cannot be undone.')) {
            console.log('Deleting account');
            // Call to backend to delete account
        }
    });
});
