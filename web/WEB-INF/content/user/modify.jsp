<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/19
  Time: 下午4:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>

</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <div>
        <form id="modifyUserInfoForm" method="post" action="${pageContext.request.contextPath}/user-update">
            <div class="form-group">
                <label for="inputNickname"><s:text name="userNickname"/></label>
                <input name="nickname" type="text" id="inputNickname" placeholder="Nickname" class="form-control" value="${sessionScope.user.nickname}"
                       required>
            </div>
            <div class="form-group">
                <label><s:text name="userGender"/></label><br>
                <input type="radio" name="gender" value="MALE" id="inputGenderMALE" <s:if test="#session.user.gender.toString() == 'MALE'">checked</s:if>>
                <label for="inputGenderMALE"><s:text name="genderMale"/></label><br>
                <input type="radio" name="gender" value="FEMALE" id="inputGenderFEMALE" <s:if test="#session.user.gender.toString() == 'FEMALE'">checked</s:if>>
                <label for="inputGenderFEMALE"><s:text name="genderFemale"/></label><br>
                <input type="radio" name="gender" value="UNKNOWN" id="inputGenderUNKNOWN" <s:if test="#session.user.gender.toString() == 'UNKNOWN'">checked</s:if>>
                <label for="inputGenderUNKNOWN"><s:text name="genderUnknown"/></label>
            </div>
            <button type="submit" class="btn btn-default form-control">更新</button>
        </form>
        <script>
            function submitUserInfoUpdate() {
                var nickname = document.getElementById('inputNickname').value;
                if (nickname.length < 1 || nickname.length > 32) {
                    alert('昵称长度 1~32 位');
                    return false;
                }
                return true;
            }
        </script>
    </div>

    <div>
        <form id="modifyPasswordForm" onsubmit="return submitPassword();">
            <div class="form-group">
                <label for="inputOldPassword">旧密码<span>&nbsp;(<s:text name="userPasswordLength"/>)</span></label>
                <input name="oldPassword" type="password" class="form-control" id="inputOldPassword"
                       placeholder="Password" required>
            </div>
            <div class="form-group">
                <label for="inputPassword">新密码<span>&nbsp;(<s:text name="userPasswordLength"/>)</span></label>
                <input name="newPassword" type="password" class="form-control" id="inputPassword"
                       placeholder="Password" oninput="passwordValidate();" required>
            </div>
            <div id="confirmGroup" class="form-group">
                <label for="confirmPassword">确认密码
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
                    function submitPassword() {
                        var oldPassword = document.getElementById('inputOldPassword');
                        if (oldPassword.length < 8) {
                            alert('旧密码长度至少为8位');
                            return false;
                        }
                        var input1 = document.getElementById('inputPassword').value;
                        var input2 = document.getElementById('confirmPassword').value;
                        if (input1 != input2) {
                            alert('新密码不一致');
                            return false;
                        }
                        $.post('${pageContext.request.contextPath}/modify-password', $('#modifyPasswordForm').serialize(), function(data) {
                            switch (data['message']) {
                                case 'success':
                                    alert('密码修改成功，请重新登录');
                                    window.location = '${pageContext.request.contextPath}';
                                    break;
                                default:
                                    alert(data['message']);
                            }
                        }, 'json');
                        return false;
                    }
                </script>
            </div>

            <button class="btn btn-default form-control" type="submit">修改密码</button>
        </form>

    </div>

</div>
</body>
</html>
