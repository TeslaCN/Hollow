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
    <title><s:text name="student.grade"/></title>
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
    <div id="grades">
        <div id="grade">
            <div class="row" v-for="grade in grades"
                 style="margin: 15px;padding: 15px; background-color: rgba(255,255,255,0.80)">
                <div>
                    <div>
                        <span><b>{{grade.title}}</b></span>
                        <div style="float: right;">
                            <span><s:text name="grade.credit"/></span>
                            <span>{{grade.credit}}</span>
                        </div>
                    </div>
                </div>
                <div style="margin: 10px;">
                    <div><span><s:text name="grade.gpa"/></span><span>{{grade.gpa}}</span></div>
                    <div><span><s:text name="grade.total"/></span><span>{{grade.total}}</span></div>
                    <div><span><s:text name="grade.final"/></span><span>{{grade.finalExam}}</span></div>
                    <div><span><s:text name="grade.usual"/></span><span>{{grade.usual}}</span></div>
                    <div v-if="grade.experiment != 0.0">
                        <span><s:text name="grade.experiment"/></span><span>{{grade.experiment}}</span>
                    </div>
                    <div v-if="grade.retake != 0.0">
                        <span><s:text name="grade.retake"/></span><span>{{grade.retake}}</span>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var vm = new Vue({
                el: '#grade',
                data: {
                    grades: []
                },
                methods: {}
            });

            function load() {
                $.get('${pageContext.request.contextPath}/json/jwc/list-grades', {}, function (data) {
                    vm.grades = data['grades'];
                }, 'json')
            }

            load();
        </script>
    </div>
</div>
</body>
</html>
