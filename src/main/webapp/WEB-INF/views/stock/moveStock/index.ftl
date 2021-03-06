<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>转移管理</title>
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
        转移批次号：${moveBatchId!}
    </div>
    <input type="hidden" id="moveBatchId" name="moveBatchId" value="${moveBatchId!}"/>
    <div class="layui-inline">
        <input type="text" id="moveCodeInput" placeholder="请输入商品编码" autocomplete="off" class="layui-input"/>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-primary" id="moveFind" >查询</a>
    </div>
    <div class="layui-inline">
        <label>选择店铺：</label>
        <select id="move-orgId" style="width: 80px;height: 36px;">
        <#if orgs??>
            <#list orgs as org>
                <option value="${org.code!}">${org.value!}</option>
            </#list>
        </#if>
        </select>
        <a class="layui-btn layui-btn-normal" id="move">确认转移</a>
    </div>
</blockquote>
<div class="layui-table-box">
    <div class="layui-table-header">
        <table id="table-move" cellspacing="0" cellpadding="0" border="0" class="layui-table">
            <thead>
            <tr>
                <th style="width: 3%">
                </th>
                <th style="width: 11%">
                    <div class="" align="center"><span>编码</span></div>
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
                    <div class="" align="center"><span>尺码</span></div>
                </th>
                <th style="width: 4%">
                    <div class="" align="center"><span>男女</span></div>
                </th>
                <th style="width: 5%">
                    <div class="" align="center"><span>价格</span></div>
                </th>
                <th style="width: 6%">
                    <div class="" align="center"><span>预览图</span></div>
                </th>
                <th style="width: 8%">
                    <div class="" align="center"><span>备注</span></div>
                </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>


</div>
</body>
<script type="text/javascript" src="${(request.contextPath)!}/layui/layui.js"></script>
<script type="text/javascript" src="${(request.contextPath)!}/js/common/util.js"></script>
<script>
    layui.use(['form','layer'],function(){
        var layer = layui.layer ,
                $ = layui.$;
        //转移
        $("body").on("click","#move",function(){
            var orgId = $("#move-orgId").val();
            var orgName = $("#move-orgId").find("option:selected").text();
            layer.confirm('此操作不可撤销，确定要转移到【'+orgName+'】吗？',function(index){
                var codeArr = [];
                $("#table-move").find("tr").each(function(){
                    var tdArr = $(this).children();
                    var codeText = tdArr.eq(1).text();
                    codeArr.push(codeText);
                });
                if(codeArr.length<1){
                    layer.close(index);
                    return;
                }
                var jsonStr = JSON.stringify(codeArr);
                $.ajax({
                    type: "POST",
                    url: '${(request.contextPath)!}/stock/move/',
                    data: {"data":jsonStr,"orgId":orgId,"orgName":orgName,"moveBatchId":"${moveBatchId!}"} ,
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
                        layer.alert('转移异常!');
                    }
                });
                layer.close(index);
            }, function(){});
        });

        //删除本行
        $("body").on("click",".moveDeleteTrBtn",function(){
            var $tr = $(this).parents("tr");
            layer.confirm('确定要删除吗？',function(index){
                $tr.remove();
                layer.close(index);
            }, function(){});
        });

        //查询
        $("body").on("click","#moveFind",function(){
            var code = $("#moveCodeInput").val();
            getGoods(code);
        });
        //input框回车查询
        $("body").on("keydown","#moveCodeInput",function(event){
            if(event.keyCode===13){
                var code = $("#moveCodeInput").val();
                getGoods(code);
            }
        });

        function getGoods(code) {
            var flag = false;
            $("#table-move").find("tr").each(function(){
                var tdArr = $(this).children();
                var codeText = tdArr.eq(1).text();
                if(codeText==code){
                    flag = true;
                    return false;
                }
            });
            if(flag){
                layer.alert("当前商品已存在！请勿重复添加！");
                return;
            }
            $.ajax({
                type: 'GET',
                url: '${(request.contextPath)!}/stock/detail/'+ code,
                success: function(result){
                    if(result.statusCode==="200"){
                        var data = result.data;
                        var seasonStr = "";
                        if(data.season === "C"){
                            seasonStr = "春季款"
                        }else if(data.season === "X"){
                            seasonStr = "夏季款"
                        }else if(data.season === "Q"){
                            seasonStr = "秋季款"
                        }else if(data.season === "D"){
                            seasonStr = "冬季款"
                        }
                        var sexStr = "";
                        if(data.sex === "0"){
                            sexStr = "女款"
                        }else if(data.sex === "1"){
                            sexStr = "男款"
                        }else if(data.sex === "9"){
                            sexStr = "中性款"
                        }
                        if(data.remake===null){
                            data.remake = "";
                        }
                        var html = "<tr>" +
                                "      <td>" +
                                "          <div  align=\"center\">" +
                                "               <button class=\"moveDeleteTrBtn layui-btn layui-btn-xs layui-btn-radius layui-btn-danger\" > &nbsp;删除&nbsp;</button>\n" +
                                "          </div>\n" +
                                "       </td>\n" +
                                "       <td style=\"text-align:center;\">"+data.code+"</td>" +
                                "       <td style=\"text-align:center;\">"+data.name+"</td>" +
                                "       <td style=\"text-align:center;\">"+seasonStr+"</td>" +
                                "       <td style=\"text-align:center;\">"+data.resourceName+"</td>" +
                                "       <td style=\"text-align:center;\">"+data.sizeType+"</td>" +
                                "       <td style=\"text-align:center;\">"+sexStr+"</td>" +
                                "       <td style=\"text-align:center;\">"+data.salePrice+"</td>" +
                                "       <td style=\"text-align:center;\"></td>" +
                                "       <td style=\"text-align:center;\">"+data.remake+"</td>" +
                                "   </tr>";
                        $("#table-move").append(html);
                        $("#moveCodeInput").focus();
                    }else{
                        layer.alert(result.message);
                    }
                },
                error: function(){
                    layer.alert('查询商品信息异常!');
                }
            });
        }
    })

</script>
</html>