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
    <title>树洞——华农匿名社区</title>

</head>
<body style="
/*background-color: rgba(168,168,168,0.30);*/
 margin-bottom: 100px">
<%@include file="header.jsp" %>
<div class="container">
    <div class="row" style="margin: 15px;">

        <s:if test="#session.user == null">
            <%--<div class="alert alert-danger alert-dismissible" role="alert">--%>
                <%--<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span--%>
                        <%--aria-hidden="true">&times;</span></button>--%>
                <%--<span><strong>本站使用且必须使用https加密链接保证访问过程信息安全</strong></span>--%>
            <%--</div>--%>
            <div class="alert alert-info alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <strong>提示：</strong><span>请在右上角注册登录，解锁更多功能😂🌚</span>
            </div>
            <a href="${pageContext.request.contextPath}/jwc/student/sport">
                <div class="alert alert-info alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <span>点我免注册查询体测成绩😂🌚</span>
                </div>
            </a>
        </s:if>


        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="publish" id="messageform" class="form-group" method="post"
                          enctype="multipart/form-data"
                          onsubmit="return submitting();">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">发布</h4>
                        </div>
                        <div class="modal-body">
                            <s:if test="#session.user == null">
                                请登录
                            </s:if>
                            <s:elseif test="#session.user.level.toString() == 'NOTVALIDATE'">
                                请验证账户
                            </s:elseif>
                            <s:else>
                        <textarea id="inputtext" placeholder="<s:text name="homepageTextarea"/>" name="message.content"
                                  class="form-control"
                                  style="max-height: 300px;"></textarea>
                                <input id="inputimage" name="image" accept="image/jpeg,image/png,image/gif" type="file"
                                       style="border: none;" onchange="getSize(this.id);"/>
                                <p>暂时不支持超过2MB的图片<span id="filesize"></span></p>
                                <img id="image">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="status" value="anonymous" checked><s:text
                                            name="anonymous"/>
                                    </label>
                                </div>
                                <script>
                                    function getSize(id) {
                                        var size = getFileSize(id);
                                        document.getElementById('filesize').innerHTML = '，当前图片大小为 ' + Math.round(size / 1024) + ' KB';
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
                                </script>
                                <div id="publishresult"></div>
                                <div class="row" id="progressbar" style="display: none;">
                                    <div class="progress" style="margin: 10%;">
                                        <div id="bar" class="progress-bar" role="progressbar" aria-valuenow="60"
                                             aria-valuemin="0"
                                             aria-valuemax="100" style="width: 0%;">
                                            0%
                                        </div>
                                    </div>
                                </div>
                                <script>
                                    function submitting() {
                                        if (getFileSize('inputimage') > 2 * 1024 * 1024) {
                                            alert('图片大小超过限制');
                                            return false;
                                        }
                                        if (getFileSize('inputimage') == 0 && document.getElementById('inputtext').value.trim().length < 1) {
                                            alert('不能发布空信息');
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
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button id="btn_submit" type="submit" class="btn btn-primary" <s:if
                                    test="#session.user == null || #session.user.level.toString() == 'NOTVALIDATE'"> disabled</s:if>>
                                发布
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <s:if test="#session.user.level.toString() == 'NOTVALIDATE'">
            <div align="center">
                <a href="javascript:void(0);" onclick="send();">
                    <div class="alert alert-info" role="alert">
                        <s:text name="validateDescription">
                            <s:param value="#session.user.nickname"/>
                            <s:param value="#session.user.account"/>
                        </s:text>
                        <span id="sendEmail" style="color: red;"><s:text name="clickmeSendValidate"/></span>
                        <span id="result"></span>
                    </div>
                </a>
                <script>
                    function send() {
                        $('#sendEmail').css('display', 'none');
                        $('#result').text('<s:text name="validateEmailSending"/>');
                        $.get('send-validate', function (data) {
                            $('#result').text('<s:text name="validateEmailSent"/>');
                            $('#result').css('color', '#0F0');
                        })
                    }
                </script>
            </div>
        </s:if>
    </div>

    <%--<div class="row">--%>
    <%--<img class="img-responsive center-block" src="<s:property value="#application.qrCode"/>">--%>
    <%--</div>--%>

    <div id="message-list">
        <div id="msg">
            <div class="row" v-for="message in messages"
                 style="padding: 15px 15px 5px; background-color: rgba(255,255,255,0.80);">
                <hr>
                <div style=";">
                    <a :href="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + message.user.icon">
                        <img class="" v-if="message.user.icon != null" style="width: 15%; max-width: 100px;"
                             :src="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + message.user.icon+ '<s:property value="#application.ossHead"/>'"/>
                    </a>
                    <span v-if="message.status == 'ANONYMOUS'">
                        <img style="width: 15%; max-width: 100px"
                             src="<s:property value="#application.pathPrefix"/>/anonymous.jpg<s:property value="#application.ossHead"/>">
                        <span style="color: #f00;">匿名</span>
                    </span>
                    <span>
                        {{message.user.nickname}}&nbsp;&nbsp;{{gender(message.user.gender)}}
                    </span>
                    <br>
                    <span>{{humanTime(message.time)}}</span>
                </div>
                <a :href="'detail?id=' + message.id" style="text-decoration: none; color: #000;">
                    <div class="detail" style="padding-left: 8%;padding-right: 8%">
                        <span>{{message.content}}</span>
                        <br>
                        <br>
                        <img class="img-responsive center-block" v-if="message.imagePath != null" style="max-width: 250px; max-height: 250px;"
                             :src="'<s:property value="#application.pathPrefix"/>' + '${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}' + message.imagePath + '<s:property value="#application.ossThumbnail"/>'"/>
                    </div>
                </a>
                <div style="float: right; padding-right: 5%;">
                    <a :href="'detail?id=' + message.id" style="text-decoration: none; color: #000;">
                        <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                        <span>{{message.comments != null ? message.comments.length : 0}} &nbsp;</span>
                    </a>
                    <%--<span class="glyphicon glyphicon-heart-empty favor" aria-hidden="true" :id="message.id"></span>--%>
                    <%--<span>{{message.favors != null ? message.favors.length : 0}}</span>--%>
                    <a v-if="message.status != 'ANONYMOUS' && message.user.id == '<s:property value="#session.user.id"/>' || '${sessionScope.user.level}' == 'ADMINISTRATOR'"
                       :href="'${pageContext.request.contextPath}/delete-message?id=' + message.id"
                       onclick="return confirm('确认删除吗');"><s:text name="delete"/></a>
                </div>
            </div>
        </div>
        <a href="javascript:load();"><p align="center">😂尝试手动加载😂</p></a>
        <p align="center">😂可能到底了😂</p>
    </div>

    <%--悬浮--%>
    <div style="overflow: hidden;position: fixed;right: 10px;bottom: 70px;z-index: 10;">
        <div style="overflow: hidden;">
            <div style="">
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
        if (loading) return;
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
        if ($(document).scrollTop() + 100 >= $(document).height() - window.innerHeight) {
//            alert($(document).scrollTop() + ', ' + $(document).height() + ', ' + $(window).height() + ', ' + window.innerHeight);
            load();
        }
    })
</script>
<div>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"
            style="position: fixed; bottom: 0; width: 100%;">
        <span>发布</span>
    </button>
</div>
</body>
</html>
