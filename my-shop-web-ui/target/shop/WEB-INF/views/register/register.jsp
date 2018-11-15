<%--
  Created by IntelliJ IDEA.
  User: YURENCHEN
  Date: 2018/10/25
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>MyShop——个人注册</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/ziy.css">
    <script src="js/jquery-1.11.3.min.js" ></script>
    <%--<script src="js/index.js" ></script> --%>
    <!-- <script type="text/javascript" src="js/jquery1.42.min.js"></script> -->
    <!--
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
     <script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.source.js"></script> -->

</head>
<body>
<!--dengl-->
<div class="yiny">
    <div class="beij_center">
        <div class="dengl_logo">
            <img src="images/logo_1.png">
            <h1>欢迎注册</h1>
        </div>
    </div>
</div>
<div class="beij_center">
    <div class="ger_zhuc_beij">
        <div class="ger_zhuc_biaot">
            <ul>
                <li class="ger_border_color"><a href="zhuc.html">个人注册</a></li>
                <i>丨</i>
                <li><a href="shenq_ruz.html">申请入驻</a></li>
                <p>我已经注册，现在就<a class="ftx-05 ml10" href="/getLoginForm">登录</a></p>
            </ul>
        </div>

        <form action="/registerUser" method="post" id="MyForm">

        <div class="zhuc_biaod">
            <div class="reg-items" style="color:red;font-size: 20px">
                ${result.message}
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="username">用户名：</label>
              	</span>
                <input   class="i-text" type="text" name="username" id="username">
                <!--备注————display使用 inline-block-->
                <div class="msg-box">
                    <div class="msg-weak err-tips"  style="display: none;"></div>
                </div>
                    <span class="suc-icon" style="display: none"></span>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="password">设置密码：</label>
              	</span>
                <input   class="i-text" type="text" name="password" id="password">
                <!--备注————display使用 inline-block-->
                <div class="msg-box">
                    <div class="msg-weak err-tips"  style="display:none;" ><div>请输入密码</div></div>
                </div>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="repassword">确认密码：</label>
              	</span>
                <input   class="i-text" type="text" id="repassword" name="repassword" >
                <!--备注————display使用 inline-block-->
                <div class="msg-box">
                    <div class="msg-weak err-tips"  style="display: none;"><div>密码不一样</div></div>
                </div>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="email">邮箱：</label>
              	</span>
                <input   class="i-text" type="text" name="email" id="email">
                <!--备注————display使用 inline-block-->
                <div class="msg-box">
                    <div class="msg-weak err-tips"  style="display:none;"></div></div>
                </div>
                <span class="suc-icon" style="display: none"></span>
            </div>

            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="phone">手机号码：</label>
              	</span>
                <input   class="i-text" type="text" name="phone" id="phone">
            <!--备注————display使用 inline-block-->
            <div class="msg-box">
                <div class="msg-weak err-tips"  style="display:none;"><div></div></div>
            </div>
                <span class="suc-icon" style="display: none"></span>
        </div>
            <%--验证码--%>
            <div class="reg-items">
            <span class="reg-label">
                <label for="vcode"> 验证码：</label>
            </span>
                    <input class="i-text i-short" type="text" name="vcode" id="vcode">
                    <img class="i-text i-short" src="http://localhost:8082/kaptcha" id="vcodeImg" alt="加载失败!">
                <%--<!--备注————display使用 inline-block-->--%>
                <div class="msg-box">
                    <div class="msg-weak err-tips" style="display:none;">
                        <div>请输入短信验证码</div>
                    </div>
                </div>
            </div>
            <%--<div class="reg-items" >--%>
              	<%--<span class="reg-label">--%>
                	<%--&lt;%&ndash;<label for="J_Name">手机号码：</label>&ndash;%&gt;--%>
              	<%--</span>--%>
                <%--<input   class="i-text i-short" type="text">--%>
                <%--<div class="check check-border" style="position:relative;left:0">--%>
                    <%--<a class="check-phone" style="padding:11px 10px 14px 10px;*line-height:60px;">获取短信验证码</a>--%>
                    <%--<span class="check-phone disable" style="display: none;"><em>60</em>秒后重新获取</span>--%>
                    <%--<a class="check-phone" style="display: none;padding:11px 10px 14px 10px">重新获取验证码</a>--%>
                <%--</div>--%>
                <%--<!--备注————display使用 inline-block-->--%>
                <%--<div class="msg-box">--%>
                    <%--<div class="msg-weak err-tips"  style="display:none;"><div>请输入短信验证码</div></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="reg-items" >
              	<span class="reg-label">
                	<%--<label for="J_Name"> </label>--%>
              	</span>
                <div class="dag_biaod">
                    <input type="checkbox" value="english" >
                    阅读并同意
                    <a href="#" class="ftx-05 ml10">《MyShop用户注册协议》</a>
                    <a href="#" class="ftx-05 ml10">《隐私协议》</a>
                </div>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<%--<label for="J_Name"> </label>--%>
              	</span>
                <input class="reg-btn" value="立即注册" type="button" id="subBtn">
            </div>
        </div>
        </form>
        <div class="xiaogg">
            <img src="images/cdsgfd.jpg">
        </div>
    </div>
