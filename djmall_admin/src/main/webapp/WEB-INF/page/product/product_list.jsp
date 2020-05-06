<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/messages_zh.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css">
</head>
<body>
<center>
    <form id = "frm">
    商品名称：<input type = "text" name = "productName"/><br/>
    商品分类：<input type = "radio" name = "productType"  />
        <c:forEach items="${productTypeList}"  var="productTypes">
                <input type = "radio" name = "productType" value = "${productTypes.code}" />${productTypes.name}
            </c:forEach>
        <br/>
        <input type = "button" value = "搜索" onclick = "search()" class='layui-btn layui-btn-sm'/>
    </form><br/>
     <input type = "button" value = "添加" onclick = "addProduct()" class='layui-btn layui-btn-sm' />&nbsp;&nbsp;
    <input type = "button" value = "修改" onclick = "updateProduct()" class='layui-btn layui-btn-sm'/>&nbsp;
    <input type = "button" value = "上架/下架" onclick = "productUpOrDown()" class='layui-btn layui-btn-sm' />
    <input type = "button" value = "查看评论" onclick = "lookComment()" class='layui-btn layui-btn-sm' />&nbsp;&nbsp;&nbsp;
    <input type = "button" value = "下载导入模板"  class='layui-btn layui-btn-sm'/>&nbsp;&nbsp;&nbsp;
    <input type = "button" value = "导入"  class='layui-btn layui-btn-sm'/>&nbsp;&nbsp;&nbsp;
    <table class="layui-table" lay-skin="row" lay-size="lg" align="center">
        <colgroup>
            <col width="100">
            <col width="140">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th></th>
            <th>名称</th>
            <th>类型</th>
            <th>状态</th>
            <th>邮费</th>
            <th>商品图片</th>
            <th>描述</th>
            <th>点赞量</th>
            <th>订单量</th>
        </tr>
        </thead>
        <tbody id = "tbd"></tbody>
    </table>
</center>
</body>
<script>
    $(function() {
        search();
    })

    function search() {
        $.post(
            "<%=request.getContextPath()%>/product/productList",
            $("#frm").serialize(),
            function(data) {
                var html = "";
                var product = data.data;
                for(var i=0; i<product.length; i++) {
                    html += "<tr>";
                    html += "<td><input type = 'checkbox'name = 'ids' value = '"+product[i].id+"'/><input type = 'hidden' id = '"+product[i].id+"' value = '"+product[i].status+"' /></td>"
                    html += "<td>"+product[i].productName+"</td>";
                    html += "<td>"+product[i].productTypeName+"</td>";
                    html += product[i].status == 1 ? "<td style='color:red'>上架</td>" : "<td style='color:red'>下架</td>" ;
                    html += "<td>"+product[i].productExpressageShow+"</td>";
                    html += "<td><img src='"+product[i].picture+"' /></td>";
                    html += "<td>"+product[i].productDescribe+"</td>";
                    html += "<td>"+product[i].likeNumber+"</td>";
                    html += "<td>"+product[i].orderNumber+"</td>";
                    html += "</tr>";
                }
                $("#tbd").html(html);
            }
        )
    }

    /**
     * 商品修改
     */
    function updateProduct(){
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
            area: ['800px', '80%'],
            //路径
            content: '<%=request.getContextPath()%>/product/toUpdate?id='+array
        });
    }

    /**
     * 上下架
     */
    function productUpOrDown(){
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
        var idArray = "";
        $("input[name = 'ids']:checked").each(function (index, item) {
            if ($("input[name='ids']:checked").length-1==index) {
                idArray += $(this).val();
            }
        });
        var sta = $("#"+idArray).val();
        var msg = "您确定将该商品设置为";
        var status = "";
        if (sta == 0) {
            msg += "上架吗？";
            status = 1;
        } else {
            msg += "下架吗？";
            status = 0;
        }
        layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
            layer.close(index);
            $.get("<%=request.getContextPath()%>/product/updateStatus",
                {"id":idArray, "status":status},
                function(data){
                    if(data.code == 200){
                        layer.msg(data.msg, {
                            icon: 6,
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            //do something
                            window.location.href = "<%=request.getContextPath()%>/product/toList";
                        });
                        return;
                    }
                    layer.msg(data.msg, {icon: 6});
                })
        });
    }

    /**
     * 添加商品
     */
    function addProduct() {
        window.location.href = "<%=request.getContextPath()%>/product/toAddProduct"
    }

</script>
</html>
