
document.querySelector('.instagram-reel-prompt-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var topic = document.getElementById('instagram-reel-topic').value;
    var voiceSelection = document.getElementById('voice-selection').value;
    var voiceType = document.getElementById('voice-type').value;
    var subtitles = document.getElementById('subtitles').value;
    var istockUsage = document.getElementById('istock-usage').value;

    // Handle the form submission
    console.log('Creating Instagram reel prompt with topic:', topic, voiceSelection, voiceType, subtitles, istockUsage);
});
