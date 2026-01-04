<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Chỉnh sửa Hệ Sinh Thái</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
    <link rel="stylesheet" href="../assets/css/indexfont.css" />
    <style>
        /* CSS đơn giản cho phần gợi ý tìm kiếm */
        .search-box { position: relative; width: 100%; max-width: 400px; margin-bottom: 20px; }
        .search-input { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
        .suggestions {
            position: absolute; top: 100%; left: 0; width: 100%; background: white;
            border: 1px solid #ddd; z-index: 1000; max-height: 200px; overflow-y: auto; display: none;
        }
        .suggestion-item { padding: 10px; cursor: pointer; border-bottom: 1px solid #eee; }
        .suggestion-item:hover { background: #f0f0f0; }
        .product-list table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .product-list th, .product-list td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        /* Thêm CSS mới này để sửa lỗi nút bấm */
        .form-actions {
            display: flex;          /* Xếp hàng ngang */
            align-items: center;    /* Canh giữa theo chiều dọc */
            gap: 15px;             /* Khoảng cách giữa 2 nút */
            margin-top: 25px;       /* Cách xa các ô input bên trên */
        }

        /* CSS cho nút Primary (Lưu) */
        .btn-primary {
            background-color: #0f6cbd;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 600;
            font-size: 14px;
            text-decoration: none;
            height: 40px; /* Chiều cao cố định */
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }
        .btn-primary:hover { background-color: #0c569b; }

        /* CSS cho nút Secondary (Hủy) */
        .btn-secondary {
            background-color: #f1f5f9;
            color: #475569;
            padding: 10px 20px;
            border: 1px solid #cbd5e1;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 600;
            font-size: 14px;
            text-decoration: none; /* Bỏ gạch chân */
            height: 40px; /* Chiều cao cố định bằng nút Lưu */
            display: inline-flex;
            align-items: center;
            justify-content: center;
            box-sizing: border-box; /* Đảm bảo padding không làm vỡ khung */
        }
        .btn-secondary:hover {
            background-color: #e2e8f0;
            color: #1e293b;
        }
    </style>
</head>
<body>
<div class="admin-layout">
    <jsp:include page="common/sidebar.jsp"></jsp:include>
    <div class="admin-main">
        <header class="admin-header">
            <h2>${empty ecosystem ? 'Thêm mới' : 'Cập nhật'} Hệ Sinh Thái</h2>
        </header>

        <main class="admin-content">
            <div class="admin-card">
                <form action="${pageContext.request.contextPath}/admin/ecosystems" method="post">
                    <input type="hidden" name="action" value="${empty ecosystem ? 'insert' : 'update'}">
                    <c:if test="${not empty ecosystem}">
                        <input type="hidden" name="id" value="${ecosystem.id}">
                    </c:if>

                    <div class="form-group">
                        <label>Tên Hệ Sinh Thái:</label>
                        <input type="text" name="name" class="form-control" value="${ecosystem.name}" required>
                    </div>

                    <div class="form-group">
                        <label>Link Ảnh (URL):</label>
                        <input type="text" name="image" class="form-control" value="${ecosystem.image}">
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-primary">
                            <i class="fa-solid fa-floppy-disk"></i> Lưu thông tin
                        </button>

                        <a href="${pageContext.request.contextPath}/admin/ecosystems" class="btn-secondary">
                            <i class="fa-solid fa-xmark"></i> Hủy
                        </a>
                    </div>
                </form>
            </div>

            <c:if test="${not empty ecosystem}">
                <div class="admin-card" style="margin-top: 20px;">
                    <h3>Thêm sản phẩm vào Hệ sinh thái này</h3>

                    <form action="${pageContext.request.contextPath}/admin/ecosystems" method="post" style="display: flex; gap: 10px; align-items: flex-start;">
                        <input type="hidden" name="action" value="add_product">
                        <input type="hidden" name="ecosystem_id" value="${ecosystem.id}">
                        <input type="hidden" id="selectedProductId" name="product_id_to_add" required>

                        <div class="search-box">
                            <input type="text" id="productSearch" class="search-input" placeholder="Gõ tên sản phẩm để tìm..." autocomplete="off">
                            <div id="suggestions" class="suggestions"></div>
                        </div>

                        <button type="submit" class="btn-primary" id="btnAddProduct" disabled>
                            <i class="fa-solid fa-plus"></i> Thêm ngay
                        </button>
                    </form>

                    <div class="product-list">
                        <h4>Danh sách sản phẩm hiện có (${products.size()})</h4>
                        <table>
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Ảnh</th>
                                <th>Tên sản phẩm</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="p" items="${products}">
                                <tr>
                                    <td>${p.product_id}</td>
                                    <td><img src="${p.image}" width="50" style="object-fit: contain;"></td>                                    <td>${p.product_name}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/ecosystems?action=remove_product&id=${ecosystem.id}&pid=${p.product_id}"
                                           class="btn-action delete" onclick="return confirm('Gỡ sản phẩm này khỏi hệ?');">
                                            <i class="fa-solid fa-trash"></i> Gỡ
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </main>
    </div>
</div>

<script>
    const searchInput = document.getElementById('productSearch');
    const suggestionsBox = document.getElementById('suggestions');
    const hiddenInput = document.getElementById('selectedProductId');
    const btnAdd = document.getElementById('btnAddProduct');

    if (searchInput) {
        searchInput.addEventListener('input', function() {
            const query = this.value;
            if (query.length < 2) {
                suggestionsBox.style.display = 'none';
                return;
            }

            // Gọi API tìm kiếm (Giả lập: bạn cần có API search thật hoặc gọi servlet)
            // Ở đây dùng fetch gọi đến ProductSearchApiServlet mà ta đã làm trước đó
            fetch('${pageContext.request.contextPath}/api/product-search?q=' + encodeURIComponent(query))
                .then(response => response.json())
                .then(data => {
                    suggestionsBox.innerHTML = '';
                    if (data.length > 0) {
                        data.forEach(prod => {
                            const div = document.createElement('div');
                            div.className = 'suggestion-item';
                            // Hiển thị tên sản phẩm - ID
                            div.textContent = prod.product_name + " (ID: " + prod.product_id + ")";
                            div.onclick = function() {
                                searchInput.value = prod.product_name; // Điền tên vào ô tìm
                                hiddenInput.value = prod.product_id;   // Điền ID vào ô ẩn
                                suggestionsBox.style.display = 'none';
                                btnAdd.disabled = false; // Bật nút thêm
                                btnAdd.style.opacity = "1";
                            };
                            suggestionsBox.appendChild(div);
                        });
                        suggestionsBox.style.display = 'block';
                    } else {
                        suggestionsBox.style.display = 'none';
                    }
                })
                .catch(err => console.error('Lỗi tìm kiếm:', err));
        });

        // Ẩn gợi ý khi click ra ngoài
        document.addEventListener('click', function(e) {
            if (e.target !== searchInput && e.target !== suggestionsBox) {
                suggestionsBox.style.display = 'none';
            }
        });
    }
</script>
</body>
</html>