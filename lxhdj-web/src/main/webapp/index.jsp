<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Demo</title>
</head>
<body>
<p><a href="article/list">文章列表</a></p>
<p><a href="login.jsp">管理界面登录</a></p>
<p><a href="login">管理界面</a></p>
<p><a href="loginCookie.jsp">cookie 登录</a></p>
<p><a href="cookie">cookie 界面</a></p>
<form method="post" action="hello">
    <h2>Name:</h2>
    <input type="text" id="say-hello-text-input" name="name"/>
    <input type="submit" id="say-hello-button" value="Say Hello"/>
</form>
</body>
</html>