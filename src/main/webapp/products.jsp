<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8" />
    <title>Tất cả sản phẩm - Bếp Thông Minh TTB</title>
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/Header.css" />
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/products.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>

<body>
<!-- HEADER (Giữ nguyên đồng bộ) -->
<header class="header">
    <div class="header__top-bar">
        <div class="container">
            <span><i class="fa fa-phone"></i> Hỗ trợ Kỹ thuật: 1900.1234</span>
            <span><i class="fa fa-phone"></i> Kinh doanh: 1900.5678</span>
            <span class="spacer"></span>
            <a href="../TrongBao/Showroom.html">Hệ thống Showroom</a>
            <a href="../TrongBao/BaoHanh.html">Tra cứu Bảo hành</a>
        </div>
    </div>
    <div class="header__main">
        <div class="container">
            <a href="../../index.html" class="header__logo">
                <img src="../../assets/images/banners/logo.png" alt="TTB" />
            </a>
            <div class="header__search">
                <input type="text" placeholder="Tìm kiếm bếp từ, robot hút bụi..." />
                <button><i class="fa fa-search"></i></button>
            </div>
            <div class="header__actions">
                <a href="../QuangToan/Account.html"><i class="fa fa-user"></i> Tài khoản</a>
                <a href="yeuthich.html"><i class="fa fa-heart"></i> Yêu thích</a>
                <a href="giohang.html"><i class="fa fa-shopping-cart"></i> Giỏ hàng (0)</a>
            </div>
        </div>
    </div>
    <nav class="header__nav">
        <div class="container">
            <ul>
                <li class="nav-item has-megamenu">
                    <a href="#">Sản phẩm <i class="fa fa-chevron-down"></i></a>
                    <div class="mega-menu">
                        <a href="${pageContext.request.contextPath}/products?categoryId=1">
                            Bếp từ
                        </a>
                        <a href="${pageContext.request.contextPath}/products?category=2">
                            Tủ lạnh
                        </a>
                        <a href="${pageContext.request.contextPath}/products?category=3">
                            Robot Hút bụi
                        </a>
                        <a href="${pageContext.request.contextPath}/products?category=3">
                            Máy rửa bát
                        </a>
                        <a href="${pageContext.request.contextPath}/products?category=4">
                            Cảm biến & An ninh
                        </a>
                        <a href="${pageContext.request.contextPath}/products?category=5">
                            Pha chế
                        </a>
                    </div>
                </li>

                <li class="nav-item"><a href="#">Giải pháp & Combo</a></li>
                <li class="nav-item"><a href="../TrongBao/goctuvan.html">Góc Tư vấn</a></li>
                <li class="nav-item"><a href="../TrongBao/DichVuLapDat.html">Dịch vụ Lắp đặt</a></li>
                <li class="nav-item"><a href="vechungtoi.html">Về chúng tôi</a></li>
                <li class="nav-item"><a href="khuyenmai.html">Khuyến mãi</a></li>
            </ul>
        </div>
    </nav>
</header>

