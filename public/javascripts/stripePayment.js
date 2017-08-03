// Set your secret key: remember to change this to your live secret key in production
// See your keys here: https://dashboard.stripe.com/account/apikeys

requirejs(["helper/util"], function(util) {
    //This function is called when scripts/helper/util.js is loaded.
    //If util.js calls define(), then this function is not fired until
    //util's dependencies have loaded, and the util argument will hold
    //the module value for "helper/util".
});

var stripe = require("stripe")("sk_test_7vTgUG7ocbG5J5rbDgWTMi4v");

// Token is created using Stripe.js or Checkout!
// Get the payment token ID submitted by the form:
var token = request.body.stripeToken; // Using Express

// Charge the user's card:

var charge = stripe.charges.create({
    amount: 1000,
    currency: "gbp",
    description: "Example charge",
    source: token,
}, function(err, charge) {
    // asynchronously called
});