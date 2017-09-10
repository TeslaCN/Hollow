<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/8
  Time: 下午2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="student.exam"/></title>
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
<body style="background-color: rgba(168,168,168,0.51)">
<div class="container">
    <div id="exams">
        <div id="exam">
            <div class="row" v-for="exam in exams"
                 style="margin: 15px;padding: 15px; background-color: rgba(255,255,255,0.80)">
                <div>
                    <div>
                        <span><b>{{exam.title}}</b></span>
                        <div style="float: right;">
                            <span><s:text name="exam.time"/></span>
                            <span>{{exam.time}}</span>
                        </div>
                    </div>
                </div>
                <div style="margin: 10px;">
                    <div><span><s:text name="exam.stuName"/></span><span>{{exam.stuName}}</span></div>
                    <div><span><s:text name="exam.zone"/></span><span>{{exam.zone}}</span></div>
                    <div><span><s:text name="exam.locale"/></span><span>{{exam.locale}}</span></div>
                    <div><span><s:text name="exam.seat"/></span><span>{{exam.seat}}</span></div>
                    <div v-if="exam.form != ''">
                        <span><s:text name="exam.form"/></span><span>{{exam.form}}</span>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var vm = new Vue({
                el: '#exam',
                data: {
                    exams: []
                },
                methods: {}
            });

            function load() {
                $.get('${pageContext.request.contextPath}/json/jwc/list-exams', {}, function (data) {
                    vm.exams = data['exams'];
                }, 'json')
            }

            load();
        </script>
    </div>
</div>
</body>
</html>
