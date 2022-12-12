import {getBackendUrl} from '../js/configuration.js';
import { getParameterByName } from '../js/dom_utils.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    fetchAndDisplayClub();
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
        if (this.readyState === 4 && this.status === 202) {
            alert("Club edited successfully!");
            window.location.href = '../club_list/club_list.html';
        }
    };

    xhttp.open("PUT", getBackendUrl() + '/clubs/' + getParameterByName('club'), true);

    const request = {
        'name': document.getElementById('name').value,
        'money': parseFloat(document.getElementById('money').value),
        'league': parseInt(document.getElementById('league').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

}

function fetchAndDisplayClub(){
let xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        displayClub(JSON.parse(this.responseText));
    }
};

xhttp.open('GET', getBackendUrl() + '/clubs/'+ getParameterByName('club') );
xhttp.send();

}

 function displayClub(club){
    document.getElementById('name').value = club.name;
    document.getElementById('money').value = club.money;
    document.getElementById('league').value = club.league;
 }
