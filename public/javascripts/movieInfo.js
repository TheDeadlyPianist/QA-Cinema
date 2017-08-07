$(document).ready(function(){
    getMovieInformation();
    getMovieTrailer();
    getDates();
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


        $(('<h1 id="filmTitle">'+response.original_title+'</h1>' + '<h2>Release Date: '+response.release_date+'</h2>' + '<h2>Runtime: '+response.runtime+' Minutes</h2>' + '<h2>Movie Rating: '+response.vote_average+'</h2>' + '<h2>Overview: '+response.overview+'</h2>')).appendTo('#movieInformation');
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

    var getTitle = document.getElementById("filmTitle").textContent;

    var e = document.getElementById("dates");
    var strUser = e.options[e.selectedIndex].value.toString();

    var formatDate = new Date(strUser);
    var totalTime = "9:00"
    var properlyFormatted = formatDate.getFullYear() + ("0" + (formatDate.getMonth() + 1)).slice(-2) + ("0" + formatDate.getDate()).slice(-2);

    window.location = "/seatBooking?filmName="+getTitle+"&date="+properlyFormatted+"&time="+totalTime;

}


function getDates(){

    var getStartDate = movieIDGlobalObject.startDate;
    var getEndDate = movieIDGlobalObject.endDate;


    var startDateYear = getStartDate.substring(0,4);
    var startDateMonth = getStartDate.substring(4,6);
    var startDateDay = getStartDate.substring(6,8);

    var convertedStartDate = new Date(startDateYear, startDateMonth-1, startDateDay);


    var endDateYear = getEndDate.substring(0,4);
    var endDateMonth = getEndDate.substring(4,6);
    var endDateDay = getEndDate.substring(6,8);

    var convertedEndDate = new Date(endDateYear, endDateMonth-1, endDateDay);


    var getDates = function(startDate, endDate) {
        var dates = [],
            currentDate = startDate,
            addDays = function(days) {
                var date = new Date(this.valueOf());
                date.setHours(9);
                date.setDate(date.getDate() + days);


                return date;
            };
        while (currentDate <= endDate) {
            dates.push(currentDate);
            currentDate = addDays.call(currentDate, 1);
        }
        return dates;
    };

    var completedDate = "";

    var dates = getDates(convertedStartDate, convertedEndDate);

    dates.forEach(function(date) {
        completedDate += '<option>'+date+'</option>'
    });

    $('<div><select id="dates">'+completedDate+'</select></div>').appendTo('#movieInformation')
}