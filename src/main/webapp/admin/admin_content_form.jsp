<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm bài viết mới | Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">

    <script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
</head>
<body>

<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>

    <main class="admin-main">
        <header class="admin-header">
            <div class="header-left">
                <h2>Quản lý nội dung / Bài viết mới</h2>
            </div>
        </header>

        <div class="admin-content">
            <c:if test="${not empty errorMessage}">
                <div style="color: red; margin-bottom: 10px;">${errorMessage}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/admin/add-article" method="post">
            <div class="card">
                <div class="card-body">

                        <input type="hidden" name="action" value="create">

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group mb-3">
                                    <label class="form-label">Tiêu đề bài viết</label>
                                    <input type="text" name="title" class="form-control" value="${oldArticle.title}" placeholder="Ví dụ: Cách chọn thiết bị bếp công nghiệp..." required>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label class="form-label">Tác giả</label>
                                    <input type="text" name="author" class="form-control" value="${oldArticle.author}" placeholder="Tên tác giả hoặc Admin">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label class="form-label">Thể loại (Tip)</label>
                                    <input type="text" name="tip" class="form-control" value="${oldArticle.tip}" placeholder="Mẹo vặt / Tư vấn / Xu hướng">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label class="form-label">Tóm tắt nội dung</label>
                                    <textarea name="summary" class="form-control" rows="12" placeholder="Tóm tắt">${oldArticle.content}</textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label class="form-label">Danh mục</label>
                                    <input type="text" name="cate" class="form-control" placeholder="Nhập ID danh mục ">
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group mb-3">
                                    <label class="form-label">Nội dung bài viết</label>
                                    <textarea name="body" id="content-editor" class="form-control" rows="12" placeholder="Nhập nội dung bài viết chi tiết tại đây...">${oldArticle.body}</textarea>
                                </div>
                            </div>


                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label class="form-label">Hình ảnh đại diện (Thumbnail)</label>
                                    <input type="text" name="image" placeholder="URL đến ảnh" class="form-control" >
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label class="form-label">Trạng thái bài đăng</label>
                                    <select name="is_active" class="form-control">
                                        <option value="1" ${param.is_active == 1 ? 'selected' : ''}>Đã xuất bản (Hiện trên web)</option>
                                        <option value="0" ${param.is_active == 0 ? 'selected' : ''}>Bản nháp (Lưu tạm/Ẩn)</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="text-end mt-4">
                            <button type="reset" class="btn btn-secondary">
                                <i class="fa-solid fa-rotate-left"></i> Làm lại
                            </button>
                            <button type="submit" class="btn btn-primary">
                                <i class="fa-solid fa-paper-plane"></i> Đăng bài viết
                            </button>
                        </div>

                </div>
            </div>
            </form>
        </div>
    </main>
</div>

<script>
    CKEDITOR.replace('content-editor', {
        height: 400,

        language: 'vi',
        versionCheck: false
    });
</script>

</body>
</html>