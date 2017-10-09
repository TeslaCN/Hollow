<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/17
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html xmlns:wb="https://open.weibo.com/wb">
<head>
    <title>header</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <script src="https://cdn.bootcss.com/vue/2.4.2/vue.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script>
        <%@include file="/utils.js"%>
    </script>
    <style>
        <%@include file="/default.css"%>
    </style>

    <script src="https://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=3480942371" type="text/javascript"
            charset="utf-8"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <s:a value="/" cssClass="navbar-brand">
            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;主页
        </s:a>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false" style="border: none;">
                <s:if test="#session.user == null">
                    <span class="sr-only">Toggle navigation</span>
                    <%--<span class="icon-bar"></span>--%>
                    <%--<span class="icon-bar"></span>--%>
                    <%--<span class="icon-bar"></span>--%>
                    <span>更多</span>
                </s:if>
                <s:else>
                    ${sessionScope.get("user").nickname}
                </s:else>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <%--<ul class="nav navbar-nav">--%>
            <%--<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>--%>
            <%--<li><a href="#">Link</a></li>--%>
            <%--<li class="dropdown">--%>
            <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"--%>
            <%--aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
            <%--<ul class="dropdown-menu">--%>
            <%--<li><a href="#">Action</a></li>--%>
            <%--<li><a href="#">Another action</a></li>--%>
            <%--<li><a href="#">Something else here</a></li>--%>
            <%--<li role="separator" class="divider"></li>--%>
            <%--<li><a href="#">Separated link</a></li>--%>
            <%--<li role="separator" class="divider"></li>--%>
            <%--<li><a href="#">One more separated link</a></li>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--</ul>--%>

            <ul class="nav navbar-nav navbar-right">
                <s:if test="#session.user == null">
                    <form id="header_signInForm" class="navbar-form navbar-right"
                        <%--action="${pageContext.request.contextPath}/sign-in" --%>
                          onsubmit="header_signIn();return false;"
                          method="post">
                            <%--<div class="form-group">--%>
                        <input id="header_inputAccount" name="account" type="text" class="form-control"
                               placeholder="<s:text name="userAccount"/>" required>

                        <input id="header_inputPassword" name="password" type="password" class="form-control"
                               placeholder="<s:text name="userPassword"/>" required>
                            <%--</div>--%>
                        <button type="submit" class="btn btn-default" style="border: none;">
                            <s:text
                                    name="signIn"/></button>
                        <script>
                            function header_signIn() {
                                var account = document.getElementById('header_inputAccount').value;
                                if (account == null) {
                                    alert('<s:text name="userAccountRequired"/>');
                                    return false;
                                }
                                var password = document.getElementById('header_inputPassword').value;
                                if (password == null) {
                                    alert('<s:text name="userPasswordRequired"/>');
                                    return false;
                                }
                                if (password.length < 8 || password.length > 255) {
                                    alert('<s:text name="userPasswordLength"/>');
                                    return false;
                                }
                                console.log('Data validated done! Start posting...')
                                setTimeout(function () {
                                    $.post(
                                        '${pageContext.request.contextPath}/sign-in',
                                        $('#header_signInForm').serialize(),
                                        function (data) {
                                            switch (data['message']) {
                                                case 'success':
                                                    location.reload(true);
                                                    return false;
                                                default:
                                                    alert(data['message']);
                                            }
                                        },
                                        'json');
                                });
                                return false;
                            }
                        </script>
                    </form>
                    <li>
                        <a>
                            <wb:login-button type="3,2" onlogin="login" onlogout="logout" onclick="alert('新浪还在审核我，暂时没法使用');">登录按钮</wb:login-button>
                        </a>
                    </li>
                    <li><s:a value="/forgot"><s:text name="forgotPassword"/></s:a></li>
                    <li><s:a value="/sign-up-page"><s:text name="signUp"/></s:a></li>
                </s:if>
                <s:if test="#session.user != null">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.get("user").nickname}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                                <%--<s:if test="#session.user != null && #session.user.level.toString() == 'ADMINISTRATOR'">--%>
                                <%--<li><s:a value="/administrator/all-messages">内容管理</s:a></li>--%>
                                <%--<li role="separator" class="divider"></li>--%>
                                <%--</s:if>--%>
                            <li><s:a value="/user-profile"><s:text name="userProfile"/></s:a></li>
                            <li><s:a value="/user/my-messages">我的发布</s:a></li>
                            <li role="separator" class="divider"></li>
                            <li><s:a value="/jwc/student"><s:text name="studentValidate"/></s:a></li>
                            <li><s:a value="/jwc/student/sport">体测排名</s:a></li>
                            <li role="separator" class="divider"></li>
                            <li><s:a value="/sign-out"><s:text name="signOut"/></s:a></li>
                        </ul>
                    </li>
                </s:if>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-feuid -->
</nav>
</body>
</html>
