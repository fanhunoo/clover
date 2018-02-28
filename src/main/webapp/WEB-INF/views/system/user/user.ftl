<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
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
<table lay-even class="layui-table">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col width="200">
        <col width="80">
        <col width="250">
    </colgroup>
    <thead>
    <tr>
        <th>用户名</th>
        <th>真实姓名</th>
        <th>手机号</th>
        <th>所属机构</th>
        <th>状态</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
    <tr>
        <td>${user.userName!}</td>
        <td>${user.realName!}</td>
        <td>${user.phone!}</td>
        <td>
            <#if user.orgId==0>总店<#else>${user.orgId!}号店</#if>
        </td>
        <td>
            <#if user.status==0>禁用</#if>
            <#if user.status==1>启用</#if>
        </td>
        <td>${user.remarkes!}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>