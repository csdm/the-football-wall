function pageReload(playerId) {
    window.location.replace(`./player.html?playerId=${playerId}`);
  
    const url = "player.html";
    const params={
      playerId //equal to -> playerId: playerId
    }
  
    //History of visited pages
    window.history.pushState(params, '', url); //equal to -> window.history.pushState({playerId}, '', url)
  } 