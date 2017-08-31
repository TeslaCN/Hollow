<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/17
  Time: 下午1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        setTimeout(function () {
            window.location = 'homepage';
        });
    </script>
</head>
<body>
<div>
    Welcome! ${sessionScope.user.nickname}<br/>
</div>
</body>
</html>
