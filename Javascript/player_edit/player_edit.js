import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    fetchAndDisplayPlayer();
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
            alert("Player edited successfully!");
            window.location.href = '../club_view/club_view.html?club=' + getParameterByName('club');
        }
    };

    xhttp.open("PUT", getBackendUrl() + '/players/' + getParameterByName('player'), true);

    const request = {
        'name': document.getElementById('name').value,
        'club': parseInt(getParameterByName('club')),
        'age': parseInt(document.getElementById('age').value)

    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    
}

function fetchAndDisplayPlayer(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if( this.readyState == 4 && this.status == 200){
            displayPlayer(JSON.parse(this.responseText));
        }
    };
    xhttp.open('GET', getBackendUrl() + '/players/' + getParameterByName('player'))
    xhttp.send();
}

function displayPlayer(player){
    document.getElementById('name').value = player.name;
    document.getElementById('age').value = player.age;
}