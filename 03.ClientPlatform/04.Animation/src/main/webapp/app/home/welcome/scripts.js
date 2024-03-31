
// scripts.js
document.addEventListener('DOMContentLoaded', () => {
    const signUpButton = document.querySelector('.sign-up a');
    const emailInput = document.querySelector('.email-input');
    const continueButton = document.querySelector('.continue-button');

    signUpButton.addEventListener('click', (e) => {
        e.preventDefault();
        // Logic for signing up
    });

    continueButton.addEventListener('click', () => {
        const email = emailInput.value;
        // Logic for continuing with email
    });
});
