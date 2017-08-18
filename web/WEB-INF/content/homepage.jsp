<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/11
  Time: 下午5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="homepage"/></title>
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
    <div align="center">
        <s:form action="/publish">
            <s:textarea name="message.content"/>
            <s:submit cssClass="btn btn-default"/>
        </s:form>
    </div>

    <button onclick="load()">Click me</button>

    <div id="message-list">
        <div id="msg">
            <div v-for="message in messages">
                {{message.content}}
            </div>
        </div>

        <div id="show">
        </div>
    </div>
</div>

<script>
    var pageSize = 10, pageNo = 1;
    var msgData;
    var vm = new Vue({
        el: '#msg',
        data: {
            messages: msgData
        }
    });
    function load() {
        $.get('json/list-messages', {pageNo: pageNo++, pageSize: pageSize}, function (data, status) {
            msgData += data['messages']
        }, 'json')
    }
</script>
</body>
<%@include file="footer.jsp" %>
</html>
