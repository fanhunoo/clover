<blockquote class="layui-elem-quote">
    <div class="layui-inline">
        上架批次号：${onSaleBatchId!}
    </div>
    <input type="hidden" id="onSaleBatchId" name="onSaleBatchId" value="${onSaleBatchId!}"/>
</blockquote>
<div class="layui-table-box">
    <div class="layui-table-header">
        <table id="table-ruku" cellspacing="0" cellpadding="0" border="0" class="layui-table">
            <thead>
            <tr>
                <th style="width: 3%">
                    <div  align="center">
                        <button class="addTrBtn layui-btn layui-btn-xs layui-btn-radius  layui-btn-normal" > &nbsp;添加&nbsp;</button>
                    </div>
                </th>
                <th style="width: 4%">
                    <div class="" align="center"><span>编号起始</span></div>
                </th>
                <th style="width: 4%">
                    <div class="" align="center"><span>编号截至</span></div>
                </th>
                <th style="width: 5%">
                    <div class="" align="center"><span>上架地点</span></div>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <div  align="center">
                        <button class="deleteTrBtn layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" > &nbsp;删除&nbsp;</button>
                    </div>
                </td>
                <td class="cd-start"><input  class="layui-center layui-table-edit layui-input" style="font-size: 18px;"/></td>
                <td class="cd-end"><span  class="layui-center layui-table-edit layui-input" style="font-size: 18px;padding-top: 12px;"></span></td>
                <td class="target-address">
                    <select class="layui-center layui-table-edit" style="font-size: 16px;">
                    <#if onsaleOrgs??>
                        <#list onsaleOrgs as onsaleOrg>
                            <option value="${onsaleOrg.code!}">${onsaleOrg.value!}</option>
                        </#list>
                    </#if>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </div>


</div>
