<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
</head>
<body>
<form id = "frm">
    <input type="hidden" name="_method" value="put"><br />
    <input type="hidden" name="expressId" value="${exp.expressId}"><br />
    运费:<input type="text" name="expressage" value="${exp.expressage}"><br />
    <input type="button" value="修改提交" onclick="update()"><br />
</form>
</body>
<script type="text/javascript">

    function update (){
        var index = layer.load(1,{shade:0.5});
        $.post(
            "<%=request.getContextPath()%>/dict/express/",
            $("#frm").serialize(),
            function(data){
                if (data.code != -1) {
                    layer.msg(data.msg, {icon: 6}, function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/dict/express/toList";
                    });
                    return;
                }
                layer.msg(data.msg, {icon: 5})
                layer.close(index);

            }
        )
    }

</script>
</html>