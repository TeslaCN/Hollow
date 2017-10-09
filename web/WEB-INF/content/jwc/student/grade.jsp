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
    <title><s:text name="studentGrade"/></title>

</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body style="background-color: rgba(168,168,168,0.51)">
<div class="container">
    <div id="grades">
        <div id="grade">
            <div>
                <label for="selectYear">学年</label>
                <select id="selectYear" v-model="selectYear">
                    <option disabled value="">选择学年</option>
                    <option>all</option>
                    <option v-for="year in years">{{year}}</option>
                </select>
                <label for="selectSemester">学期</label>
                <select id="selectSemester" v-model="selectSemester">
                    <option disabled value="">选择学期</option>
                    <option>all</option>
                    <option v-for="semester in semesters">{{semester}}</option>
                </select>
                <label for="gpa">平均绩点</label>
                <span id="gpa">{{totalGpa(grades)}}</span>
            </div>
            <div class="row" v-for="grade in grades"
                 v-if="selected(grade.year, grade.semester)"
                 style="margin: 15px;padding: 15px; background-color: rgba(255,255,255,0.80)">
                <div>
                    <div>
                        <span><b>{{grade.title}}</b></span>
                        <div style="float: right;">
                            <span><s:text name="gradeCredit"/></span>
                            <span>{{grade.credit}}</span>
                        </div>
                    </div>
                </div>
                <div style="margin: 10px;">
                    <div><span><s:text name="gradeGpa"/></span><span>{{grade.gpa}}</span></div>
                    <div><span><s:text name="gradeTotal"/></span><span>{{grade.total}}</span></div>
                    <div><span><s:text name="gradeFinal"/></span><span>{{grade.finalExam}}</span></div>
                    <div><span><s:text name="gradeUsual"/></span><span>{{grade.usual}}</span></div>
                    <div v-if="grade.experiment != 0.0">
                        <span><s:text name="gradeExperiment"/></span><span>{{grade.experiment}}</span>
                    </div>
                    <div v-if="grade.retake != 0.0">
                        <span><s:text name="gradeRetake"/></span><span>{{grade.retake}}</span>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var vm = new Vue({
                el: '#grade',
                data: {
                    grades: [],
                    selectYear: '',
                    selectSemester: '',
                    years: [],
                    semesters: []
                },
                methods: {
                    totalGpa: function(grades) {
                        var creditGpaSum = 0;
                        var creditSum = 0;
                        for (i in grades) {
                            if (!vm.selected(grades[i]['year'], grades[i]['semester'])) continue;
                            var credit = grades[i]['credit'];
                            var gpa = grades[i]['gpa'];
                            creditGpaSum += credit * gpa;
                            creditSum += credit;
                        }
                        return Math.round((creditGpaSum / creditSum) * 100) / 100;
                    },
                    selected: function(year, semester) {
                        var selectedYear = false;
                        if (vm.selectYear == 'all') selectedYear = true;
                        else if (year == vm.selectYear) selectedYear = true;
                        else selectedYear = false;

                        var selectedSemester = false;
                        if (vm.selectSemester == 'all') selectedSemester = true;
                        else if(semester == vm.selectSemester) selectedSemester = true;
                        else selectedSemester = false;

                        return selectedYear && selectedSemester;
                    }
                }
            });

            function load() {
                $.get('${pageContext.request.contextPath}/json/jwc/list-grades', {}, function (data) {
                    vm.grades = data['grades'];
                    for (var i = 0; i < vm.grades.length; i++) {
                        if (!contains(vm.years, vm.grades[i]['year'])) {
                            vm.years.push(vm.grades[i]['year'])
                        }
                        if (!contains(vm.semesters, vm.grades[i]['semester'])) {
                            vm.semesters.push(vm.grades[i]['semester'])
                        }
                    }
                }, 'json')
            }

            function contains(array, value) {
                for (i in array) {
                    if (array[i] == value) return true;
                }
                return false;
            }

            load();
        </script>
    </div>
</div>
</body>
</html>
