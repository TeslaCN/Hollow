<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/4
  Time: 下午9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="user.validate"/></title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div>
    <button id="sendEmail" onclick="send();" style="">发送验证邮件</button>
    <p id="result"></p>
    <script>
        function send() {
            $('#sendEmail').css('display', 'none');
            $.get('send-validate', function(data) {
                $('#p').html(data);
            })
        }
    </script>
</div>
</body>
</html>
