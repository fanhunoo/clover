// /**
//  项目JS主入口
//  以依赖Layui的layer和form模块为例
//  **/
// layui.define(['layer', 'form'], function(exports){
//     var layer = layui.layer
//         ,form = layui.form;
//
//     layer.msg('Hello World');
//
//     exports('user', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
// });

//由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
;!function(){
    var layer = layui.layer
        ,form = layui.form;

    layer.msg('Hello World');
}();