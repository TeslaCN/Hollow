<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/13
  Time: 上午9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="signUpPage"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <script src="https://cdn.bootcss.com/vue/2.4.2/vue.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="utils.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
    </div>
    <div class="row" style="padding: 30px">
        <form action="sign-up" onsubmit="return validate();" method="post">
            <div class="form-group">
                <label for="inputEmail"><s:text name="user.account"/></label>
                <input name="user.account" type="email" class="form-control" id="inputEmail" placeholder="Email"
                       required>
            </div>
            <div class="form-group">
                <label for="inputPassword"><s:text name="user.password"/></label>
                <input name="user.password" type="password" class="form-control" id="inputPassword"
                       placeholder="Password" required>
            </div>
            <div id="confirmGroup" class="form-group">
                <label for="confirmPassword"><s:text name="user.password.confirm"/></label>
                <input type="password" class="form-control" id="confirmPassword" placeholder="Password"
                       oninput="passwordValidate();" required>
                <script>
                    function passwordValidate() {
                        var input1 = document.getElementById('inputPassword').value;
                        var input2 = document.getElementById('confirmPassword').value;
                        if (input1 != input2) {
                            $('#confirmGroup').removeClass('has-error');
                            $('#confirmGroup').addClass('has-error');
                        } else {
                            $('#confirmGroup').removeClass('has-error');
                            $('#confirmGroup').addClass('has-success');
                        }
                    }
                </script>
            </div>
            <div class="form-group">
                <label for="inputNickname"><s:text name="user.nickname"/></label>
                <input name="user.nickname" type="text" id="inputNickname" placeholder="Nickname" class="form-control" required>
                <p class="help-block">Example block-level help text here.</p>
            </div>
            <%--<div class="checkbox">--%>
            <%--<label>--%>
            <%--<input type="checkbox"> Check me out--%>
            <%--</label>--%>
            <%--</div>--%>
            <div class="form-group">
                <label><s:text name="user.gender"/></label><br>
                <%--<s:radio id="inputGender" list="{'MALE', 'FEMALE'}" name="user.gender"/>--%>
                <input type="radio" name="user.gender" value="MALE" id="inputGenderMALE">
                <label for="inputGenderMALE"><s:text name="gender.male"/></label><br>
                <input type="radio" name="user.gender" value="FEMALE" id="inputGenderFEMALE">
                <label for="inputGenderFEMALE"><s:text name="gender.female"/></label><br>
                <input type="radio" name="user.gender" value="UNKNOWN" id="inputGenderUNKNOWN" checked>
                <label for="inputGenderUNKNOWN"><s:text name="gender.unknown"/></label>
            </div>
            <div class="form-group">
                <label for="inputRemark"><s:text name="user.remark"/></label>
                <s:textarea id="inputRemark" name="user.remark" cssClass="form-control"/>
            </div>
            <button type="submit" class="btn btn-default"><s:text name="signUp"/></button>
            <script>
                function validate() {
                    var email = document.getElementById('inputEmail').value;
                    if (email == null) {
                        alert('<s:text name="user.account.required"/>');
                        return false;
                    }
                    if (!validEmail(email)) {
                        alert('<s:text name="user.account.invalid"/>');
                        return false;
                    }
                    var password = document.getElementById('inputPassword').value;
                    if (password == null) {
                        alert("<s:text name="user.password.required"/>");
                        return false;
                    }
                    if (password.length < 8 || password.length > 255) {
                        alert("<s:text name="user.password.length"/>");
                        return false;
                    }
                    if (password != document.getElementById('confirmPassword').value) {
                        alert('<s:text name="user.password.different"/>')
                        return false;
                    }
                    var nickname = document.getElementById('inputNickname').value;
                    if (nickname == null) {
                        alert("<s:text name="user.nickname.required"/>");
                        return false;
                    }
                    if (nickname.length < 1 || nickname.length > 32) {
                        alert("<s:text name="user.nickname.length"/>");
                        return false;
                    }
                    return true;
                }
            </script>
        </form>
    </div>
</div>
</body>
</html>
