$(document).ready(function(){
    getMovieInformation();
    getMovieTrailer();
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


        $(('<h1>'+response.original_title+'</h1>' + '<h2>Release Date: '+response.release_date+'</h2>' + '<h2>Runtime: '+response.runtime+' Minutes</h2>' + '<h2>Movie Rating: '+response.vote_average+'</h2>' + '<h2>Overview: '+response.overview+'</h2>')).appendTo('#movieInformation');
        $('<img src="https://image.tmdb.org/t/p/original'+response.poster_path+'">').appendTo('#movieImage');

        var genres = "";

        for(var i=0; i < response.genres.length; i++){

           genres += response.genres[i].name +  '</br>';

        };

        $('<h2 id="genres">'+"Genres: " + '</br></br>' + genres +'</h2>').appendTo('#movieInformation');


        var productionCompanies = "";

        for(var i=0; i < response.production_companies.length; i++){

            productionCompanies += response.production_companies[i].name + '</br>';

        };

        $('<h2 id="productionCompanies">'+"Production Companies: " + '</br></br>' + productionCompanies+'</h2>').appendTo('#movieInformation');

        getAgeRating();

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

        var ageRating = "";

        for (var i = 0; i < response.results.length; i++) {
            if (response.results[i].iso_3166_1 == "GB"){
                for (var j = 0; j  < response.results[i].release_dates.length; j ++) {

                    if(response.results[i].release_dates[j].certification == "U"){
                        ageRating += "https://vignette4.wikia.nocookie.net/memoryalpha/images/3/37/BBFC_U.png/revision/20120614184504/scale-to-width-down/120?path-prefix=en";
                    }else if(response.results[i].release_dates[j].certification == "PG"){
                        ageRating += "https://jaybullimore98.files.wordpress.com/2014/12/pg.png";
                    }else if(response.results[i].release_dates[j].certification == "12"){
                        ageRating += "https://jaybullimore98.files.wordpress.com/2014/12/12.png";
                    }else if(response.results[i].release_dates[j].certification == "12A"){
                        ageRating += "https://jaybullimore98.files.wordpress.com/2014/12/12a.png";
                    }else if(response.results[i].release_dates[j].certification == "15"){
                        ageRating += "https://jaybullimore98.files.wordpress.com/2014/12/15.png";
                    }else if(response.results[i].release_dates[j].certification == "18"){
                        ageRating += "https://jaybullimore98.files.wordpress.com/2014/12/18.png";
                    }else if(response.results[i].release_dates[j].certification == "R18"){
                        ageRating += "http://www.erotictradeonly.com/wp-content/uploads/2014/01/LOGO_BBFC_R18-175x109.jpg";
                    }

                };
            };
        };

        //$('<h2 id="ageRating">'+"Age Rating: " + ageRating+'</h2></br>').appendTo('#movieInformation');
        $('<h2 id="ageRating">'+"Age Rating: " + '</br>' + '<img src="'+ageRating+'" id="ageRatingImg">'+'</h2>' + '<button id="bookTicketBtn" onclick="bookTicket()">Book</button>').appendTo('#movieInformation');

    });
}


function getMovieTrailer(){

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "https://api.themoviedb.org/3/movie/"+movieIDGlobalObject.movieid+"/videos?api_key=324938bccc324fb58e236a92cb0a9bc3",
        "method": "GET",
        "headers": {},
        "data": "{}"
    };

    $.ajax(settings).done(function (response) {
        console.log(response);

        var videoLink = "";

        videoLink = response.results[0].key;

        $('<iframe src="https://www.youtube.com/embed/'+videoLink+'" frameborder="0" allowfullscreen id="filmTrailer" align="middle"></iframe>').appendTo('#movieTrailer');


    });

}

function bookTicket() {

    alert("Book Ticket")


}