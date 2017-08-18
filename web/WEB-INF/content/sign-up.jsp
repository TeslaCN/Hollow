<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/13
  Time: 上午9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="signUp"/></title>
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
<body>
<div class="container">
    <div class="row">
    </div>
    <div class="row" style="padding: 30px">
        <form action="sign-up">
            <div class="form-group">
                <label for="inputEmail"><s:text name="user.account"/></label>
                <input name="user.account" type="email" class="form-control" id="inputEmail" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="inputPassword"><s:text name="user.password"/></label>
                <input name="user.password" type="password" class="form-control" id="inputPassword"
                       placeholder="Password">
            </div>
            <div class="form-group">
                <label for="inputNickname"><s:text name="user.nickname"/></label>
                <input name="user.nickname" type="text" id="inputNickname" placeholder="Nickname" class="form-control">
                <p class="help-block">Example block-level help text here.</p>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox"> Check me out
                </label>
            </div>
            <div class="form-group">
                <label for="inputGender"><s:text name="user.gender"/></label>
                <s:radio id="inputGender" list="{'MALE', 'FEMALE'}" name="user.gender"/>
            </div>
            <div class="form-group">
                <label for="inputRemark"><s:text name="user.remark"/></label>
                <s:textarea id="inputRemark" name="user.remark" cssClass="form-control"/>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
