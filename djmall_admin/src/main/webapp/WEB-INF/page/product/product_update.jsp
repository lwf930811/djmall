
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/messages_zh.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css">
</head>
<style>
    .error{
        color:red;
    }
</style>
<body>
    <form id = "frm">
        <br/><br/>
        <input type = "hidden" name = "id" value = "${product.id}" />
        名称：<input type = "text" name = "productName" value = "${product.productName}" /><br/><br/>
        邮费：
        <select name = "productExpressage">
            <c:forEach items="${expressageList}" var="expressage">
                <option value="${expressage.expressId}" <c:if test="${product.productExpressage == expressage.expressId}">selected</c:if>>
                        ${expressage.logisticsCompanyName}-${expressage.expressageName}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        描述：<textarea rows="4px" cols="50px" name="productDescribe">${product.productDescribe}</textarea><br/><br/>
        <input type = "hidden" name = "picture" value = "${product.picture}" />
        图片：<img src = "${product.picture}" height="100" width="100" id = "picture"/><input type = "file" name = "file"/><br/><br/>
        分类：<select name = "productType"  readonly="true" >
                <option value = "${product.productType}">${dict.name}</option>
            </select><br/><br/>

        SKU列表：<input type = "button" value = "修改库存"  class='layui-btn layui-btn-sm' onclick="updateCount()" />&nbsp;&nbsp;
        <input type = "button" value = "编辑" onclick = "writeSku()" class='layui-btn layui-btn-sm' />&nbsp;&nbsp;
        <input type = "button" value = "设为默认" onclick = "setSkuDefault()" class='layui-btn layui-btn-sm' />
        <br/><br/>
        <table class="layui-table" lay-skin="row" lay-size="lg" align="center">
            <thead>
            <tr>
                <th>编号</th>
                <th>SKU属性</th>
                <th>库存</th>
                <th>价格(元)</th>
                <th>折扣(%)</th>
                <th>是否默认(%)</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach var="sku" items="${skuList}">
            <tr>
                <td><input type = "checkbox" value = "${sku.id}" name = "ids" />${sku.id}</td>
                <td>${sku.skuAttrValueNames}</td>
                <td>${sku.skuCount}</td>
                <td>${sku.skuPrice}</td>
                <td>${sku.skuRate}</td>
                <td><c:if test="${sku.isDefault ==0}">否</c:if>
                    <c:if test="${sku.isDefault ==1}">是</c:if>
                </td>
                <td><input type = "button" value = "下架" onclick = "setDownSku(${sku.id}, ${sku.isDefault})" /></td>
            </tr>
            </c:forEach>
        </table>
        <input type = "submit" value = "修改" class='layui-btn layui-btn-sm'  />
    </form>
</body>
<script>

    /**
     * 修改库存
     */
    function updateCount() {
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
            title: '库存修改页面',
            shadeClose: true,
            shade: 0.8,
            area: ['450px', '80%'],
            //路径
            content: '<%=request.getContextPath()%>/product/count/toUpdate?id='+array
        });
    }

    //下架
    function setDownSku(id, isDefault) {
        if(isDefault == 1) {
            layer.alert("默认商品不可以下架");
            return;
        }
        layer.confirm("确认要将商品下架吗？", {icon: 3, title:'提示'}, function(index) {
            var index = layer.load();
            $.post(
                "<%=request.getContextPath()%>/product/sku/downSku",
                {"id":id},
                function (data) {
                    if (data.code != 200) {
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 2000, //2秒关闭（如果不配置，默认是3秒）
                            shade: 0.3
                        });
                        return;
                        layer.close(index);
                    }
                    layer.msg('操作成功', {
                        icon: 1,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade: 0.3
                    }, function () {
                        layer.close(index);
                        window.location.href = "<%=request.getContextPath()%>/product/toUpdate?id="+${product.id}
                    });
                })
        })
    }


    /**
     * 编辑
     */
    function writeSku() {
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
            title: '库存修改页面',
            shadeClose: true,
            shade: 0.8,
            area: ['450px', '80%'],
            //路径
            content: '<%=request.getContextPath()%>/product/sku/toUpdate?id='+array
        });
    }

    /**
     * 设为默认
     */
    function setSkuDefault() {
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
        var msg = "您确定要设置为";
        var status = "";
        if (sta == 0) {
            msg += "默认？";
            status = 1;
        }
        layer.confirm(msg, {icon: 3, title:'提示'}, function(index) {
            var index = layer.load();
            $.post(
                "<%=request.getContextPath()%>/product/sku/updateDefault",
                {"id":idArray,"isDefault":status},
                function (data) {
                    if (data.code != 200) {
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 2000, //2秒关闭（如果不配置，默认是3秒）
                            shade: 0.3
                        });
                        layer.close(index);
                        return;

                    }
                    layer.msg('操作成功', {
                        icon: 1,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade: 0.3
                    }, function () {
                        layer.close(index);
                        window.location.href = "<%=request.getContextPath()%>/product/toUpdate?id="+$("#productId").val()
                    });
                })
        })
    }
    //validate
    $(function () {
        $("#frm").validate({
            rules: {
                productName: {
                    required: true,
                },
                productExpressage: {
                    required: true,
                },
                productDescribe:{
                    required:true,
                },
            },
            errorPlacement: function (error, element) { //指定错误信息位置
                if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                    var eid = element.attr('name'); //获取元素的name属性
                    error.appendTo(element.parent()); //将错误信息添加当前元素的父结点后面
                } else {
                    error.insertAfter(element);
                }
            },
            messages: {
                productName: {
                    required: "请填写属性值"
                },
                productExpressage: {
                    required: "请选择邮费"
                },
                productDescribe:{
                    required:"请填写描述 但不宜过多"
                },
            }
        });
    });

    //修改商品
    $.validator.setDefaults({
        submitHandler: function () {
            var formData = new FormData($("#frm")[0]);
            var index = layer.load();
            //七牛雲上传图片只能ajax提交
            $.ajax({
                type: 'post',
                url:  "<%=request.getContextPath()%>/product/updateProduct",
                data:formData,
                /* data:$("#file").val(), */
                cache: false, //上传文件不需要缓存
                processData: false,//告诉jQuery不要去处理发送的数据
                contentType: false,//告诉jQuery不要去设置Content-Type请求
                success:function (data) {
                    if (data.code != 200) {
                        layer.msg(data.msg, {
                            icon: 5,
                            time: 2000,
                        });
                        layer.close(index); //关闭
                        return;
                    }
                    layer.msg('修改成功', {
                        icon: 6,
                        time: 2000
                    }, function(){
                        layer.close(index); //关闭
                        window.location.href = "<%=request.getContextPath()%>/product/toShowProduct";
                    });
                }
            });
        }
    });

</script>
</html>
