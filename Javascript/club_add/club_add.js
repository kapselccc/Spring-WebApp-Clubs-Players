import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));

});


/**
 * Action event handled for updating player info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            alert("Club added successfully!");
            window.location.href = '../club_list/club_list.html';
        }
    };

    xhttp.open("POST", getBackendUrl() + '/clubs', true);

    const request = {
        'name': document.getElementById('name').value,
        'money': parseFloat(document.getElementById('money').value),
        'league': parseInt(document.getElementById('league').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

}

