<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>入库管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${(request.contextPath)!}/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${(request.contextPath)!}/css/public.css" media="all"/>
</head>
<body>


<blockquote class="layui-elem-quote">
    <div class="layui-inline">
        入库批次号：${stockBatchId!}
    </div>
    <input type="hidden" id="rk-stockBatchId" name="stockBatchId" value="${stockBatchId!}"/>
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
                <th style="width: 11%">
                    <div class="" align="center"><span>名称</span></div>
                </th>
                <th style="width: 4%">
                    <div class="" align="center"><span>季节</span></div>
                </th>
                <th style="width: 5%">
                    <div class="" align="center"><span>厂商</span></div>
                </th>
                <th style="width: 4%">
                    <div class="" align="center"><span>件数</span></div>
                </th>
                <th style="width: 11%">
                    <div class="" align="center"><span>编码</span></div>
                </th>
                <th style="width: 4%">
                    <div class="" align="center"><span>尺码</span></div>
                </th>
                <th style="width: 4%">
                    <div class="" align="center"><span>男女</span></div>
                </th>
                <th style="width: 5%">
                    <div class="" align="center"><span>进价</span></div>
                </th>
                <th style="width: 5%">
                    <div class="" align="center"><span>定价</span></div>
                </th>
                <th style="width: 6%">
                    <div class="" align="center"><span>预览图</span></div>
                </th>
                <th style="width: 5%">
                    <div class="" align="center"><span>入库地点</span></div>
                </th>
                <th style="width: 8%">
                    <div class="" align="center"><span>操作员</span></div>
                </th>
                <th style="width: 8%">
                    <div class="" align="center"><span>备注</span></div>
                </th>
                <th style="width: 8%">
                    <div class="" align="center"><span>操作</span></div>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr stockType="${stockType!}">
                <td>
                    <div  align="center">
                        <button class="deleteTrBtn layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" > &nbsp;删除&nbsp;</button>
                    </div>
                </td>
                <td class="td-name"><input  class="layui-center layui-table-edit layui-input" style="font-size: 18px;"/></td>
                <td class="td-season">
                    <select class="data-season layui-center layui-table-edit" style="font-size: 16px;">
                        <option value="C" <#if season?? && season == "C">selected</#if>>春</option>
                        <option value="X" <#if season?? && season == "X">selected</#if>>夏</option>
                        <option value="Q" <#if season?? && season == "Q">selected</#if>>秋</option>
                        <option value="D" <#if season?? && season == "D">selected</#if>>冬</option>
                    </select>
                </td>
                <td class="td-resource">
                    <select class="data-resource layui-center layui-table-edit" style="font-size: 16px;">
                        <option value="01">01厂</option>
                        <option value="02">02厂</option>
                        <option value="03">03厂</option>
                        <option value="00">重新上架</option>
                    </select>
                </td>
                <td class="td-jianshu"><input class="data-jianshu layui-center layui-table-edit layui-input"  style="font-size: 20px;"/></td>
                <td class="td-code"><span  class="layui-center layui-table-edit layui-input" style="font-size: 18px;padding-top: 12px;"></span></td>
                <td class="td-size">
                    <select class="layui-center layui-table-edit" style="font-size: 16px;">
                        <option value="L">L</option>
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="XL">XL</option>
                        <option value="XXL">XXL</option>
                    </select>
                </td>
                <td class="td-sex">
                    <select class="layui-center layui-table-edit" style="font-size: 16px;">
                        <option value="0">女款</option>
                        <option value="1">男款</option>
                        <option value="9">中性款</option>
                    </select>
                </td>
                <td class="td-purchasePrice"><input  class="layui-center layui-table-edit layui-input" style="font-size: 16px;"/></td>
                <td class="td-salePrice"><input  class="layui-center layui-table-edit layui-input" style="font-size: 16px;"/></td>
                <td><input disabled class="layui-center layui-table-edit layui-input" style="font-size: 16px;"/></td>
                <td class="td-storage">
                    <select class="layui-center layui-table-edit" style="font-size: 16px;">
                        <option value="01">仓库01</option>
                        <option value="02">仓库02</option>
                        <option value="03">仓库03</option>
                    </select>
                </td>
                <td class="td-operator"><span style="font-size: 18px;">${realname!}</span></td>
                <td class="td-remark"><input  class="layui-center layui-table-edit layui-input" style="font-size: 16px;"/></td>
                <td>
                    <button class="yyrukuBtn layui-btn layui-btn-sm" style="width: 50%;margin-left: 25%">入库</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>


</div>

</body>
<div id="choose-onsale-addr" style="display:none;" ><#include "./dialog.ftl"></div>

<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script type="text/javascript" src="${(request.contextPath)!}/js/common/util.js"></script>
<script>
    layui.use(['form','layer','layedit','laydate','upload'],function(){
        var form = layui.form,
                layer = layui.layer ,
                laypage = layui.laypage,
                upload = layui.upload,
                layedit = layui.layedit,
                laydate = layui.laydate,
                $ = layui.$;
        var util = new Util();
        //绑定函数--入库按钮
        $("body").on("click",".yyrukuBtn",function(){

//            layer.open({
//                type: 2,
//                title: false,
//                closeBtn: 0, //不显示关闭按钮
//                shade: [0],
//                area: ['340px', '215px'],
//                offset: 'rb', //右下角弹出
//                time: 2000, //2秒后自动关闭
//                anim: 2,
//                content: ['test/guodu.html', 'no'], //iframe的url，no代表不显示滚动条
//                end: function(){ //此处用于演示
//                    layer.open({
//                        type: 2,
//                        title: '很多时候，我们想最大化看，比如像这个页面。',
//                        shadeClose: true,
//                        shade: false,
//                        maxmin: true, //开启最大化最小化按钮
//                        area: ['893px', '600px'],
//                        content: '//fly.layui.com/'
//                    });
//                }
//            });
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['800px', '500px'], //宽高
                content: $("#choose-onsale-addr")
            });

        });


    })

</script>
</html>