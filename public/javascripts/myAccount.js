(function() {

    // Initialize Firebase
    const config = {
        apiKey: "AIzaSyAsoLmgYoUIcZAq8O6NJVWtYfzr7_ZEjn0",
        authDomain: "qa-cinema.firebaseapp.com",
        databaseURL: "https://qa-cinema.firebaseio.com",
        projectId: "qa-cinema",
        storageBucket: "qa-cinema.appspot.com",
        messagingSenderId: "712079829380"
    };
    firebase.initializeApp(config);


    const btnLogout = document.getElementById('btnLogout');



    btnLogout.addEventListener('click', e => {
        firebase.auth().signOut();
        //console.log('User logged out')
        location.reload();
    });

    firebase.auth().onAuthStateChanged(firebaseUser => {

        if(firebaseUser) {
            console.log(firebaseUser)
            // btnLogout.classList.remove('hide');
            //btnLogin.style.display.block
            document.getElementById("btnLogout").style.display = "block";
            document.getElementById("myAccountLabel").innerText = 'Logged in as ' + firebaseUser.email;
            // btnLogin.classList.add('hide');
        }
        else {
            console.log('Not signed in');
            window.location.href = "../login";

        }

    });











}());