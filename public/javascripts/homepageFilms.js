$(document).ready(function(){
    getMovieInformation();
    nowShowingMovies();
});

function imgClick(id) {
    document.getElementById("mov" + id).click();
}

function getMovieInformation(){

    const getMovieInformation = JSON.parse(popularMoviesGlobalObject.getPopularMovies.replace(/&quot;/g, '"'));

    $.each(getMovieInformation, function(key, movie) {

        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "https://api.themoviedb.org/3/movie/"+movie.apiID+"?api_key=324938bccc324fb58e236a92cb0a9bc3",
            "method": "GET",
            "headers": {},
            "data": "{}"
        };

        $.ajax(settings).done(function (response) {

            document.getElementById("popularMoviesSection").innerHTML +=
                '<div class="col-20">' +
                '<div class="item">' +
                '<div id="popularMoviesContent">' +
                '<h3>' +
                '<a id="mov' + response.id + '" class="hyperColour" href="/movieInfo?movieID=' + response.id + '">' + response.original_title + '</a>' +
                '</h3>' +
                '<img id="img' + response.movieID + '" class="listImg" src="https://image.tmdb.org/t/p/original' + response.poster_path + '" onclick="imgClick(response.id)">' +
                '</div>' +
                '</div>' +
                '</div>';


        });

    });

}