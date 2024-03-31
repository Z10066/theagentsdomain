
document.querySelector('.explainer-prompt-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var videoType = document.getElementById('video-type').value;
    var topic = document.getElementById('video-about').value;
    var directions = document.getElementById('creative-directions').value;
    var music = document.getElementById('background-music').value;
    var voiceSelection = document.getElementById('voice-selection').value;
    var voiceType = document.getElementById('voice-type').value;
    var subtitles = document.getElementById('subtitles').value;
    var watermark = document.getElementById('watermark-text').value;
    var stockUsage = document.getElementById('stock-usage').value;

    // Handle the form submission
    console.log('Creating explainer prompt with details:', videoType, topic, directions, music, voiceSelection, voiceType, subtitles, watermark, stockUsage);
});
