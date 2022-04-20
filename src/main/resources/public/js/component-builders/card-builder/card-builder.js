const getTopTen = () => {
    preloader = document.getElementById('loader');
    preloader.setAttribute('style', 'display:block');

    axios.get(API_GET_URL_TOPTEN)
        .then(response => {
            console.log(response);
            buildCards(response);
            preloader.setAttribute('style', 'display:none');
        })
        .catch(error => console.error(error));
};

getTopTen();

function buildCards(response) {
    const players = response.data.result.payload;
    const row = document.getElementById("card-row");

    for (const player of players) {
        let playerId = player.playerId;

        var newCol = document.createElement("div");
        newCol.setAttribute("class", "col-sm");

        let newCard = document.createElement("div");
        newCard.setAttribute("class", "card my-1");
        newCard.setAttribute("style", "width: 18rem;");

        let img = document.createElement("img");
        img.setAttribute("class", "card-img-top");
        img.setAttribute("src", `/api/asset/getImage?playerId=${player.playerId}&isFullImage=true`);
        img.setAttribute("alt", `${player.name} ${player.surname}`);

        let cardBody = document.createElement("div");
        cardBody.setAttribute("class", "card-body");

        let cardTitle = document.createElement("h5")
        cardTitle.setAttribute("class", "card-title");
        cardTitle.innerText = player.name + " " + player.surname;

        let cardText = document.createElement("p");
        cardText.setAttribute("class", "card-text");

        let textRole = document.createElement("p");
        textRole.setAttribute("class", "text-start my-0");
        textRole.innerHTML = `<strong>Role</strong>: ${player.role}`;

        let textNationality = document.createElement("p");
        textNationality.setAttribute("class", "text-start my-0");
        textNationality.innerHTML = `<strong>Nationality</strong>: ${player.nationality}`;

        let textAge = document.createElement("p");
        textAge.setAttribute("class", "text-start my-0");
        textAge.innerHTML = `<strong>Age</strong>: ${player.age}`;

        let button = document.createElement("button");
        //button.id = "modalTrigger";
        button.setAttribute("type", "button");
        button.setAttribute("class", "btn btn-lg custom-btn");
        button.setAttribute("data-bs-toggle", "modal");
        button.setAttribute("data-bs-target", "#showPlayer");
        button.setAttribute("onclick", `buildModalByPlayerById(${playerId})`);
        button.innerText = "More";

        cardText.appendChild(textRole);
        cardText.appendChild(textNationality);
        cardText.appendChild(textAge);
        cardBody.appendChild(cardTitle);
        cardBody.appendChild(cardText);
        cardBody.appendChild(button);
        newCard.appendChild(img);
        newCard.appendChild(cardBody);
        newCol.appendChild(newCard);
        row.appendChild(newCol);
    };
}