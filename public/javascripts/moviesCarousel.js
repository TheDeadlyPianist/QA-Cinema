
function retrieveNowShowingMovies(){

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "https://api.themoviedb.org/3/movie/popular?api_key=324938bccc324fb58e236a92cb0a9bc3",
        "method": "GET",
        "headers": {},
        "data": "{}"
    };

    $.ajax(settings).done(function (response) {
        console.log(response);


        for(var i=0; i < response.results.length; i++){

            $('<div class="item"><img src="https://image.tmdb.org/t/p/original'+response.results[i].backdrop_path+'" alt=""><div class="carousel-caption"><h3>'+response.results[i].original_title+'</h3></div></div>').appendTo('.carousel-inner');

        }

        $('.item').first().addClass('active');
        $('#myCarousel').carousel();

    });

}

function retriveUpcomingMovies(){

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "https://api.themoviedb.org/3/movie/upcoming?page=1&api_key=324938bccc324fb58e236a92cb0a9bc3",
        "method": "GET",
        "headers": {},
        "data": "{}"
    };

    $.ajax(settings).done(function (response) {
        console.log(response);


        for(var i=0; i < response.results.length; i++){

            $('<div class="item"><img src="https://image.tmdb.org/t/p/original'+response.results[i].backdrop_path+'" alt=""><div class="carousel-caption"><h3>'+response.results[i].original_title+'</h3></div></div>').appendTo('.carousel-inner');

        }

        $('.item').first().addClass('active');
        $('#myCarousel2').carousel();

    });

}

$(document).ready(function(){

    retrieveNowShowingMovies()
    //retriveUpcomingMovies()



});


