<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
</head>
<body>
	<form id="frm">
		<input type="hidden" value="1" id="pageNo" name="page">
		<input type="text" name="userName" value="" placeholder="用户名/手机号/邮箱"><br />
		角色:
		<c:forEach items="${role}" var="role">
			<input type = "radio" name = "userRole" value="${role.roleId}">${role.roleName}
		</c:forEach><br>
		性别:
		<input type = "radio" name = "userSex"  value="男"  <c:if test="${user.userSex == '男'}">checked</c:if>>男
		<input type = "radio" name = "userSex"  value="女"  <c:if test="${user.userSex == '女'}">checked</c:if>>女</br>
		状态:
		<select name = "status">
			<option value="">请选择</option>
			<c:forEach items="${statusList}"  var="status" >
				<option value="${status.name}">${status.name}</option>
			</c:forEach>
		</select></br>
			<input type = "button" value = "search" onclick = "show()"><br/><br/>
	</form>
		<shiro:hasPermission name="USER_UPDATE_BTN">
			<input type = "button" value="修改" onclick="update()">&nbsp
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_ACTIVE_BTN">
				<input type = "button" value="激活" onclick="activate()">&nbsp
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_RESERT_PASSWORD_BTN">
			<input type = "button" value="重置密码" onclick="resetNewPassword()">&nbsp
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_DELETE_BTN">
			<input type = "button" value="删除" onclick="del()">&nbsp
		</shiro:hasPermission>
		<shiro:hasPermission name="USER_AUTH_BTN">
			<input type = "button" value="授权" onclick="auth()"><br/></br>
		</shiro:hasPermission>
		<table border="1px solid red" cellpadding="20" >
			<tr>
				<th>用户id</th>
				<th>用户名</th>
				<th>昵称</th>
				<th>电话</th>
				<th>邮箱</th>
				<th>性别</th>
				<th>级别</th>
				<th>状态</th>
				<th>注册时间</th>
				<th>最后登录时间</th>
			</tr>
			<tbody id = "tbd">
			</tbody>
		</table>
	<div id="pageInfo"></div>
