<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/21
  Time: 上午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="forgotPassword"/></title>

</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <h1><s:text name="forgotPassword"/></h1>
    <div class="row">
        <form id="forgot_form" onsubmit="return submitAccount();" style="padding: 10px 30px;">
            <div class="form-group">
                <label for="inputEmail"><s:text name="userAccount"/></label>
                <input id="inputEmail" name="account" class="form-control" placeholder="Email" type="email" required>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <div id="result" style="padding: 10px 40px;"></div>
        <script>
            function submitAccount() {
                $('#forgot_form').css('display', 'none');
                $('#result').html('<p>正在发送重置密码邮件</p>');
                $.post(
                    '${pageContext.request.contextPath}/send-reset',
                    $('#forgot_form').serialize(),
                    function (data) {
                        if (data['message'] != null && data['message'] != 'success') {
                            alert(data['message']);
                        }
                        else if (data['message'] == 'success') {
                            document.getElementById('result').innerHTML = '<p style="color: green">邮件已发送，请在注册邮箱中完成找回密码</p>';
                        }
                    },
                    'json');
                return false;
            }
        </script>
    </div>
</div>
</body>
</html>
