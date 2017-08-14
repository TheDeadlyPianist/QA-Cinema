function movieSearch(theSwitch, inSearch){

    document.getElementById("theList").innerHTML = "";
    const getMovieInformation = JSON.parse(movieInformationGlobalObject.movieInfo.replace(/&quot;/g,'"'));

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

            var theDate = new Date()
            var movDate = new Date(response.release_date)

            if(theSwitch == 0)
            {
                if(movDate <= theDate)
                {
                    document.getElementById("theList").innerHTML += '<div class="col-20"><div class="item"><div><h3><a id="mov'+response.id+'" class="hyperColour" href="/movieInfo?movieID=' + response.id + '">' + response.original_title + '</a></h3><img id="img' + response.movieID + '" class="listImg" src="https://image.tmdb.org/t/p/original' + response.poster_path + '" onclick="imgClick(' + response.id + ')"></div></div>';
                }
            }
            else if(theSwitch == 1)
            {
                if(movDate > theDate)
                {
                    document.getElementById("theList").innerHTML += '<div class="col-20"><div class="item"><div><h3><a id="mov'+response.id+'" class="hyperColour" href="/movieInfo?movieID=' + response.id + '">' + response.original_title + '</a></h3><img id="img' + response.movieID + '" class="listImg" src="https://image.tmdb.org/t/p/original' + response.poster_path + '" onclick="imgClick(' + response.id + ')"></div></div>';
                }
            }

            else if(theSwitch == 2 && inSearch.trim() != "")
            {
                if(response.original_title.toLowerCase().includes(inSearch.toLowerCase()) )
                {
                    document.getElementById("theList").innerHTML += '<div class="col-20"><div class="item"><div><h3><a id="mov'+response.id+'" class="hyperColour" href="/movieInfo?movieID=' + response.id + '">' + response.original_title + '</a></h3><img id="img' + response.movieID + '" class="listImg" src="https://image.tmdb.org/t/p/original' + response.poster_path + '" onclick="imgClick(' + response.id + ')"></div></div>';
                }
            }
            console.log(response);

        });

    });

}

function imgClick(theID){
    document.getElementById("mov"+theID).click();
}