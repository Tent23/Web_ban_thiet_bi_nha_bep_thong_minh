<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Tổng quan</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}../assets/css/admin_style.css">
</head>
<body>

<div class="admin-layout">

    <jsp:include page="common/sidebar.jsp"></jsp:include>
    <main class="admin-main">
        <header class="admin-header">
            <h2>Tổng quan</h2>
            <div class="admin-profile">
                <span>Xin chào, <strong>Admin</strong></span>
            </div>
        </header>

        <div class="admin-content">
            <p style="margin-bottom: 25px; color: var(--admin-text-light);">Chào mừng bạn đến với hệ thống quản trị nội dung.</p>

            <div class="admin-grid stat-cards">

                <div class="stat-card">
                    <div class="card-icon blue">
                        <i class="fas fa-shopping-cart"></i>
                    </div>
                    <div class="card-info">
                        <span class="card-title">Đơn hàng mới</span>
                        <span class="card-value">12</span>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="card-icon green">
                        <i class="fas fa-box"></i>
                    </div>
                    <div class="card-info">
                        <span class="card-title">Sản phẩm</span>
                        <span class="card-value">340</span>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="card-icon yellow">
                        <i class="fas fa-users"></i>
                    </div>
                    <div class="card-info">
                        <span class="card-title">Khách hàng</span>
                        <span class="card-value">1,205</span>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="card-icon red">
                        <i class="fas fa-chart-line"></i>
                    </div>
                    <div class="card-info">
                        <span class="card-title">Doanh thu tháng</span>
                        <span class="card-value">45.2M</span>
                    </div>
                </div>

            </div>

            <div class="admin-card">
                <h3>Đơn hàng gần đây</h3>
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th>Mã đơn</th>
                        <th>Khách hàng</th>
                        <th>Ngày đặt</th>
                        <th>Trạng thái</th>
                        <th>Tổng tiền</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
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
</body>
</html>