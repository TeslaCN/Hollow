<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/25
  Time: 下午10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="user.profile"/></title>

</head>
<%@include file="header.jsp" %>
<body>
<div class="container">
    <div id="icon" class="row">
    </div>
    <div id="info" class="row">
        <div>
        </div>
        <div>
            <table class="table table-hover">
                <tr>
                    <td><s:text name="user.nickname"/></td>
                    <td>${requestScope.user.nickname}</td>
                </tr>
                <tr>
                    <td><s:text name="user.gender"/></td>
                    <td>${requestScope.user.gender}</td>
                </tr>
                <tr>
                    <td><s:text name="user.account"/></td>
                    <td>${requestScope.user.account}</td>
                </tr>
                <tr>
                    <td><s:text name="signUpTime"/></td>
                    <td id="signUpTime"></td>
                </tr>
            </table>
            <script>
                document.getElementById('signUpTime').innerHTML = new Date(${requestScope.user.time}).toLocaleString();
            </script>
        </div>
    </div>
</div>
</body>
</html>
