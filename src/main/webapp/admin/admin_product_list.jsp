<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Sản phẩm | Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
</head>
<body>

<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <main class="admin-main">
        <header class="admin-header">
            <div class="header-left">
                <h2>Danh sách Sản phẩm</h2>
            </div>
            <div class="admin-header-actions">
                <a href="${pageContext.request.contextPath}/admin/product-save?action=new" class="btn-primary">
                    <i class="fa-solid fa-plus"></i> Thêm Sản phẩm
                </a>
            </div>
        </header>

        <div class="admin-content">
            <c:if test="${not empty sessionScope.msg}">
                <div style="background-color: #dcfce7; color: #16a34a; padding: 15px; border-radius: 8px; margin-bottom: 20px; font-weight: 600;">
                    <i class="fa-solid fa-check-circle"></i> ${sessionScope.msg}
                    <% session.removeAttribute("msg"); %>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.error}">
                <div style="background-color: #fee2e2; color: #dc2626; padding: 15px; border-radius: 8px; margin-bottom: 20px;">
                    <i class="fa-solid fa-circle-exclamation"></i> ${sessionScope.error}
                    <% session.removeAttribute("error"); %>
                </div>
            </c:if>

            <div class="admin-card">
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Hình ảnh</th>
                        <th>Tên sản phẩm</th>
                        <th>Giá bán</th>
                        <th>Tồn kho</th>
                        <th>Brand ID</th> <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listProducts}" var="p">
                        <tr>
                            <td>#${p.product_id}</td>
                            <td>
                                <img src="${p.image}" class="product-thumbnail"
                                     onerror="this.src='https://via.placeholder.com/60?text=No+Img'">
                            </td>
                            <td>
                                <div style="font-weight: 600;">${p.product_name}</div>
                                <small style="color: var(--admin-text-light);">Cat ID: ${p.category_id}</small>
                            </td>
                            <td style="color: var(--red); font-weight: 700;">
                                <fmt:formatNumber value="${p.price}" pattern="#,###"/> đ
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.stock_quantity == 0}">
                                        <span style="color: var(--red); font-weight: 600;">Hết hàng</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: var(--admin-text);">${p.stock_quantity}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${p.brand_id}</td>
                            <td class="text-right">
                                <a href="${pageContext.request.contextPath}/admin/product-save?action=edit&id=${p.product_id}"
                                   class="btn-action edit" title="Sửa">
                                    <i class="fa-solid fa-pen"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/products?action=delete&id=${p.product_id}"
                                   onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này?')"
                                   class="btn-action delete" title="Xóa">
                                    <i class="fa-solid fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</div>

</body>
</html>