console.log("Running validation");
/* Check validation */
var firstName = document.getElementById('firstNameC');
var lastName = document.getElementById('lastNameC');
var phone = document.getElementById('phoneNumber');
var email = document.getElementById('email');
var address = document.getElementById('address');

function checkSubmitDataCustomer() {
    if(checkFirstName() && checkLastName() && checkPhone() && checkEmail() && checkAddress()
    ) {
        return true;
    }
    else{
        alert("Vui long kiem tra lai tat ca thong tin")
    return false;
    }
}


function checkFirstNameC() {
    var display = document.getElementById('userErrorC');
    if(firstName.value.length === 0) {
        display.innerHTML = 'Họ không được để trống!';
        return false;
    }

    else if (firstName.value.length < 1) {
        display.innerHTML = 'Họ không được ít hơn 1 ký tự!';
        return false;
    }

    else if (firstName.value.length > 16) {
        display.innerHTML = 'Họ không được lớn hơn 16 ký tự!';
        return false;
    }

    else {
        display.innerHTML = '';
        return true;
    }
    return true;
}
function checkLastNameC() {
    var display = document.getElementById('userLastErrorC');
    if(lastName.value.length === 0) {
        display.innerHTML = 'Tên không được để trống!';
        return false;
    }

    else if (lastName.value.length < 1) {
        display.innerHTML = 'Tên không được ít hơn 1 ký tự!';
        return false;
    }

    else if (lastName.value.length > 16) {
        display.innerHTML = 'Tên không được nhiều hơn 16 ký tự';
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
        display.innerHTML = 'Số điện thoại không được trống!';
        return false;
    }

    if(!regex1.test(phone.value)) {
        display.innerHTML = 'Vui lòng nhập đúng số điện thoại!';
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

    if(email.value.length === 0 ){
        display.innerHTML = 'Email không được để trống!';
        return false;
    }

    if (!regex2.test(email.value)) {
        display.innerHTML = 'Email không hợp lệ!'
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
        display.innerHTML = 'Địa chỉ không được trống!';
        return false;
    }
    else if(address.value.length < 10) {
        display.innerHTML = 'Địa chỉ không được ít hơn 10 ký tự!';
        return false;
    }
    else {
        display.innerHTML = '';
        return true;
    }
    return true;
}


