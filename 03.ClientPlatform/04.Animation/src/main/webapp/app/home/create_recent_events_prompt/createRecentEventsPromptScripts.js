
document.querySelector('.recent-events-prompt-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var topic = document.getElementById('recent-event-topic').value;
    var source = document.getElementById('news-source').value;
    var voiceSelection = document.getElementById('voice-selection').value;
    var voiceType = document.getElementById('voice-type').value;
    var subtitles = document.getElementById('subtitles').value;
    var stockUsage = document.getElementById('stock-usage').value;

    // Handle the form submission
    console.log('Creating recent events prompt with details:', topic, source, voiceSelection, voiceType, subtitles, stockUsage);
});
