<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript">

        jQuery.validator.addMethod("phone",
            function(value, element) {
                var tel = /^1[3456789]\d{9}$/;
                return tel.test(value)
            }, "请正确填写您的手机号");


        $(function(){
            $("#fm").validate({
                //效验规则
                rules: {
                    username:{
                        required:true,
                        minlength:2,
                        remote: {
                            type: 'POST',
                            url: "<%=request.getContextPath()%>/user/findByUsername",
                            data:{
                                userName:function() {
                                    return $("#userName").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
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
                    password2:{
                        required:true,
                        minlength:1,
                        digits:true,
                        equalTo:"#pwd"
                    },
                    phone:{
                        required:true,
                        phone:true,
                        digits:true,
                        remote: {
                            type: 'POST',
                            url: "<%=request.getContextPath()%>/user/findByPhone",
                            data:{
                                phone:function() {
                                    return $("#phone").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }
                            }
                        }
                    },
                    email:{
                        required:true,
                        email:5,
                        remote: {
                            type: 'POST',
                            url: "<%=request.getContextPath()%>/user/findByEmail",
                            data:{
                                email:function() {
                                    return $("#email").val();
                                },
                                dataType:"json",
                                dataFilter:function(data,type){
                                    if (data == 'true'){
                                        return true;
                                    }else {
                                        return false	;
                                    }
                                }
                            }
                        }
                    },
                },
                messages:{
                    userName:{
                        required:"名字必填",
                        minlength:"最少两个字儿"
                    },
                    password:{
                        required:"请填写密码",
                        minlength:"最少5个字儿",
                        digits:"只能是数字"
                    },
                    password2:{
                        required:"请确认密码",
                        minlength:"最少5个字儿",
                        digits:"只能是数字",
                        equalTo:"两次输入密码不同"
                    },
                    phone:{
                        required:"请填写手机号",
                        rangelength:"11位呀",
                        digits:"只能是数字"
                    },
                    email:{
                        required:"请填写你的邮箱",
                        email:"邮箱格式不对"
                    },
                },
            })
        })

        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(1,{shade:0.5});
                $.post("<%=request.getContextPath()%>/user/add",
                    $("#fm").serialize(),
                    function(data){
                        if(data.code == -1){
                            layer.close(index);
                            layer.msg(data.msg, {icon: 5});
                            return
                        }
                        layer.msg(data.msg, {
                            icon: 6,
                            time: 2000
                        }, function(){
                            layer.close(index);
                            parent.window.location.href = "<%=request.getContextPath()%>/user/toLogin";
                        });
                    }
                )
            }
        });

    </script>
    <!-- 错误时提示颜色 -->
    <style>
        .error{
            color:red;
        }
    </style>

</head>
<body>
<form id="fm">
    用户名:<input type="text" name="username" id="userName"><br />
    密码:<input type="text" name="password" id="pwd"><br />
    确认密码:<input type="text" name="password2"><br />
    手机号:<input type="text" name="phone" id="phone"><br />
    邮箱:<input type="text" name="email" id="email"><br />
    <input type="hidden" name="isDel" value="1">
    <a href="<%=request.getContextPath()%>/user/toLogin">已有账号?前去登录</a>
    <input type="submit" value="注册">
</form>
</body>
</html>