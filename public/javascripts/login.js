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


    const txtEmail = document.getElementById("txtEmail");
    const txtPassword = document.getElementById("txtPassword");
    const btnLogin = document.getElementById("btnLogin");
    const btnReset = document.getElementById("btnReset");
    const btnSignUp = document.getElementById("btnSignUp");
    const btnLogout = document.getElementById("btnLogout");


    // Login

    btnLogin.addEventListener("click", (e) => {

        const email = txtEmail.value;
        const pass = txtPassword.value;
        const auth = firebase.auth();
        //

        const promise = auth.signInWithEmailAndPassword(email, pass);
        promise.catch(e => console.log(e.message))

        console.log("logged in as " + email);

    });


    btnReset.addEventListener("click", (e) => {

        window.location.href = "../forgotPassword";


    });




    // register
    //
    // btnSignUp.addEventListener('click', e => {
    //
    //     const email = txtEmail.value;
    //     const pass = txtPassword.value;
    //     const auth = firebase.auth();
    //
    //     const promise = auth.createUserWithEmailAndPassword(email, pass);
    //     promise.catch(e => console.log(e.message))
    //
    // });


    btnLogout.addEventListener("click", (e) => {
        firebase.auth().signOut();
        //console.log('User logged out')
        location.reload();
    });

    firebase.auth().onAuthStateChanged(firebaseUser => {

        if(firebaseUser) {
            console.log(firebaseUser)
            window.location.href = "../myAccount";
        }
        else {
            console.log("Not signed in");

        }

    });



}());
