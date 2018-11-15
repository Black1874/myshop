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
<link rel="stylesheet" href="/static/plugins/dropzone/min/dropzone.min.css">
<link rel="stylesheet" href="/static/plugins/dropzone/min/basic.min.css">
<link rel="stylesheet" href="/static/plugins/wangEditor/wangEditor.min.css">
<title>内容管理</title>
<body>
<jsp:include page="/WEB-INF/views/includes/head-nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/includes/side-nav.jsp"></jsp:include>
<div class="content-wrapper">
    <section class="content">
        <%--标题--%>
        <div class="row">
            <h3 style="padding-left: 15px">${result.data.id eq null ? "新建内容" : "修改内容"}</h3>
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

                <form role="form" id="categoryForm" action="/content/formDispatcher" method="post">
                    <div class="box-body">
                        <%--id--%>
                        <div class="form-group">
                            <input type="hidden" name="id" class="form-control" id="id" placeholder="id"
                                   value="${result.data.id}">
                        </div>
                        <%--categoryname--%>
                        <%--配置了触发弹出框--%>
                        <div class="form-group">
                            <label for="categoryName">CategoryName</label>
                            <input type="text" readonly="readonly" class="form-control" id="categoryName" name="category.name" data-toggle="modal" data-target="#modal-info"
                                   placeholder="categoryName"
                                   value="${result.data.category.name}" required>
                        </div>
                        <%--Title--%>
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input type="text"  class="form-control" id="title" name="title"
                                   placeholder="title"
                                   value="${result.data.title}" required>
                        </div>
                        <%--subTitle--%>
                        <div class="form-group">
                            <label for="subTitle">subTitle</label>
                            <input type="text"  class="form-control" id="subTitle" name="subTitle"
                                   placeholder="subTitle"
                                   value="${result.data.subTitle}" required>
                        </div>
                        <%--titleDesc--%>
                        <div class="form-group">
                            <label for="titleDesc">titleDesc</label>
                            <input type="text"  class="form-control" id="titleDesc" name="titleDesc"
                                   placeholder="titleDesc"
                                   value="${result.data.titleDesc}" required>
                        </div>
                        <%--categoryID--%>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="categoryID" name="category.id"
                                   placeholder="categoryID"
                                   value="${result.data.category.id}" required>
                        </div>
                        <%--url--%>
                        <div class="form-group">
                            <label for="url">url</label>
                            <input type="text" class="form-control" id="url" name="url"
                                   value="${result.data.url}"
                                   placeholder="url" required>
                        </div>
                        <%--pic1--%>
                        <div class="form-group" >
                            <label for="pic1">pic1</label>
                            <input type="text" class="form-control" id="pic1" name="pic1" readonly="readonly"
                                   value="${result.data.pic1}"
                                   placeholder="pic1" required>
                            <div class="dropzone " id="dropzone1">

                            </div>
                        </div>

                        <%--pic2--%>
                        <div class="form-group">
                            <label for="pic2">pic2</label>
                            <input type="text" class="form-control" id="pic2" name="pic2" readonly="readonly"
                                   value="${result.data.pic2}"
                                   placeholder="pic2" required>
                            <div class="dropzone" id="dropzone2">

                            </div>
                        </div>

                        <%--detail--%>
                        <div class="form-group">
                            <label for="detail">detail</label>
                            <input type="hidden" class="form-control" id="detail" name="detail"
                                   value="${result.data.detail}"
                                   placeholder="detail" required>
                            <div class="wangEditor" id="editor">

                            </div>
                        </div>
                        <%--created--%>
                        <div class="form-group">
                            <input type="hidden" name="created" class="form-control" id="created"
                                   placeholder="created"
                                   value="${result.data.created}">
                        </div>

                        <%--updated--%>
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
    <img src="#" alt="hahha" id="myImg">;

</div>

<jsp:include page="/WEB-INF/views/includes/copyRight.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
<script type="text/javascript" src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="/static/plugins/jqueryValidator/method-validate.js"></script>
<script type="text/javascript" src="/static/plugins/jquery-ztree/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="/static/plugins/dropzone/dropzone.js"></script>
<script type="text/javascript" src="/static/plugins/wangEditor/wangEditor.min.js"></script>
<script>
    Dropzone.autoDiscover=false;
</script>

<%--创建日期--%>
<script type="text/javascript">
    $(function () {
        //日期的创建
        $("#submit").click(function () {
            $("#created").val(new Date());
            $("#updated").val(new Date());
        });
    })
</script>

<%-------------------------validator-------------------------------%>
<script type="text/javascript">
    //开启表单校验
    $("#userForm").validate();
</script>

<%----------------------------ztree----------------------------------%>
<script type="text/javascript">

    // 异步加载数据源
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
            url: "/category/treeData",
            //携带的数据
            autoParam: ['id']
        }
    };
    // 初始化tree
    $.fn.zTree.init($("#category-tree"), setting,null);

    // //本地数据数据源
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

        $("#categoryID").val(id);
        $("#categoryName").val(name);
        $("#modal-info").modal("hide");//将model隐藏，这是bootstrap自带的方法
    }
</script>

<%---------------------------dropzone-------------------------------------%>
<script type="text/javascript">

    //自定义配置文件
    var opts1 = {
        url: "/content/upload",
        paramName: "file",
        autoDiscover:false,
        maxFiles: 1, // 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        autoProcessQueue:true,//是否自动上传
        addRemoveLinks: true,
        parallelUploads: 1, // 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传 1 个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        init: function () {
            this.on('success', function (files, response) {
                //文件上传成功之后的操作
                $("#pic1").val(response.data);

            });
        }
    };
    var opts2 = {
        url: "/content/upload",
        paramName: "file",
        autoDiscover:false,
        maxFiles: 1, // 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        autoProcessQueue:true,//是否自动上传
        addRemoveLinks: true,
        parallelUploads: 1, // 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传 1 个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        init: function () {
            this.on('success', function (files, response) {
                //文件上传成功之后的操作
                $("#pic2").val(response.data);
                $("#myImg").src(response.data);
            });
        }
    };

    //开启dropzone
   var dropzone1= new Dropzone("#dropzone1",opts1);
   var dropzone2= new Dropzone("#dropzone2",opts2);

</script>

<%---------------------------wangEditor---------------------------------%>
<script type="text/javascript">
    //获取到wangEditor类
    var E = window.wangEditor;
    //创建wangEditor实例
    var editor = new E("#editor");
    editor.customConfig.uploadImgServer = '/content/upload';
    editor.customConfig.uploadFileName = 'file';
    //开启富文本框
    editor.create();
    //提交时,将内容赋值给detail
    var submit=$("#submit").get(0);
    submit.addEventListener("click",function(){
        var htmlText = editor.txt.html();
        $("#detail").val(htmlText);
    })


</script>
</body>
</html>


