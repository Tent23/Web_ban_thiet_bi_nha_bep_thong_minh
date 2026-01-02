<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${category != null ? 'Cập nhật Danh mục' : 'Thêm Danh mục mới'}</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/indexfont.css" />

</head>
<body>

<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <div class="admin-main">
        <header class="admin-header">
            <div class="header-left">
                <a href="${pageContext.request.contextPath}/admin/categories" class="btn-back">
                    <i class="fa-solid fa-arrow-left"></i> Quay lại danh sách
                </a>
                <h2 style="margin-left: 15px;">
                    ${category != null ? 'Cập nhật Danh mục' : 'Thêm Danh mục mới'}
                </h2>
            </div>

            <div class="admin-header-actions">
                <a href="${pageContext.request.contextPath}/admin/categories" class="btn-secondary">
                    Hủy bỏ
                </a>
                <button type="submit" form="categoryForm" class="btn-primary">
                    <i class="fa-solid fa-save"></i> Lưu lại
                </button>
            </div>
        </header>

        <main class="admin-content">
            <form id="categoryForm" action="${pageContext.request.contextPath}/admin/categories" method="post" class="admin-form-layout">

                <c:if test="${category != null}">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="category_id" value="${category.category_id}">
                </c:if>
                <c:if test="${category == null}">
                    <input type="hidden" name="action" value="insert">
                </c:if>

                <div class="form-col-main">
                    <div class="admin-card">
                        <h3>Thông tin chung</h3>

                        <div class="form-group">
                            <label>Tên danh mục <span style="color:var(--red)">*</span></label>
                            <input type="text" name="category_name"
                                   class="form-control"
                                   value="${category.category_name}"
                                   placeholder="Ví dụ: Bếp từ, Máy hút mùi..." required>
                        </div>

                        <div class="form-group">
                            <label>Mô tả (Tùy chọn)</label>
                            <textarea class="form-control" rows="4" placeholder="Nhập mô tả ngắn về danh mục này..."></textarea>
                        </div>

                        <div class="form-group">
                            <label>Hình ảnh / Logo</label>

                            <input type="text" name="logo" id="logoInput"
                                   class="form-control"
                                   value="${category.logo}"
                                   placeholder="Dán đường dẫn ảnh (URL) vào đây..."
                                   oninput="updatePreview(this.value)"
                                   style="margin-bottom: 15px;">

                            <div class="image-upload-area">
                                <div class="upload-placeholder">
                                    <i class="fa-solid fa-cloud-arrow-up"></i>
                                    <p>Kéo thả ảnh vào đây hoặc click để tải lên</p>
                                    <span style="font-size: 0.8rem; color: #94a3b8;">(Hiện tại hỗ trợ nhập URL ở trên)</span>
                                </div>
                            </div>

                            <div class="image-preview-container" id="previewContainer" style="${empty category.logo ? 'display:none;' : ''}">
                                <div class="preview-item">
                                    <img src="${category.logo}" id="imgPreview" alt="Preview">
                                    <button type="button" class="btn-remove-img" onclick="removeImage()">
                                        <i class="fa-solid fa-times"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-col-sidebar">
                    <div class="admin-card">
                        <h3>Trạng thái</h3>

                        <div class="form-group">
                            <label>Hiển thị danh mục</label>
                            <div class="toggle-group">
                                <label class="switch">
                                    <input type="checkbox" checked>
                                    <span class="slider round"></span>
                                </label>
                                <span>Đang hoạt động</span>
                            </div>
                        </div>

                        <div class="divider"></div>

                        <div class="form-group">
                            <label>Thứ tự hiển thị</label>
                            <input type="number" class="form-control" value="0">
                        </div>
                    </div>

                    <div class="admin-card">
                        <h3>Lưu ý</h3>
                        <p style="font-size: 0.9rem; color: var(--admin-text-light); line-height: 1.5;">
                            Vui lòng kiểm tra kỹ tên danh mục để tránh trùng lặp. Ảnh đại diện nên có tỉ lệ vuông hoặc 4:3 để hiển thị đẹp nhất trên trang chủ.
                        </p>
                    </div>
                </div>

            </form>
        </main>
    </div>
</div>

<script>
    function updatePreview(url) {
        const container = document.getElementById('previewContainer');
        const img = document.getElementById('imgPreview');
        if (url.trim() !== "") {
            img.src = url;
            container.style.display = 'grid'; // Grid theo CSS .image-preview-container
        } else {
            container.style.display = 'none';
        }
    }

    function removeImage() {
        document.getElementById('logoInput').value = '';
        document.getElementById('previewContainer').style.display = 'none';
    }
</script>

</body>
</html>