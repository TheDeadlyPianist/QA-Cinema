var stripe = Stripe('pk_test_KWyw9m8hsxsgelLoigCo42Cr');

var elements = stripe.elements();

stripe.createToken('bank_account', {
    country: 'us',
    currency: 'usd',
    routing_number: '110000000',
    account_number: '000123456789',
    account_holder_name: 'Jenny Rosen',
    account_holder_type: 'individual',
}).then(function(result) {
    // handle result.error or result.token
});