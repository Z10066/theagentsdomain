
document.querySelector('.prompt-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var details = document.getElementById('shorts-detail').value;
    var voiceSelection = document.getElementById('voice-selection').value;
    var voiceType = document.getElementById('voice-type').value;
    var subtitles = document.getElementById('subtitles').value;
    var stockUsage = document.getElementById('stock-usage').value;

    // Here you would handle the form submission, such as sending it to a server or processing it further
    console.log('Creating prompt with details:', details, voiceSelection, voiceType, subtitles, stockUsage);
});
