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
