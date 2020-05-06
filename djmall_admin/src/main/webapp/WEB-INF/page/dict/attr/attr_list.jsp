
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
<center>
    <form id = "frm">
        属性名：<input type = "text" name = "attrName" id = "attrName" />
        <input type = "submit" value = "新增商品属性" />
    </form>
    <table  border="1px">
        <tr>
            <th>编号</th>
            <th>属性名</th>
            <th>属性值</th>
            <th>操作</th>
        </tr>
        <tbody id = "tb"></tbody>
    </table><br/>
</center>
</body>
<script>
    $(function() {
        show();
        $("#frm").validate({
            rules: {
                attrName: {
                    //验证属性名是否存在
                    required:true,
                    remote:{
                        //ajax请求方式（get/post）
                        type:"post",
                        //请求路径
                        url:"<%=request.getContextPath()%>/dict/attr/findAttrName",
                        //请求参数
                        data:{
                            attrName:function(){
                                return $("#attrName").val();
                            }
                        },
                        //返回值类型
                        dataType:"json",
                        //预处理返回值的函数
                        dataFilter:function(data, type) {
                            if(data == 'true') {
                                return true;
                            }else {
                                return false;
                            }
                        }
                    },
                },
            },
            errorElement: "span",
            messages: {
                attrName: {
                    required:"输入不能为空",
                    remote:"属性名已重复"
                },
            },
            submitHandler: function () {
                var index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.post(
                    "<%=request.getContextPath()%>/dict/attr/add",
                    $("#frm").serialize(),
                    function(data) {
                        layer.close(index);
                        if(data.code != 200) {
                            layer.alert(data.msg, {icon: 5});
                            return;
                        }
                        layer.alert('添加成功', {icon: 6});
                        show();
                    }

                )
            }
        })
    })

    function show() {
        $.post(
            "<%=request.getContextPath()%>/dict/attr/list",
            {},
            function(data) {
                var html = "";
                for(var i=0; i<data.data.length; i++) {
                    var attr = data.data[i];
                    html += "<tr>";
                    html += "<td>"+attr.attrId+"</td>";
                    html += "<td>"+attr.attrName+"</td>";
                    html += "<td>"+attr.attrValues+"</td>";
                    html += "<td><input type = 'button' value = '关联属性值' onclick = 'attrAndValue("+attr.attrId+")' /></td>";
                    html += "</tr>";
                }
                $("#tb").html(html);
            }
        )
    }

    /**
     * 关联属性值
     */

    function attrAndValue(id) {
        window.location.href =  "<%=request.getContextPath()%>/dict/value/toList?id="+id
    }
</script>
</html>
