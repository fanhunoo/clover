<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>上架管理</title>
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
        <a class="layui-btn" id="onSale">上架</a>
    </div>
    <div class="layui-inline">
        上架批次号：${onSaleBatchId!}
    </div>
    <input type="hidden" id="onSaleBatchId" name="onSaleBatchId" value="${onSaleBatchId!}"/>
</blockquote>

<div>
    <table class="layui-hidden" id="onSale-list" lay-filter="onSale-list"></table>
</div>
</body>
<div id="onSale-info" style="display:none;" ><#include "./dialog.ftl"></div>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script>
    layui.use(['jquery','table','layer'], function(){
        var table = layui.table;
        var $ = layui.$;
        //第一个实例
        table.render({
            elem: '#onSale-list'
            ,url: '${(request.contextPath)!}/stock/onSale/list' //数据接口
            ,page: {layout: ['count', 'prev', 'page', 'next', 'skip', 'limits'],limits:[10, 15, 20, 30, 50]} //开启分页
            ,cols: [[ //表头
                {type:'checkbox'}
                ,{field: 'code', title: '商品编码', align:'center'}
                ,{field: 'name', title: '名称',align:'center'}
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
                ,{field: 'siteCode', title: '存放地点id',align:'center',hide:true}
                ,{field: 'siteName', title: '存放地点',align:'center'}
                ,{field: 'remake', title: '备注',align:'center'}
            ]]
        });

        //上架
        $("body").on("click","#onSale",function(){
            var data = table.checkStatus("onSale-list").data;
            if(data.length===0){
                layer.alert("请选择要上架的商品");
                return;
            }
            layer.open({
                type: 1,
                title:"上架>>选择店铺",
                btn: ['确认', '取消'],
                btnAlign: 'c',
                yes: function(index, layero){
                    var data = table.checkStatus("onSale-list").data;
                    var jsonStr = JSON.stringify(data);
                    //console.log(data);
                    $.ajax({
                        type: "POST",
                        url: '${(request.contextPath)!}/stock/onSale/',
                        data: {"data":jsonStr,"orgId":$("#onSale-orgId").val(),"onSaleBatchId":"${onSaleBatchId!}"} ,
                        dataType:'json',
                        success: function(result) {
                            if(result.statusCode==="200"){
                                layer.msg(result.message);
                                setTimeout("location.reload()", 900);//刷新页面
                            }else{
                                layer.alert(result.message);
                            }
                        },
                        error: function(){
                            layer.alert('上架异常!');
                        }
                    });
                } ,
                btn2: function(index, layero){},
                skin: 'layui-layer-rim', //加上边框
                area: ['300px', '250px'], //宽高
                content: $("#onSale-info")
            });
        });
    });
</script>
</html>