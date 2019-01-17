<form class="layui-form layui-form-pane" style="padding: 10px 30px" action="${(request.contextPath)!}/system/user/">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名：</label>
        <div class="layui-input-block">
            <input type="text" name="userName" id="user-userName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">真实姓名：</label>
        <div class="layui-input-block">
            <input type="text" name="realName" id="user-realName" required  lay-verify="required" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" id="passwordDiv">
        <label class="layui-form-label">密码：</label>
        <div class="layui-input-block">
            <label class="layui-form-label" style="width: 100%;color: red;">初始密码为：123456</label>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色：</label>
        <div class="layui-input-block">
            <select id="user-roleId" name="roleId">
            <#if roles??>
                <#list roles as role>
                    <option value="${role.id!}">${role.name!}</option>
                </#list>
            </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所属机构：</label>
        <div class="layui-input-block">
            <select id="user-orgId" name="orgId" lay-verify="required" >
                <#if orgs??>
                    <#list orgs as org>
                        <option value="${org.code!}">${org.value!}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">确认密码：</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="password" name="passWord2" required lay-verify="required|password" placeholder="请再次输入密码" autocomplete="off" class="layui-input">-->
        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">手机号：</label>
        <div class="layui-input-block">
            <input type="text" name="phone" id="user-phone"  placeholder="请输入手机号" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">状态：</label>
        <div class="layui-input-block">
            <input name="status" id="user-status" type="checkbox" lay-skin="switch" lay-text="启用|禁用" value="1">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-block">
            <input type="text" name="remark" id="user-remark" required placeholder="请输入备注" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formAddUser">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <input type="hidden" name="submitType" id="user-submitType" >
    <input type="hidden" name="id" id="user-id">
</form>

