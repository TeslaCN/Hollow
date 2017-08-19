<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/15
  Time: 上午12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Detail</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>
requestScope:
${requestScope.message}

<hr>
<div id="user">
    ${requestScope.message.user}
</div>
<hr>
<div id="content">
    ${requestScope.message.content}
</div>
<hr>
<div id="comments">
    ${requestScope.message.comments}
</div>
<s:debug/>
</body>
</html>
