function validEmail(email) {
    return email.match('^([^@\\.]+\\.)*[^@\\.]+@([\\w|\\d]+\\.)+[\\w|\\d|-]*[^\\.]$');
}

function timestampToHuman(time) {
    var current = new Date().getTime();
    var result = (current - time) / 1000;
    if (result < 60) return '<s:text name="justNow"/>';
    result /= 60;
    if (result < 60) return Math.floor(result) + '<s:text name="minute.before"/>';
    result /= 60;
    if (result < 24) return Math.floor(result) + '<s:text name="hour.before"/>';
    result /= 24;
    return Math.floor(result) + '<s:text name="day.before"/>';
}

function genderi18n(gender) {
    switch (gender) {
        case 'MALE':
            return '<s:text name="gender.male"/>';
        case 'FEMALE':
            return '<s:text name="gender.female"/>';
        default:
            return '<s:text name="gender.unknown"/>';
    }
}