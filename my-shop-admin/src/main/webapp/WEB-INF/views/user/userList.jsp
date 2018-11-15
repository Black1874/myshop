<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sys" uri="/WEB-INF/myTags/pageTag.tld" %>
<html>
<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
<link rel="stylesheet" href="/static/plugins/adminLTE/plugins/iCheck/all.css" type="text/css">

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
                <h3 class="box-title">用户列表</h3>
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
                                <a type="btn" href="/user/getForm" class="btn btn-default btn-sm fa fa-plus">新建</a>&nbsp;&nbsp;&nbsp;

                                <button type="btn"  id="deleteMutil" class="btn btn-default btn-sm fa fa-trash">删除</button>&nbsp;&nbsp;&nbsp;

                                <a type="btn" href="#" class="btn btn-default btn-sm fa fa-download">导入</a>&nbsp;&nbsp;&nbsp;

                                <a type="btn" href="#" class="btn btn-default btn-sm fa fa-upload">导出</a>&nbsp;&nbsp;&nbsp;
                            </div>

                            <div class="col-xs-8">
                                <form action="/user/getUserPage" method="post">
                                    <div class="col-xs-2">

                                    </div>

                                    <div class="col-xs-2">
                                        <input type="text" name="username" class="form-control" placeholder="username">
                                    </div>
                                    <div class="col-xs-2">
                                        <input type="text" name="email" class="form-control" placeholder="email">
                                    </div>
                                    <div class="col-xs-2">
                                        <input type="text" name="phone" class="form-control" placeholder="phone">
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
                        <th><input type="checkbox"  id="checkMutil"/></th>
                        <th>用户名</th>
                        <th>邮箱</th>
                        <th style="width: 40px">电话</th>
                        <th style="width: 40px">创建时间</th>
                        <th style="width: 40px">修改时间</th>
                        <th style="width: 40px">操作</th>
                    </tr>
                    <c:set var="list" value="${result.data.pageResult.list}"></c:set>
                    <c:set var="page" value="${result.data.pageResult}"></c:set>
                    <c:forEach items="${list}" var="user" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td><input type="checkbox" name="checkOne"  value="${user.id}"></td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td><fmt:formatDate value="${user.created}"
                                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                            <td><fmt:formatDate value="${user.updated}"
                                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                            <td>

                                <a type="button" href="/user/getUserById?id=${user.id}"
                                   class="btn btn-default btn-xs fa fa-search"> Detail</a>
                                <a type="button" href="/user/getForm?id=${user.id}"
                                   class="btn btn-info btn-xs fa fa-edit"> Edit</a>
                                <a type="button" href="/user/deleteUser?id=${user.id}"
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
<script type="text/javascript" src="/static/plugins/adminLTE/plugins/iCheck/icheck.min.js"></script>
<script>

    function page(current) {
        var pageSize = $("#pageSize").val();
        window.location.href = "/user/getUserPage?current=" + current + "&pageSize=" + pageSize;
    }
    // //自定义全选
    // $(function(){
    //     $("#checkMutil").change(function(){
    //         if($("#checkMutil").prop("checked")){
    //             $("input[name='checkOne']").prop("checked",true);
    //         }else{
    //             $("input[name='checkOne']").prop("checked",false);
    //         }
    //     })
    // })

    var ids='';
    $("#deleteMutil").click(function(){
        $("input[name='checkOne']:checked").each(function(){
            ids+=","+$(this).val();
        })
        if(ids!=null && ids!=''){
            $.post(
                "/user/deleteMutil",
                {"ids":ids},
                function(data){
                    alert(data);
                    window.location.reload();
                })
        }
    })
</script>

<script type="text/javascript">
    $(document).ready(function(){
        //样式
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-blue',  // 注意square和blue的对应关系
        });
        //全选
        $('#checkMutil').on('ifChecked', function(event){
            $('input').iCheck('check');
        });

        //反选
        $('#checkMutil').on('ifUnchecked', function(event){
            $('input').iCheck('uncheck');
        });
    });

</script>

</body>
</html>

