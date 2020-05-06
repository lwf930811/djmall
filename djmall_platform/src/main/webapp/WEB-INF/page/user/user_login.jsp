<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/md5/md5-min.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/js/gVerify.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" media="all">
</head>
<style>
    #v_container{
        width: 100px;
        height: 40px;
    }
</style>
<body>
<form id = "frm">
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">普通登录</li>
        <li>手机登录</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            用户名：<input type = "text" name = "userName" placeholder="用户名/手机号/邮箱" onblur="getSalt(this)" id="userName"/><br/><br/>
            密码：<input type = "password" name = "password" id = "pwd"/><br/><br/>
            <span class = "loginBut"></span>
        </div>
        <div class="layui-tab-item">
            手机号：<input type = "text" name = "phone" id="userPhone" /><br/><br/>
            <div id="v_container"></div>
            <input type="text" id="code_input" value="" placeholder="请输入验证码">
            <input id="my_button" value = "验证图片" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" onclick = "picture()"/>
            <br/><br/>
                <div id = "div_code">短信验证码：<input type = "text" name = "phoneCode"/>&nbsp;
                    <input type = "button" value = "发送短信验证码" id = "testbtn" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" /><br/><br/>
                </div>
            <span class = "loginBut"></span>
        </div>
    </div>
</div>
</form>
</body>
<script>
    $("#div_code").hide();
    var html = "<input type = 'button' value = '登录' onclick = 'userLogin()' class='layui-btn layui-btn-sm layui-btn-radius layui-btn-primary'/>&nbsp;&nbsp;&nbsp;<input type= 'button' value = '去注册' onclick= 'toRegister()' class='layui-btn layui-btn-sm layui-btn-radius layui-btn-primary' />"
    $(".loginBut").html(html);

    /**
     * 图形验证
     */
    var verifyCode = new GVerify("v_container");
    console.log(verifyCode)
    function picture() {
        var res = verifyCode.validate(document.getElementById("code_input").value);
        if(res){
            layer.alert("验证正确");
            $("#div_code").show();
        }else{
            layer.alert("验证码错误");
            $("#div_code").hide();
        }
    }
    /**
     *	短信验证
     */
    $("#testbtn").on("click",function(){
        if($("#userPhone").val() == null) {
            layer.alert("请输入手机号");
            return;
        }
        $.post(
            "<%=request.getContextPath()%>/user/sendMessage",
            {"phone":$("#userPhone").val()},
            function(result) {
                if(result.code != 200) {
                    layer.msg(result.msg, {icon: 5});
                    return;
                }
                layer.msg('发送成功', {icon: 6});
            });
        $(this).attr("disabled","disabled");
        $("#testbtn").val("倒计时60秒");
        var num = 60;
        var sid = setInterval(function(){
            if(num>0){
                num--;
                $("#testbtn").val("倒计时"+num+"秒");
            } else {
                clearInterval(sid);
                $("#testbtn").removeAttr("disabled","disabled");
                $("#testbtn").val("重新发送");
            }
        }, 1000);
    });


    //获取盐
    function getSalt(obj) {
        $.get("<%=request.getContextPath()%>/user/getSalt",
            {userName: obj.value},
            function (data) {
                if (data.code == 200) {
                    $("#salt").val(data.data);
                } else {
                    layer.msg(data.msg, {icon: 5});
                }
            })
    }

    /**
     * 登录
     */
    function userLogin() {
        var index = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        var pwd = md5($("#pwd").val());
        var salt = $("#salt").val();
        var newPassword = md5(pwd + salt);
        $("#pwd").val(newPassword)
        $.post(
            "<%=request.getContextPath()%>/user/login",
            $("#frm").serialize(),
            function(data) {
                layer.close(index);
                if (data.code != 200) {
                    layer.msg(data.msg, {
                        icon: 2,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade:0.3
                    });
                    layer.close(index);
                    return;
                }
                layer.msg('登录成功', {
                    icon: 1,
                    time: 2000, //2秒关闭（如果不配置，默认是3秒）
                    shade:0.3
                }, function(){
                    var user = data.data;
                    set_login(user.token,user.nickName);
                    cookie.set("USERID",user.userId,1);
                    parent.location.href = "<%=request.getContextPath()%>/index/toIndex"
                });
            }
        )
    }

    function toRegister() {
        window.location.href = "<%=request.getContextPath()%>/user/toRegister";
    }
</script>
</html>
