<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/7
  Time: 下午9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="studentValidate"/><s:text name="error"/></title>
</head>
<body>
学生数据读取失败
原因：${requestScope.error}
</body>
</html>
