<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/md5/md5-min.js"></script>
</head>
<body>
	<form id = "frm">
				<input type="hidden" name="salt"  id="salt"/><br>
				<input type="hidden" name = "_method" value="get" >
		用户：<input type ="text" name = "userName" placeholder="用户名/手机号/邮箱" onblur="getSalt(this)" id="userName"><br>
		密码：<input type = "password" name = "password" id="pwd" placeholder="密码"><br>
		     <input type = "button" value = "登陆" onclick = "login()"><br>
		<p><span><a href="<%=request.getContextPath()%>/auth/user/toRegister">还有没有账号?点我去注册</a></span></p>
		<p><span><a href="<%=request.getContextPath()%>/auth/user/findPassword">忘记密码?</a></span></p><br>
	</form>
</body>
	<script type="text/javascript">
		function login(){
			var index = layer.load(1, {shade: 0.3}, {shadeClose: true}); //解决网络延迟的加载重复操作问题
			var pwd = md5($("#pwd").val());
			var salt = $("#salt").val();
			var newPassword = md5(pwd + salt);
			$("#pwd").val(newPassword)
			$.get(
					"<%=request.getContextPath()%>/auth/user/login",
					$("#frm").serialize(),
					function (data){
						if (data.code != -1) {
							layer.msg(data.msg, {icon: 6}, function(){
								window.location.href = "<%=request.getContextPath()%>/index/toIndex";
							});
							return;
						}
						if (data.code == 300) {
							var userName = $("#userName").val();
							layer.msg(data.msg, {icon: 6}, function(){
								window.location.href = "<%=request.getContextPath()%>/auth/user/toUpdatePassword?userName="+userName;
							});
							return;
						}
						layer.msg(data.msg, {icon: 5})
						layer.close(index);
			})
		}

		//获取盐
		function getSalt(obj) {
			$.get("<%=request.getContextPath()%>/auth/user/getSalt",
					{userName: obj.value},
					function (data) {
						if (data.code == 200) {
							$("#salt").val(data.data);
						} else {
							layer.msg(data.msg, {icon: 5});
						}
					})
		}
		
		function register(){
			layer.open({
			      type: 2,
				  title: '注册页面',
				  shadeClose: true,
				  shade: 0.8,
				  area: ['380px', '70%'],
				  //路径
		           content:  '<%=request.getContextPath()%>/auth/user/toRegister'
				});
		}

		//判断当前窗口路径与加载路径是否一致。
		if(window.top.document.URL != document.URL){
			//将窗口路径与加载路径同步
			window.top.location = document.URL;
		}

	</script>
</html>