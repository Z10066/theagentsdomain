
document.querySelector('.video-prompt-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var videoType = document.getElementById('video-type').value;
    var script = document.getElementById('script').value;
    var music = document.getElementById('background-music').value;
    var voiceSelection = document.getElementById('voice-selection').value;
    var voiceType = document.getElementById('voice-type').value;

    // Handle the form submission
    console.log('Creating video prompt with script:', videoType, script, music, voiceSelection, voiceType);
});
