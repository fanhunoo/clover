<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>库存详情</title>
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
        <label>商品编码：</label>
    </div>
    <div class="layui-inline">
        <input type="text" id="stockDetailCodeInput" placeholder="请输入商品编码" autocomplete="off" class="layui-input"/>
    </div>
    <div class="layui-inline">
        <label>商品名称：</label>
    </div>
    <div class="layui-inline">
        <input type="text" id="stockDetailNameInput" placeholder="请输入商品名称" autocomplete="off" class="layui-input"/>
    </div>
    <div class="layui-inline">
        <label>状态：</label>
        <select  id="stockDetailStatusInput" style="font-size:16px;width: 80px;height: 36px;">
            <option value="">全部</option>
            <#if containStoraged?? && containStoraged == 'Y'><option value="100">已入库</option></#if>
            <option value="101">已上架</option>
            <option value="102">已售出</option>
            <option value="103">已下架</option>
            <option value="104">已损耗</option>
        </select>
    </div>
    <div class="layui-inline">
        <label>季节：</label>
        <select  id="stockDetailSeasonInput"  style="font-size:16px;width: 80px;height: 36px;">
            <option value="">全部</option>
            <option value="C">春</option>
            <option value="X">夏</option>
            <option value="Q">秋</option>
            <option value="D">冬</option>
        </select>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal" id="stockDetailFind" >查询</a>
    </div>
</blockquote>
<div class="layui-table-box">
    <div class="layui-table-header">
        <table id="table-stockDetail" cellspacing="0" cellpadding="0" border="0" class="layui-table">
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script type="text/javascript" src="${(request.contextPath)!}/js/common/util.js"></script>
<script>
    layui.config({
        base: '${(request.contextPath)!}/layui/extend/'
    }).extend({
        treeGrid:'treeGrid'
    }).use(['jquery','treeGrid','layer','form'], function(){
        var ptable=null;
        var $ = layui.$;
        var treeGrid = layui.treeGrid;
        //查询
        $("body").on("click","#stockDetailFind",function(){
            getStockDetail();
        });
        //input框回车查询
        $("body").on("keydown",["#stockDetailCodeInput","#stockDetailNameInput"],function(event){
            if(event.keyCode===13){
                getStockDetail();
            }
        });
        function getStockDetail() {
            var code = $("#stockDetailCodeInput").val();
            var name = $("#stockDetailNameInput").val();
            var season = $("#stockDetailSeasonInput").val();
            var status = $("#stockDetailStatusInput").val();
            var urlParam = '?code='+code+'&name='+name+'&season='+season+'&status='+status;
            ptable = treeGrid.render({
                id:'table-stockDetail'
                ,elem: '#table-stockDetail'
                ,url:'${(request.contextPath)!}/stock/detail/list'+urlParam
                ,cellMinWidth: 100
                ,idField:'id'
                ,treeId:'id'//树形id字段名称
                ,treeUpId:'parentId'//树形父id字段名称
                ,treeShowName:'code'//以树形式显示的字段
                ,height:'90%'
                ,isFilter:false
                ,iconOpen:false//是否显示图标【默认显示】
                ,isOpenDefault:true//节点默认是展开还是折叠【默认展开】
                ,loading:true
                ,method:'get'
                ,isPage:true
                ,cols: [[
                    {type:'numbers'}
                    ,{field: 'code', title: '商品编码', align:'center',width:220}
                    ,{field: 'name', title: '名称',align:'center'}
                    ,{field: 'salePrice', title: '价格',align:'center'}
                    ,{field: 'season', title: '季节款式',align:'center'
                        ,templet: function(d){
                            if(d.season === 'C'){
                                return '春季款';
                            }else if(d.season === 'X'){
                                return '夏季款';
                            }else if(d.season === 'Q'){
                                return '秋季款';
                            }else if(d.season === 'D'){
                                return '冬季款';
                            }else{
                                return '';
                            }
                        }
                    }
                    ,{field: 'storageBatchId', title: '入库批次号'}
                    ,{field: 'storageTime', title: '入库时间'}
                    ,{field: 'siteName', title: '存放地点',align:'center'}
                    ,{field: 'remake', title: '备注',align:'center'}
                    ,{field: 'offsaleBatchId', title: '下架批次号',align:'center'}
                    ,{field: 'moveBatchId', title: '转移批次号',align:'center'}
                    ,{field: 'updateTime', title: '更新时间',align:'center'}
                    ,{field: 'updatePer', title: '更新人',align:'center'}
                ]]
            });
        }
    });
</script>
</html>