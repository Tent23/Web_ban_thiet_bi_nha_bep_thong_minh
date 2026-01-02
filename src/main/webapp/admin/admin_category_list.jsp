<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Quản lý Danh mục</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}../assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/admin_style.css" />
    <link rel="stylesheet" href="../assets/css/indexfont.css" />
    <style>
        /* CSS bổ sung nhỏ để hiển thị ảnh logo đẹp hơn trong bảng */
        .brand-thumbnail {
            width: 50px;
            height: 50px;
            object-fit: contain; /* Giữ tỉ lệ ảnh logo */
            border-radius: 4px;
            border: 1px solid #ddd;
            background: #fff;
        }

    </style>
</head>
<body>

<div class="admin-layout">

    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <div class="admin-main">

        <header class="admin-header">
            <h2>Danh mục Sản phẩm</h2>
            <div class="admin-header-actions">
                <a href="${pageContext.request.contextPath}/admin/category-save" class="btn-primary">
                    <i class="fa-solid fa-plus"></i> Thêm danh mục
                </a>
                <div class="admin-profile">
                    <img src="https://placehold.co/40x40" alt="Admin">
                    <span>Xin chào, Admin!</span>
                </div>
            </div>
        </header>

        <main class="admin-content">

            <c:if test="${not empty sessionScope.msg}">
                <div class="status completed" style="margin-bottom: 20px; padding: 15px;">
                    <i class="fa-solid fa-check-circle"></i> ${sessionScope.msg}
                </div>
                <c:remove var="msg" scope="session"/>
            </c:if>

            <div class="admin-card admin-filters">
                <input type="text" placeholder="Tìm theo Tên danh mục, Mã danh mục...">
                <select>
                    <option value="filterAZ">Lọc từ A - Z theo danh mục </option>
                    <option value="ZA">Lọc từ Z - A theo danh mục </option>
                </select>

                <button class="btn-filter">Lọc</button>
            </div>

            <div class="admin-card">
                <h3>Danh sách Danh mục hiện có</h3>
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th>Ảnh</th>
                        <th>Tên danh mục</th>
                        <th>Mã danh mục</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${categories}">
                        <tr>
                            <td>
                                <img src="${not empty c.logo ? c.logo : 'https://placehold.co/60x60?text=No+Img'}"
                                     alt="${c.category_name}" class="brand-thumbnail">
                            </td>
                            <td style="font-weight: 600;">${c.category_name}</td>
                            <td>#${c.category_id}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/category-save?action=edit&id=${c.category_id}"
                                   class="btn-action edit">
                                    <i class="fa-solid fa-pencil"></i> Sửa
                                </a>

                                <a href="${pageContext.request.contextPath}/admin/category-list?action=delete&id=${c.category_id}"
                                   class="btn-action delete"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">
                                    <i class="fa-solid fa-trash"></i> Xóa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty categories}">
                        <tr>
                            <td colspan="4" style="text-align: center; padding: 30px;">
                                Chưa có danh mục nào. Hãy thêm mới!
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

                <div class="admin-pagination">
                    <a href="#" class="page-link">&laquo; Trước</a>
                    <a href="#" class="page-link active">1</a>
                    <a href="#" class="page-link">2</a>
                    <a href="#" class="page-link">3</a>
                    <a href="#" class="page-link">Sau &raquo;</a>
                </div>
            </div>

        </main>
    </div>
</div>

</body>
</html>