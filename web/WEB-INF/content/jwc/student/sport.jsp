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

</head>
<%@include file="/WEB-INF/content/header.jsp" %>
<body>
<div class="container">
    <div class="alert alert-danger alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <strong>🌚警告🌚</strong>
        <span>由于<strong>学校<u>&nbsp;官方&nbsp;</u>体育成绩查询系统<u>😂本身漏洞可能较多😂</u></strong>
            ，体能数据所对应的分数可能会不稳定（尤其是总分），此处数据仅供参考</span>
    </div>
    <div class="alert alert-info alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <strong>提醒：</strong><span>年级排名为全校同级排名，如果数值相同，名次也相同</span>
    </div>
    <s:if test="#session.user.level.toString() != 'STUDENT'">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>提示：</strong>本账号尚未绑定学生账号<s:a value="/jwc/bind-student">点我进行学生认证</s:a>
        </div>
    </s:if>
    <div id="tables" class="row">
        <div v-for="record in records">
            <div>
                <h1>{{toSemester(record.examId)}}</h1>
                <p>{{record.message}}</p>
            </div>
            <div>
                手机页面可向右滚动表格
            </div>
            <div class="table-responsive">
                <table class="table table-hover" v-if="record.text != '免测'">
                    <thead>
                    <th>名称</th>
                    <th>成绩</th>
                    <th>分数</th>
                    <th>等级</th>
                    <th>🌚没有对比就没有伤害🌚</th>
                    <th>录入时间</th>
                    </thead>
                    <tr v-for="item in record.items" :id="record.examId + '_' + item.itemId"
                        v-if="(student.gender == 'MALE' && item.itemId <= 8) || (student.gender == 'FEMALE' && item.itemId != 5 && item.itemId != 8)">
                        <td>{{item.name}}</td>
                        <td>{{item.value}}&nbsp;{{item.unit}}</td>
                        <td>{{item.bestScore}}</td>
                        <td>{{item.scoreGrade}}</td>
                        <td :id="record.examId + '_' + item.itemId">
                            <div>
                                <button :id="record.examId + '_' + item.itemId + '_rank'"
                                        v-on:click="getRank(record.examId, item.itemId, item.value)">🌚排名🌚
                                </button>
                                <span :id="record.examId + '_' + item.itemId + '_RESULT'"></span>
                            </div>
                        </td>
                        <td>{{humanTime(item.updateTime)}}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <script>
        var vm = new Vue({
            el: '#tables',
            data: {
                student: {},
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
                getRank: function (examId, itemId, value) {
                    $('#' + examId + '_' + itemId + '_rank').css('display', 'none');
                    $.get("${pageContext.request.contextPath}/json/jwc/sport/rank", {
                        examId: examId,
                        itemId: itemId,
                        value: value
                    }, function (data) {
                        var total = data['total'];
                        var ascendRank = data['rank'];
                        var same = data['same'];
                        var descendRank = total - ascendRank - same;

                        var ascendPercent = Math.floor((ascendRank / total) * 100);
                        var descendPercent = Math.floor((descendRank / total) * 100);

                        var content = '';

                        var contentAscend = '超过了 ' + ascendPercent + '% 的同学，排名 ' + descendRank + ' / ' + total + ' ，有 ' + same + ' 位同学并列';
                        var contentDescend = '超过了 ' + descendPercent + '% 的同学，排名 ' + ascendRank + ' / ' + total + ' ，有 ' + same + ' 位同学并列';
                        switch (itemId) {
                            case 1://height
                                content = '身高' + contentDescend;
                                break;
                            case 2://weight
                                content = '体重' + contentDescend;
                                break;
                            case 3://breath
                                content = '肺活量' + contentDescend;
                                break;
                            case 4://50m
                                content = '50m 速度' + contentAscend;
                                break;
                            case 5://1000m
                                content = '1000m 速度' + contentAscend;
                                break;
                            case 6://jump
                                content = '跳远距离' + contentDescend;
                                break;
                            case 7://sit and forward
                                content = '体前屈' + contentDescend;
                                break;
                            case 8://male up
                                content = '引体向上个数' + contentDescend;
                                break;
                            case 9://female sit
                                content = '仰卧起坐个数' + contentDescend;
                                break;
                            case 10://800m
                                content = '800m 速度' + contentAscend;
                                break;
                        }

                        $('#' + examId + "_" + itemId + '_RESULT').prepend(content);
                    }, 'json')
                }
            }
        });

        $.get('${pageContext.request.contextPath}/json/jwc/list-sports', function (data) {
            vm.student = data;
            vm.records = data['records'];
        }, 'json');

    </script>
</div>
</body>
</html>
