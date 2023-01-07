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
    fetchAndDisplayClub();
    fetchAndDisplayPlayers();
    let button = document.getElementById('submit-button');
    button.addEventListener('submit', event => updateInfoAction(event));
});


function fetchAndDisplayPlayers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayers(JSON.parse(this.responseText));
            
        }
    };
    xhttp.open("GET", getBackendUrl() + '/clubs/' + getParameterByName('club') + '/players',true);
    xhttp.send();
}

function updateInfoAction(event){
    event.preventDefault();
    window.location.href = "../player_add/player_add.html?club=" + getParameterByName('club');
}

function displayPlayers(players) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    players.players.forEach(player => {
        tableBody.appendChild(createTableRow(player));
    })
}


function createTableRow(player) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(player.name));
    tr.appendChild(createLinkCell('view', '../player_view/player_view.html?player=' + player.playerId + '&club=' + getParameterByName('club')));
    tr.appendChild(createLinkCell('edit', '../player_edit/player_edit.html?player=' + player.playerId + '&club=' + getParameterByName('club')));
    tr.appendChild(createButtonCell('delete', () => deletePlayer(player.playerId)));
    return tr;
}


function deletePlayer(player) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPlayers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() +'/players/' + player, true);
    xhttp.send();
}


function fetchAndDisplayClub() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayClub(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/clubs/' + getParameterByName('club'), true);
    xhttp.send();
}


function displayClub(club) {

    setTextNode('clubId', 'Club ID: ' + getParameterByName('club'));
    setTextNode('name', club.name);
    setTextNode('money', club.money);
    setTextNode('league', club.league);
}




