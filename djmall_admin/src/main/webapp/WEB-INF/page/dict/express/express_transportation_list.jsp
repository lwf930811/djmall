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
        物流公司
        <select name="logisticsCompany">
            <c:forEach items="${dictList}"  var="d" >
                <option value="${d.code}">${d.name}</option>
            </c:forEach>
        </select><br />
        运费<input type="text" name="expressage"><br />
        <input type="button" value="新增" onclick="add()">
    </form>
<br />
<table border="1px">
    <tr>
        <th>物流公司</th>
        <th>运费</th>
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
            "<%=request.getContextPath()%>/dict/express/",
            {},
            function(data){
                var html = "";
                for (var i = 0; i < data.data.length; i++) {
                    var exp = data.data[i];
                    html += "<tr>"
                    html += "<td>"+exp.logisticsCompanyName+"</td>"
                    html += "<td>"+exp.expressageName+"</td>"
                    html += "<td><input type='button' value='修改' onclick='updateById("+exp.expressId+")'></td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
            }
        )
    }

    function updateById(id){
        layer.confirm('确定修改吗?', {icon: 3, title:'提示'}, function(index){
            //do something
            layer.open({
                type: 2,
                title: '物流修改页面',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath()%>/dict/express/toUpdate?id='+id
            });
        });
    }

    function add(){
            var index = layer.load(1,{shade:0.5});
            $.post(
                "<%=request.getContextPath()%>/dict/express/",
                $("#frm").serialize(),
                function (data) {
                    if (data.code != -1) {
                        layer.msg(data.msg, {icon: 6}, function () {
                            window.location.href = "<%=request.getContextPath()%>/dict/express/toList";
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