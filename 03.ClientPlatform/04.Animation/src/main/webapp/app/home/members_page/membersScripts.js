
document.addEventListener('DOMContentLoaded', function() {
    var membersData = [
        { name: 'gun tei', email: 'tei95@gmail.com', role: 'Owner' }
        // More members could be added here
    ];

    var tbody = document.querySelector('.members-table tbody');
    membersData.forEach(function(member) {
        var tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${member.name} <br> <span class="email">${member.email}</span></td>
            <td>${member.role}</td>
            <td>-</td>
        `;
        tbody.appendChild(tr);
    });
});
