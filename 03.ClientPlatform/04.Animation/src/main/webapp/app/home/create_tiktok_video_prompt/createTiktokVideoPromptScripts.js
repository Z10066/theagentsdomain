
document.querySelector('.tiktok-video-prompt-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var topic = document.getElementById('tiktok-video-topic').value;
    var voiceSelection = document.getElementById('voice-selection').value;
    var voiceType = document.getElementById('voice-type').value;
    var subtitles = document.getElementById('subtitles').value;
    var stockUsage = document.getElementById('stock-usage').value;

    // Handle the form submission
    console.log('Creating TikTok video prompt with topic:', topic, voiceSelection, voiceType, subtitles, stockUsage);
});
