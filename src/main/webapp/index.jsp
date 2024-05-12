<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            padding: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 20px;
            width: 300px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="submit"],
        .form-group img {
            display: inline-block;
            vertical-align: middle;
        }
        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="submit"] {
            width: calc(100%);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-group img {
            width: 70px;
            height: 30px;
            margin-right: 10px;
            margin-top: 2px;
        }
        .form-group input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            cursor: pointer;
            width: auto; /* Set width to auto for the submit button */
        }
        .form-group input[type="submit"]:hover {
            background-color: #4cae4c;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="login-container">
    <form action="login" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="captcha">验证码：</label>
            <input type="text" id="captcha" name="captcha" required>
            <img src="code" alt="验证码" onclick="refreshCaptcha()">
        </div>
        <div class="form-group">
            <input type="submit" value="登录">
        </div>
    </form>
    <% // 检查是否有错误信息
         String error =(String) request.getAttribute("error");
        if (error != null) { %>
    <p class="error"><%= error %></p>
    <% } %>
</div>

<script>
    function refreshCaptcha() {
        var captchaImg = document.querySelector('.form-group img');
        captchaImg.src = 'code?' + Math.random();
    }
    // 检查URL中是否有错误参数
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    if (error) {
        document.querySelector('.error').innerText = error;
        document.querySelector('.error').style.display = 'block';
    }
</script>
</body>
</html>
