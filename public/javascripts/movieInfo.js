$(document).ready(function(){
    getMovieInformation();
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

        //$('<div class="item"><img src="https://image.tmdb.org/t/p/original'+response.results[i].backdrop_path+'" alt="" id="'+response.results[i].id+'"><div class="carousel-caption"><h3>'+response.results[i].original_title+'</h3></div></div>').appendTo('.carousel-inner');
        $(('<h1>'+response.original_title+'</h1>' + '<h2>'+response.release_date+'</h2>' + '<h3>'+response.overview+'</h3>')).appendTo('#movieInformation');
        $('<img src="https://image.tmdb.org/t/p/original'+response.poster_path+'">').appendTo('#movieImage');;

    });

}