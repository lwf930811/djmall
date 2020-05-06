<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面</title>
	<<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
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
	<form id ="frm">
		<input type = "hidden" name = "salt" id="salt" value = "${salt}"/>
		<input type = "hidden" name = "userName"  value = "${userName}"/>
	   	密码框:
	    	<input type="password" name="password"  id = "password"><br><Br>
		确认密码:
			<input type="password" name="surePwd"  id = "surePwd"><br><Br>
		<input type = "submit" value="确认修改"/>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$("#frm").validate({
			rules:{
				password:{
					required:true,
					minlength:1
				},
				surePwd:{
					required:true,
					equalTo:"#password"
				},
			},
			messages:{
				password:{
					required:"请填写密码!",
					minlength:"密码最少1位"
				},
				surePwd:{
					required:"请确认密码!",
					equalTo:"这么快忘了呀"
				}
			}
		})
	})
	$.validator.setDefaults({
		submitHandler: function() {
			var index = layer.load(1); //换了种风格
			var pwd = md5($("#password").val());
			var salt = $("#salt").val();
			$("#password").val(md5(pwd + salt));
			$.post(
					"<%=request.getContextPath()%>/auth/user/updatePassword",
					$("#frm").serialize(),
					function(data){
						if (data.code != 200) {
							layer.msg(data.msg, {icon: 5, time: 1000}, function(){
								layer.close(index);
							});
							return;
						}
						layer.msg(data.msg, {icon: 6, time: 1000}, function(){
							layer.close(index);
							parent.window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
						})
					})
		}
	});
</script>
</html>