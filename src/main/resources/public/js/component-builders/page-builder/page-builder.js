(() => {
    preloader = document.getElementById('loader');
    preloader.setAttribute('style', 'display:block');

    const urlParams = new URLSearchParams(window.location.search);
    const playerId = urlParams.get('playerId');

    console.log(playerId);

    axios.get(`${API_GET_URL_BY_ID}/${playerId}`)
        .then(response => {
            pageBuilder(response);    
            preloader.setAttribute('style', 'display:none');        
        })
        .catch(error => console.error(error));
})(); //self-invoked function

const progressBarColorKeeper = (value) => {
    if (value > 0 && value <= 33) return "bg-secondary";
    if (value > 33 && value <= 66) return "bg-danger";
    if (value > 66 && value <= 80) return "bg-warning";
    if (value > 80 && value <= 100) return "bg-success";
}

function pageBuilder(response) {
    const player = response.data.result.payload; //list[0]
    const pageTitle = document.getElementById('player-data');
    pageTitle.innerHTML = `
    <div class="row">
    <div class="col-sm-auto m-0 shadow-lg p-0 mb-5 bg-body rounded"
        style="background-image: url('https://ae01.alicdn.com/kf/HTB1SajMSpXXXXc8XVXXq6xXFXXXz/8x8FT-spot-luce-notturna-calcio-campo-da-calcio-piattaforma-stadio-foto-personalizzata-sfondo-Studio-sfondi-vinile.jpg_Q90.jpg_.webp'); background-size: cover;">
        <img class="" width="200"
        src="/api/asset/getImage?playerId=${player.playerId}&isFullImage=false" alt="${player.name} ${player.surname}">
    </div>
    <div class="col ms-3">
        <div class="row">
            <div class="col-sm-auto">
                <h2>${player.name} ${player.surname}</h2>
            </div>
            <div class="col-sm-auto d-flex align-items-center">
                <h6>${player.role}</h6>
            </div>
            <div class="col-sm-auto d-flex align-items-center">
                <h6>${player.totalScore}</h6>
            </div>
        </div>
        <div class="row mt-0">
            <div class="col-sm-auto pe-1">
            <img src="https://cdn.countryflags.com/thumbs/${player.nationality.toLowerCase()}/flag-800.png" width="35" alt="${player.nationality}">
            </div>
            <div class="col ps-1">
                <h5>${player.nationality}</h5>
            </div>
        </div>
        <div class="row mt-0">
            <div class="col-sm-auto">
                <p><strong>Age:</strong> ${player.age}</p>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-sm-auto">
                <h5>Skills</h5>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-sm-auto">
                <p class="mb-1"><strong>Skill Moves:</strong> ${player.skills.skillMoves}</p>
                <p class="mb-1"><strong>Weak Foot:</strong> ${player.skills.weakFoot}</p>
            </div>
            <div class="col-sm-auto">
                <p class="mb-1"><strong>Att Work Rate:</strong> ${player.skills.attWorkRate}</p>
                <p class="mb-1"><strong>Def Work Rate:</strong> ${player.skills.defWorkRate}</p>
            </div>
            <div class="col-sm-auto">
                <p class="mb-1"><strong>Preferred Foot:</strong> ${player.skills.preferredFoot}</p>
            </div>
        </div>
    </div>
</div>

<div class="row mt-1">
            <div class="col">
                <div class="row">
                    <h5>Statistics</h5>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Acceleration: ${player.statistics.acceleration}</label>
                            <div class="progress">
                                <div class="progress-bar ${progressBarColorKeeper(player.statistics.acceleration)}"
                                    role="progressbar" style="width: ${player.statistics.acceleration}%;"
                                    aria-valuenow="${player.statistics.acceleration}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Sprint speed: ${player.statistics.sprintSpeed}</label>
                            <div class="progress">
                                <div class="progress-bar ${progressBarColorKeeper(player.statistics.sprintSpeed)} role="
                                    progressbar" style="width: ${player.statistics.sprintSpeed}%;"
                                    aria-valuenow="${player.statistics.sprintSpeed}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Positioning: ${player.statistics.positioning}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.positioning)} role="
                                    progressbar" style="width: ${player.statistics.positioning}%;"
                                    aria-valuenow="${player.statistics.positioning}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Finishing: ${player.statistics.positioning}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.positioning)} role="
                                    progressbar" style="width: ${player.statistics.positioning}%;"
                                    aria-valuenow="${player.statistics.positioning}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Shot Power: ${player.statistics.shotPower}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.shotPower)} role="
                                    progressbar" style="width: ${player.statistics.shotPower}%;"
                                    aria-valuenow="${player.statistics.shotPower}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Long Shots: ${player.statistics.longShots}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.longShots)} role="
                                    progressbar" style="width: ${player.statistics.longShots}%;"
                                    aria-valuenow="${player.statistics.longShots}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Volleys: ${player.statistics.volleys}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.volleys)} role="
                                    progressbar" style="width: ${player.statistics.volleys}%;"
                                    aria-valuenow="${player.statistics.volleys}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Penalties: ${player.statistics.penalties}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.penalties)} role="
                                    progressbar" style="width: ${player.statistics.penalties}%;"
                                    aria-valuenow="${player.statistics.penalties}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Vision: ${player.statistics.vision}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.vision)} role="
                                    progressbar" style="width: ${player.statistics.vision}%;"
                                    aria-valuenow="${player.statistics.vision}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Crossing: ${player.statistics.crossing}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.crossing)} role="
                                    progressbar" style="width: ${player.statistics.crossing}%;"
                                    aria-valuenow="${player.statistics.crossing}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Free kick accuracy:
                                ${player.statistics.freeKickAccuracy}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.freeKickAccuracy)} role="
                                    progressbar" style="width: ${player.statistics.freeKickAccuracy}%;"
                                    aria-valuenow="${player.statistics.freeKickAccuracy}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Short Passing: ${player.statistics.shortPassing}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.shortPassing)} role="
                                    progressbar" style="width: ${player.statistics.shortPassing}%;"
                                    aria-valuenow="${player.statistics.shortPassing}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Long Passing: ${player.statistics.longPassing}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.longPassing)} role="
                                    progressbar" style="width: ${player.statistics.longPassing}%;"
                                    aria-valuenow="${player.statistics.longPassing}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Curve: ${player.statistics.curve}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.curve)} role=" progressbar"
                                    style="width: ${player.statistics.curve}%;" aria-valuenow="${player.statistics.curve}"
                                    aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Agility: ${player.statistics.agility}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.agility)} role="
                                    progressbar" style="width: ${player.statistics.agility}%;"
                                    aria-valuenow="${player.statistics.agility}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Balance: ${player.statistics.balance}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.balance)} role="
                                    progressbar" style="width: ${player.statistics.balance}%;"
                                    aria-valuenow="${player.statistics.balance}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Reactions: ${player.statistics.reactions}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.reactions)} role="
                                    progressbar" style="width: ${player.statistics.reactions}%;"
                                    aria-valuenow="${player.statistics.reactions}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Ball Control: ${player.statistics.ballControl}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.ballControl)} role="
                                    progressbar" style="width: ${player.statistics.ballControl}%;"
                                    aria-valuenow="${player.statistics.ballControl}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Dribbling: ${player.statistics.dribbling}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.dribbling)} role="
                                    progressbar" style="width: ${player.statistics.dribbling}%;"
                                    aria-valuenow="${player.statistics.dribbling}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Composure: ${player.statistics.composure}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.composure)} role="
                                    progressbar" style="width: ${player.statistics.composure}%;"
                                    aria-valuenow="${player.statistics.composure}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Interceptions: ${player.statistics.interceptions}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.interceptions)} role="
                                    progressbar" style="width: ${player.statistics.interceptions}%;"
                                    aria-valuenow="${player.statistics.interceptions}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Heading Accuracy:
                                ${player.statistics.headingAccuracy}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.headingAccuracy)} role="
                                    progressbar" style="width: ${player.statistics.headingAccuracy}%;"
                                    aria-valuenow="${player.statistics.headingAccuracy}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Marking: ${player.statistics.marking}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.marking)} role="
                                    progressbar" style="width: ${player.statistics.marking}%;"
                                    aria-valuenow="${player.statistics.marking}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Standing Tackle:
                                ${player.statistics.standingTackle}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.standingTackle)} role="
                                    progressbar" style="width: ${player.statistics.standingTackle}%;"
                                    aria-valuenow="${player.statistics.standingTackle}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Sliding Tackle:
                                ${player.statistics.slidingTackle}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.slidingTackle)} role="
                                    progressbar" style="width: ${player.statistics.slidingTackle}%;"
                                    aria-valuenow="${player.statistics.slidingTackle}" aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Jumping: ${player.statistics.jumping}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.jumping)} role="
                                    progressbar" style="width: ${player.statistics.jumping}%;"
                                    aria-valuenow="${player.statistics.jumping}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Stamina: ${player.statistics.stamina}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.stamina)} role="
                                    progressbar" style="width: ${player.statistics.stamina}%;"
                                    aria-valuenow="${player.statistics.stamina}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3">
                            <label for="movesInput" class="form-label">Strength: ${player.statistics.strength}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.strength)} role="
                                    progressbar" style="width: ${player.statistics.strength}%;"
                                    aria-valuenow="${player.statistics.strength}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-1">
                        <div class="col mb-3 me-3">
                            <label for="movesInput" class="form-label">Aggression: ${player.statistics.aggression}</label>
                            <div class="progress">
                                <div class="progress-bar  ${progressBarColorKeeper(player.statistics.aggression)} role="
                                    progressbar" style="width: ${player.statistics.aggression}%;"
                                    aria-valuenow="${player.statistics.aggression}" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                        <div class="col mb-3 ms-3"></div>
                    </div>
        
                </div>
            </div>
        </div>
    `;
}