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
    <script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.js"></script>
    <script>
        <%@include file="/utils.js"%>
    </script>
</head>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Link</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<body style="background-color: rgba(168,168,168,0.30);">

<div class="container" style="margin-top: 100px; margin-bottom: 100px;">
    <div class="row">
    </div>
    <div id="content" class="row" style="margin: 15px; padding: 15px; background-color: #fff;">
        <div id="user">
            ${requestScope.message.user.nickname}
        </div>
        <div id="message" style="margin: 10px;">
            ${requestScope.message.content}
        </div>
        <s:if test="#request.message.imagePath != null">
            <div>
                <a href="<s:property value="#application.oss"/>${requestScope.message.imagePath}"
                   target="_blank">
                    <img src="<s:property value="#application.oss"/>${requestScope.message.imagePath}<s:property value="#application.ossThumbnail"/>"
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
                    <span>{{comment.user.gender}}</span>
                    <br>
                    <span>{{humanTime(comment.time)}}</span>
                </div>
                <div style="margin: 10px;">
                    <span>{{comment.content}}</span>
                </div>
            </div>
        </div>
        <p align="center">下拉加载更多</p>
        <script>
            var pageSize = 10, pageNo = 1;
            var vm = new Vue({
                el: '#comment',
                data: {
                    comments: []
                },
                methods: {
                    humanTime: timestampToHuman
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

</body>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container-fluid">
        <form class="navbar-form navbar-left" action="comment" method="post">
            <div class="form-group">
                <input type="text" name="comment.content" class="form-control"
                       placeholder="<s:text name="comment.input"/>">
                <input name="id" value="${requestScope.message.id}" hidden/>
                <button type="submit" class="btn btn-default"><s:text name="comment.submit"/></button>
            </div>
        </form>
    </div>
</nav>
</html>
