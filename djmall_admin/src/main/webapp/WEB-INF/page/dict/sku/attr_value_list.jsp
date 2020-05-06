<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
</head>
<body>
<form id = "frm">
<input type = "button" value = "保存" onclick = "addValues()" />
<input type = "hidden" name = "productType" value = "${productType}" />
<table border="1px" align="center">
    <tr>
        <th colspan="3"><h2>通用SKU关联属性</h2></th>
    </tr>
    <tr>
        <th>编号</th>
        <th>属性名</th>
        <th>属性值</th>
    </tr>
    <c:forEach items="${attrList}" var="att">
        <tr>
            <th><input type="checkbox" name="ids" value="${att.attrId}"
            <c:forEach var="attrId" items="${attrIds}">
                       <c:if test="${att.attrId == attrId}">checked</c:if>
            </c:forEach> >${att.attrId}</th>
            <th>${att.attrName}</th>
            <th>${att.attrValues}</th>
        </tr>
    </c:forEach>
</table>
</form>
</body>
<script>
    function addValues() {
        var index = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.post(
            "<%=request.getContextPath()%>/dict/sku/addAttrValues",
            $("#frm").serialize(),
            function(data) {
                layer.close(index);
                if(data.code != 200) {
                    layer.alert(data.msg(),{
                        icon: 2,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade:0.3
                    },function(){
                        return;
                    })
                }else{
                    layer.alert("添加成功",{
                        icon: 1,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade:0.3
                    },function(){
                        parent.location.href = "<%=request.getContextPath()%>/dict/sku/toList"
                    });
                }
            }
        )
    }

</script>
</html>
