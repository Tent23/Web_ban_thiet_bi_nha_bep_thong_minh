<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Thương hiệu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/indexfont.css" />
</head>
<body>
<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <div class="admin-main">
        <header class="admin-header">
            <h2>Quản lý Thương hiệu</h2>
            <a href="${pageContext.request.contextPath}/admin/brands?action=new" class="btn-primary">
                <i class="fa-solid fa-plus"></i> Thêm mới
            </a>
        </header>

        <main class="admin-content">
            <c:if test="${not empty param.message}">
                <div style="padding:10px; background:#d4edda; color:#155724; margin-bottom:15px; border-radius: 5px;">
                        ${param.message == 'deleted' ? 'Đã xóa thương hiệu thành công!' : 'Đã lưu thương hiệu thành công!'}
                </div>
            </c:if>

            <div class="admin-card">
                <h3>Danh sách thương hiệu</h3>
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th style="width: 80px;">ID</th>
                        <th style="width: 150px;">Logo</th>
                        <th>Tên Thương hiệu</th>
                        <th style="width: 150px;">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="b" items="${brands}">
                        <tr>
                            <td>#${b.brand_id}</td>
                            <td>
                                <img src="${not empty b.logo_url ? b.logo_url : 'https://placehold.co/50'}"
                                     alt="logo" style="width: 60px; height: 40px; object-fit: contain; border: 1px solid #e2e8f0; background: #fff; padding: 2px; border-radius: 4px;">
                            </td>
                            <td style="font-weight: 600;">${b.brand_name}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/brands?action=edit&id=${b.brand_id}" class="btn-action edit" title="Sửa">
                                    <i class="fa-solid fa-pencil"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/brands?action=delete&id=${b.brand_id}"
                                   class="btn-action delete"
                                   title="Xóa"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa thương hiệu này không?');">
                                    <i class="fa-solid fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty brands}">
                        <tr>
                            <td colspan="4" style="text-align: center; color: #64748b; padding: 30px;">
                                Chưa có thương hiệu nào. Hãy thêm mới!
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
</body>
</html>