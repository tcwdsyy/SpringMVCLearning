<%--
  Created by IntelliJ IDEA.
  User: shiyuyang
  Date: 2023/12/29
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<form method="post" action="login">--%>
<%--    <label>用户名：
<input type="text" name="username"></label><br>--%>
<%--    <label>密码：
<input type="password" name="password"></label><br>--%>
<%--    <label>自动登录
<input type="checkbox" name="autoLogin" value="autoLogin"><br></label>--%>
<%--    <input type="submit" value="登录">--%>
<%--    --%>
<%--</form>--%>

    <form class="loginForm" action="login"  name="actionForm" id="actionForm"  method="post" >
        <div class="inputbox">
            <label for="username">用户名：</label>
            <input type="text" class="input-text" id="username" name="username" placeholder="请输入用户名" required/>
        </div>
        <div class="inputbox">
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" placeholder="请输入密码" required/>
        </div>
        <div class="subBtn">
            <input type="submit" value="登录"/>
        </div>
    </form>

</body>
</html>
