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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

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
    <style>
        .redirect {
        }
    </style>
</head>
<body style="background-color: rgba(168,168,168,0.30); margin-bottom: 100px">
<%@include file="header.jsp" %>
<div class="container">
    <div align="center">
        <s:form action="/publish" cssClass="form-group">
            <s:textarea name="message.content" cssClass="form-control"/>
            <s:submit cssClass="btn btn-default"/>
        </s:form>
    </div>

    <div id="message-list">
        <div id="msg">
            <div class="row" v-for="message in messages"
                 style="margin: 15px;padding: 15px; background-color: rgba(255,255,255,0.50)">
                <div>
                    <span>{{message.user.nickname}}</span>
                </div>
                <div class="redirect" style="margin: 10px;" :id="message.id">
                    <span>{{message.content}}</span>
                </div>
                <div style="float: right">
                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                    <span>{{message.comments != null ? message.comments.length : 0}} &nbsp;</span>
                    <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
                    <span>{{message.favors != null ? message.favors.length : 0}}</span>
                </div>
            </div>
        </div>

    </div>
</div>

<script>
    var pageSize = 10, pageNo = 1;
    var vm = new Vue({
        el: '#msg',
        data: {
            messages: []
        }
    });
    var loading = false;

    function load() {
        loading = true;
        $.get('json/list-messages', {pageNo: pageNo++, pageSize: pageSize}, function (data, available) {
            vm.messages = vm.messages.concat(data['messages']);
        }, 'json')
        setTimeout(function () {
            generateUrl();
            loading = false;
        }, 1000);
    }

    function generateUrl() {
        $('.redirect').on('tap', function () {
            var id = $(this).attr('id');
            window.location = 'detail?id=' + id;
        });
        $('.redirect').click(function () {
            var id = $(this).attr('id');
            window.location = 'detail?id=' + id;
        });
    }

    load();

    $(window).scroll(function () {
        if (loading === false && $(document).scrollTop() - 50 >= $(document).height() - $(window).height()) {
            load();
        }
    })
</script>
</body>
<%@include file="footer.jsp" %>
</html>
