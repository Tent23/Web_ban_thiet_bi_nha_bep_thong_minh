<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${brand.brand_name} - Bếp Thông Minh</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Brand.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
</head>

<body>

<jsp:include page="common/header.jsp"/>

<main class="brand-page">

    <section class="brand-hero">
        <h1>${brand.brand_name}</h1>
        <p>${brand.slogan}</p>
    </section>

    <!-- GIỚI THIỆU -->
    <section class="brand-info">
        <div class="container">
            <img src="${brand.logo_url}" alt="${brand.brand_name}">
            <div class="brand-info-text">
                <h2>Giới thiệu về ${brand.brand_name}</h2>
                <p>${brand.description}</p>
            </div>
        </div>
    </section>

    <section class="brand-products">
        <div class="container">
            <h2>Sản phẩm của ${brand.brand_name}</h2>

            <c:choose>
                <c:when test="${empty products}">
                    <p class="empty-text">Hiện chưa có sản phẩm cho thương hiệu này.</p>
                </c:when>

                <c:otherwise>
                    <div class="product-list">
                        <c:forEach var="p" items="${products}">
                            <div class="product">
                                <a href="${pageContext.request.contextPath}/product?id=${p.product_id}">
                                    <img src="${p.image}" alt="${p.product_name}">
                                </a>

                                <h3 class="product-name">${p.product_name}</h3>

                                <p class="product-price">
                                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="₫"/>
                                </p>

                                <a class="btn-buy"
                                   href="${pageContext.request.contextPath}/product?id=${p.product_id}">
                                    Xem chi tiết
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </section>

</main>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