</div>


<div class="jianj_dib jianj_dib_1">
    <div class="beia_hao">
        <p>京ICP备：123456789号  </p>
        <p class="gonga_bei">京公网安备：123456789号</p>
    </div>
</div>
<script type="text/javascript">
    <%--验证码刷新--%>
    $(function(){

        $("#vcodeImg").click(function(){
            var date1 = new Date();
            var s = date1.toString();
            $(this).attr("src","http://localhost:8082/kaptcha?a="+s);
        });

    //    校验数据
        $("#username").blur(function(){
            $.post(
                '/checkForm',
                {'username': $("#username").val()},
                function(data){
                    //失败,显示失败的原因
                    debugger;
                    if(data.status==500){
                        $("#username+.msg-box .err-tips").html(data.message);
                        $("#username+.msg-box .err-tips").css("display","inline-block");
                        $("#username~ .suc-icon").css("display","none");
                    }else{
                        //成功,显示正确图标
                        $("#username+.msg-box .err-tips").css("display","none");
                        $("#username~.suc-icon").css("display","inline-block");
                    }
                },"json"

            );
        });
        $("#email").blur(function(){
            $.post(
                '/checkForm',
                {'email': $("#email").val()},
                function(data){
                    //失败,显示失败的原因
                    debugger;
                    if(data.status==500){
                        $("#email+.msg-box .err-tips").html(data.message);
                        $("#email+.msg-box .err-tips").css("display","inline-block");
                        $("#email~ .suc-icon").css("display","none");
                    }else{
                        //成功,显示正确图标
                        $("#email+.msg-box .err-tips").css("display","none");
                        $("#email~.suc-icon").css("display","inline-block");
                    }
                },"json"

            );
        });
        $("#phone").blur(function(){
            $.post(
                '/checkForm',
                {'phone': $("#phone").val()},
                function(data){
                    //失败,显示失败的原因
                    debugger;
                    if(data.status==500){
                        $("#phone+.msg-box .err-tips").html(data.message);
                        $("#phone+.msg-box .err-tips").css("display","inline-block");
                        $("#phone~ .suc-icon").css("display","none");
                    }else{
                        //成功,显示正确图标
                        $("#phone+.msg-box .err-tips").css("display","none");
                        $("#phone~.suc-icon").css("display","inline-block");
                    }
                },"json"

            );
        });
        $("#subBtn").click(function(){
            var status=true;
           $(".err-tips").each(function(){
               alert($(this).css("display"));
               if($(this).css("display")!="none"){
                    status=flase;
               };
           });
           if(status==true){
               $("#MyForm").submit();
           }else{
               return;
           }
        });
    })




</script>

</body>
</html>

