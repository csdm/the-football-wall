/**
 ****** CONSTANTS ****** 
 */
/*
 const API_GET_URL_ALL = "http://localhost:8080/api/getPlayer/all";
 const API_GET_URL_TOPTEN = "http://localhost:8080/api/getPlayer/topTen";
 const API_GET_URL_BY_ID = 'http://localhost:8080/api/getPlayer';
 const API_GET_URL_PAGINATED = 'http://localhost:8080/api/getPlayer/all/paginated';
 const API_POST_URL = "http://localhost:8080/api/addPlayer";
*/
 
const API_GET_URL_ALL = "/api/getPlayer/all";
const API_GET_URL_TOPTEN = "/api/getPlayer/topTen";
const API_GET_URL_BY_ID = '/api/getPlayer';
const API_GET_URL_PAGINATED = '/api/getPlayer/all/paginated';
const API_POST_URL = "/api/addPlayer";


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


function getAcronym(string) {
  const arr = string.split(" ");

    for(let i = 0; i < arr.length; i++) {
      if(arr.length > 1) {
      arr[i] = arr[i].charAt(0).toUpperCase();
      } else {
        arr[i] = arr[i].charAt(0).toUpperCase() + arr[i].charAt(1).toUpperCase();
      }
    } 

  return arr.join("");
}


/*****************************/

