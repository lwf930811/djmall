<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/layer/layui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/token.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/productDetails/modernizr-custom-v2.7.1.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/productDetails/shouye.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/layer/css/layui.css" media="all">
</head>
<style type="text/css">
    #category {
        height: 50px;
        float: left;
        margin-top: 8px;
        margin-left: 30px
    }
</style>
<body class="layui-layout-body">
<ul class="layui-nav" style="float: right">
        <span id = "topShow">
            <li class="layui-nav-item">
                <button type="button" onclick="show()" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary">首页</button>
            </li> <li class="layui-nav-item noLogin" >
                <input type = "button" onclick = "loginUser()" value = "登录" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" />
            </li>
            <li class="layui-nav-item noLogin">
                <input type = "button" onclick = "addUser()" value = "注册" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-primary" />
            </li>
        </span>
</ul>

<input type = "hidden" name = "productId" value = "${product.id}"/>
<div class="Xcontent">
    <ul class="Xcontent01">
        <div class="Xcontent06"><img src="${product.picture}" width="390px" height="400px"></div>
        <ol class="Xcontent13">
            <div class="Xcontent14"><a href="#"><p>${product.productName}</p></a></div>
            <div class="Xcontent16"><p>${product.productDescribe}</p></div>
            <div class="Xcontent17">
                <p class="Xcontent18">原价</p>
                <p class="Xcontent19">￥<span class="price">${product.skuPrice}</span></p>
                <input type = "hidden" id = "skuCount" value = "${product.skuCount}" />
                <div class="Xcontent20">
                    <p class="Xcontent21">折扣</p>
                    <p class="Xcontent22">
                        <c:choose>
                            <c:when test="${product.skuRate == 0}">
                                无
                            </c:when>
                            <c:otherwise>
                                ${product.skuRate}%
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <div class="Xcontent23">
                    <p class="Xcontent24">邮费</p>
                    <p class="Xcontent25">
                        <c:choose>
                            <c:otherwise>
                                ${product.productExpressageShow}
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>
            <div class="Xcontent26">
                <p class="Xcontent27">类别</p>
                <div class="layui-input-block" id="category">
                    <form id="typeFom" class="layui-form" action="">
                        <br>
                        <c:forEach var="list" items="${sku}" varStatus="i">
                            <input type="radio" name="skuId" title="${list.skuAttrValueNames}"
                                   value="${list.id}" lay-filter="sku"
                            <c:if test="${list.skuCount == 0}">
                                   disabled
                            </c:if>
                            <c:if test="${i.index == 0}">
                                   checked
                            </c:if>
                            >
                            <c:if test="${(i.index+1)%3 == 0}">
                                <br>
                            </c:if>
                        </c:forEach>
                    </form>
                </div>
            </div>
            <div class="Xcontent30">
                <p class="Xcontent31">数量</p>
                <div class="Xcontent32"><img src="<%=request.getContextPath()%>/res/productDetails/images/X15.png">
                </div>
                <form id="numFom">
                    <input class="input" value="1">
                </form>
                <div class="Xcontent33"><img src="<%=request.getContextPath()%>/res/productDetails/images/16.png"></div>
            </div>
            <div class="Xcontent34"><a href="#"><img
                    src="<%=request.getContextPath()%>/res/productDetails/images/X17.png" onclick="buy()"></a></div>
            <div class="Xcontent35"><a href="#"><img
                    src="<%=request.getContextPath()%>/res/productDetails/images/X18.png" onclick="addCart()"></a></div>
        </ol>
    </ul>
</div>
<script>
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

    function show() {
        window.location.href="<%=request.getContextPath()%>/index/toIndex";
    }

    /**
     *用户去登陆
     */
    function loginUser() {
        layer.open({
            align: top,
            type: 2,
            title: '用户登录',
            shadeClose: false,
            shade: 0.8,
            area: ['450px', '50%'],
            content: "<%=request.getContextPath()%>/user/toLogin"
        });
    }

    /**
     * 注册用户
     */
    function addUser() {
        layer.open({
            align:top,
            type: 2,
            title: '用户注册',
            shadeClose: false,
            shade: 0.8,
            area: ['450px', '50%'],
            content: "<%=request.getContextPath()%>/user/register"
        });
    }

    /**
     * 去个人主页
     */
    function myHomePage() {
        window.location.href = "<%=request.getContextPath()%>/user/toMyHomePage?TOKEN="+getToken();
    }


</script>
</body>

</html>
