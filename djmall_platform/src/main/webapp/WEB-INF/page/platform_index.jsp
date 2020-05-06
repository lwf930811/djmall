<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/token.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" media="all">
</head>
<style type="text/css">
    * {
        font-size: 14px;
    }

    .input-val {
        background: #ffffff;
        padding: 0 2%;
        height: 48px;
        border-radius: 5px;
        border: none;
        border: 1px solid rgba(0, 0, 0, .2);
        font-size: 0.8625rem;
    }

    #zi{
        font-size: 25px;
    }
    a{
        text-decoration:none;
    }
    #d {
        filter: alpha(opacity=50);
        -moz-opacity: 0.2;
        -khtml-opacity: 0.2;
        height: 100%;
        width: 100%;
        position: absolute;
        z-index: 1;
        background: url(<%=request.getContextPath()%>/static.res/52.jpg);
        background-size:cover;
        opacity: 1;
    }
    #hide,#show{
        display: inline-block;
    }

    #fr label.error {
        color: Red;
        font-size: 13px;
        margin-left: 5px;
        padding-left: 16px;
        background: url("<%=request.getContextPath()%>/source/js/error.png") left no-repeat;
    }
    a{
        color: #A3C6C8;
    }
    .table{
        color: #c1c7c8;
    }
    img{
        width:50px;
        height:70px;
    }
    .fgh{
        height: 30px;
        width: 30px;
    }
</style>
<body>

       <ul class="layui-nav">
        <span id = "topShow">
            <li class="layui-nav-item">
                <button type="button" onclick="show()" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary">首页</button>
            </li> <li class="layui-nav-item noLogin" >
                <input type = "button" onclick = "login()" value = "登录" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" />
            </li>
            <li class="layui-nav-item noLogin">
                <input type = "button" onclick = "register()" value = "注册" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" />
            </li>
            <li class="layui-nav-item userLogin">
                <input type = "button" id="geren" onclick = "myHomePage()" value = "" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" />
            </li>

        </span>
    </ul>

<center>
    <form id = "frm">
               <input type="hidden" value="1" id="pageNo" name="page">
        商品名：<input type = "text" name = "productName"/><br/><br/>
        价格：<input type = "text" name = "minPrice"/>~<input type = "text" name = "maxPrice"/><br/><br/>
        分类：<c:forEach items="${prouctType}" var="prouctType">
                <input type = "radio" name = "productType" value = "${prouctType.code}"/>${prouctType.name}
            </c:forEach>
        <br/>
        <input type="button" value = "查询" onclick = "search()" class='layui-btn layui-btn-sm' />
    </form>
<table>
    <table class="layui-table" lay-skin="row" lay-size="lg" align="center">
        <colgroup>
            <col width="100">
            <col width="140">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>名称</th>
            <th>价格</th>
            <th>库存</th>
            <th>分类</th>
            <th>折扣</th>
            <th>邮费</th>
            <th>图片</th>
            <th>描述</th>
            <th>点赞</th>
        </tr>
        </thead>
        <tbody id = "tbd"></tbody>
    </table>
</table>

</center>
</body>
<script>

    /**
     *用户去登陆
     */
    function login() {
        layer.open({
            align:top,
            type: 2,
            title: '用户登录',
            shadeClose: false,
            shade: 0.8,
            area: ['450px', '50%'],
            content: "<%=request.getContextPath()%>/user/toLogin"
        });
    }

    //判断是否都登陆
    $(function () {
        if (check_login()) {
            var nickName = cookie.get("NICK_NAME");
            $(".noLogin").hide();
            $(".userLogin").show();
            $("#geren").val(nickName);
        }else {
            $(".noLogin").show();
            $(".userLogin").hide();
        }
    });

    /**
     * 注册用户
     */
    function register() {
        layer.open({
            align:top,
            type: 2,
            title: '用户注册',
            shadeClose: false,
            shade: 0.8,
            area: ['450px', '50%'],
            content: "<%=request.getContextPath()%>/user/toRegister"
        });
    }

    /**
     * 去个人主页
     */
    function myHomePage() {
        window.location.href = "<%=request.getContextPath()%>/user/toMyHomePage?TOKEN="+getToken();
    }

    /**
     * 页面加载事件
     */
    $(function() {
        search();
    })


    var arr = new Array();
    /**
     * 商品展示
     */
    function search() {
        $.post(
            "<%=request.getContextPath()%>/index/indexProductList",
            $("#frm").serialize(),
            function(data) {
                var html = "";
                for (var i =0; i<data.data.list.length; i++) {
                    var pro = data.data.list[i];
                    html += "<tr>";
                    html += "<td><a href = '<%=request.getContextPath()%>/index/productDetails?id="+pro.id+"'>"+pro.productName+"</a></td>";
                    html += "<td>"+pro.skuPrice+"</td>";
                    html += "<td>"+pro.skuCount+"</td>";
                    html += "<td>"+pro.productTypeName+"</td>";
                    html += "<td>"+pro.skuRate+"</td>";
                    html += "<td>"+pro.productExpressageShow+"</td>";
                    html += "<td><img src='"+pro.picture+"' /></td>";
                    html += "<td>"+pro.productDescribe+"</td>";
                    //html += "<td>"+pro[i].likeNumber+"</td>";
                    html += "<td><img class='fgh' id='img"+ pro.id + "' src='<%=request.getContextPath()%>/static/js/u694.png' onclick=toInsert('"+pro[i].id+"')>"+pro[i].likeNumber;
                    html += "</tr>";
                    arr.push(pro.id);
                }
                $("#tbd").html(html);
                var pageNo = $("#pageNo").val();
                var pageInfo = "<input type='button' value='上一页'  onclick='page("+(parseInt(pageNo)-1)+")'>"
                pageInfo +="<input type='button' value='下一页'  onclick='page("+(parseInt(pageNo)+1)+","+data.data.pages+")'>"
                $("#pageInfo").html(pageInfo)
            })
    }

    function page (page,total) {
        if (page < 1) {
            layer.msg("已经是首页了");
            return;
        }
        if (total < page) {
            layer.msg("已经是尾页了");
            return;
        }
        $("#pageNo").val(page);
        search();
    }




</script>
</html>