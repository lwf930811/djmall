<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/md5/md5-min.js"></script>
<style type="text/css">
	.error{
		color:red;
	}
</style>
</head>
<body>
	<form id = "frm">
				<input type = "hidden" name = "isDel" value="1" ><br>
		用户名:<input type = "text" name = "userName" id="userName" ><br>
		昵称:<input type="text" name="nickName" id="nickName" /><br>
		密码:<input type = "password" name = "password" id="password"><br>
			<input type="hidden" name="salt" value="${salt}" id="salt"/>
		确认密码:<input type="password" name="confirmPassword"><br>
		邮箱:<input type = "text" name = "email" id="email"><br>
		手机号:<input type = "text" name = "phone" id="phone"><br>
		级别:
		<c:forEach items="${role}" var="role">
			<input type = "radio" name = "userRole"  value="${role.roleId}">${role.roleName}
		</c:forEach><br>
		<div id ="userRole" class="error" ></div><br>
		性别:
		<c:forEach items="${sexList}" var="sex">
		<input type = "radio" name = "userSex" value="${sex.name}">${sex.name}
		</c:forEach><br>
			<div id="userSex" class="error" ></div><br>
		<a href="<%=request.getContextPath()%>/auth/user/toLogin">已有账号?前去登录</a>
		<input type = "submit" value = "提交">
	</form>
</body>
	<script type="text/javascript">
 $(function (){
		$("#frm").validate({
			errorPlacement: function(error, element) {
				if(element.is("[name='userRole']")){//如果需要验证的元素名为userSex
					error.appendTo($("#userRole"));   //错误信息增加在id为‘radio-lang’中

				}else if(element.is("[name='userSex']")){//如果需要验证的元素名为userSex
					error.appendTo($("#userSex"));   //错误信息增加在id为‘radio-lang’中

				} else {
					error.insertAfter(element);//其他的就显示在添加框后
				}
			},
			rules:{
					userName:{
						required:true,
						remote:{
							type: 'GET',
							url:"<%=request.getContextPath()%>/auth/user/findName",
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
				nickName:{
					required:true,
					notEqu:true
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
							url:"<%=request.getContextPath()%>/auth/user/findEmail",
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
							url:"<%=request.getContextPath()%>/auth/user/findPhone",
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
				userRole:{
					required:true,
				},
				userSex:{
					required:true,
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
			nickName:{
				required:"请填写昵称"

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
			userRole:{
					required:"必须选一个",
				},
			userSex:{
					required:"必须选一个",
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
 
		// 注册
		$.validator.setDefaults({
			submitHandler: function (){
				var index = layer.load(1, {shade: 0.3}, {shadeClose: true});
				//密码加盐
				var pwd = md5($("#password").val());
				var salt = $("#salt").val();
				var newPassword = md5(pwd + salt);
				$("#password").val(newPassword);
				$.post(
						"<%=request.getContextPath()%>/auth/user/insertUser",
						$("#frm").serialize(),
						function (data){
						if(data.code != 200){
							layer.msg(data.msg, {icon: 5});
							layer.close(index);
							return;
						}
						layer.msg(data.msg, {
							  icon: 6,
							  time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
							  //do something
							    layer.close(index);
								parent.window.location.href = "<%=request.getContextPath()%>/auth/user/toLogin";
							});
				})
			}
			
		})
	</script>
</html>