
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
</head>
<body>
<center>
    属性名：<input readonly = "true" name = "attrName" value = "${attr.attrName}"/><br/>
    <form id = "fm">
        <input type = "hidden" name="attrId" value = "${attr.attrId}" />
        属性值：<input type = "text" name ="attrValue" />&nbsp<input type = "button" value = "新增" onclick = "addValue()" /><br/>
    </form>
    <table  align="center" border="1px">
        <tr>
            <th>编号</th>
            <th>属性值</th>
            <th></th>
        </tr>
        <tbody id = "tb"></tbody>
    </table><br/>
</center>
</body>
<script>
    $(function() {
        show();
    })

    function show() {
        $.post(
            "<%=request.getContextPath()%>/dict/value/list",
            {"attrId":'${attr.attrId}'},
            function(data) {
                var html = "";
                for(var i=0; i<data.data.length; i++) {
                    var value = data.data[i];
                    html += "<tr>";
                    html += "<td>"+value.id+"</td>";
                    html += "<td>"+value.attrValue+"</td>";
                    html += "<td><input type = 'button' value = '移除' onclick = 'delValue("+value.id+","+value.attrId+")' /></td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    function delValue(id,attrId) {
        var index = layer.load();
        layer.confirm('确认移除吗?', {icon: 3, title:'提示'}, function(index){
            $.post(
                "<%=request.getContextPath()%>/dict/value/delValue",
                {"id": id},
                function (data) {
                    if(data.code!= 200) {
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 2000, //2秒关闭（如果不配置，默认是3秒）
                            shade:0.3
                        });
                        return ;
                    }
                    layer.msg('移除成功', {
                        icon: 1,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade:0.3
                    }, function(){
                        layer.close(index);
                        window.location.href = "<%=request.getContextPath()%>/dict/value/toList?id="+attrId;
                    });

                });
        });
        layer.close(index);
    }

    function addValue() {
        var index = layer.load();
        layer.confirm('确认添加吗?', {icon: 3, title:'提示'}, function(index){
            $.post(
                "<%=request.getContextPath()%>/dict/value/addValue",
                $("#fm").serialize(),
                function (data) {
                    if(data.code != 200) {
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 2000, //2秒关闭（如果不配置，默认是3秒）
                            shade:0.3
                        });
                        return ;
                    }
                    layer.msg('添加成功', {
                        icon: 1,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade:0.3
                    }, function(){
                        layer.close(index);
                        window.location.href = "<%=request.getContextPath()%>/dict/value/toList?id="+'${attr.attrId}';
                    });

                });
        });
        layer.close(index);
    }

</script>
</html>
