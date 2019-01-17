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
<script>
    layui.use('layer', function(){
        var layer = layui.layer;

        layer.open({
            type: 2,
            content: 'https://www.baidu.com/'
        });
    });

</script>