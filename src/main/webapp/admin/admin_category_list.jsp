<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Danh mục</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/indexfont.css" />

</head>
<body>
<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <div class="admin-main">
        <header class="admin-header">
            <h2>Quản lý Danh mục</h2>
            <a href="${pageContext.request.contextPath}/admin/categories?action=new" class="btn-primary">
                <i class="fa-solid fa-plus"></i> Thêm mới
            </a>
        </header>

        <main class="admin-content">
            <c:if test="${not empty param.message}">
                <div style="padding:10px; background:#d4edda; color:#155724; margin-bottom:15px;">
                        ${param.message == 'deleted' ? 'Đã xóa thành công!' : 'Đã lưu thành công!'}
                </div>
            </c:if>

            <table class="admin-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Logo</th>
                    <th>Tên Danh Mục</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${categories}">
                    <tr>
                        <td>${c.category_id}</td>
                        <td>
                            <img src="${not empty c.logo ? c.logo : 'https://placehold.co/50'}"
                                 alt="logo" style="width: 50px; height: 50px; object-fit: contain; border: 1px solid #ddd;">
                        </td>
                        <td>${c.category_name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/categories?action=edit&id=${c.category_id}" class="btn-action edit">
                                <i class="fa-solid fa-pencil"></i>
                            </a>
                            <a href="${pageContext.request.contextPath}/admin/categories?action=delete&id=${c.category_id}"
                               class="btn-action delete"
                               onclick="return confirm('Xóa danh mục này?');">
                                <i class="fa-solid fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</div>
</body>
</html>