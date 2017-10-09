<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/10/8
  Time: 下午2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的发布</title>
</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body style="background: rgba(189,189,189,0.5)">
<div class="container">
    <div id="message-list">
        <div id="msg">
            <p>总共有 {{datas.messageCount}} 条消息</p>
            <div class="row" v-for="message in messages"
                 style="margin-bottom: 25px;padding: 15px 15px 5px; background-color: rgba(255,255,255,0.80)">
                <div style=";">
                    <a :href="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + message.user.icon">
                        <img class="" v-if="message.user.icon != null" style="width: 15%; max-width: 100px;"
                             :src="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + message.user.icon+ '<s:property value="#application.ossHead"/>'"/>
                    </a>
                    <span>
                        {{message.user.nickname}}&nbsp;&nbsp;{{gender(message.user.gender)}}
                    </span>
                    <br>
                    <span>{{humanTime(message.time)}}</span>
                </div>
                <a :href="'${pageContext.request.contextPath}/detail?id=' + message.id"
                   style="text-decoration: none; color: #000;">
                    <div class="detail" style="padding-left: 8%;padding-right: 8%">
                        <span>{{message.content}}</span>
                        <br>
                        <img class="img-responsive center-block" v-if="message.imagePath != null"
                             :src="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + message.imagePath + '<s:property value="#application.ossThumbnail"/>'"/>
                    </div>
                </a>
                <div style="float: right; padding-right: 5%;">
                    <a :href="'${pageContext.request.contextPath}/detail?id=' + message.id"
                       style="text-decoration: none; color: #000;">
                        <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                        <span>{{message.comments != null ? message.comments.length : 0}} &nbsp;</span>
                    </a>
                    <%--<span class="glyphicon glyphicon-heart-empty favor" aria-hidden="true" :id="message.id"></span>--%>
                    <%--<span>{{message.favors != null ? message.favors.length : 0}}</span>--%>
                    <a v-if="message.user.id == '<s:property value="#session.user.id"/>'"
                       :href="'${pageContext.request.contextPath}/delete-message?id=' + message.id"><s:text
                            name="delete"/></a>
                </div>
            </div>
            <nav aria-label="...">
                <ul class="pager">
                    <li><a href="javascript:previous();" >上一页</a></li>
                    <li><a href="javascript:next();">下一页</a></li>
                </ul>
                <form id="pageForm" onsubmit="return getMessages();">
                    <label for="pageNo">当前页面</label>
                    <input id="pageNo" name="pageNo" :value="datas.pageNo == null ? 1 : datas.pageNo"
                           style="width: 30px;">
                    <label for="pageSize">页面大小</label>
                    <input id="pageSize" name="pageSize" :value="datas.pageSize == null ? 10 : datas.pageSize"
                           style="width: 60px;">
                    <button type="submit">跳转</button>
                </form>
            </nav>
            <%--<div>--%>
                <%--<form id="pageForm" onsubmit="return getMessages();">--%>
                    <%--<label for="pageNo">当前页面</label>--%>
                    <%--<input id="pageNo" name="pageNo" :value="datas.pageNo == null ? 1 : datas.pageNo"--%>
                           <%--style="width: 30px;">--%>
                    <%--<label for="pageSize">页面大小</label>--%>
                    <%--<input id="pageSize" name="pageSize" :value="datas.pageSize == null ? 10 : datas.pageSize"--%>
                           <%--style="width: 60px;">--%>
                    <%--<button type="submit">跳转</button>--%>
                <%--</form>--%>
            <%--</div>--%>
        </div>
        <script>
            var pageSize = 10;
            var pageNo = 1;
            getMessages();
            var vm = new Vue({
                el: '#msg',
                data: {
                    datas: {},
                    messages: []
                },
                methods: {
                    humanTime: timestampToHuman,
                    gender: genderi18n
                }
            });

            function getMessages() {
                $.get('${pageContext.request.contextPath}/json/my-published', $('#pageForm').serialize(), function (data) {
                    vm.datas = data;
                    vm.messages = data['messages'];
                }, 'json');
                return false;
            }

            function next() {
                var pageNo = parseInt(document.getElementById('pageNo').value);
                var pageSize = parseInt(document.getElementById('pageSize').value);
                if (pageNo < Math.round(vm.datas['messageCount']) / pageSize) {
                    document.getElementById('pageNo').value = pageNo + 1;
                }
                getMessages();
            }
            function previous() {
                var pageNo = parseInt(document.getElementById('pageNo').value);
                if (pageNo > 1) {
                    document.getElementById('pageNo').value = pageNo - 1;
                }
                getMessages();
            }
        </script>
    </div>
</div>
</body>
</html>
