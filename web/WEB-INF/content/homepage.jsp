<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/11
  Time: 下午5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="homepage"/></title>

</head>
<body style="background-color: rgba(168,168,168,0.30); margin-bottom: 100px">
<%@include file="header.jsp" %>
<div class="container">
    <div class="row" style="margin: 15px;">

        <s:if test="#session.user == null">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <span><strong>本站使用且必须使用https加密链接保证访问过程信息安全</strong></span>
            </div>
            <div class="alert alert-info alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <strong>提示：</strong><span>请在右上角注册登录，解锁更多功能😂🌚</span>
            </div>
            <div></div>
        </s:if>
        <s:elseif test="#session.user.level.toString() == 'NOTVALIDATE'">
            <div align="center">
                <div class="alert alert-info" role="alert">
                    <s:text name="validate.description">
                        <s:param value="#session.user.nickname"/>
                        <s:param value="#session.user.account"/>
                    </s:text>
                    <a id="sendEmail" href="javascript:void(0);" onclick="send();" , style="color: red;"><s:text
                            name="clickme.send.validate"/></a>
                    <p id="result"></p>
                </div>
                <script>
                    function send() {
                        $('#sendEmail').css('display', 'none');
                        $('#result').text('<s:text name="validate.email.sending"/>');
                        $.get('send-validate', function (data) {
                            $('#result').text('<s:text name="validate.email.sent"/>');
                            $('#result').css('color', '#0F0');
                        })
                    }
                </script>
            </div>
        </s:elseif>
        <s:else>
            <form action="publish" id="messageform" class="form-group" method="post" enctype="multipart/form-data"
                  onsubmit="return submitting();">
            <textarea id="inputtext" placeholder="<s:text name="homepage.textarea"/>" name="message.content"
                      class="form-control"
                      style="max-height: 300px;"></textarea>
                <input id="inputimage" name="image" accept="imagePath/jpeg,imagePath/png,imagePath/gif" type="file"
                       style="border: none;" onchange="getSize(this.id);"/>
                <p>暂时不支持超过2MB的图片<span id="filesize"></span></p>
                <input id="btn_submit" type="submit" class="btn btn-default" style="border: none;"/>
                <script>
                    function getSize(id) {
                        var size = getFileSize(id);
                        document.getElementById('filesize').innerHTML = '，当前文件大小为 ' + Math.round(size / 1024) + ' KB';
                    }
                </script>
                <div id="publishresult"></div>
            </form>
            <img id="image">
            <div class="row" id="progressbar" style="display: none;">
                <div class="progress" style="margin: 10%;">
                    <div id="bar" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
                         aria-valuemax="100" style="width: 0%;">
                        0%
                    </div>
                </div>
            </div>
            <script>
                function submitting() {
                    if (getFileSize('inputimage') > 2 * 1024 * 1024) {
                        alert('文件大小超过限制');
                        return false;
                    }
                    $('#btn_submit').css('display', 'none');
                    $('#publishresult').append('<span><s:text name="uploading"/></span>');
                    startProgress();
                    var formData = new FormData(document.getElementById('messageform'));
                    $.ajax({
                        url: '${pageContext.request.contextPath}/publish',
                        type: 'POST',
                        data: formData,
                        enctype: 'multipart/form-data',
                        processData: false,
                        contentType: false
                    }).done(function (data) {
                        location.reload(true);
                    });
                    return false;
                }

                function getFileSize(elementID) {
                    var file = document.getElementById(elementID);
                    var size = 0;
                    if (file.value) {
                        if (file.files) {
                            size = file.files[0].size;
                        }
                        else {
                            var imgid = 'image';
                            var obj_img = document.getElementById(imgid);
                            obj_img.dynsrc = file.value;
                            size = obj_img.fileSize;
                        }
                    }
                    return size;
                }

                function startProgress() {
                    $('#progressbar').css('display', '');
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
                }

            </script>
        </s:else>
    </div>

    <div id="message-list">
        <div id="msg">
            <div class="row" v-for="message in messages"
                 style="margin-bottom: 25px;padding: 15px 30px 5px; background-color: rgba(255,255,255,0.80)">
                <div style=";">
                    <span>{{message.user.nickname}}</span>
                    <span>{{gender(message.user.gender)}}</span>
                    <br>
                    <span>{{humanTime(message.time)}}</span>
                </div>
                <a :href="'detail?id=' + message.id" style="text-decoration: none; color: #000;">
                    <div class="detail" style="padding-left: 8%;padding-right: 8%" :id="message.id">
                        <span>{{message.content}}</span>
                        <br>
                        <img class="img-responsive center-block" v-if="message.imagePath != null"
                             :src="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + message.imagePath + '<s:property value="#application.ossThumbnail"/>'"/>
                    </div>
                </a>
                <div style="float: right; padding-right: 5%;">
                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                    <span>{{message.comments != null ? message.comments.length : 0}} &nbsp;</span>
                    <span class="glyphicon glyphicon-heart-empty favor" aria-hidden="true"></span>
                    <span>{{message.favors != null ? message.favors.length : 0}}</span>
                </div>
            </div>
        </div>
        <p align="center">😂已经到底了😂</p>
    </div>

    <%--悬浮--%>
    <div style="overflow: hidden;position: fixed;right: 10px;bottom: 70px;z-index: 10;">
        <div style="overflow: hidden;">
            <div style="padding-top:20px;padding-right:50px;padding-bottom:30px">
                <a href="#" style="float: right;">
                    <button class="pure">回到顶部</button>
                </a>
            </div>
        </div>
    </div>

</div>

<script>
    var pageSize = 10, pageNo = 1;
    var vm = new Vue({
        el: '#msg',
        data: {
            messages: []
        },
        methods: {
            humanTime: timestampToHuman,
            gender: genderi18n
        }
    });
    var loading = false;

    function load() {
        loading = true;
        $.get('json/list-messages', {pageNo: pageNo++, pageSize: pageSize}, function (data, available) {
            vm.messages = vm.messages.concat(data['messages']);
        }, 'json')
        setTimeout(function () {
            likeAction();
            loading = false;
        }, 1000);
    }

    load();

    function likeAction() {
        function like(id) {
            $.post('like?id=' + id);
        }

        $('.favor').on('tap', function () {
            var id = $(this).attr('id');
            like(id);
        });
        $('.favor').click(function () {
            var id = $(this).attr('id');
            like(id);
        });
    }

    $(window).scroll(function () {
        if (loading === false && $(document).scrollTop() - 50 >= $(document).height() - $(window).height()) {
            load();
        }
    })
</script>
</body>
<%--<%@include file="footer.jsp" %>--%>
</html>
