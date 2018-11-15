<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sys" uri="/WEB-INF/myTags/pageTag.tld" %>
<html>
<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
<title>MY-SHOP | USERLIST</title>
<body>

<div class="wrapper">
    <jsp:include page="/WEB-INF/views/includes/head-nav.jsp"></jsp:include>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="/WEB-INF/views/includes/side-nav.jsp"></jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <div class="box">
            <div class="box-header with-border">
                <h3 class="box-title">目录列表</h3>
                <%--<jsp:include page="/WEB-INF/views/includes/notice.jsp"></jsp:include>--%>
                <c:if test="${result.message !=null && result.message!=''}">
                    <c:if test="${result.status eq 500}">
                        <div class="alert alert-danger alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${result.message}
                        </div>
                    </c:if>
                    <c:if test="${result.status eq 200}">
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${result.message}
                        </div>
                    </c:if>
                </c:if>
            </div>
            <!-- /.box-header -->
            <div class=" box-body table-responsive no-padding">
                <%--条件搜索框--%>
                <div class="box box-danger">

                    <div class="box-body">
                        <%--操作--%>
                        <div class="row" style="padding-left: 20px">
                            <div class="col-xs-4">
                                <a type="btn" href="/category/getForm" class="btn btn-default btn-sm fa fa-plus">新建</a>&nbsp;&nbsp;&nbsp;

                                <a type="btn" href="#" class="btn btn-default btn-sm fa fa-trash">删除</a>&nbsp;&nbsp;&nbsp;

                                <a type="btn" href="#" class="btn btn-default btn-sm fa fa-download">导入</a>&nbsp;&nbsp;&nbsp;

                                <a type="btn" href="#" class="btn btn-default btn-sm fa fa-upload">导出</a>&nbsp;&nbsp;&nbsp;
                            </div>

                            <div class="col-xs-8">
                                <form action="/category/getCategoryPage" method="post">
                                    <div class="col-xs-2">
                                        高级搜索
                                    </div>

                                    <div class="col-xs-2">
                                        <input type="text" name="name" class="form-control" placeholder="name">
                                    </div>

                                    <div class="col-xs-4">
                                        <button type="submit" class="btn btn-default btn-default fa fa-search"></button>
                                    </div>
                                    <%--搜索图标--%>
                                </form>
                            </div>

                        </div>

                    </div>
                </div>
                <%--表单--%>
                <table class="table table-bordered  table-hover">
                    <tr>
                        <th style="width: 10px">#</th>
                        <th>名称</th>
                        <th>父类ID</th>
                        <th>父类名称</th>
                        <th style="width: 40px">创建时间</th>
                        <th style="width: 40px">修改时间</th>
                        <th style="width: 40px">操作</th>
                    </tr>
                    <c:set var="list" value="${result.data.pageResult.list}"></c:set>
                    <c:set var="page" value="${result.data.pageResult}"></c:set>
                    <c:forEach items="${list}" var="category" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td>${category.name}</td>
                            <td>${category.parent.id}</td>
                            <td>${category.parent.name}</td>
                            <td><fmt:formatDate value="${category.created}"
                                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                            <td><fmt:formatDate value="${category.updated}"
                                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                            <td>


                                <a type="button" href="/category/getForm?id=${category.id}"
                                   class="btn btn-info btn-xs fa fa-edit"> Edit</a>
                                <a type="button" href="/category/deleteCategory?id=${category.id}"
                                   class="btn btn-warning btn-xs fa fa-trash"> Delete</a>

                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix">
                <ul class="pagination pagination-sm no-margin pull-right">
                    <input type="hidden" name="current" id="current" value="${page.current}"/>
                    <input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
                    <sys:page count="${page.count}" current="${page.current}"
                              countPerPage="${page.pageSize}"></sys:page>
                    <%--<li><a href="#">&laquo;</a></li>--%>
                    <%--<li><a href="#">1</a></li>--%>
                    <%--<li><a href="#">2</a></li>--%>
                    <%--<li><a href="#">3</a></li>--%>
                    <%--<li><a href="#">&raquo;</a></li>--%>
                </ul>
            </div>
        </div>
    </div>

    <!-- /.content-wrapper -->
    <jsp:include page="/WEB-INF/views/includes/copyRight.jsp"></jsp:include>

</div>

<%--javascript:page--%>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

<script>

    function page(current) {
        var pageSize = $("#pageSize").val();
        window.location.href = "/category/getCategoryPage?current=" + current + "&pageSize=" + pageSize;
    }


</script>

</body>
</html>

