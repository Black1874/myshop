<%--
  Created by IntelliJ IDEA.
  User: YURENCHEN
  Date: 2018/10/23
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>MyShop——登录</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/ziy.css">
    <!--  <script src="js/jquery-1.11.3.min.js" ></script>
    <script src="js/index.js" ></script>  -->
    <!-- <script type="text/javascript" src="js/jquery1.42.min.js"></script> -->
    <!--
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
     <script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.source.js"></script> -->

</head>
<body>
<!--dengl-->
<div class="beij_center">
    <div class="dengl_logo">
        <img src="images/logo_1.png">
        <h1>欢迎登录</h1>
    </div>
</div>
<div class="dengl_beij">

    <div class="banner_xin">
        <img src="images/ss.jpg">
    </div>
    <div class="beij_center dengl_jvz">
        <form action="/frontLogin">
        <div class="login_form">
            <div class="login_tab">

                <h2>欢迎登录 ${result.message}</h2>

                <div class="dengl_erwm">
                    <a href="#"><img src="images/er_wm.png"></a>
                    <div class="tanc_erwm_kuang">
                        <img src="images/mb_wangid.png">
                        <div class="qrcode_panel">
                            <ul>
                                <li class="fore1">
                                    <span>打开</span>
                                    <a href="#" target="_blank"> <span class="red">手机MyShop</span></a>
                                </li>
                                <li>扫描二维码</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="kengl_kuang">
                <div class="txt_kuang">
                    <input type="text" class="itxt"  name="email" placeholder="邮箱/用户名/已验证手机">
                    <input type="text" class="itxt"  name="password" placeholder="密码">
                </div>
                <div class="remember">
                    <%--<div class="fl">--%>
                    <%--<input type="checkbox" >--%>
                    <%--<label for="autoLoginFlag">自动登录</label>--%>
                    <%--</div>--%>
                    <div class="fr">
                        <a href="#" class="fl" target="_blank" title="忘记密码">忘记密码?</a>
                    </div>
                </div>
                <input type="submit" tabindex="5" value="登 录" class="btnnuw">
            </div>
            <div class="corp_login">
                <div class="mingb_shoq"><a href="#">名榜授权登录！</a></div>
                <div class="regist_link"><a href="/getRegisterForm" target="_blank"><b></b>立即注册</a></div>
            </div>
        </div>
        </form>
    </div>
</div>


<div class="jianj_dib">
    <div class="beia_hao">
        <p>京ICP备：123456789号  </p>
        <p class="gonga_bei">京公网安备：123456789号</p>
    </div>
</div>

</body>

