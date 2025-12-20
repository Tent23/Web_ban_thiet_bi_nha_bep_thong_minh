<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <title>Cửa hàng bếp</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link rel="stylesheet" href="assets/css/Header.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/Login.css">
    <link rel="stylesheet" href="assets/css/main.css">


</head>

<body>

<jsp:include page="common/header.jsp"/>

<main class="main-content">

    <!-- HERO -->
    <section class="hero-banner swiper" id="hero-slider">
        <div class="swiper-wrapper">

            <div class="swiper-slide hero-slide"
                 style="background-image: url('${pageContext.request.contextPath}/assets/images/banners/banner3.png');">
                <div class="hero-content">
                    <h1>Nâng Tầm Trải Nghiệm Bếp</h1>
                    <p>Khám phá các giải pháp nhà bếp thông minh, tự động hóa toàn diện.</p>
                    <a href="${pageContext.request.contextPath}/assets" class="btn btn-primary">Khám Phá Ngay</a>
                </div>
            </div>

            <div class="swiper-slide hero-slide"
                 style="background-image: url('${pageContext.request.contextPath}/assets/images/banners/banner4.jpg');">
                <div class="hero-content">
                    <h1>An Toàn & Tiện Lợi</h1>
                    <p>Hệ thống cảm biến và điều khiển từ xa.</p>
                    <a href="#" class="btn btn-primary">Xem Sản Phẩm</a>
                </div>
            </div>

        </div>
        <div class="swiper-pagination"></div>
    </section>

    <!-- DANH MỤC -->
    <section class="featured-categories section-padding">
        <div class="container">
            <h2 class="section-title">Danh mục Nổi bật</h2>
            <div class="category-grid">

                <c:forEach var="cat" items="${categories}">
                    <a href="${pageContext.request.contextPath}/category?id=${cat.id}" class="category-item">
                        <img src="${pageContext.request.contextPath}/${cat.image}" alt="${cat.name}" />
                        <br>
                        <span>${cat.name}</span>
                    </a>
                </c:forEach>

            </div>
        </div>
    </section>

    <!-- SẢN PHẨM BÁN CHẠY -->
    <section class="best-sellers section-padding bg-light">
        <div class="container">
            <h2 class="section-title">Sản phẩm Bán chạy nhất</h2>

            <div class="swiper" id="bestseller-slider">
                <div class="swiper-wrapper">

                    <c:forEach var="p" items="${bestProducts}">
                        <div class="swiper-slide product-card">
                            <img src="${pageContext.request.contextPath}/${p.thumbnailUrl}" alt="${p.name}" />
                            <h3>${p.name}</h3>
                            <div class="price">
                                <fmt:formatNumber value="${p.price}" type="number"/> đ
                            </div>
                            <a href="${pageContext.request.contextPath}/product/${p.slug}"
                               class="btn btn-secondary">
                                Xem chi tiết
                            </a>
                        </div>
                    </c:forEach>

                </div>

                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
    </section>

    <!-- GIẢI PHÁP -->
    <section class="solutions-combo section-padding bg-light">
        <div class="container">
            <h2 class="section-title">Gợi ý Giải pháp</h2>
            <div class="solutions-grid">

                <c:forEach var="combo" items="${combos}">
                    <a href="${pageContext.request.contextPath}/combo?id=${combo.id}"
                       class="solution-item"
                       style="background-image: url('${pageContext.request.contextPath}/${combo.image}');">
                        <div class="solution-content">
                            <h3>${combo.title}</h3>
                            <p>${combo.description}</p>
                        </div>
                    </a>
                </c:forEach>

            </div>
        </div>
    </section>

</main>

<jsp:include page="common/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/Main.js"></script>

</body>
</html>