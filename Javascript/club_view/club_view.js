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

/**
 * Fetches all clubs and modifies the DOM tree in order to display them.
 */
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
/**
 * Updates the DOM tree in order to display players.
 *
 * @param {{players: {id: number, name:string}[]}} players
 */
function displayPlayers(players) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    players.players.forEach(player => {
        tableBody.appendChild(createTableRow(player));
    })
}


/**
 * Creates single table row for entity.
 *
 * @param {{playerId: number, name: string}} player
 * @returns {HTMLTableRowElement}
 */
function createTableRow(player) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(player.name));
    tr.appendChild(createLinkCell('view', '../player_view/player_view.html?player=' + player.playerId + '&club=' + getParameterByName('club')));
    tr.appendChild(createLinkCell('edit', '../player_edit/player_edit.html?player=' + player.playerId + '&club=' + getParameterByName('club')));
    tr.appendChild(createButtonCell('delete', () => deletePlayer(player.playerId)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} player to be deleted
 */
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


/**
 * Fetches single club and modifies the DOM tree in order to display it.
 */
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

/**
 * Updates the DOM tree in order to display club.
 *
 * @param {{clubId: number, name: string, money:string}} club
 */
function displayClub(club) {

    setTextNode('clubId', 'Club ID: ' + getParameterByName('club'));
    setTextNode('name', club.name);
    setTextNode('money', club.money);
    setTextNode('league', club.league);
}




