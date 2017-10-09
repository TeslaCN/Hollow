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
    <title><s:text name="studentExam"/></title>

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
                            <span><s:text name="examTime"/></span>
                            <span>{{exam.time}}</span>
                        </div>
                    </div>
                </div>
                <div style="margin: 10px;">
                    <div><span><s:text name="examStuName"/></span><span>{{exam.stuName}}</span></div>
                    <div><span><s:text name="examZone"/></span><span>{{exam.zone}}</span></div>
                    <div><span><s:text name="examLocale"/></span><span>{{exam.locale}}</span></div>
                    <div><span><s:text name="examSeat"/></span><span>{{exam.seat}}</span></div>
                    <div v-if="exam.form != ''">
                        <span><s:text name="examForm"/></span><span>{{exam.form}}</span>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var vm = new Vue({
                el: '#exam',
                data: {
                    exams: [],

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
