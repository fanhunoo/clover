<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>登录--紫苜蓿管理系统</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="icon" href="${(request.contextPath)!}/statics/icon/clover.png" type="image/x-icon">
        <link rel="stylesheet" href="${(request.contextPath)!}/layui/css/layui.css" media="all" />
        <link rel="stylesheet" href="${(request.contextPath)!}/css/login/login.css" media="all" />
    </head>
    <body>
        <video class="login-video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
            <source src="${(request.contextPath)!}/statics/video/login.mp4" type="video/mp4">
        </video>
        <div class="login-video-mask" ></div>
        <div class="login">
            <h1>登录</h1>
            <form class="layui-form" action="${(request.contextPath)!}/login" method="post"><!-- action="/demo/login" method="post">-->
                <input type="hidden"  name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" />
                <input type="hidden"  id="logoutMsg" value="${logoutMsg!}" />
                <div class="layui-form-item">
                    <input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
                </div>
                <div class="layui-form-item">
                    <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
                </div>
                <!--<div class="layui-form-item form_code">-->
                    <!--<input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">-->
                    <!--<div class="code"><img src="././images/code.jpg" width="116" height="36"></div>-->
                <!--</div>-->
                <button class="layui-btn login_btn" lay-submit >登录</button><!--lay-filter="login"-->
                <#if errorMsg??>
                    <div style="height: 30px;width: 100%;"><span style="color: red;margin: auto auto">${errorMsg!}</span></div>
                </#if>
            </form>
        </div>
        <script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
        <script type="text/javascript" src="${(request.contextPath)!}/js/login/login.js"></script>
    </body>
</html>