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

</head>
<%@include file="header.jsp" %>
<body style="background-color: rgba(168,168,168,0.30);">

<div class="container" style="margin-top: 0px; margin-bottom: 100px;">
    <div class="row">
    </div>
    <div id="content" class="row" style="margin: 15px; padding: 15px; background-color: #fff;">
        <div id="user">
            ${requestScope.message.user.nickname}
            <span id="gender"></span>
            <br>
            <sapn id="time"></sapn>
            <script>
                document.getElementById('gender').innerHTML = genderi18n('${requestScope.message.user.gender}');
                document.getElementById('time').innerHTML = timestampToHuman('${requestScope.message.user.time}');
            </script>
        </div>
        <div id="message" style="margin: 10px;">
            ${requestScope.message.content}
        </div>
        <s:if test="#request.message.imagePath != null">
            <div>
                <a href="<s:property value="#application.pathPrefix"/>${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}${requestScope.message.imagePath}"
                   target="_blank">
                    <img src="<s:property value="#application.pathPrefix"/>${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}${requestScope.message.imagePath}<s:property value="#application.ossThumbnail"/>"
                         class="img-responsive center-block">
                </a>
            </div>
        </s:if>
    </div>
    <div id="comments">
        <div id="comment">
            <div class="row" v-for="comment in comments"
                 style="margin: 15px;padding: 15px; background-color: rgba(255,255,255,0.80)">
                <div>
                    <span>{{comment.user.nickname}}</span>
                    <span>{{gender(comment.user.gender)}}</span>
                    <br>
                    <span>{{humanTime(comment.time)}}</span>
                </div>
                <div style="margin: 10px;">
                    <span>{{comment.content}}</span>
                </div>
            </div>
        </div>
        <p align="center">😂到底了😂</p>
        <script>
            var pageSize = 10, pageNo = 1;
            var vm = new Vue({
                el: '#comment',
                data: {
                    comments: []
                },
                methods: {
                    humanTime: timestampToHuman,
                    gender: genderi18n
                }
            });
            var loading = false;

            function load() {
                loading = true;
                $.get('json/list-comments', {
                    id: ${requestScope.message.id},
                    pageNo: pageNo++,
                    pageSize: pageSize
                }, function (data) {
                    vm.comments = vm.comments.concat(data['comments']);
                }, 'json')
                setTimeout(function () {
                    loading = false;
                }, 1000);
            }

            load();

            $(window).scroll(function () {
                if (loading === false && $(document).scrollTop() - 50 >= $(document).height() - $(window).height()) {
                    load();
                }
            })
        </script>
    </div>
</div>
<div style="overflow: hidden;position: fixed;right: 10px;bottom: 100px;z-index: 10;">
    <div style="overflow: hidden;">
        <div style="padding-top:20px;padding-right:50px;">
            <a href="javascript:history.back(-1);">
                <button class="pure"><span class="glyphicon glyphicon-home"></span>返回上级</button>
            </a>
        </div>
        <div style="padding-top:20px;padding-right:50px;padding-bottom:50px">
            <a href="#" style="float: right;">
                <button class="pure">回到顶部</button>
            </a>
        </div>
    </div>
</div>


</body>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container-fluid">
        <s:if test="#session.user != null">
            <form class="navbar-form navbar-left" action="comment" method="post">
                <div class="form-group">
                    <input type="text" name="comment.content" class="form-control"
                           placeholder="<s:text name="commentInput"/>">
                    <input name="id" value="${requestScope.message.id}" hidden/>
                    <button type="submit" class="btn btn-default"><s:text name="commentSubmit"/></button>
                </div>
            </form>
        </s:if>
        <s:else>
            <div><span>登录后才可以评论</span></div>
        </s:else>
    </div>
</nav>
</html>