</body>
	<script type="text/javascript">
	$(function(){
		show();	
	})
	function show(){
		$("#tbd").empty();
		$.get("<%=request.getContextPath()%>/auth/user/userList",
				$("#frm").serialize(),
				function(data) {
					var html = "";
					for (var i = 0; i < data.data.list.length; i++) {
						var user = data.data.list[i];
						html+="<tr>";
						html+="<td>"
						html+="<input type = 'checkbox' name = 'ids' value = '"+user.userId+"'>"+user.userId+""
						html+="<input type = 'hidden' id = '"+user.userId+"'  value = '"+user.status+"'>"
						html+="</td>"
						html+="<td>"+user.userName+"</td>"
						html+="<td>"+user.nickName+"</td>"
						html+="<td>"+user.phone+"</td>"
						html+="<td>"+user.email+"</td>"
						html+="<td>"+user.userSex+"</td>"
						html+="<td>"+user.roleName+"</td>"
						html+="<td>"+user.status+"</td>"
						user.status == 1 ? html += "<td>未激活</td>" : html += "<td>已激活</td>";
						html+="<td>"+user.registerTime+"</td>"
						html+="<td>"+user.loginTimeShow+"</td>"
						html+="</tr>"
				}
			$("#tbd").html(html);
			var pageNo = $("#pageNo").val();
			var pageInfo = "<input type='button' value='上一页'  onclick='page("+(parseInt(pageNo)-1)+")'>"
				pageInfo +="<input type='button' value='下一页'  onclick='page("+(parseInt(pageNo)+1)+","+data.data.pages+")'>"
			$("#pageInfo").html(pageInfo);
		})
	}

	function update(){
		var array = new Array();
		$("input[name = ids]:checked").each(function() {
			array.push($(this).val());
		});
		if (array.length > 1) {
			layer.msg("只能修改一条");
			return;
		}
		if (array.length < 1) {
			layer.msg("请选择");
			return;
		}

		layer.open({
			type: 2,
			title: '修改页面',
			shadeClose: true,
			shade: 0.8,
			area: ['385px', '75%'],
			//路径
			content: '<%=request.getContextPath()%>/auth/user/toUpdate?id='+array
		});
	}

	function del(id) {
		var array = new Array();
		$("input[name = ids]:checked").each(function() {
			array.push($(this).val());
		});
		if(array.length < 1){
			layer.msg("请选择");
			return;
		}
		layer.confirm('确定删除？', {icon: 3, title:'提示'}, function(index){
			layer.close(index);
			$.post("<%=request.getContextPath()%>/auth/user/delete",
					{"ids":array.join(","),"_method":"GET"},
					function(data){
						if(data.code == 200){
							layer.msg(data.msg, {
								icon: 6,
								time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								//do something
								window.location.href = "<%=request.getContextPath()%>/auth/user/toList";
							});
							return;
						}
						layer.msg(data.msg, {icon: 5});
					})
		});
	}

	function activate(){
		var id = $("input[name='ids']:checked").val();
		var size = $("input[name = 'ids']:checked").length
		if (size <= 0) {
			layer.msg("请选择");
			return;
		}
		if (size > 1) {
			layer.msg("您只能选择一项");
			return;
		}
		if ($("#"+id).val() == 2) {
			layer.msg("该用户已被激活");
			return;
		}
		var idArray = "";
		$("input[name = 'ids']:checked").each(function (index, item) {
			if ($("input[name='ids']:checked").length-1==index) {
				idArray += $(this).val();
			}
		});
		var sta = $("#"+idArray).val();
		var msg = "您确定要设置为";
		var status = "";
		if (sta == 1) {
			msg += "激活？";
			status = 2;
		}
		layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
			layer.close(index);
			$.get("<%=request.getContextPath()%>/auth/user/updateStatus",
					{"id":idArray,
					"userStatus":status},
					function(data){
						if(data.code == 200){
							layer.msg(data.msg, {
								icon: 6,
								time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								//do something
								window.location.href = "<%=request.getContextPath()%>/auth/user/toList";
							});
							return;
						}
						layer.msg(data.msg, {icon: 6});
					})
		});
	}

	function auth(){
		var array = new Array();
		$("input[name = ids]:checked").each(function() {
			array.push($(this).val());
		});
		if (array.length > 1) {
			layer.msg("只能选择一条");
			return;
		}
		if (array.length < 1) {
			layer.msg("请选择");
			return;
		}

		layer.open({
			type: 2,
			title: '授权页面',
			shadeClose: true,
			shade: 0.8,
			area: ['385px', '75%'],
			//路径
			content: '<%=request.getContextPath()%>/auth/user/toAuth?id='+array
		});
	}
	//重置密码
	function resetNewPassword(){
		var array = new Array();
		$("input[name = ids]:checked").each(function() {
			array.push($(this).val());
		});
		if (array.length > 1) {
			layer.msg("只能选择一条");
			return;
		}
		if (array.length < 1) {
			layer.msg("请选择");
			return;
		}
		var index = layer.load(1,{shade:0.5});
		//do something
		$.post(
				"<%=request.getContextPath()%>/auth/user/resetPassword?id="+array,
				{},
				function(data){
					if (data.code != -1) {
						layer.msg(data.msg, {icon: 6}, function(){
							window.location.href = "<%=request.getContextPath()%>/auth/user/toList";
						});
						return;
					}
					layer.msg(data.msg, {icon: 5})
					layer.close(index);
				}
		)
	}


	function page (page,total) {
		if (page < 1) {
			layer.msg("已经是首页了");
			return;
		}
		if (total < page) {
			layer.msg("已经是尾页了");
			return;
		}
		 $("#pageNo").val(page);
		 show();
	}


	</script>
</html>