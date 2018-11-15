<%--
  Created by IntelliJ IDEA.
  User: YURENCHEN
  Date: 2018/10/16
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- sidebar menu: : style can be found in sidebar.less -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/plugins/adminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><c:if test="${not empty UserLogin}">${UserLogin.username}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->

        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header logo-lg">MAIN NAVIGATION</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-user"></i> <span>用户管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/user/getUserPage?current=1&pageSize=10"><i class="fa fa-circle-o"></i> 用户列表</a></li>
                    <li><a href="/user/getForm"><i class="fa fa-circle-o"></i> 用户添加</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>目录管理</span>
                    <span class="pull-right-container">
                                  <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/category/getCategoryPage?current=1&pageSize=10"><i class="fa fa-circle-o"></i>目录列表</a></li>
                    <li><a href="/category2/getCategoryTreeTable"><i class="fa fa-circle-o"></i>目录树表</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>内容管理</span>
                    <span class="pull-right-container">
                                  <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/content/getContentPage?current=1&pageSize=10"><i class="fa fa-circle-o"></i>内容列表</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>内容树表</a></li>
                </ul>
            </li>


        </ul>
    </section>
    <!-- /.sidebar -->
</aside>