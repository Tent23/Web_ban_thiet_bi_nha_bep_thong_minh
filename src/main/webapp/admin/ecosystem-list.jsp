<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Hệ Sinh Thái</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/indexfont.css" />
</head>
<body>
<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <div class="admin-main">
        <header class="admin-header">
            <h2>Quản lý Hệ Sinh Thái</h2>
            <a href="${pageContext.request.contextPath}/admin/ecosystems?action=new" class="btn-primary">
                <i class="fa-solid fa-plus"></i> Thêm mới
            </a>
        </header>

        <main class="admin-content">
            <c:if test="${not empty param.message}">
                <div style="padding:15px;
                        background:${param.message == 'deleted' || param.message == 'error' ? '#fee2e2' : '#dcfce7'};
                        color:${param.message == 'deleted' || param.message == 'error' ? '#dc2626' : '#16a34a'};
                        margin-bottom:20px; border-radius: 6px; font-weight: 600;">
                    <c:choose>
                        <c:when test="${param.message == 'saved'}">Thêm mới thành công!</c:when>
                        <c:when test="${param.message == 'updated'}">Cập nhật thành công!</c:when>
                        <c:when test="${param.message == 'deleted'}">Đã xóa dữ liệu!</c:when>
                        <c:when test="${param.message == 'notfound'}">Không tìm thấy dữ liệu!</c:when>
                        <c:otherwise>Thao tác thành công!</c:otherwise>
                    </c:choose>
                </div>
            </c:if>

            <div class="admin-card">
                <h3>Danh sách Bộ sưu tập</h3>
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th style="width: 80px;">ID</th>
                        <th style="width: 150px;">Ảnh</th>
                        <th>Tên Hệ Sinh Thái</th>
                        <th style="width: 150px; text-align: center;">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="e" items="${ecosystems}">
                        <tr>
                            <td>#${e.id}</td>
                            <td>
                                <img src="${not empty e.image ? e.image : 'https://placehold.co/80'}"
                                     alt="img" style="width: 80px; height: 50px; object-fit: cover; border-radius: 4px; border: 1px solid #e2e8f0;">
                            </td>
                            <td style="font-weight: 600;">${e.name}</td>
                            <td style="text-align: center;">
                                <a href="${pageContext.request.contextPath}/admin/ecosystems?action=edit&id=${e.id}"
                                   class="btn-action edit" title="Sửa">
                                    <i class="fa-solid fa-pencil"></i>
                                </a>

                                <a href="${pageContext.request.contextPath}/admin/ecosystems?action=delete&id=${e.id}"
                                   class="btn-action delete" title="Xóa"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa bộ sưu tập này? Hành động này không thể hoàn tác.');">
                                    <i class="fa-solid fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty ecosystems}">
                        <tr>
                            <td colspan="4" style="text-align: center; padding: 30px; color: #64748b;">
                                <i class="fa-solid fa-box-open" style="font-size: 2rem; margin-bottom: 10px; display: block;"></i>
                                Chưa có dữ liệu hệ sinh thái nào. Hãy thêm mới!
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