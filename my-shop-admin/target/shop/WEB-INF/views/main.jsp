<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sys" uri="/WEB-INF/myTags/pageTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: YURENCHEN
  Date: 2018/10/15
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
<title>MY-SHOP  | MAIN </title>
<body>

<div class="wrapper">

   <jsp:include page="/WEB-INF/views/includes/head-nav.jsp"></jsp:include>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="/WEB-INF/views/includes/side-nav.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">


    </div>
    <!-- /.content-wrapper -->
   <jsp:include page="/WEB-INF/views/includes/copyRight.jsp"></jsp:include>

</div>

<%--javascript:page--%>


<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
</body>
</html>

