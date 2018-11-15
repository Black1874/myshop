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
<link href="/static/plugins/jquery-ztree/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css" type="text/css" rel="stylesheet">
<title>内容管理</title>
<body>
<jsp:include page="/WEB-INF/views/includes/head-nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/includes/side-nav.jsp"></jsp:include>
<div class="content-wrapper">
    <section class="content">
        <%--标题--%>
        <div class="row">
            <h3 style="padding-left: 15px">${result.data.id eq null ? "新建目录" : "修改目录"}</h3>
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

                <form role="form" id="categoryForm" action="/category2/formDispatcher" method="post">
                    <div class="box-body">

                        <%--parent_name--%>
                        <%--配置了触发弹出框--%>
                        <div class="form-group">
                            <label for="parentName">Parent_name</label>
                            <input type="text" readonly="readonly" class="form-control" id="parentName" name="parent.name" data-toggle="modal" data-target="#modal-info"
                                   placeholder="parentName"
                                   value="${result.data.parent.name}" required>
                        </div>


                        <%--parent_id--%>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="parentId" name="parent.id"
                                   placeholder="parentId"
                                   value="${result.data.parent.id}" required>
                        </div>

                        <%--id--%>
                        <div class="form-group">
                            <input type="hidden" name="id" class="form-control" id="id" placeholder="id"
                                   value="${result.data.id}">
                        </div>

                        <%--name--%>
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="type" class="form-control " id="name" name="name"
                                   placeholder="name" required
                                   value="${result.data.name}">

                        </div>


                        <%--status--%>
                        <div class="form-group">
                            <label for="status">Status</label>
                            <input type="text" class="form-control" id="status" name="status"
                                   value="${result.data.status}"
                                   placeholder="status" required>
                        </div>
                        <%--order--%>
                        <div class="form-group">
                            <label for="order">Order</label>
                            <input type="text" class="form-control" id="order" name="order"
                                   value="${result.data.order}"
                                   placeholder="order" required>
                        </div>
                        <%--isParent--%>
                        <div class="form-group">
                            <label for="isParent">IsParent</label>
                            <input type="text" class="form-control" id="isParent" name="isParent"
                                   value="${result.data.isParent}"
                                   placeholder="isParent" required>
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

                    </div>
                    <%--弹出框--%>
                    <div class="modal modal-info fade in" id="modal-info"
                         style="display: none; padding-right: 17px;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title">父节点数据</h4>
                                </div>
                                <div class="modal-body">
                                    <%--配置弹出框显示的内容为树--%>
                                    <ul id="category-tree" class="ztree"></ul>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline pull-left"
                                            data-dismiss="modal">取消
                                    </button>
                                    <button type="button" onclick="getData()" class="btn btn-outline">保存</button>
                                </div>
                            </div>
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
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script src="/static/plugins/jqueryValidator/method-validate.js"></script>
<script src="/static/plugins/jquery-ztree/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript">


    $(function () {
        //日期的创建
        $("#submit").click(function () {
            $("#created").val(new Date());
            $("#updated").val(new Date());
        });

        //-----------------------validator-----------------------------//
        //开启表单校验
        $("#userForm").validate();

        // --------------------------ztree--------------------------------//
        // 异步加载数据
        // 加载ztree
        var setting = {
            view: {
                //是否允许多选
                selectedMulti: false
            },
            //异步
            async: {
                enable: true,
                //请求的目的地
                url: "/category2/treeData",
                //携带的数据
                autoParam: ['id']
            }
        };
        // 初始化tree
        $.fn.zTree.init($("#category-tree"), setting,null);


        // //本地数据tree
        // var setting = {
        //     isSimpleData : true,              //数据是否采用简单 Array 格式，默认false
        //     treeNodeKey : "name",               //在isSimpleData格式下，当前节点id属性
        //     treeNodeParentKey : "name",        //在isSimpleData格式下，当前节点的父节点id属性
        //     showLine : true,                  //是否显示节点间的连线
        //     checkable : true                  //每个节点上是否显示 CheckBox
        // };
        // //初始化ztree
        // var treeNodes =[{'created':1428051098000,'id':30,'isParent':1,'name':'LeeShop','order':1,'parent':{'id':0},'status':1,'updated':1428051100000}]
        // $.fn.zTree.init($("#category-tree"), setting,treeNodes );



    })

    // 提交事件获取数据
    function getData() {
        var zTree = $.fn.zTree.getZTreeObj("category-tree");
        var nodes = zTree.getSelectedNodes();//因为ztree允许多选，所以getSelectedNodes返回一个json对象数组
        if(nodes.length==0){
            alert("请选择一条数据");
            return;
        }
        var name=nodes[0].name;
        var id=nodes[0].id;

        $("#parentId").val(id);
        $("#parentName").val(name);
        $("#modal-info").modal("hide");//将model隐藏，这是bootstrap自带的方法
    }


</script>
</body>
</html>



