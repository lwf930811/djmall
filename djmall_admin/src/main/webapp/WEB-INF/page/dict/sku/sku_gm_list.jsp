<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <style>
        .error{
            color:red;
        }
    </style>
</head>
<body>
<table border="1px" align="center">
    <tr>
        <th colspan="4"><h2>通用SKU属性维护</h2></th>
    </tr>
    <tr>
        <th>编号</th>
        <th>商品类型</th>
        <th>属性名</th>
        <th></th>
    </tr>
    <tbody id = "tb"></tbody>
</table>
</body>
<script>
    $(function() {
        show();
    })

    function show() {
        $.post(
            "<%=request.getContextPath()%>/dict/sku/list",
            {},
            function(data) {
                var html = "";
                for(var i=0; i<data.data.length; i++) {
                    var skuGm = data.data[i];
                    var code = '"' + skuGm.productType + '"';
                    html += "<tr>";
                    html += "<td>"+skuGm.id+"</td>";
                    html += "<td>"+skuGm.typeName+"</td>";
                    html += skuGm.attrNames == null ? "<td style='color:red' >暂无关联属性</td>" :"<td>"+skuGm.attrNames+"</td>"
                    html += "<td><input type = 'button' value = '关联属性' onclick = 'addValues("+code+")' /></td>"
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    function addValues(productType) {
        layer.open({
            align:top,
            type: 2,
            title: '关联属性',
            shadeClose: false,
            shade: 0.8,
            area: ['360px', '80%'],
            content: "<%=request.getContextPath()%>/dict/sku/toValueList?productType="+productType
        });
    }
</script>
</html>
