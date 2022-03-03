/**
 ****** CONSTANTS ****** 
 */
const API_GET_URL_ALL = "http://localhost:8080/api/getPlayer/all";
const API_GET_URL_TOPTEN = "http://localhost:8080/api/getPlayer/topTen";
const API_POST_URL = "http://localhost:8080/api/addPlayer";

/*****************************/

/**
 ****** FUNCTIONS ******
 */

function sleep(milliseconds) {
    const date = Date.now();
    let currentDate = null;
    do {
      currentDate = Date.now();
    } while (currentDate - date < milliseconds);
}


function validateString(string) {
  const arr = string.split(" ");

  for (let i = 0; i < arr.length; i++) {
    arr[i] = arr[i].toLowerCase().charAt(0).toUpperCase() + arr[i].slice(1).toLowerCase();
  }

  return arr.join(" ");
}

/*****************************/