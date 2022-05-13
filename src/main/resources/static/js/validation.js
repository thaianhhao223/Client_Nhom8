console.log("Running validation");
/* Check validation */
var firstName = document.getElementById('first');
var lastName = document.getElementById('last');
var phone = document.getElementById('number');
var email = document.getElementById('email');
var address = document.getElementById('message');

function checkSubmitData() {
    if(checkFirstName() && checkLastName() && checkPhone() && checkEmail() && checkAddress()
        // && checkPhone() && checkEmail() && checkAddress()
    ) {
        return true;
    }
    else {
        return false;
    }
}

function checkFirstName() {
    var display = document.getElementById('userError');
    if(firstName.value.length == 0) {
        display.innerHTML = 'First name must not be empty!';
        return false;
    }

    else if (firstName.value.length < 1) {
        display.innerHTML = 'First name cannot be less than 1 character!';
        return false;
    }

    else if (firstName.value.length > 16) {
        display.innerHTML = 'First name cannot be larger than 16 character!';
        return false;
    }

    else {
        display.innerHTML = '';
        return true;
    }
    return true;
}
function checkLastName() {
    var display = document.getElementById('userLastError');
    if(lastName.value.length == 0) {
        display.innerHTML = 'Last name must not be empty!';
        return false;
    }

    else if (lastName.value.length < 1) {
        display.innerHTML = 'Last name cannot be less than 1 character!';
        return false;
    }

    else if (lastName.value.length > 16) {
        display.innerHTML = 'Last name cannot be larger than 16 character!';
        return false;
    }

    else {
        display.innerHTML = '';
        return true;
    }
    return true;
}

function checkPhone() {
    var display = document.getElementById('phoneError');
    var regex1 = /^(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|076|078|088|091|094|083|084|085|081|082|092|056|058|099|059)([0-9]{7}|[0-9]{8})$/;

    if(phone.value.length == 0) {
        display.innerHTML = 'Your phone must not be empty!';
        return false;
    }

    if(!regex1.test(phone.value)) {
        display.innerHTML = 'Please enter real phone number and correct format!';
        return false;
    }

    else {
        display.innerHTML = '';
        return true;
    }
    return true;

}

function checkEmail() {
    var display = document.getElementById('emailError');
    var regex2 = /^[a-z+0-9.+]+(@gmail.com|@email.com)$/;

    if(email.value.length == 0 ){
        display.innerHTML = 'Email must not be empty!';
        return false;
    }

    if (!regex2.test(email.value)) {
        display.innerHTML = 'Email invalid!'
        return false;
    }

    else {
        display.innerHTML = '';
        return true;
    }
    return true;
}

function checkAddress() {
    var display = document.getElementById('addressError');
    if(address.value.length == 0) {
        display.innerHTML = 'Address must not be empty!!';
        return false;
    }
    else if(address.value.length < 10) {
        display.innerHTML = 'Address cannot be less than 10 character!';
        return false;
    }
    else {
        display.innerHTML = '';
        return true;
    }
    return true;
}