<%@ page import="org.springframework.ui.Model" %><%--
  Created by IntelliJ IDEA.
  User: 李晓涛
  Date: 2020/2/21
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript">

      function register(){

        document.name.action="/register";

        document.name.submit();

      }

      function login(){

        document.name.action="/login";

        document.name.submit();

      }

    </script>
  </head>
  <body>
     <center>
          <h1>
            <small>
              登陆聊天室<br/><br/>
            </small>
          </h1>
        <form action="" name="name" method="post">
     用户名：<input type="text" name="user" required><br/><br/>
     密码：<input type="password" name="pwd" required><br/><br/>
     <input type="button" value="注册" onclick="register()">
     <input type="button" value="登陆" onclick="login()">
     </form>
     <span>${msg}</span>
     </center>

  </body>
</html>
