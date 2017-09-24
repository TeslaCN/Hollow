<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/4
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="userValidate"/></title>
</head>
<body>
<h1><s:text name="userValidate"/><s:text name="success"/></h1>
<script>
    setTimeout(function () {
        window.location = 'homepage';
    }, 1000);
</script>
</body>
</html>
