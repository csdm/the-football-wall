let pageNumber = 0;

(() => {
    preloader = document.getElementById('loader');
    preloader.setAttribute('style', 'display:block');

    axios.get(`${API_GET_URL_PAGINATED}?pageNum=${pageNumber}&pageSize=10`)
        .then(response => {
            paginatedTableBuilder(response);
            preloader.setAttribute('style', 'display:none');
        })
        .catch(error => console.error(error));
})(); //self-invoked function


function paginatedTableBuilder(response) {
    const totalPages = response.data.result.payload.totalPages;
    const players = response.data.result.payload.content;
    const tableBody = document.getElementById("paginated-player-table-body");
    const ulPagination = document.getElementById("table-pagination");
    const sizeSelector = document.getElementById("size-selector");

    /**
     * Build the table rows for each player in players
     */
    tableBody.innerHTML = `
        ${players.map(function (player, i) {
        return `<tr class="zoom-row" style="cursor: pointer" onclick="pageReload(${player.playerId})">
                                <td><img src="/api/asset/getImage?playerId=${player.playerId}&isFullImage=false" alt="${player.name} ${player.surname}"width="55"></td>
                                <td>${player.totalScore}</td>
                                <td>${player.surname}</td>
                                <td>${player.name}</td>
                                <td>${player.age}</td>
                                <td>${player.nationality}</td>
                            </tr>`;
    }).join('')}
    `;

    /**
     * build the buttons for each page on a total of totalPages
     */
    ulPagination.innerHTML = `
        ${[...Array(totalPages)].map(function (currentElem, i) {
        return `<li class="page-item" style="cursor: pointer"><a class="btn custom-btn ${pageNumber === i && 'custom-btn-active'}"" onclick="tableReload(${i}, 10)">${(i + 1)}</a></li>`;
    }).join('')}
    `;

}

function tableReload(page, size) {
    preloader = document.getElementById('loader');
    preloader.setAttribute('style', 'display:block');

    axios.get(`${API_GET_URL_PAGINATED}?pageNum=${page}&pageSize=${size}`)
        .then(response => {
            pageNumber = page;
            paginatedTableBuilder(response);
            preloader.setAttribute('style', 'display:none');
        })
        .catch(error => console.error(error));
} 