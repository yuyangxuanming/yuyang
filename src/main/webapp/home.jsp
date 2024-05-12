<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h2>欢迎，<%= session.getAttribute("username") %>！</h2>
<p>这是您的个人主页。</p>
</body>
</html>
