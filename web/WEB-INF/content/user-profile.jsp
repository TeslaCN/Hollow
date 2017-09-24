<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TESLA_CN
  Date: 2017/8/25
  Time: 下午10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="userProfile"/></title>

</head>
<%@include file="header.jsp" %>
<body>
<div class="container">
    <div id="icon" class="row">
        <s:if test="#session.user.icon != null">
            <img src="<s:property value="#application.pathPrefix"/>${pageContext.request.contextPath.equals("/") ? "/" : pageContext.request.contextPath.concat("/")}${requestScope.user.icon}<s:property value="#application.ossThumbnail"/>"
        </s:if>
    </div>
    <div>
        <form id="uploadIcon" onsubmit="return submitIcon();">
            <div class="form-group">
                <input id="inputimage" name="icon" accept="image/jpeg,image/png,image/gif" type="file"
                       onchange="getSize(this.id);"/>
                <p id="filesize">暂时不支持超过2MB的图片</p>
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
            </div>
            <button id="btnSubmit" type="submit" class="btn btn-default">Submit</button>
            <p id="uploadResult"></p>
            <script>
                function submitIcon() {
                    if (getFileSize('inputimage') > 2 * 1024 * 1024) {
                        alert('图片大小超过限制');
                        return false;
                    }
                    $('#btnSubmit').css('display', 'none');
                    $('#uploadResult').append('<span><s:text name="uploading"/></span>');
                    var formData = new FormData(document.getElementById('uploadIcon'));
                    $.ajax({
                        url: '${pageContext.request.contextPath}/upload-icon',
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
            </script>
        </form>
    </div>
    <div id="info" class="row">
        <div>
        </div>
        <div>
            <table class="table table-hover">
                <tr>
                    <td><s:text name="userNickname"/></td>
                    <td>${requestScope.user.nickname}</td>
                </tr>
                <tr>
                    <td><s:text name="userGender"/></td>
                    <td>${requestScope.user.gender}</td>
                </tr>
                <tr>
                    <td><s:text name="userAccount"/></td>
                    <td>${requestScope.user.account}</td>
                </tr>
                <tr>
                    <td><s:text name="signUpTime"/></td>
                    <td id="signUpTime"></td>
                </tr>
            </table>
            <script>
                document.getElementById('signUpTime').innerHTML = new Date(${requestScope.user.time}).toLocaleString();
            </script>
        </div>
    </div>
</div>
</body>
</html>
