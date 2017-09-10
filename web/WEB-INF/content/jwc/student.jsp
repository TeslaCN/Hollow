<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/8
  Time: 上午12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="student.profile"/></title>
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
</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body onload="reload();">
<div class="container">
    <div style="padding-left: 10%;padding-right: 10%;">
        <div>
            <label for="stu_id"><s:text name="student.id"/></label>
            <span id="stu_id">${sessionScope.user.stuId}</span>
        </div>
        <div>
            <label for="stu_name"><s:text name="student.name"/></label>
            <span id="stu_name">${sessionScope.user.name}</span>
        </div>
        <div>
            <div><a href="bind-student"><s:text name="student.password.modified"/>?</a></div>
        </div>
        <hr>
        <s:if test="#session.user.stuId == null || #session.user.name == null">
            <a href="bind-student">
                <button><s:text name="clickme"/><s:text name="student.validate"/></button>
            </a>
        </s:if>
        <s:else>
            <div id="reload" style="margin-bottom: 15px;">
                <button onclick="reload();"><s:text name="reload"/></button>
            </div>
            <div id="buttons">
                <div class="row">
                    <div id="queryGrade" class="col-xs-6 col-sm-4">
                        <a href="student/grade">
                            <button><s:text name="clickme"/><s:text name="student.grade.query"/></button>
                        </a>
                    </div>
                    <div>
                        <a href="student/exam">
                            <button><s:text name="clickme"/><s:text name="student.exam.query"/></button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="progress" style="margin: 10%;">
                    <div id="bar" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
                         aria-valuemax="100" style="width: 0%;">
                        0%
                    </div>
                </div>
            </div>
            <script>
                function reload() {
                    $('#bar').css('width', '0%');
                    $('#bar').attr('aria-valuenow', 0);
                    var i = 0;
                    var progressbar = setInterval(function () {
                        if (++i < 100) {
                            $('#bar').attr('aria-valuenow', i);
                            $('#bar').css('width', i + '%');
                            $('#bar').text(i + '%');
                        }
                    }, 120);
                    $('#reload').css('display', 'none');
                    $('#buttons').css('display', 'none');
                    $('#result').append('<p>正在从教务系统读取最新成绩数据，请等待...（大约需要10秒）</p>')
                    $.get('load-student', function (data) {
                        $('#result').append('<p>处理完毕</p>');
                        $('#result').append(data);
                        $('#reload').css('display', '');
                        $('#buttons').css('display', '');
                        clearInterval(progressbar);
                        $('#bar').attr('aria-valuenow', 100);
                        $('#bar').css('width', '100%');
                        $('#bar').text('100%');
                    })
                }
            </script>
        </s:else>
        <div id="result">

        </div>
    </div>
</div>
</body>
</html>
