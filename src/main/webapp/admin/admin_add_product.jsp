<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product != null && product.product_id > 0 ? 'Cập nhật sản phẩm' : 'Thêm sản phẩm mới'} | Admin</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/indexfont.css" />

    <style>
        /* CSS bổ sung cho thông báo */
        .alert { padding: 15px; margin-bottom: 20px; border: 1px solid transparent; border-radius: 4px; }
        .alert-success { color: #155724; background-color: #d4edda; border-color: #c3e6cb; }
        .alert-danger { color: #721c24; background-color: #f8d7da; border-color: #f5c6cb; }
    </style>
</head>
<body>

<jsp:include page="common/sidebar.jsp"></jsp:include>

<div class="admin-layout">

    <aside class="admin-sidebar">
        <div class="sidebar-header">
        </div>
        <ul class="sidebar-menu">
            <li>
                <a href="${pageContext.request.contextPath}/products">
                    <i class="fa-solid fa-box"></i> Quản lý sản phẩm
                </a>
            </li>
        </ul>
    </aside>
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <main class="admin-main">

        <header class="admin-header">
            <div class="header-left">
                <a href="${pageContext.request.contextPath}/products" class="btn-back">
                    <i class="fa-solid fa-arrow-left"></i> Quay lại danh sách
                </a>
            </div>
            <div class="admin-profile">
                <span style="font-weight: 600;">Administrator</span>
            </div>
        </header>

        <div class="admin-content">

            <c:if test="${not empty sessionScope.msg}">
                <div class="alert alert-success">
                        ${sessionScope.msg}
                    <% session.removeAttribute("msg"); %> </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form id="productForm" action="${pageContext.request.contextPath}/admin/product-save" method="post" class="admin-form-layout">

                <input type="hidden" name="product_id" value="${product != null ? product.product_id : 0}">

                <div class="col-main">
                    <div class="admin-card">
                        <h3>Thông tin chung</h3>

                        <div class="form-group">
                            <label>Tên sản phẩm</label>
                            <input type="text" class="form-control" name="product_name"
                                   value="${product != null ? product.product_name : ''}" required>
                        </div>

                        <div class="form-group">
                            <label>Mô tả chi tiết</label>
                            <textarea class="form-control" name="description" rows="6">${product != null ? product.description : ''}</textarea>
                        </div>
                    </div>

                    <div class="admin-card">
                        <h3>Dữ liệu giá & Kho</h3>
                        <div class="admin-grid" style="grid-template-columns: 1fr 1fr; gap: 20px;">
                            <div class="form-group">
                                <label>Giá bán (VNĐ)</label>
                                <input type="number" class="form-control" name="price"
                                       value="${product != null && product.price > 0 ? String.format('%.0f', product.price) : ''}" required>
                            </div>

                            <div class="form-group">
                                <label>Số lượng tồn kho</label>
                                <input type="number" class="form-control" name="stock_quantity"
                                       value="${product != null ? product.stock_quantity : 0}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-sidebar">
                    <div class="admin-card">
                        <h3>Đăng sản phẩm</h3>
                        <div style="display: flex; flex-direction: column; gap: 10px;">
                            <button type="submit" id="btnSave" class="btn-primary" style="justify-content: center; width: 100%;">
                                <i class="fa-solid fa-save"></i>
                                ${product != null && product.product_id > 0 ? 'Cập nhật' : 'Thêm mới'}
                            </button>
                        </div>
                    </div>

                    <div class="admin-card">
                        <h3>Phân loại</h3>
                        <div class="form-group">
                            <label>Category ID (Nhập số)</label>
                            <input type="number" class="form-control" name="category_id"
                                   value="${product != null ? product.category_id : ''}" placeholder="Ví dụ: 1">
                            <small style="color: #666; font-size: 12px;">ID phải tồn tại trong bảng Categories</small>
                        </div>
                        <div class="form-group">
                            <label>Brand ID (Nhập số)</label>
                            <input type="number" class="form-control" name="brand_id"
                                   value="${product != null ? product.brand_id : ''}" placeholder="Ví dụ: 1">
                        </div>
                    </div>

                    <div class="admin-card">
                        <h3>Ảnh sản phẩm</h3>
                        <div class="form-group">
                            <label>Link ảnh (URL)</label>
                            <input type="text" class="form-control" name="image" id="imgInput"
                                   value="${product != null ? product.image : ''}" onchange="previewImage()" placeholder="https://...">
                        </div>

                        <div class="image-upload-area">
                            <div class="upload-placeholder" id="placeholder" style="${product != null && product.image != null && product.image != '' ? 'display:none' : ''}">
                                <i class="fa-regular fa-image"></i>
                                <p>Nhập link để xem trước</p>
                            </div>

                            <div class="image-preview-container" id="previewContainer" style="${product != null && product.image != null && product.image != '' ? '' : 'display:none'}">
                                <div class="preview-item" style="width: 100%; height: 200px;">
                                    <img src="${product != null ? product.image : ''}" id="imgPreview"
                                         style="width: 100%; height: 100%; object-fit: contain;"
                                         onerror="this.src='https://via.placeholder.com/300?text=No+Image'">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </main>
</div>

<script>
    function previewImage() {
        const url = document.getElementById('imgInput').value;
        if(url) {
            document.getElementById('imgPreview').src = url;
            document.getElementById('placeholder').style.display = 'none';
            document.getElementById('previewContainer').style.display = 'grid';
        }
    }
</script>

<script>
    document.getElementById('productForm').addEventListener('submit', function() {
        var btn = document.getElementById('btnSave');
        // Vô hiệu hóa nút sau khi bấm
        setTimeout(function() {
            btn.disabled = true;
            btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Đang xử lý...';
        }, 10);
    });
</script>

<script>
    // Script Sidebar cũ của bạn
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