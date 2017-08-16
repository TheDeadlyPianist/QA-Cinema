function imgClick(id) {
    document.getElementById("mov" + id).click();
}

function getNowShowing() {
    document.getElementById("theList").innerHTML = "";
    const getMovieInformation = JSON.parse(movieInformationGlobalObject.movieInfo.replace(/&quot;/g, '"'));
    $.each(getMovieInformation, function (key, movie) {

        const url = "https://api.themoviedb.org/3/movie/" + movie.apiID + "?api_key=324938bccc324fb58e236a92cb0a9bc3";

        $.getJSON(url, function (response) {
            var theDate = new Date()
            var movDate = new Date(response.release_date)
            if (movDate <= theDate) {
                insertMovie(response)
            }
        });
    });
}

function getUpcoming() {
    document.getElementById("theList").innerHTML = "";
    const getMovieInformation = JSON.parse(movieInformationGlobalObject.movieInfo.replace(/&quot;/g, '"'));
    $.each(getMovieInformation, function (key, movie) {

        const url = "https://api.themoviedb.org/3/movie/" + movie.apiID + "?api_key=324938bccc324fb58e236a92cb0a9bc3";

        $.getJSON(url, function (response) {
            var theDate = new Date()
            var movDate = new Date(response.release_date)
            if (movDate > theDate) {
                insertMovie(response)
            }
        });
    });
}

function search(input) {

    document.getElementById("theList").innerHTML = "";
    const getMovieInformation = JSON.parse(movieInformationGlobalObject.movieInfo.replace(/&quot;/g, '"'));
    $.each(getMovieInformation, function (key, movie) {

        const url = "https://api.themoviedb.org/3/movie/" + movie.apiID + "?api_key=324938bccc324fb58e236a92cb0a9bc3";

        $.getJSON(url, function (response) {
            if(response.original_title.toLowerCase().includes(input.toLowerCase())) {
                insertMovie(response)
            }
        });
    });

}

function insertMovie(response) {
    document.getElementById("theList").innerHTML +=
        '<div class="col-20">' +
            '<div class="item">' +
                '<div>' +
                    '<h3>' +
                        '<a id="mov' + response.id + '" class="hyperColour" href="/movieInfo?movieID=' + response.id + '">' + response.original_title + '</a>' +
                    '</h3>' +
                    '<img id="img' + response.movieID + '" class="listImg" src="https://image.tmdb.org/t/p/original' + response.poster_path + '" onclick="imgClick(response.id)">' +
                '</div>' +
            '</div>' +
        '</div>';
}