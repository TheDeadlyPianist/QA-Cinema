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


    const btnReset = document.getElementById('btnReset');


    btnReset.addEventListener('click', e => {

        const email = txtEmail.value;
        const auth = firebase.auth();

        const promise = auth.sendPasswordResetEmail(email);
        promise.catch(e => console.log(e.message))


        //window.console.log("Email sent to " + email + " follow steps on email to reset password")
        alert("Email sent to " + email + " follow steps on email to reset password")
        //window.location.href = "../login";



    });


    firebase.auth().onAuthStateChanged(firebaseUser => {

        if(firebaseUser) {
            console.log(firebaseUser)

            window.location.href = "../myAccount";
        }
        else {
            console.log('Not signed in');
            //window.location.href = "../login"

        }

    });




}());
