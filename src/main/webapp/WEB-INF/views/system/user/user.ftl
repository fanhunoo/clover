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
<blockquote class="layui-elem-quote">
    <div class="layui-inline">
        <a class="layui-btn" id="add-user">新增用户</a>
    </div>
</blockquote>
<table id="user-list" lay-filter="user-table"></table>
</body>
<div id="user-info" style="display:none;" ><#include "./dialog.ftl"></div>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script>
    layui.use(['jquery','table','layer','form'], function(){
        var table = layui.table;
        var form = layui.form;
        var $ = layui.$;
        //第一个实例
        table.render({
            elem: '#user-list'
            ,url: '${(request.contextPath)!}/system/user/list' //数据接口
            ,page: {layout: ['count', 'prev', 'page', 'next', 'skip', 'limits'],limits:[1, 2, 3, 4, 5]} //开启分页
            ,cols: [[ //表头
                {fixed: 'left',width:160,title: '操作', align:'center'
                    ,toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-radius layui-btn-xs" lay-event="edit">编辑</a>' +
                              ' <a class="layui-btn layui-btn-danger layui-btn-radius layui-btn-xs" lay-event="del">删除</a></div>'
                }
                ,{field: 'userName', title: '用户名', align:'center'}
                ,{field: 'realName', title: '真实姓名',align:'center'}
                ,{field: 'roleName', title: '角色',align:'center'}
                ,{field: 'roleId', title: '角色ID',hide:true}
                ,{field: 'orgId', title: '所属机构ID',hide:true}
                ,{field: 'orgName', title: '所属机构',align:'center'}
                ,{field: 'phone', title: '手机号',align:'center'}
                ,{field: 'status', title: '状态',align:'center', templet: function(d){return d.status === 1?'启用':'禁用';}}
                ,{field: 'remarkes', title: '备注',align:'center'}
                ,{field: 'createDate', title: '创建时间',align:'center'}
                ,{field: 'createPer', title: '创建人',align:'center'}
                ,{field: 'updateDate', title: '更新时间',align:'center'}
                ,{field: 'updatePer', title: '更新人',align:'center'}
            ]]
        });

        table.on('tool(user-table)',function (obj) {
            if(obj.event === 'del'){//删除行
                layer.confirm("你确定删除数据吗？此操作不能撤销！", {icon: 3, title:'提示'},
                        function(index){//确定回调
                            $.ajax({
                                type: 'DELETE',
                                url: '${(request.contextPath)!}/system/user/'+ obj.data.id,
                                success: function(result){
                                    if(result.statusCode==="200"){
                                        layer.msg(result.message);
                                        obj.del();
                                    }else{
                                        layer.alert(result.message);
                                    }
                                },
                                error: function(){
                                    layer.alert('删除用户信息异常!');
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
                    title:"编辑用户",
                    skin: 'layui-layer-rim', //加上边框
                    area: ['500px', '580px'], //宽高
                    content: $("#user-info")
                });
            }
        });

        //添加资源
        $("body").on("click","#add-user",function(){
            beforeAdd();
            layer.open({
                type: 1,
                title:"添加用户",
                skin: 'layui-layer-rim', //加上边框
                area: ['500px', '580px'], //宽高
                content: $("#user-info")
            });
        });

        function beforeEdit(obj) {
            $("#passwordDiv").addClass("layui-hide");
            $("#user-userName").val(obj.data.userName);
            $("#user-realName").val(obj.data.realName);
            $("#user-phone").val(obj.data.phone);
            $("#user-orgId").val(obj.data.orgId);
            $("#user-roleId").val(obj.data.roleId);
            if(obj.data.status===1){
                $("#user-status").prop("checked",true);
            }else{
                $("#user-status").prop("checked",false);
            }
            $("#user-remark").val(obj.data.remark);
            $("#user-submitType").val("PUT");//修改
            $("#user-id").val(obj.data.id);//修改
            form.render();
        }

        function beforeAdd() {
            $("#passwordDiv").removeClass("layui-hide");
            $("#user-userName").val("");
            $("#user-realName").val("");
            $("#user-phone").val("");
            $("#user-orgId").val("");
            $("#user-roleId").val("");
            $("#user-status").prop("checked",true);
            $("#user-remark").val("");
            $("#user-submitType").val("POST");//新增
            $("#user-id").val("");
            form.render();
        }

        //监听提交
        form.on('submit(formAddUser)', function(data){
            $.ajax({
                type: data.field.submitType,
                url: '${(request.contextPath)!}/system/user/',
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
                    layer.alert('保存用户信息异常!');
                }
            });
            return false;//这里是拦截layui自带的提交
        });
    });
</script>
</html>