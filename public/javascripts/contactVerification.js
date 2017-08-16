var formValidation = (function () {

    function checkEmail() {

        var elem = document.getElementById("email");
        return elem.value.match("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})");
    }

    function notEmpty(id){
        var elem = document.getElementById(id);
        return !elem.value.isEmpty;
    }

    function checkQuestion(){
        var elem = document.getElementById("content").value;
        return !elem.isEmpty && elem.length >= 10 && elem.length <= 500;
    }

    function check(){
        if(checkEmail() && notEmpty("name") &&
            notEmpty("content") && checkQuestion()){
            document.getElementById("submitButton").disabled = false;
        } else {
            document.getElementById("submitButton").disabled = true;
        }
    }

    return {
        check: check
    };

})();

$("#email").keyup(function() {
    formValidation.check();
});

$("#name").keyup(function() {
    formValidation.check();
});

$("#subject").keyup(function() {
    formValidation.check();
});

$("#content").keyup(function() {
    formValidation.check();
});