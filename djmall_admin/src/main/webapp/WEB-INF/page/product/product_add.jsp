<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        名称：<input type = "text" name = "productName" /><br/><br/>
        邮费：
        <select name = "productExpressage">
            <option value = "">请选择</option>
            <c:forEach items="${expressageList}" var="expressage">
                <option value="${expressage.expressId}">
                        ${expressage.logisticsCompanyName}-${expressage.expressageName}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        描述：<textarea rows="4px" cols="50px" name="productDescribe"></textarea><br/><br/>
        图片<input type = "file" name = "file"/><br/><br/>
        <input type = "hidden" name = "status" value="1" />
        分类：<select name = "productType" onchange="findAttr(this.value)" >
                <option value = "">请选择</option>
                <c:forEach items="${productTypeList}" var="productTypes" >
                    <option value = "${productTypes.code}">${productTypes.name}</option>
                </c:forEach>
            </select><br/><br/>
        SKU：<input type = "button" value = "+"  class='layui-btn layui-btn-sm' onclick="addCustomAttr()"/>&nbsp;&nbsp;
        <input type = "button" value = "生成SKU" onclick = "createSku()" class='layui-btn layui-btn-sm' />
        <br/><br/>
        <table class="layui-table" lay-skin="row" lay-size="lg" align="center">
            <thead>
            <tr>
                <th>属性名</th>
                <th>属性值</th>
            </tr>
            </thead>
            <tbody id = "tbd"></tbody>
        </table>
        <table class="layui-table" lay-skin="row" lay-size="lg" align="center">
            <thead>
            <tr>
                <th>编号</th>
                <th>SKU属性</th>
                <th>库存</th>
                <th>价格(元)</th>
                <th>折扣(%)</th>
                <th></th>
            </tr>
            </thead>
            <tbody id = "skuAttr"></tbody>
        </table>
        <input type = "submit" value = "添加" class='layui-btn layui-btn-sm'  />
    </form>
