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
                    <button class="rukuBtn layui-btn layui-btn-sm" style="width: 50%;margin-left: 25%">入库</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>


</div>

</body>
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
        $("body").on("click",".rukuBtn",function(){
            //1.checkParam()
            var $nameInput = $(this).parents("tr").find('td[class="td-name"]').children();
            var name = $nameInput.val();
            if(util.isNull(name)){
                layer.tips('请填写名称！', $nameInput,{tips: [2, '#FF4444'],time: 4000});
                $nameInput.focus();
                return;
            }
            var season = $(this).parents("tr").find('td[class="td-season"]').children().val();
            var resource = $(this).parents("tr").find('td[class="td-resource"]').children().val();
            var $jianshuInput = $(this).parents("tr").find("td[class='td-jianshu']").children();
            var jianshu = $jianshuInput.val();
            if(util.isNull(jianshu)){
                layer.tips('请填写件数！',$jianshuInput,{tips: [2, '#FF4444'],time: 4000});
                $jianshuInput.focus();
                return;
            }
            var $salePriceInput = $(this).parents("tr").find("td[class='td-salePrice']").children();
            var salePrice = $salePriceInput.val();
            if(util.isNull(salePrice)){
                layer.tips('请填写定价！',$salePriceInput,{tips: [2, '#FF4444'],time: 4000});
                $salePriceInput.focus();
                return;
            }
            var code = $(this).parents("tr").find('td[class="td-code"]').children().text();
            var size = $(this).parents("tr").find('td[class="td-size"]').children().val();
            var sex = $(this).parents("tr").find('td[class="td-sex"]').children().val();
            var storage = $(this).parents("tr").find('td[class="td-storage"]').children().val();
            var operator = $(this).parents("tr").find('td[class="td-operator"]').children().text();
            var remark = $(this).parents("tr").find('td[class="td-remark"]').children().val();
            var purchasePrice = $(this).parents("tr").find('td[class="td-purchasePrice"]').children().val();
            var beforeCode = $(this).next().val();
            var stockBatchId =$("#rk-stockBatchId").val();
            var param = {
                "name" : name,
                "season" : season,
                "resource" : resource,
                "jianshu" : jianshu,
                "code" : code,
                "sizeType" : size,
                "sex" : sex,
                "storage" : storage,
                "operator" : operator,
                "remark" : remark,
                "beforeCode" : beforeCode,
                "stockBatchId" : stockBatchId,
                "purchasePrice" : purchasePrice,
                "salePrice" : salePrice
            };
            var _this = $(this);
            //2.ajax后台保存 如果是编辑还要删除之前的 ；失败-提示；成功：1本行除操作项其他置为diasabled，操作项改为编辑和上架
            $.ajax({
                url:"../stock/addStock",
                dataType:"json",
                data: param,
                type:"POST",
                success:function(result){
                    if("200" === result.statusCode){
                        layer.msg("入库成功!【编码："+code+";共"+jianshu+"件】");
                        _this.parents("tr").attr("saved","saved");
                        _this.parents("tr").find('td').children().attr("disabled","disabled");
                        var html = "<button class=\"editBtn layui-btn layui-btn-warm layui-btn-sm\" style=\"width: 35%;margin-left: 10%\">编辑</button>" +
                                "<button class=\"onSaleBtn layui-btn layui-btn-normal layui-btn-sm\" style=\"width: 35%;\">上架</button>";
                        _this.parent().html(html);
                    }else{
                        layer.msg('入库操作失败!'+result.message);
                    }
                },
                error: function(){
                    layer.msg('入库操作异常!');
                }
            });

        });

        //绑定函数--编辑按钮
        $("body").on("click",".editBtn",function(){
            $(this).parents("tr").find('td').children().removeAttr("disabled");
            var code = $(this).parents("tr").find('td[class="td-code"]').children().text();
            var html = "<button class=\"rukuBtn layui-btn layui-btn-sm\" style=\"width:50%;margin-left:25%\">入库</button>" +
                    "<input type=\"hidden\" class=\"editpch\" value=\""+code+"\"/>";
            $(this).parent().html(html);
        });

        //绑定函数--上架按钮
        $("body").on("click",".onSaleBtn",function(){
            layer.msg("上架");
        });

        //绑定函数--编码生成
        $("body").on("change",".data-resource",function(){
            generateCode(this);
        });
        $("body").on("blur",".data-jianshu",function(){
            $(this).val($(this).val().replace(/[^0-9]/g,''));
            generateCode(this);
        });
        $("body").on("change",".data-season",function(){
            generateCode(this);
        });

        //添加一行tr
        $("body").on("click",".addTrBtn",function(){
            //获取类别编码
            var stockType = "";
            $.ajax({
                url:"../stock/getStockType",
                type:"GET",
                async:false,//同步获取
                success:function(result){
                    if("200" === result.statusCode){
                        stockType = result.data.stockType;
                    }
                },
                error: function(){
                    layer.msg('获取类别编码异常!');
                }
            });
            var html = "<tr stockType="+stockType+">\n" +
                    "                <td>\n" +
                    "                    <div  align=\"center\">\n" +
                    "                        <button class=\"deleteTrBtn layui-btn layui-btn-xs layui-btn-radius layui-btn-danger\" > &nbsp;删除&nbsp;</button>\n" +
                    "                    </div>\n" +
                    "                </td>\n" +
                    "                <td class=\"td-name\"><input  class=\"layui-center layui-table-edit layui-input\" style=\"font-size: 18px;\"/></td>\n" +
                    "                <td class=\"td-season\">\n" +
                    "                    <select class=\"data-season layui-center layui-table-edit\" style=\"font-size: 16px;\">\n" +
                    "                        <option value=\"C\" "+"<#if season?? && season == 'C'>selected</#if>"+">春</option>\n" +
                    "                        <option value=\"X\" "+"<#if season?? && season == 'X'>selected</#if>"+">夏</option>\n" +
                    "                        <option value=\"Q\" "+"<#if season?? && season == 'Q'>selected</#if>"+">秋</option>\n" +
                    "                        <option value=\"D\" "+"<#if season?? && season == 'D'>selected</#if>"+">冬</option>\n" +
                    "                    </select>\n" +
                    "                </td>\n" +
                    "                <td class=\"td-resource\">\n" +
                    "                    <select class=\"data-resource layui-center layui-table-edit\" style=\"font-size: 16px;\">\n" +
                    "                        <option value=\"01\">01厂</option>\n" +
                    "                        <option value=\"02\">02厂</option>\n" +
                    "                        <option value=\"03\">03厂</option>\n" +
                    "                        <option value=\"00\">重新上架</option>\n" +
                    "                    </select>\n" +
                    "                </td>\n" +
                    "                <td class=\"td-jianshu\"><input class=\"data-jianshu layui-center layui-table-edit layui-input\"  style=\"font-size: 20px;\"/></td>\n" +
                    "                <td class=\"td-code\"><span  class=\"layui-center layui-table-edit layui-input\" style=\"font-size: 18px;padding-top: 12px;\"></span></td>\n" +
                    "                <td class=\"td-size\">\n" +
                    "                    <select class=\"layui-center layui-table-edit\" style=\"font-size: 16px;\">\n" +
                    "                        <option value=\"L\">L</option>\n" +
                    "                        <option value=\"S\">S</option>\n" +
                    "                        <option value=\"M\">M</option>\n" +
                    "                        <option value=\"XL\">XL</option>\n" +
                    "                        <option value=\"XXL\">XXL</option>\n" +
                    "                    </select>\n" +
                    "                </td>\n" +
                    "                <td class=\"td-sex\">\n" +
                    "                    <select class=\"layui-center layui-table-edit\" style=\"font-size: 16px;\">\n" +
                    "                        <option value=\"0\">女款</option>\n" +
                    "                        <option value=\"1\">男款</option>\n" +
                    "                        <option value=\"9\">中性款</option>\n" +
                    "                    </select>\n" +
                    "                </td>\n" +
                    "                <td class=\"td-purchasePrice\"><input  class=\"layui-center layui-table-edit layui-input\" style=\"font-size: 16px;\"/></td>\n" +
                    "                <td class=\"td-salePrice\"><input  class=\"layui-center layui-table-edit layui-input\" style=\"font-size: 16px;\"/></td>\n" +
                    "                <td><input disabled class=\"layui-center layui-table-edit layui-input\" style=\"font-size: 16px;\"/></td>\n" +
                    "                <td class=\"td-storage\">\n" +
                    "                    <select class=\"layui-center layui-table-edit\" style=\"font-size: 16px;\">\n" +
                    "                        <option value=\"01\">仓库01</option>\n" +
                    "                        <option value=\"02\">仓库02</option>\n" +
                    "                        <option value=\"03\">仓库03</option>\n" +
                    "                    </select>\n" +
                    "                </td>\n" +
                    "                <td class=\"td-operator\"><span style=\"font-size: 18px;\">${realname!}</span></td>\n" +
                    "                <td class=\"td-remark\"><input  class=\"layui-center layui-table-edit layui-input\" style=\"font-size: 16px;\"/></td>\n" +
                    "                <td>\n" +
                    "                    <button class=\"rukuBtn layui-btn layui-btn-sm\" style=\"width: 50%;margin-left: 25%\">入库</button>\n" +
                    "                </td>\n" +
                    "            </tr>";
            $("#table-ruku").append(html);
        });

        //删除本行
        $("body").on("click",".deleteTrBtn",function(){
            var $tr = $(this).parents("tr");
            if("saved" === $tr.attr("saved")){
                layer.confirm('已入库的数据删除后将无法恢复，确定要删除吗？',function(index){
                    //todo:ajax删除
                    $tr.remove();
                    layer.msg("已删除");
                    layer.close(index);
                }, function(){});
            }else{
                $tr.remove();
            }
        });

        //自动生成编码
        function generateCode(obj) {
            var jianshu = $(obj).parents("tr").find('td[class="td-jianshu"]').children().val();
            var stockType = $(obj).parents("tr").attr('stockType');
            if(jianshu==''){
                return;
            }
            var season = $(obj).parents("tr").find('td[class="td-season"]').children().val();
            var resource = $(obj).parents("tr").find('td[class="td-resource"]').children().val();
            var nowDate = getNowFormatDate();
            if(jianshu==1){
                $(obj).parents("tr").find('td[class="td-code"]').children().text(season+stockType+nowDate+resource+"001");
                return;
            }else if(jianshu>1 && jianshu <10){
                jianshu = "00" + jianshu;
            }else if(jianshu>=10 && jianshu <100){
                jianshu = "0" + jianshu;
            }
            $(obj).parents("tr").find('td[class="td-code"]').children().text(season+stockType+nowDate+resource+"001~"+jianshu);
        }
        
        //绑定函数--限制输入
        $("body").on("keyup",".data-jianshu",function(){
            $(this).val($(this).val().replace(/[^0-9]/g,''));
        });
        $("body").on("afterpaste",".data-jianshu",function(){
            $(this).val($(this).val().replace(/[^0-9]/g,''));
        });

        //获取当前时间，格式YYMMDD
        function getNowFormatDate() {
            var date = new Date();
            var year = date.getFullYear().toString();
            year = year.substring(2,2);
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            return year  + month  + strDate;
        }


    })

</script>
</html>