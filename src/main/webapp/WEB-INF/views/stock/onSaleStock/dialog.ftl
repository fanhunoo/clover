<form class="layui-form layui-form-pane" style="padding: 10px 30px" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">选择店铺：</label>
        <div class="layui-input-block">
            <select id="onSale-orgId">
            <#if orgs??>
                <#list orgs as org>
                    <option value="${org.code!}">${org.value!}</option>
                </#list>
            </#if>
            </select>
        </div>
    </div>
</form>


