<%--
  Created by IntelliJ IDEA.
  User: 李晓涛
  Date: 2020/2/22
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>聊天室</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        window.setInterval("check();",1000);
        window.setInterval("showContent();",1000);
        window.setInterval("showOnLine();",10000);
        $(function () {
            showOnLine();
            showContent();
            check();
        })
        function showOnLine() {
            $.post({
                url:"${pageContext.request.contextPath}/find",
                dataType:"json",
                success: function (data) {
                    var res = eval(data)
                    var tr ="";
                    for(var i=0;i<res.length;i++) {
                        tr =tr+'<tr><td>'+ res[i].user+'</td></tr>'
                    }
                    $("#tabletest").html(tr)
                    var value = ${userinfo.id}
                    var a ="<option value=''>请选择聊天的对象</option>";
                    for(var i=0;i<res.length;i++) {
                        if(value!=res[i].id){
                            a += "<option value='" + res[i].user+ "'>" + res[i].user + "</option>";
                        }
                    }
                    $("#get").html(a);
                    $("#count").html(res.length);
                }
            });
        }
        function showContent() {
            $.post({
                url:"${pageContext.request.contextPath}/user/getMessage?"+new Date().getTime(),
                success: function (data) {
                    console.log(data)
                    $("#content").html(data)
                }
            });
        }
        function sendmessage() {
            $.post({
                url:"${pageContext.request.contextPath}/user/send?"+new Date().getTime(),
                data:$("#form").serialize(),
                success: function (data) {
                    if(data==1){
                        alert("请选择聊天对象");
                    }else if(data==2){
                        alert("请输入聊天内容");
                    }else  $("#content").html(data);
                }
            });
        }
        function check() {
            $.post({
                url:"${pageContext.request.contextPath}/user/check?"+new Date().getTime(),
                success: function (data) {
                    if(data==1)
                    {
                        alert("用户名已过期");
                        window.location="index.jsp";
                    }
                }
            });
        }
    </script>
</head>
<body>
<center>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="page-header">
                    <h1>
                        <small>聊天室</small>
                    </h1>
                    <h2>
                    <small>您好 ,${userinfo.user}
                    <a href="/logout/${userinfo.id}">退出登录</a>
                        当前在线人数：<label id="count"></label>
                        <c:if test="${userinfo.type==0}">
                            <form:form action="/user/kick/" method="post">
                                违规人员：<input type="text" name="username" required>
                                <input type="submit" value="踢出群聊">
                            </form:form>
                        </c:if>
                    </small>
                    </h2>
                </div>
                <div class="row-fluid">
                    <div class="span8">
                    </div>
                    <div class="span4">
                        <table class="table" >
                            <thead>
                            <tr>
                                <th>
                                    在线人员
                                </th>
                            </tr>
                            </thead>
                            <tbody id="tabletest">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <p id="content">
        </p>
    </div>
    <div>
        <form id="form" method="post" onsubmit="return false" >
            <input name="post" type="hidden" value="${userinfo.user}">向
                <select name="get" id="get">
                </select>发送消息
            <input name="message" type="text" size="55">
            <input type="submit" value="发送" onclick="sendmessage()">
        </form>
    </div>
</center>



</table>
</body>
</html>
