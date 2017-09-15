<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/9/12
  Time: 下午1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.js"></script>
</head>
<body>

<div>
    <input id="page" name="page" value="390">
    <input id="examId" name="examId" value="1">
    <button onclick="post();">POST</button>
    <button onclick="traverse();">Traverse</button>

    <div id="result"></div>
    <script>
        function traverse() {
            $('#result').append('<p>Starting traverse...</p>');
            $('button').css('display', 'none');
            $.get('${pageContext.request.contextPath}/jwc/traverse', function (data) {
                $('#result').append('<p>Started!</p>');
                $('#result').append(data);
            });
        }
        function post() {
            $('#result').append('<p>Loading...</p>');
            var page = document.getElementById('page').value;
            var examId = document.getElementById('examId').value;
            $.get('${pageContext.request.contextPath}/jwc/sport', {page: page, examId: examId}, function (data) {
                $('#result').append('<p>Got Data</p>');
                $('#result').append(data);
            });
        }
    </script>

</div>
</body>
</html>
