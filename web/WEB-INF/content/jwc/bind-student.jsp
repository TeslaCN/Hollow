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
    <title><s:text name="student.validate"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <script src="https://cdn.bootcss.com/vue/2.4.2/vue.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <s:if test="#request.error != null && #request.error != ''">
        <div class="alert alert-danger" role="alert"><s:property value="#request.error"/></div>
    </s:if>
    <s:form action="/jwc/bind-student" method="post">
        <div class="form-group">
                <%--<label for="exampleInputEmail1"><s:text name="student.id"/></label>--%>
            <input type="text" name="id" class="form-control" id="inputId" placeholder="<s:text name="student.id"/>">
        </div>
        <div class="form-group">
                <%--<label for="inputPassword1"><s:text name="student.password"/></label>--%>
            <input type="password" name="password" class="form-control" id="inputPassword1"
                   placeholder="<s:text name="student.password"/>">
        </div>
        <button type="submit" class="btn btn-default"><s:text name="signIn"/></button>
    </s:form>
</div>

</body>
</html>
