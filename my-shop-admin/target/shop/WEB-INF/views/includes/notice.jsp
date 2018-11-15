<%--
  Created by IntelliJ IDEA.
  User: YURENCHEN
  Date: 2018/10/18
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
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