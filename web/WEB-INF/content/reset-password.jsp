<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/22
  Time: 下午1:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="resetPassword"/></title>
</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <div class="row">
        <form id="resetForm" style="padding: 10px 30px;" onsubmit="return submitReset();">
            <div class="form-group">
                <label for="account"><s:text name="userAccount"/></label>
                <span id="account">${requestScope.account}</span>
                <input id="inputAccount" style="display: none;" value="${requestScope.account}">
            </div>
            <div class="form-group">
                <label for="inputPassword"><s:text name="userPassword"/>
                    <span>
                        &nbsp;(<s:text name="userPasswordLength"/>)
                    </span>
                </label>
                <input name="newPassword" id="inputPassword" type="password" placeholder="Password" class="form-control"
                       oninput="passwordValidate();">
            </div>
            <div id="confirmGroup" class="form-group">
                <label for="inputConfirmPassword"><s:text name="userPasswordConfirm"/>
                    <span id="confirmTip" style="display: none; color: red;">
                        <s:text name="userPasswordDifferent"/>
                   </span>
                </label>
                <input id="inputConfirmPassword" placeholder="Password" type="password" class="form-control"
                       oninput="passwordValidate();">
            </div>
            <script>
                function passwordValidate() {
                    var input1 = document.getElementById('inputPassword').value;
                    var input2 = document.getElementById('inputConfirmPassword').value;
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
            <input id="uuid" name="uuid" style="display: none;" value="${requestScope.uuid}">
            <button id="btnSubmit" type="submit" class="btn btn-default">Submit</button>
        </form>
        <script>
            function submitReset() {
                $('#btnSubmit').css('display', 'none');
                $('#btnSubmit')
                $.post('${pageContext.request.contextPath}/reset-password', $('#resetForm').serialize(), function (data) {
                    if (data['message'] != 'success') {
                        alert(data['message']);
                    } else if(data['message'] == 'success') {
                        window.location = '${pageContext.request.contextPath}/';
                    }
                });
                return false;
            }
        </script>
    </div>
</div>
</body>
</html>
