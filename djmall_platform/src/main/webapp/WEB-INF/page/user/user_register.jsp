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
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/jquery.validate.min.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/messages_zh.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" media="all">
</head>
<body>
<center>
    <form id = "frm">
        <input type = "hidden" id = "salt" value="${salt}" name = "salt" />
    用户名：<input type = "text" name = "userName" id = "userName"/><br/><br/>
        <input type = "hidden" name = "status" value = "2"/>
    密码：<input type = "password" name = "password" id = "password"/> <br/><br/>
    重复密码：<input type = "password" name = "confirmPassword"/><br/><br/>
    手机号：<input type = "text" name = "phone" id = "phone" /><br/><br/>
    邮箱：<input type = "text" name = "email" id = "email" /><br/><br/>
    <input type = "submit" value = "注册" />
    <input type = "button" onclick="toLogin()" value = "返回登陆" />
</form>
</center>
</body>
<script>
    $(function(){
        $("#frm").validate({
            rules:{
                userName:{
                    required:true,
                    remote:{
                        type: 'GET',
                        url:"<%=request.getContextPath()%>/user/findName",
                        data: {
                            userName:function () {
                                return $("#userName").val();
                            },
                            dataType: "json",
                            dataFilter:function (data,type){
                                if (data == "true"){
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }

                    }
                },
                password:{
                    required:true,
                    minlength:1,
                    digits:true
                },
                confirmPassword:{
                    required:true,
                    equalTo:"#password"
                },
                email:{
                    required:true,
                    email:true,
                    remote:{
                        type: 'GET',
                        url:"<%=request.getContextPath()%>/user/findEmail",
                        data: {
                            email:function () {
                                return $("#email").val();
                            },
                            dataType: "json",
                            dataFilter:function (data,type){
                                if (data == "true"){
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }

                    }
                },
                phone:{
                    required:true,
                    digits:true,
                    phone:true,
                    remote:{
                        type: 'GET',
                        url:"<%=request.getContextPath()%>/user/findPhone",
                        data: {
                            phone:function () {
                                return $("#phone").val();
                            },
                            dataType: "json",
                            dataFilter:function (data,type){
                                if (data == "true"){
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }

                    }
                },
            },
            messages:{
                userName:{
                    required:"请输入用户名",
                },
                userPwd:{
                    required:"请输入密码",
                    minlength:"输入符规则的长度",
                    digits:"必须输入数字"
                },
                confirmPassword:{
                    required:"请输入密码",
                    equalTo:"这么快就忘记了"
                },
                email:{
                    required:"请输入邮箱号",
                    remote: "該邮箱号已存在，請重新输入"

                },
                phone:{
                    required:"请输入电话号",
                    remote: "該电话号已存在，請重新输入"
                },
            }
        })
    })
    //邮箱验证
    jQuery.validator.addMethod("email",
        function(value, element) {
            var ema =  /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            return ema.test(value)
        }, "请正确填写您的邮箱号");

    //手机验证
    jQuery.validator.addMethod("phone",
        function(value, element) {
            var ph =/^1[3578]\d{9}$/;
            return ph.test(value)
        }, "请正确填写您的电话号码");
    // 判断用户名昵称不同
    jQuery.validator.addMethod("notEqu",
        function(value, element) {
            return value != $("#userName").val();
        }, "不能与用户名一样!");
    $.validator.setDefaults({
        submitHandler: function () {
                    //最终密码
                    var overPwd = md5(md5($("#pwd").val())+$("#salt").val());
                    $("#pwd").val(overPwd);
                    var index = layer.load(1, {
                        shade: [0.1,'#fff'] //0.1透明度的白色背景
                    });
                    $.post(
                        "<%=request.getContextPath()%>/user/register",
                        $("#frm").serialize(),
                        function(data) {
                            layer.close(index);
                            if(data.code != 200) {
                                layer.alert(data.msg(),{
                                    icon:5,
                                    area: ['280px', '190px'],
                                    skin: 'layui-layer-lan',
                                    class:'layui-bg-black',
                                    title:'注册提示',
                                    shade: 0.5 , //遮罩透明度
                                    anim: 1 , //0-6的动画形式，-1不开启
                                },function(){
                                    return;
                                })
                            }else{
                                layer.alert( "注册成功",{
                                    icon:6,
                                    area: ['280px', '190px'],
                                    skin: 'layui-layer-lan',
                                    class:'layui-bg-black',
                                    title:'注册提示',
                                    shade: 0.5 , //遮罩透明度
                                    anim: 1 , //0-6的动画形式，-1不开启
                                },function(){
                                    parent.location.href = "<%=request.getContextPath()%>/index/toIndex"
                                });
                            }
                        }
                    )
                }
        })

    //返回登陆
    function toLogin() {
        window.location.href = "<%=request.getContextPath()%>/user/toLogin";
    }
</script>
</html>
