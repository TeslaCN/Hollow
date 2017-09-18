<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/15
  Time: 下午11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>体测成绩</title>
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

    <style>
    </style>
</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <div id="tables" class="row">
        <div v-for="record in records">
            <div>
                <h1>{{toSemester(record.examId)}}</h1>
                <p>{{record.message}}</p>
            </div>
            <table class="table table-hover" v-if="record.text != '免测'">
                <thead>
                <th>名称</th>
                <th>成绩</th>
                <th>分数</th>
                <th>等级</th>
                <th>录入时间</th>
                <th>排名</th>
                </thead>
                <tr v-for="item in record.items" :id="record.examId + '_' + item.itemId">
                    <td>{{item.name}}</td>
                    <td>{{item.value}}&nbsp;{{item.unit}}</td>
                    <td>{{item.bestScore}}</td>
                    <td>{{item.scoreGrade}}</td>
                    <td>{{humanTime(item.updateTime)}}</td>
                    <td :id="record.examId + '_' + item.itemId">
                        <div>
                            <button :id="record.examId + '_' + item.itemId + '_' + 'ASCEND'"
                                    v-on:click="getRank(record.examId, item.itemId, item.value, 'ASCEND')">升序
                            </button>
                            <span :id="record.examId + '_' + item.itemId + '_' + 'ASCEND' + '_RESULT'"></span>
                        </div>
                        <div>
                            <button :id="record.examId + '_' + item.itemId + '_' + 'DESCEND'"
                                    v-on:click="getRank(record.examId, item.itemId, item.value, 'DESCEND')">降序
                            </button>
                            <span :id="record.examId + '_' + item.itemId + '_' + 'DESCEND' + '_RESULT'"></span>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <script>
        var vm = new Vue({
            el: '#tables',
            data: {
                records: []
            },
            methods: {
                humanTime: function (millis) {
                    var date = new Date(millis);
                    return date.toLocaleString();
                },
                toSemester: function (examId) {
                    switch (examId) {
                        case 1:
                            return '2015-2';
                        case 8:
                            return '2016-1';
                        case 9:
                            return '2016-2';
                        case 10:
                            return '2017-1';
                        case 11:
                            return '2017-2';
                    }
                    return '';
                },
                getRank: function (examId, itemId, value, orderType) {
                    $('#' + examId + '_' + itemId + '_' + orderType).css('display', 'none');
                    $.get("${pageContext.request.contextPath}/json/jwc/sport/rank", {
                        examId: examId,
                        itemId: itemId,
                        value: value,
                        orderType: orderType
                    }, function (data) {
                        $('#' + examId + "_" + itemId + '_' + orderType + '_RESULT').prepend(data['rank']);
                    }, 'json')
                }
            }
        });

        $.get('${pageContext.request.contextPath}/json/jwc/list-sports', function (data) {
            vm.records = data['records'];
        }, 'json');

    </script>
</div>
</body>
</html>
