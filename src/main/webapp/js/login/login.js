layui.use(['layer','form'],function(){
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		/**视频尺寸自适应算法**/
		var video_width =  $(".login-video-player").width();
		var video_height =  $(".login-video-player").height();
		var window_width =  $(window).width();
		var window_height =  $(window).height();
		var window_compare = window_width/window_height;
		var video_compare = video_width/video_height;
		if(window_compare<=video_compare){
            $(".login-video-player").css({"width":video_compare*window_height,"height":window_height});
		}else{
            $(".login-video-player").css({"width":window_width,"height":window_width*video_height/video_width});
		}
    }).resize();

	if($("#logoutMsg").val()!=""){
        layer.msg($("#logoutMsg").val());
    }

	//登录按钮事件
	form.on("submit(logins)",function(data){
        //alert(JSON.stringify(data.field));
		//window.location.href = "../home";
        var load = layer.load();
        $.ajax({
            url:"../login",
            data:data.field,//JSON.stringify(data.field),
            dataType:"json",
            type:"POST",
            //contentType:'application/json;charset=UTF-8',
            success:function(result){
                if("200"==result.statusCode){
                    window.location.href = "../home";
				}else {
                    layer.msg(result.message);
				}
                layer.close(load);
                if(result && result.status != "200"){
                    layer.msg(result.message,function(){});
                    $(".password").val("");
                }else{
                    layer.msg('登录成功！');
                    setTimeout(function(){
                        //登录返回
                        window.location.href = "../home";
                       // window.location.href= result.back_url || "${basePath}/";
                    },1000)
                }
            },
            error: function(result){
                layer.msg('error');
                layer.close(load);
            }
        });
        return false;
	})
})
