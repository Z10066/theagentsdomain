
// Example data structure for history entries. This would likely come from a server in a real application.
var historyData = [
    {
        date: 'Tue Feb 27 2024',
        entries: [
            { time: '14:33', title: 'Chapter 5: A New Future - The Unfolding Story of "Domain of Agents"' },
            { time: '14:16', title: 'Chapter 5: A New Future - Stability and Harmony in "Agents' Domain"' },
            // ...more entries
        ]
    },
    // ...more dates with entries
];

function generateHistory() {
    var historyContainer = document.querySelector('.history-entries');
    historyData.forEach(day => {
        var dateElem = document.createElement('div');
        dateElem.classList.add('date');
        dateElem.textContent = day.date;

        historyContainer.appendChild(dateElem);

        day.entries.forEach(entry => {
            var entryElem = document.createElement('div');
            entryElem.classList.add('entry');
            entryElem.innerHTML = `<span class="time">${entry.time}</span> ${entry.title}`;
            historyContainer.appendChild(entryElem);
        });
    });
}

// Call generateHistory on page load
generateHistory();
