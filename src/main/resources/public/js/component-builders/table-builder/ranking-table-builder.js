const getPlayer = () => {
  preloader = document.getElementById('loader');
  preloader.setAttribute('style', 'display:block');

  axios.get(API_GET_URL_ALL)
    .then(response => {
      buildTable(response);
      preloader.setAttribute('style', 'display:none');
    })
    .catch(error => console.error(error));
};

getPlayer();

function buildTable(response) {
  const players = response.data.result.payload;
  const table = document.getElementById("player-table");
  const tableBody = document.getElementById("player-table-body");

  for (const player of players) {
    let newRow = document.createElement("tr");
    newRow.className = "zoom-row";
    newRow.setAttribute("onclick", `pageReload(${player.playerId})`);
    newRow.setAttribute("style", "cursor: pointer");

    let cellImg = document.createElement("td");
    //cellImg.innerHTML = `<img src="https://media.contentapi.ea.com/content/dam/ea/fifa/fifa-21/ratings-collective/f20assets/player-headshots/${player.playerId}.png" alt="${player.name} ${player.surname}"width="55">`;
    cellImg.innerHTML = `<img src="/api/asset/getImage?playerId=${player.playerId}&isFullImage=false" alt="${player.name} ${player.surname}"width="55">`;
    newRow.appendChild(cellImg);

    let cellTotalScore = document.createElement("td");
    cellTotalScore.innerText = player.totalScore;
    newRow.appendChild(cellTotalScore);

    let cellSurname = document.createElement("td");
    cellSurname.innerText = player.surname;
    newRow.appendChild(cellSurname);

    let cellName = document.createElement("td");
    cellName.innerText = player.name;
    newRow.appendChild(cellName);

    let cellAge = document.createElement("td");
    cellAge.innerText = player.age;
    newRow.appendChild(cellAge);

    let cellNationality = document.createElement("td");
    cellNationality.innerText = player.nationality;
    newRow.appendChild(cellNationality);

    tableBody.appendChild(newRow);
  };
  table.appendChild(tableBody);
}