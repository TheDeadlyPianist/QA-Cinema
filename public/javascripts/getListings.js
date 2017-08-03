
function movieSearch(){

    var movieInfo = [
        {
            movieID: "315635",
            moviename: "Spider-Man: Homecoming",
            movieyear: "2017",
        }, {
            movieID: "374720",
            moviename: "Dunkirk",
            movieyear: "2017",
        }
        ,{
            movieID: "339964",
            moviename: "Valerian and the City of a Thousand Planets",
            movieyear: "2017",
        },{
            movieID: "281338",
            moviename: "War For The Planet Of The Apes",
            movieyear: "2017",
        },{
            movieID: "324852",
            moviename: "Despicable Me 3",
            movieyear: "2017",
        }, {
            movieID: "283995",
            moviename: "Guardians of the Galaxy Vol. 2",
            movieyear: "2017"
        },{
            movieID: "378236",
            moviename: "The Emoji Movie",
            movieyear: "2017",
        },{
            movieID: "416477",
            moviename: "The Big Sick",
            movieyear: "2017",
        },{
            movieID: "353491",
            moviename: "The Dark Tower",
            movieyear: "2017",
        },{
            movieID: "297762",
            moviename: "Wonder Woman",
            movieyear: "2017",
        },{
            movieID: "395834",
            moviename: "Wind River",
            movieyear: "2017",
        },{
            movieID: "562",
            moviename: "Die Hard",
            movieyear: "1988"
        },{
            movieID: "251516",
            moviename: "Kung Fury",
            movieyear: "2015"
        },{
            movieID:"37833",
            moviename: "Reefer Madness",
            movieyear: "1936"
        },{
            movieID:"245891",
            moviename: "John Wick",
            movieyear: "2014"
        },{
            movieID:"324552",
            moviename: "John Wick: Chapter 2",
            movieyear: "2017"
        },{
            movieID:"341013",
            moviename: "Atomic Blonde",
            movieyear: "2017"
        },{
            movieID:"76341",
            moviename: "Mad Max: Fury Road",
            movieyear: "2015"
        },{
            movieID:"284053",
            moviename: "Thor: Ragnarok",
            movieyear: "2017"
        },{
            movieID:" 263115",
            moviename: "Logan",
            movieyear: "2017"
        }

    ];

    $.each(movieInfo, function(key, movie) {

        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "https://api.themoviedb.org/3/movie/"+movie.movieID+"?api_key=324938bccc324fb58e236a92cb0a9bc3",
            "method": "GET",
            "headers": {},
            "data": "{}"
        };


        $.ajax(settings).done(function (response) {
            console.log(response);

            document.getElementById("theList").innerHTML += '<div class="col-25"><div class="item"><div><h3><a href="/movieInfo?movieID='+response.id+'">'+response.original_title+'</a></h3></div><img class="listImg" src="https://image.tmdb.org/t/p/original'+response.poster_path+'" alt=""></div></div>';
            // description
            //<div class="theDescCont"><div class="theDesc">'+response.overview+'</div></div>

        });

    });
    document.getElementById("theList").innerHTML += '<p class="listTitle" text-align="center"><b><u>NOW SHOWING</u></b></p>'

}
$(document).ready(function(){

    movieSearch()

});