</body>
<script>
// 获取属性及属性对应的属性值
    function findAttr(result) {
        $.post(
            "<%=request.getContextPath()%>/product/findAttrAndValues",
            {"productType":result},
            function(data) {
                var html = "";
                var attrList = data.data;
                for(var i=0; i<attrList.length; i++) {
                    var attr = attrList[i];
                    html += "<tr>";
                    html += "<td><input type='hidden' value='"+attr.attrId+","+attr.attrName+"' />"+attr.attrName+"</td>";
                    html += "<td>";
                    var attrValueIdList = attr.attrValueIds.split(",");
                    var attrValueList = attr.attrValues.split(",");
                    for(var j=0; j<attrValueIdList.length; j++) {
                        var valueId = attrValueIdList[j];
                        html += "<input type = 'checkbox' value = '"+valueId+","+attrValueList[j]+"'/>" + attrValueList[j]
                    }
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tbd").html(html);
            }
        )
    }
//    添加自定义属性
    function addCustomAttr() {
        var content ="属性名:<input type = 'text' id='cAttrName'/></br>";
            content +="属性值:<input type = 'text' id='cAttrValue'/>";
            content +="(多个值之间,分割)";
        layer.open({
            type: 1,
            title:'自定义属性',
            shade: 0.2,
            shadeClose: true,
            area: ['60%', '80%'],
            content: content,
            btn: ['确定','取消'],
            yes: function(index, layero){
               var html = "<tr>";
                html += "<td>" + $("#cAttrName").val();
                html += "<input type = 'hidden' value='-1'>";
                html += "<input type = 'hidden' value='" + $("#cAttrName").val()+"'>";
                html += "</td>";
                html += "<td>";
                var values = $("#cAttrValue").val().split(",")
                for(var i =0; i < values.length; i++) {
                    html += "<input type = 'checkbox' value = '-1,"+values[i]+"'/>" + values[i];
                }
                html += "</td>";
                html += "</tr>";
                $("#tbd").append(html);
                //最后关闭弹出层
                layer.close(index);
            }
        });
    }
// 生成sku
    function createSku() {
        //声明数组
        //属性值数组
        var sku = new Array();
        //属性值id数组
        var skuId = new Array();
        //属性id数组
        var attrIds = new Array();
        //属性名数组
        var attrNames = new Array();
        //获取所有的属性值
        var $trs = $("#tbd tr");
        for (var i = 0; i < $trs.length; i++){
            //获取td中的对象
            var $td = $($trs[i]).find("td")[1];
            //获取被选中对象的值
            var checkedAttrValues = $($td).find(":checked");
            if ( checkedAttrValues.length > 0){
                var $tds = $($trs[i]).find("td");
                attrIds.push(($($($tds[0]).find(":input")).val().split(","))[0]);
                attrNames.push(($($($tds[0]).find(":input")).val().split(","))[1]);
               var attrValueIds = new Array();
               var attrValues = new Array();
               for (var j =0;j < checkedAttrValues.length; j++){
                   attrValueIds.push(checkedAttrValues[j].value.split(",")[0]);
                   attrValues.push(checkedAttrValues[j].value.split(",")[1]);
               }
                skuId.push(attrValueIds);
                sku.push(attrValues);
            }
        }
        var skuIdList = dkej(skuId);
        var skuValueList = dkej(sku);
        var html = "";
        for(var i = 0; i < skuIdList.length; i++){
            html += "<tr id ='tr_"+i+"'>";
            html += "<td>"+(i+1)+"</td>";
            html += "<td>"+skuValueList[i]+"</td>";
            html += "<td><input type='text' name='skuList["+i+"].skuCount' value='10'/></td>";
            html += "<td><input type='text' name='skuList["+i+"].skuPrice' value='10'/></td>";
            html += "<td>";
            html += "<input type='text' name ='skuList["+i+"].skuRate' value='10'/>";
            html += "<input type = 'hidden'  name='skuList["+i+"].skuAttrIds' value ='"+attrIds+"'>";
            html += "<input type = 'hidden'  name='skuList["+i+"].skuAttrNames' value = '"+attrNames+"'>";
            html += "<input type = 'hidden'  name='skuList["+i+"].skuAttrValueIds' value='"+skuIdList[i]+"'>";
            html+= "<input type = 'hidden'  name='skuList["+i+"].skuAttrValueNames' value='"+skuValueList[i]+"'>";
            html += "</td>";
            html += "<td><input type='button' value='移除' onclick='removeSku("+i+")' class='layui-btn layui-btn-sm' /></td>";
            html += "</tr>";
        }
        $("#skuAttr").html(html);
    }
    // 笛卡尔积算法 二维数组
    // 参数d格式 arr = [["蓝色", "红色"],["S", "M"], ["1", "2"]];

    // ys[] = ["蓝色","红色"]
    // cc[] = ["S","M"]
    // zdy[] = ["1","2"]
    // d[] = [ys, cc zdy]
    function dkej(d) {
        var total = 1;
        for (var i = 0; i < d.length; i++) {
            total *= d[i].length;
        }
        var e = [];
        var itemLoopNum = 1;
        var loopPerItem = 1;
        var now = 1;
        for (var i = 0; i < d.length; i++) {
            now *= d[i].length;
            var index = 0;
            var currentSize = d[i].length;
            itemLoopNum = total / now;
            loopPerItem = total / (itemLoopNum * currentSize);
            var myIndex = 0;
            for (var j = 0; j < d[i].length; j++) {
                for (var z = 0; z < loopPerItem; z++) {
                    if (myIndex == d[i].length) {
                        myIndex = 0;
                    }
                    for (var k = 0; k < itemLoopNum; k++) {
                        e[index] = (e[index] == null ? "" : e[index] + ":") + d[i][myIndex];
                        index++;
                    }
                    myIndex++
                }
            }
        }
        return e;
    }

    //移除sku
    function removeSku(id) {
        $("#tr_"+id).remove();
    }


    //新增商品
    $.validator.setDefaults({
        submitHandler: function () {
            var formData = new FormData($("#frm")[0]);
            var index = layer.load();
            //七牛雲上传图片只能ajax提交
            $.ajax({
                type: 'post',
                url:  "<%=request.getContextPath()%>/product/addProduct",
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
                    layer.msg('添加成功', {
                        icon: 6,
                        time: 2000
                    }, function(){
                        layer.close(index); //关闭
                        window.location.href = "<%=request.getContextPath()%>/product/toList";
                    });
                }
            });
        }
    });

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
                productType:{
                    required:true,
                },
                file:{
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
                productType:{
                    required:"请填选择商品类型"
                },
                file:{
                    required:"请上传图片"
                },
            }
        });
    });
</script>
</html>
