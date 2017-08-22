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
    <title><s:text name="detail"/></title>
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
    <script>
    </script>
</head>
<body>
<div class="container">
    <div class="row">
    </div>
    <div id="content" class="row" style="margin: 15px; padding: 15px;">
        <div id="user">
            ${requestScope.message.user.nickname}
        </div>
        <div id="message" style="margin: 10px;">
            ${requestScope.message.content}
        </div>
    </div>
    <div id="comments">

    </div>
    <s:debug/>
</div>
<nav class="navbar navbar-default navbar-fixed-buttom">
    <div class="container-fluid">
        <s:form cssClass="navbar-form navbar-left" action="comment">
            <div class="form-group">
                <input type="text" class="form-control " placeholder="<s:text name="comment.input"/>">
            </div>
            <button type="submit" class="btn btn-default"><s:text name="comment.submit"/></button>
        </s:form>
    </div>
</nav>
</body>
</html>
