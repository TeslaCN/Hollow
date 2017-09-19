# Hollow

这是一个基于SSH框架的简单应用，大概算是本人准备入门SSH前的一些练习。<br>
<br>原本只是想做个简单（很辣鸡）的树洞（留言板之类的），结果写着写着就复杂了想着多写点功能上去<br>
<br>🌚求指点🌚<br><br>
这东西算啥我也不知道怎么说，精简版微博和SCAU教务系统查成绩活着干别的的东东的结合体？<br>
应用界面朴素无奇平平淡淡（以前没写过前端🌚前端知识都是临阵磨枪磨出来的🌚）<br>
<br>
<hr>
应用已经部署<br>到
<a href="https://scau.ltd">https://scau.ltd</a>
<br>
(域名没备案没法用80访问，被阿里拦了，那我就用https😂😂😂)
<br><br>
Spring的配置文件有两个example文件，因为其中配置内容涉及私人账号，于是只提供example<br>
<br>

<hr>
关于运行所需配置：<br>
daoContext.xml 为数据库相关配置<br>
storageContext.xml 提供了example文件，根据文件内注释可以配置应用文件存储方式<br>
emailContext.xml 提供了example文件，此处配置本应用发送邮件的邮箱。<br>
<hr>

在腾讯WeTest上试了一下20~200人并发访问首页<br>
结果好像不太乐观😂😂<br>
不过也正常毕竟服务器乞丐版配置能有几十个并发访问貌似已经不错了😂😂<br>
<hr>

<br>用上了jsp页面以及各种Struts标签以后，貌似没有Struts基础的前端写手要参与开发会遇到一定困难<br>
<sub>全栈好像都是被逼出来的😂</sub><br>

