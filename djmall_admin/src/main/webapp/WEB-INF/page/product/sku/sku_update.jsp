<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/static/validate/messages_zh.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css">
<body>

<form id = "frm">
    <center>
    <input type = "hidden" name = "id" value = "${sku.id}"/>
        SKU属性：<input type = "text" value = "${sku.skuAttrNames}" name="skuAttrValueNames" readonly="true" /><br/><br/>
        库存：<input type="text" name = "skuCount" value = "${sku.skuCount}"/><br/><br/>
        价格：<input type = "text" name = "skuPrice" value = "${sku.skuPrice}" /><br/><br/>
        折扣：<input type = "text" name = "skuRate" value = "${sku.skuRate}" /><br/><br/>
    <input type = "submit" value = "保存" />
    </center>
</form>
</body>
<script>
    //validate
    $(function () {
        $("#frm").validate({
            rules: {
                skuCount: {
                    required: true,
                },
                skuPrice: {
                    required: true,
                },
                skuRate: {
                    required: true,
                },
            },
            messages: {
                skuCount: {
                    required: "请填写库存"
                },
                skuPrice: {
                    required: "请填写价格"
                },
                skuRate: {
                    required: "请填写折扣"
                },
            },
            errorElement: "span",

        });
    });

    //修改Sku库存
    $.validator.setDefaults({
        submitHandler: function () {
            var index = layer.load();
            $.post(
                "<%=request.getContextPath()%>/product/sku/updateSku",
                $("#frm").serialize(),
                function(data) {
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
                        parent.location.href = "<%=request.getContextPath()%>/product/toUpdate?id="+${sku.productId};
                    });
                }
            )
        }
    });

</script>
</html>
