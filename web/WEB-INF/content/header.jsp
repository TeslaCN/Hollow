<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/17
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
    <script src="utils.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false" style="border: none;">
                <s:if test="#session.user == null">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </s:if>
                <s:else>
                    ${sessionScope.get("user").nickname}
                </s:else>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
                <s:if test="#session.user == null">
                    <form class="navbar-form navbar-right" action="sign-in" onsubmit="return validate();" method="post">
                        <div class="form-group">
                            <input id="inputAccount" name="account" type="text" class="form-control"
                                   placeholder="<s:text name="user.account"/>">

                            <input id="inputPassword" name="password" type="password" class="form-control"
                                   placeholder="<s:text name="user.password"/>">
                        </div>
                        <button type="submit" class="btn btn-default" style="border: none;"><s:text name="signIn"/></button>
                        <script>
                            function validate() {
                                var account = document.getElementById('inputAccount').value;
                                if (account == null) {
                                    alert('<s:text name="user.account.required"/>');
                                    return false;
                                }
                                if (!validEmail(account)) {
                                    alert('<s:text name="user.account.invalid"/>');
                                    return false;
                                }
                                var password = document.getElementById('inputPassword').value;
                                if (password == null) {
                                    alert('<s:text name="user.password.required"/>');
                                    return false;
                                }
                                if (password.length < 8 || password.length > 255) {
                                    alert('<s:text name="user.password.length"/>');
                                    return false;
                                }
                                return true;
                            }
                        </script>
                    </form>
                    <li><a href="sign-up"><s:text name="signUp"/></a></li>
                </s:if>
                <s:if test="#session.user != null">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.get("user").nickname}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="user-profile"><s:text name="user.profile"/></a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="sign-out"><s:text name="signOut"/></a></li>
                        </ul>
                    </li>
                </s:if>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</body>
</html>
