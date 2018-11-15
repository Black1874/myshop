<%--
  Created by IntelliJ IDEA.
  User: YURENCHEN
  Date: 2018/10/17
  Time: 7:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

                <form:form action="/test/dispatcher"  id="userForm" method="post" modelAttribute="user">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="username">username</label>
                                <form:input path="username" class="form-control "  placeholder="username" ></form:input>
                            </div>
                            <div class="form-group">
                                <form:hidden path="id" cssClass="form-control " ></form:hidden>
                            </div>
                            <div class="form-group">
                                <label for="email">Email address</label>
                                <form:input path="email" cssClass="form-control " ></form:input>
                            </div>
                            <div class="form-group">
                                <label for="phone">phone</label>
                                <form:input path="phone" cssClass="form-control "></form:input>
                            </div>
                            <input type="submit" value="提交">

                        </form:form>

            </div>
        </div>
        </div>
    </section>

</div>

<jsp:include page="/WEB-INF/views/includes/copyRight.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
<script src="/static/plugins/jqueryValidator/method-validate.js"></script>
<script src="/static/plugins/jqueryValidator/Validform_v5.3.2_min.js"></script>
<script src="static/plugins/jqueryValidator/messages_zh.js"></script>
<script type="text/javascript">
    $(function(){
        $("#userForm").validate(
            {
                errorElement : "span",
                errorClass :"help-block",
                errorPlacement : function(error,element){
                    element.parent().parent().attr("class","form-group has-error");
                    error.insertAfter(element);
                }
            }
        );
    })



</script>
</body>
</html>



