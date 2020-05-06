<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
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
        <input type="hidden" name="_method" value="post">
        分类上级
        <select name="pCode">
            <option value="SYSTEM">SYSTEM</option>
            <c:forEach items="${dictList}"  var="d" >
                <option value="${d.code}">${d.name}</option>
            </c:forEach>
        </select><br />
        分类名称<input type="text" name="name"><br />
        分类code<input type="text" name="code"><br />
        <input type="button" value="新增" onclick="add()">
    </form>
<br />
<table border="1px">
    <tr>
        <th>CODE</th>
        <th>字典名</th>
        <th>上级CODE</th>
        <th>操作</th>
    </tr>
    <tbody id="tbd">
    </tbody>
</table>
</body>
<script type="text/javascript">
    $(function(){
        search();
    })

    function search() {
        $.get(
            "<%=request.getContextPath()%>/base/dict/",
            {},
            function(data){
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var dict = data.data[i];
                    html += "<tr>"
                    html += "<td>"+dict.code+"</td>"
                    html += "<td>"+dict.name+"</td>"
                    html += "<td>"+dict.pcode+"</td>"
                    html += "<td><input type='button' value='修改' onclick='updateByCode(&quot;"+dict.code+"&quot;)'></td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
            }
        )
    }

    function updateByCode(code){
        layer.confirm('确定修改吗?', {icon: 3, title:'提示'}, function(index){
            //do something
            layer.open({
                type: 2,
                title: '字典修改页面',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath()%>/base/dict/toUpdate?code='+code
            });
        });
    }

    function add(){
        layer.confirm('确定增加吗?', {icon: 3, title:'提示'}, function(index) {
            var index = layer.load(1,{shade:0.5});
            $.post(
                "<%=request.getContextPath()%>/base/dict/",
                $("#frm").serialize(),
                function (data) {
                    if (data.code != -1) {
                        layer.msg(data.msg, {icon: 6}, function () {
                            window.location.href = "<%=request.getContextPath()%>/base/dict/toList";
                        });
                        return;
                    }
                    layer.msg(data.msg, {icon: 5})
                    layer.close(index);
                }
            )
        })
        layer.close(index);
    }
</script>
</html>