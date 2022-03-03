const form = document.getElementById('add-player-form');

form.addEventListener("submit", function (event) {
    console.log("PREMUTO BTN");
    event.preventDefault();//permette di eseguire prima tutto il blocco di codice prima di refreshare

    let playerId = document.getElementById("playerIdInput").value;
    let surname = validateString(document.getElementById("surnameInput").value);
    let name = validateString(document.getElementById("nameInput").value);
    let age = document.getElementById("ageInput").value;
    let nationality = document.getElementById("nationalityInput").value;
    let totalScore = document.getElementById("totalScoreInput").value;
    let role = document.getElementById("roleInput").value;
    //SKILLS
    let skillMoves = document.getElementById("movesInput").value;
    let weakFoot = document.getElementById("weakFootInput").value;
    let attWorkRate = document.getElementById("attWorkRateInput").value;
    let defWorkRate = document.getElementById("defWorkRateInput").value;
    let preferredFoot = document.getElementById("preferredFootInput").value;
    //STATISTICS
    let acceleration = document.getElementById("accelerationInput").value;
    let sprintSpeed = document.getElementById("sprintSpeedInput").value;
    let positioning = document.getElementById("positioningInput").value;
    let finishing = document.getElementById("finishingInput").value;
    let shotPower = document.getElementById("shotPowerInput").value;
    let longShots = document.getElementById("longShotsInput").value;
    let volleys = document.getElementById("volleysInput").value;
    let penalties = document.getElementById("penaltiesInput").value;
    let vision = document.getElementById("visionInput").value;
    let crossing = document.getElementById("crossingInput").value;
    let freeKickAccuracy = document.getElementById("freeKickAccuracyInput").value;
    let shortPassing = document.getElementById("shortPassingInput").value;
    let longPassing = document.getElementById("longPassingInput").value;
    let curve = document.getElementById("curveInput").value;
    let agility = document.getElementById("agilityInput").value;
    let balance = document.getElementById("balanceInput").value;
    let reactions = document.getElementById("reactionsInput").value;
    let ballControl = document.getElementById("ballControlInput").value;
    let dribbling = document.getElementById("dribblingInput").value;
    let composure = document.getElementById("composureInput").value;
    let interceptions = document.getElementById("interceptionsInput").value;
    let headingAccuracy = document.getElementById("headingAccuracyInput").value;
    let marking = document.getElementById("markingInput").value;
    let standingTackle = document.getElementById("standingTackleInput").value;
    let slidingTackle = document.getElementById("slidingTackleInput").value;
    let jumping = document.getElementById("jumpingInput").value;
    let stamina = document.getElementById("staminaInput").value;
    let strength = document.getElementById("strengthInput").value;
    let aggression = document.getElementById("aggressionInput").value;

    console.log("Surname: " + surname + "\n"
        + "Name: " + name + "\n"
        + "Age: " + age + "\n"
        + "Nationality: " + nationality + "\n"
        + "Total score: " + totalScore + "\n"
    );

    // Send a POST request
    // axios({
    //     method: 'post',
    //     url: API_POST_URL,
    //     data: {
    //         name: surname,
    //         surname: name,
    //         age: age,
    //         nationality: nationality,
    //         totalScore: totalScore
    //     }
    //   }) .catch(function (error) {
    //     console.log(error);
    //     sleep(2000);
    //   });

    //axios.post(`${API_POST_URL}?name=${name}&surname=${surname}&age=${age}&nationality=${nationality}&totalScore=${totalScore}`)
    axios.post(API_POST_URL, {
        playerId: playerId,
        name: name,
        surname: surname,
        age: age,
        nationality: nationality,
        role: role,
        totalScore: totalScore,
        skills: {
            skillMoves: skillMoves,
            weakFoot: weakFoot,
            attWorkRate: attWorkRate,
            defWorkRate: defWorkRate,
            preferredFoot: preferredFoot
        },
        statistics: {
            acceleration: acceleration,
            sprintSpeed: sprintSpeed,
            positioning: positioning,
            finishing: finishing,
            shotPower: shotPower,
            longShots: longShots,
            volleys: volleys,
            penalties: penalties,
            vision: vision,
            crossing: crossing,
            freeKickAccuracy: freeKickAccuracy,
            shortPassing: shortPassing,
            longPassing: longPassing,
            curve: curve,
            agility: agility,
            balance: balance,
            reactions: reactions,
            ballControl: ballControl,
            dribbling: dribbling,
            composure: composure,
            interceptions: interceptions,
            headingAccuracy: headingAccuracy,
            marking: marking,
            standingTackle: standingTackle,
            slidingTackle: slidingTackle,
            jumping: jumping,
            stamina: stamina,
            strength: strength,
            aggression: aggression
        }
    })
        .then(function (response) {
            console.log(response);
            alert(`New player added!
        Name: ${name}
        Surname: ${surname}
        Age: ${age}
        Nationality: ${nationality}
        Total score: ${totalScore}`);
            location.reload();
        })
        .catch(function (error) {
            console.log(error);
            sleep(10000);
        });


});