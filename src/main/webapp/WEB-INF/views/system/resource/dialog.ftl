<form class="layui-form layui-form-pane" style="padding: 10px 30px" action="${(request.contextPath)!}/system/resource/">
    <div class="layui-form-item">
        <label class="layui-form-label">菜单类型：</label>
        <div class="layui-input-block">
            <select id="edit-type" name="type" lay-verify="required" lay-filter="typeSelect">
                <option value="1">一级菜单</option>
                <option value="2">二级菜单</option>
                <option value="3">三级菜单</option>
                <option value="9">按钮、url</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">父级菜单：</label>
        <div class="layui-input-block">
            <select name="parentId" id="select-parent" lay-search>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名称：</label>
        <div class="layui-input-block">
            <input type="text" name="name" id="edit-name" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请求地址：</label>
        <div class="layui-input-block">
            <input type="text" name="url" id="edit-url" required  lay-verify="required" placeholder="请输入请求地址" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">序号：</label>
        <div class="layui-input-block">
            <input type="text" name="sort"  id="edit-sort" required  lay-verify="required" placeholder="请输入序号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-block">
            <input type="text" name="remark" id="edit-remark" required placeholder="请输入备注" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formAddMenu">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <input type="hidden" name="submitType" id="resource-submitType" >
    <input type="hidden" name="id" id="resource-id">
</form>

