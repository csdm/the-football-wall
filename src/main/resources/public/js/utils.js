/**
 ****** CONSTANTS ****** 
 */
/*
 //use these variables only if the public section (the front-end pages) is out of the build path of all project 
 const API_GET_URL_ALL = "http://localhost:8080/api/getplayer/all";
 const API_GET_URL_TOPTEN = "http://localhost:8080/api/getplayer/topten";
 const API_GET_URL_BY_ID = 'http://localhost:8080/api/getplayer';
 const API_GET_URL_PAGINATED = 'http://localhost:8080/api/getplayer/all/paginated';
 const API_POST_URL = "http://localhost:8080/api/addplayer";
*/
 
const API_GET_URL_ALL = "/api/getplayer/all";
const API_GET_URL_TOPTEN = "/api/getplayer/topten";
const API_GET_URL_BY_ID = '/api/getplayer';
const API_GET_URL_PAGINATED = '/api/getplayer/all/paginated';
const API_POST_URL = "/api/addplayer";
const API_GET_EXPORT_EXCEL = "api/export/excel";

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

