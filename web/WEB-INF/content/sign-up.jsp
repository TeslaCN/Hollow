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

</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <s:if test="#request.errorMessage != null && #request.errorMessage != ''">
        <div class="row">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <strong>ERROR!</strong>&nbsp;&nbsp;${requestScope.errorMessage}
            </div>
        </div>
    </s:if>
    <div class="row" style="padding: 30px">
        <form id="signUpForm" action="sign-up" onsubmit="return validate();" method="post">
            <div class="form-group">
                <label for="inputEmail"><s:text name="userAccount"/></label>
                <input name="user.account" type="email" class="form-control" id="inputEmail" placeholder="Email"
                       required>
            </div>
            <div class="form-group">
                <label for="inputPassword"><s:text name="userPassword"/><span>&nbsp;(<s:text name="userPasswordLength"/>)</span></label>
                <input name="user.password" type="password" class="form-control" id="inputPassword"
                       placeholder="Password" oninput="passwordValidate();" required>
            </div>
            <div id="confirmGroup" class="form-group">
                <label for="confirmPassword"><s:text name="userPasswordConfirm"/>
                    <span id="confirmTip" style="color: red;display: none;">
                        &nbsp;<s:text name="userPasswordDifferent"/></span></label>
                <input type="password" class="form-control" id="confirmPassword" placeholder="Password"
                       oninput="passwordValidate();" required>
                <script>
                    function passwordValidate() {
                        var input1 = document.getElementById('inputPassword').value;
                        var input2 = document.getElementById('confirmPassword').value;
                        if (input1 != input2) {
                            $('#confirmGroup').removeClass('has-error');
                            $('#confirmGroup').addClass('has-error');
                            $('#confirmTip').css('display', '');
                        } else {
                            $('#confirmGroup').removeClass('has-error');
                            $('#confirmGroup').addClass('has-success');
                            $('#confirmTip').css('display', 'none');
                        }
                    }
                </script>
            </div>
            <div class="form-group">
                <label for="inputNickname"><s:text name="userNickname"/></label>
                <input name="user.nickname" type="text" id="inputNickname" placeholder="Nickname" class="form-control"
                       required>
                <p class="help-block">Example block-level help text here.</p>
            </div>
            <%--<div class="checkbox">--%>
            <%--<label>--%>
            <%--<input type="checkbox"> Check me out--%>
            <%--</label>--%>
            <%--</div>--%>
            <div class="form-group">
                <label><s:text name="userGender"/></label><br>
                <%--<s:radio id="inputGender" list="{'MALE', 'FEMALE'}" name="user.gender"/>--%>
                <input type="radio" name="user.gender" value="MALE" id="inputGenderMALE">
                <label for="inputGenderMALE"><s:text name="genderMale"/></label><br>
                <input type="radio" name="user.gender" value="FEMALE" id="inputGenderFEMALE">
                <label for="inputGenderFEMALE"><s:text name="genderFemale"/></label><br>
                <input type="radio" name="user.gender" value="UNKNOWN" id="inputGenderUNKNOWN" checked>
                <label for="inputGenderUNKNOWN"><s:text name="genderUnknown"/></label>
            </div>
            <div class="form-group">
                <label for="inputRemark"><s:text name="userRemark"/></label>
                <s:textarea id="inputRemark" name="user.remark" cssClass="form-control"/>
            </div>
            <button type="submit" class="btn btn-default"><s:text name="signUp"/></button>
            <script>
                function validate() {
                    var email = document.getElementById('inputEmail').value;
                    if (email == null) {
                        alert('<s:text name="userAccountRequired"/>');
                        return false;
                    }
                    if (!validEmail(email)) {
                        alert('<s:text name="userAccountInvalid"/>');
                        return false;
                    }
                    var password = document.getElementById('inputPassword').value;
                    if (password == null) {
                        alert("<s:text name="userPasswordRequired"/>");
                        return false;
                    }
                    if (password.length < 8 || password.length > 255) {
                        alert("<s:text name="userPasswordLength"/>");
                        return false;
                    }
                    if (password != document.getElementById('confirmPassword').value) {
                        alert('<s:text name="userPasswordDifferent"/>')
                        return false;
                    }
                    var nickname = document.getElementById('inputNickname').value;
                    if (nickname == null) {
                        alert("<s:text name="userNicknameRequired"/>");
                        return false;
                    }
                    if (nickname.length < 1 || nickname.length > 32) {
                        alert("<s:text name="userNicknameLength"/>");
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
