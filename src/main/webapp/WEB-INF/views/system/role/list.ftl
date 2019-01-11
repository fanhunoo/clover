<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色管理</title>
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
<table id="role-list"></table>
</body>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#role-list'
            ,url: '${(request.contextPath)!}/system/role/list' //数据接口
            ,page: {layout: ['count', 'prev', 'page', 'next', 'skip', 'limits'],limits:[1, 2, 3, 4, 5]} //开启分页
            ,cols: [[ //表头
                {field: 'code', title: '角色编码', fixed:'left',align:'center'}
                ,{field: 'name', title: '角色名称',align:'center'}
                ,{field: 'status', title: '状态',align:'center', templet: function(d){return d.status === 1?'启用':'禁用';}}
            ]]
        });
    });


</script>
</html>