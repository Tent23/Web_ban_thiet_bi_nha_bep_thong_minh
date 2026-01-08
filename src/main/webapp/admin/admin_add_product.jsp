<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.product_id > 0 ? 'Cập nhật sản phẩm' : 'Thêm sản phẩm mới'} | Admin</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">

    <style>
        .alert { padding: 15px; margin-bottom: 20px; border-radius: 4px; font-weight: 500;}
        .alert-success { color: #155724; background-color: #d4edda; border: 1px solid #c3e6cb; }
        .alert-danger { color: #721c24; background-color: #f8d7da; border: 1px solid #f5c6cb; }

        /* Ẩn input file mặc định nếu muốn dùng URL ảnh */
        .image-preview-container { border: 1px solid #ddd; margin-top: 10px; border-radius: 5px; overflow: hidden;}
    </style>
</head>
<body>

<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <main class="admin-main">

        <header class="admin-header">
            <div class="header-left">
                <a href="${pageContext.request.contextPath}/admin/products" class="btn-back">
                    <i class="fa-solid fa-arrow-left"></i> Quay lại danh sách
                </a>
            </div>
            <div class="admin-profile">
                <h3 style="margin: 0;">${product.product_id > 0 ? 'Cập nhật sản phẩm' : 'Thêm sản phẩm mới'}</h3>
            </div>
        </header>

        <div class="admin-content">

            <c:if test="${not empty sessionScope.msg}">
                <div class="alert alert-success">
                    <i class="fa-solid fa-check-circle"></i> ${sessionScope.msg}
                    <% session.removeAttribute("msg"); %>
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <i class="fa-solid fa-triangle-exclamation"></i> ${error}
                </div>
            </c:if>

            <form id="productForm" action="${pageContext.request.contextPath}/admin/product-save" method="post" class="admin-form-layout">

                <input type="hidden" name="product_id" value="${product.product_id}">

                <div class="col-main">
                    <div class="admin-card">
                        <h3>Thông tin chung</h3>

                        <div class="form-group">
                            <label>Tên sản phẩm (*)</label>
                            <input type="text" class="form-control" name="product_name"
                                   value="${product.product_name}" required placeholder="Nhập tên sản phẩm...">
                        </div>

                        <div class="form-group">
                            <label>Mô tả chi tiết</label>
                            <textarea class="form-control" name="description" rows="6" placeholder="Mô tả sản phẩm...">${product.description}</textarea>
                        </div>
                    </div>

                    <div class="admin-card">
                        <h3>Dữ liệu giá & Kho</h3>
                        <div class="admin-grid" style="grid-template-columns: 1fr 1fr; gap: 20px;">
                            <div class="form-group">
                                <label>Giá bán (VNĐ) (*)</label>
                                <input type="number" class="form-control" name="price"
                                       value="${product.price > 0 ? String.format('%.0f', product.price) : ''}"
                                       required min="0" placeholder="0">
                            </div>

                            <div class="form-group">
                                <label>Số lượng tồn kho</label>
                                <input type="number" class="form-control" name="stock_quantity"
                                       value="${product.stock_quantity > 0 ? product.stock_quantity : ''}"
                                       min="0" placeholder="0">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-sidebar">
                    <div class="admin-card">
                        <h3>Hành động</h3>
                        <div style="display: flex; flex-direction: column; gap: 10px;">
                            <button type="submit" id="btnSave" class="btn-primary" style="justify-content: center; width: 100%;">
                                <i class="fa-solid fa-save"></i>
                                ${product.product_id > 0 ? 'Cập nhật thay đổi' : 'Lưu sản phẩm'}
                            </button>
                        </div>
                    </div>

                    <div class="admin-card">
                        <h3>Phân loại</h3>

                        <div class="form-group">
                            <label>Danh mục (*)</label>
                            <select name="category_id" class="form-control" required>
                                <option value="">-- Chọn danh mục --</option>
                                <c:forEach items="${listCategories}" var="c">
                                    <option value="${c.category_id}" ${product.category_id == c.category_id ? 'selected' : ''}>
                                            ${c.category_name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Thương hiệu (*)</label>
                            <select name="brand_id" class="form-control" required>
                                <option value="">-- Chọn thương hiệu --</option>
                                <c:forEach items="${listBrands}" var="b">
                                    <option value="${b.brand_id}" ${product.brand_id == b.brand_id ? 'selected' : ''}>
                                            ${b.brand_name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="admin-card">
                        <h3>Ảnh đại diện</h3>
                        <div class="form-group">
                            <label>Link ảnh (URL)</label>
                            <input type="text" class="form-control" name="image" id="imgInput"
                                   value="${product.image}" onchange="previewImage()" placeholder="https://example.com/image.jpg">
                        </div>

                        <div class="image-upload-area">
                            <div class="upload-placeholder" id="placeholder" style="${not empty product.image ? 'display:none' : ''}">
                                <i class="fa-regular fa-image" style="font-size: 2rem; color: #ccc;"></i>
                                <p style="font-size: 0.8rem; color: #888;">Nhập link để xem trước</p>
                            </div>

                            <div class="image-preview-container" id="previewContainer" style="${not empty product.image ? '' : 'display:none'}">
                                <div class="preview-item" style="width: 100%; height: 200px; display: flex; align-items: center; justify-content: center; background: #f9f9f9;">
                                    <img src="${product.image}" id="imgPreview"
                                         style="max-width: 100%; max-height: 100%; object-fit: contain;"
                                         onerror="this.src='https://via.placeholder.com/300?text=Lỗi+Ảnh'">
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
    // Hàm xem trước ảnh
    function previewImage() {
        const url = document.getElementById('imgInput').value.trim();
        const placeholder = document.getElementById('placeholder');
        const container = document.getElementById('previewContainer');
        const img = document.getElementById('imgPreview');

        if(url) {
            img.src = url;
            placeholder.style.display = 'none';
            container.style.display = 'block';
        } else {
            placeholder.style.display = 'flex'; // hoặc block tùy css của bạn
            container.style.display = 'none';
        }
    }

    // Chặn submit nhiều lần (Loading)
    document.getElementById('productForm').addEventListener('submit', function() {
        var btn = document.getElementById('btnSave');
        setTimeout(function() {
            btn.disabled = true;
            btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Đang xử lý...';
        }, 10);
    });
</script>

</body>
</html>