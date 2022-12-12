import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayPlayer();
    fetchAndDisplayClubName();
});

function fetchAndDisplayPlayer(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayer(JSON.parse(this.responseText));            
        }
    };

    xhttp.open('GET', getBackendUrl() + '/players/' + getParameterByName('player'));
    xhttp.send();
}

function displayPlayer(player) {
    setTextNode('name', player.name);
    setTextNode('age', player.age);  
    setTextNode('playerId', 'Player ID: ' + getParameterByName('player'));
    
}

function fetchAndDisplayClubName(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let club = JSON.parse(this.responseText); 
            setTextNode('club', club.name);
        }
    };
    xhttp.open('GET', getBackendUrl() + '/clubs/' + getParameterByName('club'));
    xhttp.send();
}