<main class="main-content">
    <!-- Breadcrumb -->
    <div class="breadcrumb section-padding" style="padding-bottom: 0;">
        <div class="container">
            <p> <span style="color: var(--text-secondary);">Tất cả sản phẩm</span></p>
        </div>
    </div>

    <section class="shop-section section-padding">
        <div class="container">
            <div class="shop-layout">

                <!-- SIDEBAR: BỘ LỌC -->
                <aside class="shop-sidebar">
                    <form method="get" action="${pageContext.request.contextPath}/products">
                    <div class="filter-group">
                        <h3>Thương hiệu</h3>
                        <input type="hidden" name="categoryId" value="${categoryId}" />
                        <c:forEach var="b" items="${brandList}">
                            <label>
                                <input type="checkbox"
                                       name="brand"
                                       value="${b.brand_id}"
                                <c:if test="${brands != null && fn:contains(brands, b.brand_id)}">
                                       checked
                                </c:if>
                                >
                                    ${b.brand_name}
                            </label>
                        </c:forEach>
                        </div>
                        <input type="hidden" name="sort" value="${sort}" />
                        <div class="filter-group">
                            <h3>Khoảng giá</h3>
                            <input type="hidden" name="categoryId" value="${categoryId}" />
                            <label><input type="radio" name="priceRange" value="1" ${priceRange=='1'?'checked':''}> Dưới 5 triệu</label>
                            <label><input type="radio" name="priceRange" value="2" ${priceRange=='2'?'checked':''}> 5 - 10 triệu</label>
                            <label><input type="radio" name="priceRange" value="3" ${priceRange=='3'?'checked':''}> 10 - 20 triệu</label>
                            <label><input type="radio" name="priceRange" value="4" ${priceRange=='4'?'checked':''}> Trên 20 triệu</label>
                        </div>

                        <button type="submit" class="btn btn-primary">Lọc sản phẩm</button>
                    </form>
                </aside>


                <!-- PRODUCT GRID -->
                <div class="shop-main">
                    <div class="shop-header">
                        <h1>Tất cả sản phẩm</h1>
                        <form method="get" action="${pageContext.request.contextPath}/products">
                            <input type="hidden" name="priceRange" value="${priceRange}" />
                        <div class="sort-box">
                            <label>Sắp xếp:</label>
                            <input type="hidden" name="categoryId" value="${categoryId}" />
                            <select name="sort" onchange="this.form.submit()">
                                <option value="newest" ${sort=='newest' || sort==null?'selected':''}>Mới nhất</option>
                                <option value="price_asc" ${sort=='price_asc'?'selected':''}>Giá tăng dần</option>
                                <option value="price_desc" ${sort=='price_desc'?'selected':''}>Giá giảm dần</option>
                            </select>
                        </div>
                        </form>
                    </div>

                    <div class="shop-product-grid">
                        <c:forEach var="p" items="${products}">
                            <div class="product-card">
                                <img src="${p.image}">
                                <h3>${p.productname}</h3>
                                <h3>${p.description}</h3>
                                <div class="price">${p.priceFormat}</div>
                                <a href="${pageContext.request.contextPath}/product-detail?id=${p.productid}" class="btn btn-secondary">
                                    Xem chi tiết
                                </a>
                            </div>
                        </c:forEach>
                    </div>



                    <!-- PHÂN TRANG -->
                    <div class="pagination">
                        <a href="#" class="active">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#"><i class="fa-solid fa-arrow-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<footer class="footer">
    <div class="container">
        <div class="footer-grid">
            <div class="footer-col">
                <h4>VỀ CHÚNG TÔI</h4>
                <p>Công ty TNHH Bếp Thông Minh TTB<br>
                    MST: 031xxxxxxx<br>
                    Địa chỉ: Khu phố 6, Phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí Minh<br>
                    Hotline: 1900.1234<br>
                    Email: 23130356@.hcmuaf.edu.vn</p>
            </div>
            <div class="footer-col">
                <h4>HỖ TRỢ KHÁCH HÀNG</h4>
                <ul>
                    <li><a href="./chinhsachbaohanh.html">Chính sách Bảo hành</a></li>
                    <li><a href="../TrongBao/chinhsachvanchuyenvalapdat.html">Chính sách Vận chuyển & Lắp đặt</a></li>
                    <li><a href="../TrongBao/chinhsachdoitra.html">Chính sách Đổi trả</a></li>
                    <li><a href="./phuongthucthanhtoan.html">Phương thức Thanh toán</a></li>
                    <li><a href="./cauhoithuonggap">Câu hỏi Thường gặp (FAQs)</a></li>
                </ul>
            </div>
            <div class="footer-col">
                <h4>LIÊN KẾT NHANH</h4>
                <ul>
                    <li><a href="./vechungtoi.html">Về chúng tôi</a></li>
                    <li><a href="../TrongBao/Showroom.html">Hệ thống Showroom</a></li>
                    <li><a href="./tuyendung.html">Tuyển dụng</a></li>
                    <li><a href="./doitacB2B.html">Dành cho Đối tác B2B</a></li>
                </ul>
            </div>
            <div class="footer-col">
                <h4>KẾT NỐI VỚI CHÚNG TÔI</h4>
                <div class="social-icons">
                    <a href="#"><i class="fab fa-facebook-f"></i></a>
                    <a href="#"><i class="fab fa-youtube"></i></a>
                    <a href="#"><i class="fab fa-tiktok"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                </div>
                <p>Đăng ký nhận tin khuyến mãi:</p>
                <form class="subscribe-form">
                    <input type="email" placeholder="Email của bạn">
                    <button type="submit">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <p>&copy; 2025 Bản quyền thuộc về Bếp Thông Minh TTB.</p>
    </div>
</footer>
</body>
</html>