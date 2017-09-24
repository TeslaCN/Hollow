function validEmail(email) {
    return email.match('^([^@\\.]+\\.)*[^@\\.]+@([\\w|\\d]+\\.)+[\\w|\\d|-]*[^\\.]$');
}

function timestampToHuman(time) {
    var current = new Date().getTime();
    var result = (current - time) / 1000;
    if (result < 60) return '<s:text name="justNow"/>';
    result /= 60;
    if (result < 60) return Math.floor(result) + '<s:text name="minuteBefore"/>';
    result /= 60;
    if (result < 24) return Math.floor(result) + '<s:text name="hourBefore"/>';
    result /= 24;
    return Math.floor(result) + '<s:text name="dayBefore"/>';
}

function genderi18n(gender) {
    switch (gender) {
        case 'MALE':
            return '<s:text name="genderMale"/>';
        case 'FEMALE':
            return '<s:text name="genderFemale"/>';
        default:
            return '<s:text name="genderUnknown"/>';
    }
}