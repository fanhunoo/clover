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
    <link rel="stylesheet" href="${(request.contextPath)!}/eleTree/eleTree.css" media="all">
</head>
<body>
<blockquote class="layui-elem-quote">
    <div class="layui-inline">
        <a class="layui-btn" id="add-role">新增角色</a>
    </div>
</blockquote>
<table id="role-list" lay-filter="role-table"></table>


</body>
<div id="role-info" style="display:none;" ><#include "./dialog_role.ftl"></div>
<div id="permission-info" style="display:none;" ><#include "./dialog_permission.ftl"></div>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<#--<script type="text/javascript" src="${(request.contextPath)!}/plugins/zTree/js/jquery-1.4.4.min.js"></script>-->
<#--<script type="text/javascript" src="${(request.contextPath)!}/plugins/zTree/js/jquery.ztree.all.js"></script>-->
<script type="text/javascript" src="${(request.contextPath)!}/layui-xtree/layui-xtree.js"></script>
<script>
    layui.config({
        base: "${(request.contextPath)!}/layui/lay/mymodules/"
    }).use(['jquery','table','layer','form','eleTree'], function(){
        var table = layui.table;
        var form = layui.form;
        var $ = layui.$;
        //第一个实例
        table.render({
            elem: '#role-list'
            ,url: '${(request.contextPath)!}/system/role/list' //数据接口
            ,page: {layout: ['count', 'prev', 'page', 'next', 'skip', 'limits'],limits:[1, 2, 3, 4, 5]} //开启分页
            ,cols: [[ //表头
                {fixed: 'left',width:160,title: '操作', align:'center'
                    ,toolbar: '<div><a class="layui-btn layui-btn-radius layui-btn-xs" lay-event="permission">分配权限</a>' +
                            ' <a class="layui-btn layui-btn-normal layui-btn-radius layui-btn-xs" lay-event="edit">编辑</a>' +
                            ' <a class="layui-btn layui-btn-danger layui-btn-radius layui-btn-xs" lay-event="del">删除</a></div>'
                }
                ,{field: 'code', title: '角色编码', fixed:'left',align:'center'}
                ,{field: 'name', title: '角色名称',align:'center'}
                ,{field: 'rank', title: '级别',align:'center'}
                ,{field: 'status', title: '状态',align:'center', templet: function(d){return d.status === 1?'启用':'禁用';}}
            ]]
        });

        table.on('tool(role-table)',function (obj) {
            if(obj.event === 'del'){//删除行
                layer.confirm("你确定删除数据吗？此操作不能撤销！", {icon: 3, title:'提示'},
                        function(index){//确定回调
                            $.ajax({
                                type: 'DELETE',
                                url: '${(request.contextPath)!}/system/role/'+ obj.data.id,
                                success: function(result){
                                    if(result.statusCode==="200"){
                                        layer.msg(result.message);
                                        obj.del();
                                    }else{
                                        layer.alert(result.message);
                                    }
                                },
                                error: function(){
                                    layer.alert('删除角色信息异常!');
                                }
                            });
                            layer.close(index);
                        },function (index) {//取消回调
                            layer.close(index);
                        }
                );
            }else if(obj.event==="edit"){//编辑
                beforeEdit(obj);
                layer.open({
                    type: 1,
                    title:"编辑角色",
                    skin: 'layui-layer-rim', //加上边框
                    area: ['500px', '580px'], //宽高
                    content: $("#role-info")
                });
            }else if(obj.event==="permission"){//分配权限
                beforePermission(obj);
                layer.open({
                    type: 1,
                    title:"分配权限",
                    skin: 'layui-layer-rim', //加上边框
                    area: ['600px', '680px'], //宽高
                    content: $("#permission-info")
                });
            }
        });

        //添加资源
        $("body").on("click","#add-role",function(){
            beforeAdd();
            layer.open({
                type: 1,
                title:"添加角色",
                skin: 'layui-layer-rim', //加上边框
                area: ['500px', '580px'], //宽高
                content: $("#role-info")
            });
        });

        function beforeEdit(obj) {
            $("#role-code").val(obj.data.code);
            $("#role-name").val(obj.data.name);
            $("#role-rank").val(obj.data.rank);
            if(obj.data.status===1){
                $("#role-status").prop("checked",true);
            }else{
                $("#role-status").prop("checked",false);
            }
            $("#role-submitType").val("PUT");//修改
            $("#role-id").val(obj.data.id);//修改
            form.render();
        }

        function beforeAdd() {
            $("#role-code").val("");
            $("#role-name").val("");
            $("#role-rank").val("");
            $("#role-status").prop("checked",true);
            $("#role-submitType").val("POST");//新增
            $("#role-id").val("");
            form.render();
        }

        function beforePermission(obj) {

            var eleTree = layui.eleTree;
            eleTree.render({
                elem: '.ele1',
                url:'${(request.contextPath)!}/system/role/permission/'+obj.data.id,
                method:'GET',
                request: {
                    name: "label",
                    key: "id",
                    children: "children",
                    checked: "checked",
                    disabled: "disabled",
                    isLeaf: "isLeaf"
               }
          //todo:      https://fly.layui.com/extend/eleTree/#doc
        });

            $("#role-code").val("");
            $("#role-name").val("");
            $("#role-rank").val("");
            $("#role-status").prop("checked",true);
            $("#role-submitType").val("POST");//新增
            $("#role-id").val("");
            form.render();
        }

        //监听提交
        form.on('submit(formAddRole)', function(data){
            $.ajax({
                type: data.field.submitType,
                url: '${(request.contextPath)!}/system/role/',
                data: JSON.stringify(data.field) ,
                dataType:'json',
                contentType : 'application/json;charset=UTF-8',
                success: function(result) {
                    if(result.statusCode==="200"){
                        layer.msg(result.message);
                        setTimeout("location.reload()", 900);//刷新页面
                    }else{
                        layer.alert(result.message);
                    }
                },
                error: function(){
                    layer.alert('保存角色信息异常!');
                }
            });
            return false;//这里是拦截layui自带的提交
        });



//        var data=[
//            {
//                id: 1,
//                label: "安徽省",
//                children: [
//                    {
//                        id: 2,
//                        label: "马鞍山市",
//                        disabled: true,
//                        children: [
//                            {
//                                id: 3,
//                                label: "和县",
//                            },
//                            {
//                                id: 4,
//                                label: "花山区",
//                            }
//                        ]
//                    }
//                ]
//            },
//            {
//                id: 5,
//                label: "河南省",
//                children: [
//                    {
//                        id: 6,
//                        label: "郑州市",
//                        checked: true
//                    }
//                ]
//            }
//        ]
//        var eleTree = layui.eleTree;
//        eleTree.render({
//            elem: '.ele1',
//            data: data,
//            showCheckbox: true,
//            defaultExpandAll:true
//        });


    });

</script>
</html>