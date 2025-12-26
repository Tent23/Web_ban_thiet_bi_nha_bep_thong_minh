<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm mới</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/assets/css/admin_style.css'/>">
</head>
<body>

<jsp:include page="common/sidebar.jsp"/>

<div class="admin-main">
    <header class="admin-header">
        <div class="header-left">
            <a href="<c:url value='/admin/products'/>" class="btn-back">
                <i class="fa-solid fa-arrow-left"></i> Quay lại
            </a>
            <h2>Thêm Sản phẩm Mới</h2>
        </div>
        <div class="admin-header-actions">
            <button type="submit" form="addProductForm" class="btn-primary">Lưu Sản phẩm</button>
        </div>
    </header>

    <main class="admin-content">
        <%-- THÔNG BÁO LỖI NẾU CÓ --%>
        <c:if test="${not empty error}">
            <div style="color: red; padding: 10px; border: 1px solid red; margin-bottom: 10px;">
                    ${error}
            </div>
        </c:if>

        <form id="addProductForm" action="<c:url value='/admin/product/add'/>" method="post">

            <div class="form-col-main">
                <div class="admin-card">
                    <h3>Thông tin chung</h3>
                    <div class="form-group">
                        <label>Tên sản phẩm</label>
                        <input type="text" name="product_name" class="form-control" placeholder="Nhập tên..." required>
                    </div>
                    <div class="form-group">
                        <label>Mô tả chi tiết</label>
                        <textarea name="description" class="form-control" rows="5"></textarea>
                    </div>
                </div>

                <div class="admin-card">
                    <label>Link hình ảnh (URL)</label>
                    <input type="text" name="image" class="form-control" placeholder="https://...">
                </div>
            </div>

            <div class="form-col-sidebar">
                <div class="admin-card">
                    <h3>Trạng thái & Giá</h3>
                    <div class="form-group">
                        <label>Trạng thái</label>
                        <select name="status" class="form-control">
                            <option value="published">Đã xuất bản</option>
                            <option value="draft">Bản nháp</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Giá (VNĐ)</label>
                        <input type="number" name="price" class="form-control" value="0" required>
                    </div>
                </div>

                <div class="admin-card">
                    <h3>Kho hàng</h3>
                    <div class="form-group">
                        <label>Số lượng</label>
                        <input type="number" name="stock_quantity" class="form-control" value="1">
                    </div>
                    <div class="form-group">
                        <label>Mã SKU</label>
                        <input type="text" name="sku" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Danh mục ID (VD: 1, 2, 3)</label>
                        <input type="number" name="category_id" class="form-control" value="1">
                    </div>
                </div>
            </div>
        </form>
    </main>
</div>
</body>
</html>