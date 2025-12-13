<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8" />
    <title>Cửa hàng bếp</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/Header.css" />
    <link rel="stylesheet" href="assets/css/indexfont.css" />

    <link rel="stylesheet" href="assets/css/index.css" />
</head>

<body>

<jsp:include page="common/header.jsp"></jsp:include>

<main class="main-content">
    <section class="hero-banner swiper" id="hero-slider">
        <div class="swiper-wrapper">
            <div class="swiper-slide hero-slide" style="background-image: url('assets/images/banners/banner3.png');">
                <div class="hero-content">
                    <h1>Nâng Tầm Trải Nghiệm Bếp</h1>
                    <p>Khám phá các giải pháp nhà bếp thông minh, tự động hóa toàn diện.</p>
                    <a href="#" class="btn btn-primary">Khám Phá Ngay</a>
                </div>
            </div>
            <div class="swiper-slide hero-slide" style="background-image: url('assets/images/banners/banner4.jpg');">
                <div class="hero-content">
                    <h1>An Toàn & Tiện Lợi</h1>
                    <p>Hệ thống cảm biến và điều khiển từ xa.</p>
                    <a href="#" class="btn btn-primary">Xem Sản Phẩm</a>
                </div>
            </div>
        </div>
        <div class="swiper-pagination"></div>
    </section>

    <section class="trust-badges section-padding">
        <div class="container">
            <div class="trust-badge">
                <i class="fa fa-shield-halved"></i>
                <h3>Cam kết 100% Chính Hãng</h3>
            </div>
            <div class="trust-badge">
                <i class="fa fa-tools"></i>
                <h3>Miễn phí Lắp Đặt & Cài Đặt</h3>
            </div>
            <div class="trust-badge">
                <i class="fa fa-headset"></i>
                <h3>Hỗ trợ Kỹ thuật 24/7</h3>
            </div>
            <div class="trust-badge">
                <i class="fa fa-truck-fast"></i>
                <h3>Bảo hành Tận nhà</h3>
            </div>
        </div>
    </section>

    <section class="best-sellers section-padding bg-light">
        <div class="container">
            <h2 class="section-title">Sản phẩm Nổi bật (Dữ liệu từ Database)</h2>

            <div class="product-grid" style="display: flex; gap: 20px; flex-wrap: wrap;">
                <c:forEach items="${listP}" var="p">
                    <div class="product-card" style="width: 23%; border: 1px solid #eee; padding: 10px; background: #fff;">
                        <img src="${p.image}" alt="${p.name}" style="width: 100%; object-fit: cover;" />
                        <h3>${p.name}</h3>
                        <div class="price" style="color: #d0011b; font-weight: bold;">${p.priceFormat}</div>
                        <a href="chi-tiet?id=${p.id}" class="btn btn-secondary">Xem chi tiết</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

</main>

<jsp:include page="common/footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="assets/js/Main.js"></script>
</body>
</html>