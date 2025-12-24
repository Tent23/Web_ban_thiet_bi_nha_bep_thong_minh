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
    <link rel="stylesheet" href="../assets/css/admin_style.css" />

    <link rel="stylesheet" href="../assets/css/indexfont.css" />

</head>
<body>
<jsp:include page="common/sidebar.jsp"></jsp:include>

<main class="main-content">

    <div class="dashboard-header">
        <h2>Tổng quan</h2>
        <div class="user-action">
            <span>Xin chào, <strong>Admin</strong></span>
        </div>
    </div>

    <div class="dashboard-widgets">
        <p>Chào mừng bạn đến với trang quản trị.</p>
        <div style="display: flex; gap: 20px; margin-top: 20px;">
            <div style="background: white; padding: 20px; border-radius: 8px; flex: 1; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                <h3>Đơn hàng mới</h3>
                <p style="font-size: 24px; font-weight: bold; color: #2ecc71;">12</p>
            </div>
            <div style="background: white; padding: 20px; border-radius: 8px; flex: 1; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                <h3>Sản phẩm</h3>
                <p style="font-size: 24px; font-weight: bold; color: #3498db;">340</p>
            </div>
            <div style="background: white; padding: 20px; border-radius: 8px; flex: 1; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                <h3>Khách hàng</h3>
                <p style="font-size: 24px; font-weight: bold; color: #f1c40f;">1,205</p>
            </div>
        </div>
    </div>

</main>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Lấy tất cả các menu có class 'menu-item-has-children'
        var dropdowns = document.querySelectorAll(".menu-item-has-children > .sidebar-link");

        dropdowns.forEach(function (link) {
            link.addEventListener("click", function (e) {
                e.preventDefault(); // Chặn chuyển trang khi click vào menu cha

                var parent = this.parentElement;

                // Toggle class active/open
                parent.classList.toggle("open");
                parent.classList.toggle("active");
            });
        });
    });
</script>
</body>
</html>