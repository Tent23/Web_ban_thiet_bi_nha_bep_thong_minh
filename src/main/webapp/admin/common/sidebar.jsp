<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Khai báo đường dẫn gốc để dùng cho mọi link, tránh lỗi khi include --%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${pageContext.request.contextPath}../assets/css/">

<nav class="admin-sidebar">
    <div class="sidebar-header">
        <h3>Admin Panel</h3>
    </div>
    <ul class="sidebar-menu">
        <li>
            <a href="${pageContext.request.contextPath}/admin/admin_dashboard.jsp">
                <i class="fa-solid fa-chart-line"></i>
                <span>Tổng quan</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/admin_orders.html">
                <i class="fa-solid fa-file-invoice-dollar"></i>
                <span>Quản lý Đơn hàng</span>
            </a>
        </li>

        <li class="menu-item-has-children active open">
            <a href="#" class="sidebar-link">
                <i class="fa-solid fa-box-archive"></i>
                <span>Quản lý Sản phẩm</span>
                <i class="fa-solid fa-chevron-down toggle-icon"></i>
            </a>
            <ul class="submenu">
                <li><a href="${pageContext.request.contextPath}/admin/admin_products.html" class="active-sub">Tất cả Sản phẩm</a></li>

                <li><a href="${pageContext.request.contextPath}/TrongBao/admin_product_category.html">Danh mục</a></li>
                <li><a href="${pageContext.request.contextPath}/TrongBao/admin_product_brand.html">Thương hiệu</a></li>
                <li><a href="${pageContext.request.contextPath}/TrongBao/admin_product_ecosystem.html">Hệ sinh thái</a></li>
                <li><a href="${pageContext.request.contextPath}/TrongBao/admin_product_Combo.html">Combo / Giải pháp</a></li>
            </ul>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/QuangToan/QuanlyUser.html">
                <i class="fa-solid fa-users"></i>
                <span>Quản lý Khách hàng</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/admin_content.html">
                <i class="fa-solid fa-file-pen"></i>
                <span>Quản lý Nội dung</span>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="fa-solid fa-gear"></i>
                <span>Cài đặt</span>
            </a>
        </li>
    </ul>
    <div class="sidebar-footer">
        <a href="#">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Đăng xuất</span>
        </a>
    </div>
</nav>