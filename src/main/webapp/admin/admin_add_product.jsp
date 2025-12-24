<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm sản phẩm</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}../assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/admin_style.css" />

    <link rel="stylesheet" href="../assets/css/indexfont.css" />
</head>
<body>
<jsp:include page="common/sidebar.jsp"></jsp:include>

<div class="admin-main">

    <!-- HEADER -->
    <header class="admin-header">
        <div class="header-left">
            <a href="${pageContext.request.contextPath}/admin/products" class="btn-back">
                <i class="fa-solid fa-arrow-left"></i> Quay lại
            </a>
            <h2>Thêm Sản phẩm Mới</h2>
        </div>
        <div class="admin-header-actions">
            <a href="${pageContext.request.contextPath}/admin/products" class="btn-secondary">Hủy</a>
            <button type="submit" form="addProductForm" class="btn-primary">Lưu Sản phẩm</button>
        </div>
    </header>

    <!-- MAIN -->
    <main class="admin-content">

        <form id="addProductForm"
              action="${pageContext.request.contextPath}/admin/product/add"
              method="post"
              class="admin-form-layout">

            <!-- CỘT TRÁI -->
            <div class="form-col-main">

                <div class="admin-card">
                    <h3>Thông tin chung</h3>

                    <div class="form-group">
                        <label for="product-name">Tên sản phẩm</label>
                        <input type="text"
                               id="product-name"
                               name="product_name"
                               class="form-control"
                               placeholder="Ví dụ: Bếp từ Bosch Series 8"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="product-desc">Mô tả chi tiết</label>
                        <textarea id="product-desc"
                                  name="description"
                                  class="form-control"
                                  rows="6"
                                  placeholder="Nhập mô tả sản phẩm..."></textarea>
                    </div>
                </div>

                <div class="admin-card">
                    <h3>Hình ảnh</h3>

                    <div class="image-upload-area">
                        <div class="upload-placeholder"
                             onclick="document.getElementById('product-image').click()">
                            <i class="fa-solid fa-cloud-arrow-up"></i>
                            <p>Kéo thả ảnh vào đây hoặc nhấn để tải lên</p>

                            <!-- TẠM NHẬP LINK ẢNH -->
                            <input type="text"
                                   id="product-image"
                                   name="image"
                                   hidden>
                        </div>

                        <div class="image-preview-container">
                            <!-- preview sau này -->
                        </div>
                    </div>
                </div>

            </div>

            <!-- CỘT PHẢI -->
            <div class="form-col-sidebar">

                <div class="admin-card">
                    <h3>Trạng thái</h3>

                    <div class="form-group">
                        <select class="form-control" name="status">
                            <option value="published">Đã xuất bản</option>
                            <option value="draft">Bản nháp</option>
                            <option value="hidden">Ẩn</option>
                        </select>
                    </div>

                    <div class="toggle-group">
                        <label class="switch">
                            <input type="checkbox" name="featured" value="1">
                            <span class="slider round"></span>
                        </label>
                        <span>Sản phẩm nổi bật</span>
                    </div>
                </div>

                <div class="admin-card">
                    <h3>Giá cả</h3>

                    <div class="form-group">
                        <label>Giá (VNĐ)</label>
                        <input type="number"
                               name="price"
                               class="form-control"
                               placeholder="0"
                               required>
                    </div>
                </div>

                <div class="admin-card">
                    <h3>Phân loại</h3>

                    <div class="form-group">
                        <label>Danh mục</label>
                        <select name="category_id" class="form-control" required>
                            <option value="">-- Chọn danh mục --</option>
                            <option value="1">Bếp từ</option>
                            <option value="2">Robot hút bụi</option>
                            <option value="3">Tủ lạnh</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Thương hiệu</label>
                        <select class="form-control" name="brand_id">
                            <option value="">-- Chọn thương hiệu --</option>
                            <option value="1">Bosch</option>
                            <option value="2">Xiaomi</option>
                            <option value="3">Samsung</option>
                        </select>
                    </div>
                </div>

                <div class="admin-card">
                    <h3>Kho hàng</h3>

                    <div class="form-group">
                        <label>Mã SKU</label>
                        <input type="text"
                               name="sku"
                               class="form-control"
                               placeholder="VD: BOSCH-001">
                    </div>

                    <div class="form-group">
                        <label>Số lượng</label>
                        <input type="number"
                               name="stock_quantity"
                               class="form-control"
                               value="0">
                    </div>
                </div>

            </div>

        </form>

    </main>
</div>
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
