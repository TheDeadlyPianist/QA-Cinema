$(document).ready(function(){
    getMovieInformation();
    getAgeRating();
});

function getMovieInformation(){

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "https://api.themoviedb.org/3/movie/"+movieIDGlobalObject.movieid+"?api_key=324938bccc324fb58e236a92cb0a9bc3",
        "method": "GET",
        "headers": {},
        "data": "{}"
    };

    $.ajax(settings).done(function (response) {
        console.log(response);

        $(('<h1>'+response.original_title+'</h1>' + '<h2>Release Date: '+response.release_date+'</h2>' + '<h2>Runtime: '+response.runtime+' Minutes</h2>' + '<h2>Overview: '+response.overview+'</h2>')).appendTo('#movieInformation');
        $('<img src="https://image.tmdb.org/t/p/original'+response.poster_path+'">').appendTo('#movieImage');


        var genres = ""

        for(var i=0; i < response.genres.length; i++){

           genres += response.genres[i].name + " , ";

        }

        $('<h2 id="genres">'+"Genres: " + genres+'</h2>').appendTo('#movieInformation')



        var productionCompanies = ""

        for(var i=0; i < response.production_companies.length; i++){

            productionCompanies += response.production_companies[i].name + " , ";

        }

        $('<h2 id="productionCompanies">'+"Production Companies: " + productionCompanies+'</h2>').appendTo('#movieInformation')

    });

}

function getAgeRating(){

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "https://api.themoviedb.org/3/movie/"+movieIDGlobalObject.movieid+"/release_dates?api_key=324938bccc324fb58e236a92cb0a9bc3",
        "method": "GET",
        "headers": {},
        "data": "{}"
    };

    $.ajax(settings).done(function (response) {
        console.log(response);

        var sortedBatKickNames = sidekicks.filter(function (el) {
            return (el.hero === "Batman");
        }).map(function(el) {
            return el.name;
        }).sort();
        
    });

}