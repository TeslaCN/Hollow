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
