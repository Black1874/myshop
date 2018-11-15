<%--
  Created by IntelliJ IDEA.
  User: YURENCHEN
  Date: 2018/10/17
  Time: 7:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
<title>用户管理</title>
<body>
<jsp:include page="/WEB-INF/views/includes/head-nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/includes/side-nav.jsp"></jsp:include>
<div class="content-wrapper">
    <section class="content">
        <%--标题--%>
        <div class="row">
            <h3 style="padding-left: 15px">${result.data.id eq null ? "新建用户" : "修改用户"}</h3>
        </div>
        <%--表单--%>
        <div class="row">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <c:if test="${result.message !=null && result.message!=''}">
                    <c:if test="${result.status == 500}">
                        <div class="alert alert-danger alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${result.message}
                        </div>
                    </c:if>
                    <c:if test="${result.status == 200}">
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${result.message}
                        </div>
                    </c:if>
                    </c:if>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <c:choose>
                <c:when test="${result.data==null}">
                <form role="form" id="userForm" action="/user/saveUser" method="post">

                    </c:when>
                    <c:otherwise>
                    <form role="form" id="userForm" action="/user/updateUser" method="post">
                        </c:otherwise>
                        </c:choose>
                        <div class="box-body">
                            <div class="form-group">
                                <label for="username">username</label>
                                <%--必需 最小长度2 最大长度 10--%>
                                <input type="type" class="form-control " id="username" name="username"
                                       placeholder="username" required minlength="2" maxlength="10"
                                       value="${result.data.username}">
                            </div>
                            <div class="form-group">
                                <label for="email">Email address</label>
                                <%-- 必需 email检验--%>
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="Enter email"
                                       value="${result.data.email}" required email>
                            </div>


                                <c:if test="${result.data==null}">
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                            <%--必需 最小长度2 最大长度 10--%>
                                        <input type="password" class="form-control" id="password" name="password"
                                               value="${result.data.password}"
                                               placeholder="Password" required minlength="2" maxlength="10">
                                    </div>

                                </c:if>


                            <div class="form-group">
                                <label for="phone">phone</label>
                                <%--必需 调用自定义方法isMobile--%>
                                <input type="type" class="form-control" id="phone" name="phone" placeholder="phone"
                                       required
                                       isMobile="true" value="${result.data.phone}">
                            </div>

                            <%--created--%>
                            <div class="form-group">
                                <input type="hidden" name="created" class="form-control" id="created"
                                       placeholder="created"
                                       value="${result.data.created}">
                            </div>

                            <%--created--%>
                            <div class="form-group">
                                <input type="hidden" name="updated" class="form-control" id="updated"
                                       placeholder="updated"
                                       value="${result.data.updated}">
                            </div>
                            <%--id--%>
                            <div class="form-group">
                                <input type="hidden" name="id" class="form-control" id="id" placeholder="id"
                                       value="${result.data.id}">
                            </div>

                        </div>

                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary pull-right" id="submit">Submit</button>
                            <a type="button" class="btn btn-primary" id="back" onclick="history.go(-1)">back</a>
                        </div>
                    </form>

            </div>
        </div>

    </section>

</div>

<jsp:include page="/WEB-INF/views/includes/copyRight.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
<script src="/static/plugins/jqueryValidator/method-validate.js"></script>
<script src="/static/plugins/jqueryValidator/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">


    $(function () {

        $("#submit").click(function () {
            $("#created").val(new Date());
            $("#updated").val(new Date());
        });
        $("#userForm").validate();
    })


</script>
</body>
</html>


