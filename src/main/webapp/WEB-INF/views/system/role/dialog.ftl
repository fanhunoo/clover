<form class="layui-form layui-form-pane" style="padding: 10px 30px" action="${(request.contextPath)!}/system/role/">
    <div class="layui-form-item">
        <label class="layui-form-label">角色编码：</label>
        <div class="layui-input-block">
            <input type="text" name="code" id="role-code" required  lay-verify="required" placeholder="请输入角色编码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称：</label>
        <div class="layui-input-block">
            <input type="text" name="name" id="role-name" required  lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">级别：</label>
        <div class="layui-input-block">
            <input type="text" name="rank" id="role-rank"  required  lay-verify="required|number" placeholder="请输入级别" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态：</label>
        <div class="layui-input-block">
            <input name="status" id="role-status" type="checkbox" lay-skin="switch" lay-text="启用|禁用" value="1">
        </div>
    </div>
    <div class="layui-form-item" style="">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formAddRole">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <input type="hidden" name="submitType" id="role-submitType" >
    <input type="hidden" name="id" id="role-id">
</form>

