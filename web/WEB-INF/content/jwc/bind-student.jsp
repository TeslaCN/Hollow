<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/7
  Time: 下午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="studentValidate"/></title>

</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <s:if test="#request.error != null && #request.error != ''">
        <div class="alert alert-danger" role="alert"><s:property value="#request.error"/></div>
    </s:if>
    <form action="${pageContext.request.contextPath}/jwc/bind-student" method="post">
        <div class="form-group">
            <label for="inputId"><s:text name="studentId"/></label>
            <input type="text" name="id" class="form-control" id="inputId" placeholder="<s:text name="studentId"/>">
        </div>
        <div class="form-group">
                <label for="inputPassword1"><s:text name="studentPassword"/></label>
            <input type="password" name="password" class="form-control" id="inputPassword1"
                   placeholder="<s:text name="studentPassword"/>">
        </div>
        <button type="submit" class="btn btn-primary"><s:text name="signIn"/></button>
    </form>

</div>

</body>
</html>
