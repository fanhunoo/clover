<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
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
<table id="user-list"></table>
</body>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#user-list'
            ,height: 315
            ,url: '${(request.contextPath)!}/users/users/' //数据接口
            ,limit:2
            ,page: {layout: ['count', 'prev', 'page', 'next', 'skip', 'limits'],limits:[1, 2, 3, 4, 5]} //开启分页
            ,cols: [[ //表头
                {field: 'userName', title: '用户名', fixed:'left'}
                ,{field: 'realName', title: '真实姓名'}
                ,{field: 'phone', title: '手机号'}
                ,{field: 'orgId', title: '所属机构'
                    ,templet: function(d){
                        if(d.orgId === 0){
                            return '总店';
                        }else if(d.orgId === 1){
                            return '1号店';
                        }else if(d.orgId === 2){
                            return '2号店';
                        }else{
                            return '-';
                        }
                    }
                }
                ,{field: 'status', title: '状态', templet: function(d){return d.status === 0?'启用':'禁用';}}
                ,{field: 'remarkes', title: '备注'}
            ]]
        });
    });
</script>
</html>