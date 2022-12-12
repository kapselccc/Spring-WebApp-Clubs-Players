import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayClubs();
});

/**
 * Fetches all clubs and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayClubs() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayClubs(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/clubs', true);    
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display clubs.
 *
 * @param {{clubs: string[]}} clubs
 */
function displayClubs(clubs) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    clubs.clubs.forEach(club => {
        tableBody.appendChild(createTableRow(club));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} club
 * @returns {HTMLTableRowElement}
 */
function createTableRow(club) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(club.name));
    tr.appendChild(createLinkCell('view', '../club_view/club_view.html?club=' + club.clubId));
    tr.appendChild(createLinkCell('edit','../club_edit/club_edit.html?club=' + club.clubId));
    tr.appendChild(createButtonCell('delete', () => deleteClub(club.clubId)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } club to be deleted
 */
function deleteClub(club) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayClubs();
        }
    };
    
    xhttp.open("DELETE", getBackendUrl() + '/clubs/' + club, true);
    xhttp.send();
}
