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

<div class="container" style="">
    <div class="row">
    </div>
    <div id="content" class="row" style="margin: 15px; padding: 15px; background-color: #fff;">
        <div id="user">
            <s:if test="#request.message.user.icon != null">
                <a href="<s:property value="#application.pathPrefix"/>${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}${requestScope.user.message.icon}">
                    <img class="" style="width: 15%; max-width: 100px;"
                         src="<s:property value="#application.pathPrefix"/>${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}${requestScope.message.user.icon}<s:property value="#application.ossHead"/>"/>
                </a>
            </s:if>
            <s:if test="#request.message.status.toString() == 'ANONYMOUS'">
                <img style="width: 15%; max-width: 100px"
                     src="<s:property value="#application.pathPrefix"/>/anonymous.jpg<s:property value="#application.ossHead"/>">
                <span style="color: #f00;">匿名</span>
            </s:if>
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
        <div style="float: right; padding-right: 5%;">
            <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
            <span><s:property value="#request.message.comments.size()"/> &nbsp;</span>
            <%--<span class="glyphicon glyphicon-heart-empty favor" aria-hidden="true"></span>--%>
            <%--<span><s:property value="#request.message.favors.size()"/> &nbsp;</span>--%>
            <s:if test="#request.message.user.equals(#session.user) || #session.user.level.toString() == 'ADMINISTRATOR'">
                <a href="${pageContext.request.contextPath}/delete-message?id=${requestScope.message.id}"
                   onclick="return confirm('确认删除吗');"><s:text
                        name="delete"/></a>
            </s:if>
        </div>
    </div>
    <div id="comments">
        <div v-for="comment in comments">
            <div class="row"
                 style="margin: 15px;padding: 15px; background-color: rgba(255,255,255,0.80)">
                <div>
                    <span v-if="comment.status == 'ANONYMOUS'">
                        <img style="width: 15%; max-width: 100px"
                             src="<s:property value="#application.pathPrefix"/>/anonymous.jpg<s:property value="#application.ossHead"/>">
                        <span style="color: #f00;">匿名</span>
                    </span>
                    <a :href="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + comment.user.icon">
                        <img class="" v-if="comment.user.icon != null" style="width: 15%; max-width: 100px;"
                             :src="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + comment.user.icon+ '<s:property value="#application.ossHead"/>'"/>
                    </a>
                    <span>{{comment.user.nickname}}</span>
                    <span>{{gender(comment.user.gender)}}</span>
                    <br>
                    <span>{{humanTime(comment.time)}}</span>
                </div>
                <div style="margin: 10px;">
                    <span>{{comment.content}}</span>
                </div>
                <a style="float: right;"
                   v-if="comment.status != 'ANONYMOUS' && comment.user.id == '<s:property value="#session.user.id"/>' || '${sessionScope.user.level}' == 'ADMINISTRATOR'"
                   :href="'${pageContext.request.contextPath}/delete-comment?id=' + comment.id"
                   onclick="return confirm('确认删除吗');"><s:text name="delete"/></a>
            </div>
        </div>
        <a href="javascript:load();"><p align="center">😂尝试手动加载😂</p></a>
        <p align="center">😂可能到底了😂</p>

    </div>
    <script>
        var pageSize = 10, pageNo = 1;
        var vm = new Vue({
            el: '#comments',
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
            if (loading) return;
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
            if ($(document).scrollTop() + 100 >= $(document).height() - window.innerHeight) {
                load();
            }
        })
    </script>
</div>
<div style="overflow: hidden;position: fixed;right: 1px;bottom: 50px;z-index: 10;">
    <div style="overflow: hidden;">
        <div style="padding: 5px 20px;">
            <a href="javascript:history.back(-1);">
                <button class="pure"></span>返回上级</button>
            </a>
        </div>
        <div style="padding: 5px 20px;">
            <a href="#" style="float: right;">
                <button class="pure">回到顶部</button>
            </a>
        </div>

    </div>
</div>

<div>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"
            style="position: fixed; bottom: 0; width: 100%;">
        <span>评论</span>
    </button>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="comment" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">评论</h4>
                </div>
                <div class="modal-body">
                    <s:if test="#session.user != null">
                        <div class="form-group">
                            <textarea class="form-control" name="comment.content"
                                      placeholder="<s:text name="commentInput"/>"></textarea>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="status" value="anonymous" checked><s:text
                                        name="anonymous"/>
                                </label>
                            </div>
                            <input name="id" value="${requestScope.message.id}" hidden/>
                        </div>
                    </s:if>
                    <s:else>
                        <div><span>登录后才可以评论</span></div>
                    </s:else>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" <s:if test="#session.user == null"> disabled</s:if>>发表
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
