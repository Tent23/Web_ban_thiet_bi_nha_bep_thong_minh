<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>

    <link rel="stylesheet" href="./assets/css/Header.css">
    <link rel="stylesheet" href="./assets/css/index.css">
    <link rel="stylesheet" href="./assets/css/main.css">
    <link rel="stylesheet" href="./assets/css/products.css">
</head>

<body>

<jsp:include page="common/header.jsp"/>

<main class="container">
    <h1 class="page-title">Tất cả sản phẩm</h1>

    <div class="product-grid">

        <c:if test="${empty products}">
            <p>Không có sản phẩm nào.</p>
        </c:if>

        <c:forEach var="p" items="${products}">
            <div class="product-card">

                <!-- Không có ảnh vẫn OK -->
                <img src="${pageContext.request.contextPath}/${p.thumbnailUrl}"
                     alt="${p.name}" class="product-image"/>

                <h3 class="product-name">${p.name}</h3>

                <p class="price">${p.price} đ</p>

                <a href="${pageContext.request.contextPath}/product?id=${p.productId}"
                   class="btn btn-primary">
                    Xem chi tiết
                </a>
            </div>
        </c:forEach>

    </div>
</main>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
