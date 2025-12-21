<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Cửa hàng bếp</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/Header.css" />
    <link rel="stylesheet" href="assets/css/index.css" />
    <link rel="stylesheet" href="assets/css/indexfont.css" />
</head>

<body>



<main class="main-content">
    <section class="hero-banner swiper" id="hero-slider">
        <div class="swiper-wrapper">
            <div class="swiper-slide hero-slide" style="
              background-image: url('./assets/images/banners/banner3.png');
            ">
                <div class="hero-content">
                    <h1>Nâng Tầm Trải Nghiệm Bếp</h1>
                    <p>
                        Khám phá các giải pháp nhà bếp thông minh, tự động hóa toàn
                        diện.
                    </p>
                    <a href="./assets" class="btn btn-primary">Khám Phá Ngay</a>
                </div>
            </div>
            <div class="swiper-slide hero-slide" style="
              background-image: url('./assets/images/banners/banner4.jpg');
            ">
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

    <section class="featured-categories section-padding">
        <div class="container">
            <h2 class="section-title">Danh mục Nổi bật</h2>
            <div class="category-grid">
                <a href="pages/QuangToan/Beptu.html" class="category-item">
                    <img src="assets/images/products/beptu-2.jpg" alt="Bếp từ" />
                    <br>
                    <span>Bếp từ & Nấu nướng</span>
                </a>
                <a href="pages/QuangToan/Robot.html" class="category-item">
                    <img src="assets/images/products/robot-2.jpg" alt="Robot Hút Bụi" />
                    <br>
                    <span>Robot Hút Bụi</span>
                </a>
                <a href="pages/QuangToan/Tulanh.html" class="category-item">
                    <img src="assets/images/products/Tulanh-1.jpg" alt="Tủ Lạnh" />
                    <br>
                    <span>Tủ lạnh Thông minh</span>
                </a>
                <a href="pages/QuangToan/Mayruabat.html" class="category-item">
                    <img src="assets/images/products/mayruabat-4.jpg" alt="Máy Rửa Bát" />
                    <br>
                    <span>Máy rửa bát</span>
                </a>
                <a href="pages/QuangToan/Cambien.html" class="category-item">
                    <img src="assets/images/products/cambieng-3.jpg" alt="Cảm Biến" />
                    <br>
                    <span>Cảm biến & An ninh</span>
                </a>
                <a href="pages/QuangToan/Phache.html" class="category-item">
                    <img src="assets/images/products/phache-3.jpg" alt="Pha Chế" />
                    <br>
                    <span>Pha chế Thông minh</span>
                </a>
            </div>
        </div>
    </section>

    <section class="best-sellers section-padding bg-light">
        <div class="container">
            <h2 class="section-title">Sản phẩm Bán chạy nhất</h2>
            <div class="swiper" id="bestseller-slider">
                <div class="swiper-wrapper">
                    <c:forEach var = "pbs" items="${listP}">
                    <div class="swiper-slide product-card">
                        <img src="${pbs.image}" alt="Sản phẩm 1" />
                        <h3>${pbs.name}</h3>
                        <div class="price">${pbs.priceFormat}</div>
                        <a href="pages/QuangToan/Chitietsp.html" class="btn btn-secondary">Xem chi tiết</a>
                    </div>
                    </c:forEach>

                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
    </section>

    <section class="shop-by-ecosystem section-padding">
        <div class="container">
            <h2 class="section-title">Mua theo Hệ sinh thái</h2>
            <div class="ecosystem-grid">
                <c:forEach var = "e" items="${listE}">
                <a href="#" class="ecosystem-item" style="background-color: #e5e5e5">
                    <img src="${e.image}" alt="" />
                    <span>${e.name}</span>
                </a>
                </c:forEach>
            </div>
        </div>
    </section>

    <section class="solutions-combo section-padding bg-light">
        <div class="container">
            <h2 class="section-title">Gợi ý Giải pháp</h2>
            <div class="solutions-grid">
                <a href="pages/TrongBao/giaiphapvacombo.html" class="solution-item" style="
                    background-image: url('assets/images/products/combosp1.png');
                ">
                    <div class="solution-content">
                        <h3>Combo Bếp Căn hộ 25 Triệu</h3>
                        <p>Bếp từ + Hút mùi + Robot</p>
                    </div>
                </a>
                <a href="pages/TrongBao/giaiphapvacombo.html" class="solution-item" style="
                    background-image: url('assets/images/products/combosp2.png');
                ">
                    <div class="solution-content">
                        <h3>Giải pháp An ninh Bếp</h3>
                        <p>Cảm biến gas, khói, camera...</p>
                    </div>
                </a>
            </div>
        </div>
    </section>

    <section class="content-hub section-padding">
        <div class="container">
            <h2 class="section-title">Góc Tư vấn & Đánh giá</h2>
            <div class="hub-grid">
                <div class="hub-video">
                    <iframe width="560" height="315"
                            src="https://www.youtube.com/embed/watch?v=6JdW3GRxF3w&list=RD6JdW3GRxF3w&start_radio=1"
                            title="YouTube video player" frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen></iframe>
                </div>
                <div class="hub-articles">
                    <a href="pages/TrongBao/goctuvan.html" class="article-item">
                        <h4>So sánh Bếp từ thông minh Bosch và Hafele</h4>
                        <p>Đâu là lựa chọn tối ưu cho căn bếp của bạn?</p>
                    </a>
                    <a href="pages/TrongBao/goctuvan.html" class="article-item">
                        <h4>5 Kịch bản tự động hóa bếp bạn PHẢI BIẾT</h4>
                        <p>Tự động bật hút mùi, tắt đèn khi ra khỏi...</p>
                    </a>
                    <a href="pages/TrongBao/goctuvan.html" class="article-item">
                        <h4>Hướng dẫn cài đặt App Mi Home cho người mới</h4>
                        <p>Kết nối tất cả thiết bị Xiaomi trong 5 phút.</p>
                    </a>
                </div>
            </div>
        </div>
    </section>

    <section class="testimonials section-padding bg-light">
        <div class="container">
            <h2 class="section-title">Khách hàng Nói về Chúng tôi</h2>
            <div class="swiper" id="testimonial-slider">
                <div class="swiper-wrapper">
                    <div class="swiper-slide testimonial-item">
                        <p>
                            "Dịch vụ lắp đặt rất chuyên nghiệp, các bạn kỹ thuật viên hỗ
                            trợ cài app rất nhiệt tình."
                        </p>
                        <h4>- Chị An (Quận 7)</h4>
                    </div>
                    <div class="swiper-slide testimonial-item">
                        <p>
                            "Sản phẩm chính hãng, dùng rất ưng ý. Sẽ tiếp tục ủng hộ
                            shop."
                        </p>
                        <h4>- Anh Bách (Hà Nội)</h4>
                    </div>
                </div>
                <div class="swiper-pagination"></div>
            </div>
        </div>
    </section>

    <section class="brands section-padding">
        <div class="container">
            <h2 class="section-title">Các Thương hiệu Hàng đầu</h2>
            <div class="swiper" id="brand-slider">
                <div class="swiper-wrapper">
                    <c:forEach var ="b" items="${listB}">
                    <div class="swiper-slide brand-item">
                        <a href="pages/QuangToan/Brand.html">
                            <img src="${b.image}" alt="${p.name}" />
                        </a>
                    </div>

                    </c:forEach>


                </div>
            </div>
        </div>
    </section>
</main>



<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="assets/js/Main.js"></script>
</body>
</html>