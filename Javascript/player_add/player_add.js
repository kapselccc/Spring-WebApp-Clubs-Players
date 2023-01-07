import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));
});



function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            alert("Player added successfully!");
            window.location.href = '../club_view/club_view.html?club=' + getParameterByName('club');
        }
    };

    xhttp.open("POST", getBackendUrl() + '/players/', true);

    const request = {
        'name': document.getElementById('name').value,
        'club': parseInt(getParameterByName('club')),
        'age': parseInt(document.getElementById('age').value)

    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    
}

