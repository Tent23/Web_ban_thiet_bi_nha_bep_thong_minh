<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${context}/assets/css/admin-sidebar.css">

<nav class="admin-sidebar">
    <div class="sidebar-header">
        <h3>Admin Panel</h3>
    </div>

    <ul class="sidebar-menu">

        <li>
            <a href="${context}/admin/dashboard">
                <i class="fa-solid fa-chart-line"></i>
                <span>Tổng quan</span>
            </a>
        </li>

        <li>
            <a href="${context}/admin/orders">
                <i class="fa-solid fa-file-invoice-dollar"></i>
                <span>Quản lý Đơn hàng</span>
            </a>
        </li>

        <!-- QUẢN LÝ SẢN PHẨM -->
        <li class="menu-item-has-children open">
            <a href="#" class="sidebar-link">
                <i class="fa-solid fa-box-archive"></i>
                <span>Quản lý Sản phẩm</span>
                <i class="fa-solid fa-chevron-down toggle-icon"></i>
            </a>

            <ul class="submenu">
                <li>
                    <a href="${context}/admin/products">
                        Tất cả Sản phẩm
                    </a>
                </li>

                <!-- THÊM SẢN PHẨM -->
                <li>
                    <a href="${context}/admin/products/add">
                        ➕ Thêm sản phẩm
                    </a>
                </li>

                <li>
                    <a href="${context}/admin/categories">
                        Danh mục
                    </a>
                </li>

                <li>
                    <a href="${context}/admin/brands">
                        Thương hiệu
                    </a>
                </li>

                <li>
                    <a href="${context}/admin/ecosystems">
                        Hệ sinh thái
                    </a>
                </li>

                <li>
                    <a href="${context}/admin/combos">
                        Combo / Giải pháp
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="${context}/admin/users">
                <i class="fa-solid fa-users"></i>
                <span>Quản lý Khách hàng</span>
            </a>
        </li>

        <li>
            <a href="${context}/admin/content">
                <i class="fa-solid fa-file-pen"></i>
                <span>Quản lý Nội dung</span>
            </a>
        </li>

        <li>
            <a href="${context}/admin/settings">
                <i class="fa-solid fa-gear"></i>
                <span>Cài đặt</span>
            </a>
        </li>
    </ul>

    <div class="sidebar-footer">
        <a href="${context}/logout">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Đăng xuất</span>
        </a>
    </div>
</nav>
