<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>资源管理</title>
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
        <a class="layui-btn" id="add-menu">新增资源</a>
    <#--<a class="layui-btn layui-btn-primary"  onclick="print();">打印缓存对象</a>-->
    </div>
</blockquote>

<div>
    <table class="layui-hidden" id="resource-list" lay-filter="resource-list"></table>
</div>
</body>
<div id="resource-menu" style="display:none;" ><#include "./dialog.ftl"></div>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script>
    var ptable=null,treeGrid=null,tableId='resource-list',layer=null;
    layui.config({
        base: '${(request.contextPath)!}/layui/extend/'
    }).extend({
        treeGrid:'treeGrid'
    }).use(['jquery','treeGrid','layer','form'], function(){
        var $ = layui.$;
        var form = layui.form;
        treeGrid = layui.treeGrid;//很重要
        layer = layui.layer;
        ptable = treeGrid.render({
            id:tableId
            ,elem: '#'+tableId
            ,url:'${(request.contextPath)!}/system/resource/list'
            ,cellMinWidth: 100
            ,idField:'id'
            ,treeId:'id'//树形id字段名称
            ,treeUpId:'parentId'//树形父id字段名称
            ,treeShowName:'name'//以树形式显示的字段
           // ,heightRemove:[".dHead",10]//不计算的高度,表格设定的是固定高度，此项不生效
            ,height:'100%'
            ,isFilter:false
            ,iconOpen:true//是否显示图标【默认显示】
            ,isOpenDefault:true//节点默认是展开还是折叠【默认展开】
            ,loading:true
            ,method:'get'
            ,isPage:false
            ,cols: [[
                {type:'numbers'},
//                {type:'radio'},
                {width:150,title: '操作', align:'center'
                    ,templet: function(d){
                        var editBtn='<a class="layui-btn layui-btn-normal layui-btn-radius layui-btn-xs" lay-event="edit">编辑</a>';
                        var delBtn='<a class="layui-btn layui-btn-danger layui-btn-radius layui-btn-xs" lay-event="del">删除</a>';
                        return  editBtn+delBtn;
                    }
                }
                ,{field:'name',width:300, title: '资源名称'}
                ,{field:'url',width:300, title: 'url'}
                ,{field:'type', title: 'type',
                    templet: function(d){
                        if(d.type === 1){
                            return '一级菜单';
                        }else if(d.type === 2){
                            return '二级菜单';
                        }else if(d.type === 3){
                            return '三级菜单';
                        }else if(d.type === 9){
                            return '按钮、url';
                        }
                    }
                }
                ,{field: 'createDate', title: '创建时间',align:'center'}
                ,{field: 'createPer', title: '创建人',align:'center'}
                ,{field: 'updateDate', title: '更新时间',align:'center'}
                ,{field: 'updatePer', title: '更新人',align:'center'}
            ]]
//            ,parseData:function (res) {//数据加载后回调
//                return res;
//            }
//            ,onClickRow:function (index, o) {
//                console.log(index,o,"单击！");
//            }
//            ,onDblClickRow:function (index, o) {
//                console.log(index,o,"双击");
//            }
//            ,onCheck:function (obj,checked,isAll) {//复选事件
//                console.log(obj,checked,isAll);
//            }
//            ,onRadio:function (obj) {//单选事件
//                console.log(obj);
//            }
        });

        treeGrid.on('tool('+tableId+')',function (obj) {
            if(obj.event === 'del'){//删除行
                layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {icon: 3, title:'提示'},
                        function(index){//确定回调
                            $.ajax({
                                type: 'DELETE',
                                url: '${(request.contextPath)!}/system/resource/'+ obj.data.id,
                                success: function(result) {
                                    if(result.statusCode==="200"){
                                        layer.msg(result.message);
                                        obj.del();
                                    }else{
                                        layer.alert(result.message);
                                    }
                                },
                                error: function(){
                                    layer.alert('删除资源信息异常!');
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
                    title:"编辑资源",
                    skin: 'layui-layer-rim', //加上边框
                    area: ['500px', '510px'], //宽高
                    content: $("#resource-menu")
                });
            }
        });

        //添加资源
        $("body").on("click","#add-menu",function(){
            beforeAdd();
            layer.open({
                type: 1,
                title:"添加资源",
                skin: 'layui-layer-rim', //加上边框
                area: ['500px', '510px'], //宽高
                content: $("#resource-menu")
            });
        });

        //监听提交
        form.on('submit(formAddMenu)', function(data){
            $.ajax({
                type: data.field.submitType,
//                type: 'POST',
                url: '${(request.contextPath)!}/system/resource/',
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
                    layer.alert('保存资源信息异常!');
                }
            });
            return false;//这里是拦截layui自带的提交
        });

        form.on('select(typeSelect)', function(data){
            selectChange(data.value);
        });

        function beforeEdit(obj) {
            $("#edit-type").val(obj.data.type);
            selectChange(obj.data.type);
            $("#select-parent").val(obj.data.parentId);
            form.render('select');
            $("#edit-name").val(obj.data.name);
            $("#edit-url").val(obj.data.url);
            $("#edit-sort").val(obj.data.sort);
            $("#edit-remarkes").val(obj.data.remarkes);
            $("#resource-submitType").val("PUT");//修改
            $("#resource-id").val(obj.data.id);//修改
        }

        function beforeAdd() {
            $("#edit-type").val(1);
            $("#select-parent").val("");
            form.render('select');
            $("#edit-name").val("");
            $("#edit-url").val("");
            $("#edit-sort").val("");
            $("#edit-remarkes").val("");
            $("#resource-submitType").val("POST");//新增
            $("#resource-id").val("");
        }

        function selectChange(type) {
            $.ajax({
                type: 'GET',
                async: false,
                url: '${(request.contextPath)!}/system/resource/parents?type='+type,
                success: function(result){
                    if(result.statusCode!=='200'){
                        layer.msg(result.msg);
                        return;
                    }
                    var data = result.data;
                    $("#select-parent").html("");
                    if(data === null || data.size<1){
                        form.render('select');
                        return;
                    }
                    $.each(data, function(key, val){
                        var option1 = $("<option>").val(val.id).text(val.name);
                        $("#select-parent").append(option1);
                        form.render('select');
                    });
                    $("#select-parent").get(0).selectedIndex = 0;
                }
            });
        }

    });
</script>
</html>