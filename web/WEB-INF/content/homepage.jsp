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
    <script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.js"></script>
    <script>
        <%@include file="/utils.js"%>
    </script>
    <style>
        <%@include file="/default.css"%>
    </style>
</head>
<body style="background-color: rgba(168,168,168,0.30); margin-bottom: 100px">
<%@include file="header.jsp" %>
<div class="container">
    <div class="row" style="margin: 15px;">
        <s:if test="#session.user == null">
            <div>
                <form action="sign-in" method="post">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" name="account" class="form-control" id="exampleInputEmail1"
                               placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                               placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-default"><s:text name="signIn"/></button>
                    <a href="sign-up"><s:text name="signUp"/></a>
                </form>
            </div>
        </s:if>
        <s:elseif test="#session.user.level.toString() == 'NOTVALIDATE'">
            <div align="center">
                <s:text name="validate.description">
                    <s:param value="#session.user.nickname"/>
                    <s:param value="#session.user.account"/>
                </s:text>
                <a id="sendEmail" href="javascript:void(0);" onclick="send();"><s:text name="clickme.send.validate"/></a>
                <p id="result"></p>
                <script>
                    function send() {
                        $('#sendEmail').css('display', 'none');
                        $('#result').text('<s:text name="validate.email.sending"/>');
                        $.get('send-validate', function (data) {
                            $('#result').text('<s:text name="validate.email.sent"/>');
                            $('#result').css('color', '#0F0');
                        })
                    }
                </script>
            </div>
        </s:elseif>
        <s:else>
            <form action="publish" class="form-group" method="post" enctype="multipart/form-data"
                    <%--<s:if test="#session.user == null"> onsubmit="return notSignIn();"</s:if>--%>
            onsubmit="return submitting();">
            <textarea placeholder="<s:text name="homepage.textarea"/>" name="message.content" class="form-control"
                      style="max-height: 300px;"></textarea>
                <input name="image" accept="imagePath/jpeg,imagePath/png,imagePath/gif" type="file"
                       <%--class="btn btn-default"--%>
                       style="border: none;"/>
                <p>暂时不支持超过2MB的图片</p>
                <input id="btn_submit" type="submit" class="btn btn-default" style="border: none;"/>
                <div id="result"></div>
            </form>
            <script>
                function submitting() {
                    $('#btn_submit').css('display', 'none');
                    $('#result').append('<span><s:text name="uploading"/></span>');
                    return true;
                }
                function notSignIn() {
                    alert('<s:text name="signIn.required"/>');
                    return false;
                }
            </script>
        </s:else>
    </div>

    <div id="message-list">
        <div id="msg">
            <div class="row" v-for="message in messages"
                 style="margin: 15px;padding: 15px; background-color: rgba(255,255,255,0.80)">
                <div>
                    <span>{{message.user.nickname}}</span>
                    <span>{{gender(message.user.gender)}}</span>
                    <br>
                    <span>{{humanTime(message.time)}}</span>
                </div>
                <a :href="'detail?id=' + message.id" style="text-decoration: none; color: #000;">
                    <div class="detail" style="margin: 10px;" :id="message.id">
                        <span>{{message.content}}</span>
                        <br>
                        <img class="img-responsive center-block" v-if="message.imagePath != null"
                             :src="'<s:property value="#application.oss"/>' + message.imagePath + '<s:property value="#application.ossThumbnail"/>'"/>
                    </div>
                </a>
                <div style="float: right">
                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                    <span>{{message.comments != null ? message.comments.length : 0}} &nbsp;</span>
                    <span class="glyphicon glyphicon-heart-empty favor" aria-hidden="true"></span>
                    <span>{{message.favors != null ? message.favors.length : 0}}</span>
                </div>
            </div>
        </div>
        <p align="center">下拉加载更多</p>
    </div>

    <%--悬浮--%>
    <div style="overflow: hidden;position: fixed;right: 10px;bottom: 70px;z-index: 10;">
        <div style="overflow: hidden;">
            <div style="padding-top:20px;padding-right:50px;padding-bottom:30px">
                <a href="#" style="float: right;">
                    <button class="pure">回到顶部</button>
                </a>
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
        },
        methods: {
            humanTime: timestampToHuman,
            gender: genderi18n
        }
    });
    var loading = false;

    function load() {
        loading = true;
        $.get('json/list-messages', {pageNo: pageNo++, pageSize: pageSize}, function (data, available) {
            vm.messages = vm.messages.concat(data['messages']);
        }, 'json')
        setTimeout(function () {
            likeAction();
            loading = false;
        }, 1000);
    }

    load();

    function likeAction() {
        function like(id) {
            $.post('like?id=' + id);
        }

        $('.favor').on('tap', function () {
            var id = $(this).attr('id');
            like(id);
        });
        $('.favor').click(function () {
            var id = $(this).attr('id');
            like(id);
        });
    }

    $(window).scroll(function () {
        if (loading === false && $(document).scrollTop() - 50 >= $(document).height() - $(window).height()) {
            load();
        }
    })
</script>
</body>
<%--<%@include file="footer.jsp" %>--%>
</html>
