function validEmail(email) {
    return email.match('^([^@\\.]+\\.)*[^@\\.]+@([\\w|\\d]+\\.)+[\\w|\\d|-]*[^\\.]$');
}