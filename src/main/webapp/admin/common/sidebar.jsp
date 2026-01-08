<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<nav class="admin-sidebar">
    <div class="sidebar-header">
        <h3>Admin Panel</h3>
    </div>
    <ul class="sidebar-menu">
        <li>
            <a href="<%=request.getContextPath()%>/admin/admin_dashboard.jsp">
                <i class="fa-solid fa-chart-line"></i>
                <span>Tổng quan</span>
            </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/admin/admin_orders.html">
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
                <li>
                    <a href="<%=request.getContextPath()%>/products">Tất cả Sản phẩm</a>
                </li>

                <li>
                    <a href="<%=request.getContextPath()%>/admin/product-save">
                        <i class="fa-solid fa-plus"></i> Thêm sản phẩm mới
                    </a>
                </li>

                <li>
                    <a href="<%=request.getContextPath()%>/admin/categories" class="active-sub">Danh mục</a>
                </li>

                <li>
                    <a href="<%=request.getContextPath()%>/admin/brands" class="active-sub">Thương hiệu</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/ecosystems" class="active-sub">Hệ sinh thái</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/combo-list" class="active-sub">Combo/giải pháp</a>
                </li>
            </ul>
        </li>

        <li>
            <a href="<%=request.getContextPath()%>/QuangToan/QuanlyUser.html">
                <i class="fa-solid fa-users"></i>
                <span>Quản lý Khách hàng</span>
            </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/admin/admin_content.html">
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
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var dropdowns = document.querySelectorAll(".menu-item-has-children > .sidebar-link");
            dropdowns.forEach(function (link) {
                link.addEventListener("click", function (e) {
                    e.preventDefault();
                    var parent = this.parentElement;
                    parent.classList.toggle("open");
                });
            });
        });
    </script>
</nav>