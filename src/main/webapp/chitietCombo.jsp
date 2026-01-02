<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết Combo Bếp Luxury - TTB</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="assets/css/ctCombo.css">
    <link rel="stylesheet" href="assets/css/Header.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/indexfont.css.css">

</head>
<header class="header">
    <div class="header__top-bar">
        <div class="container">
            <span><i class="fa fa-phone"></i> Hỗ trợ Kỹ thuật: 1900.1234</span>
            <span><i class="fa fa-phone"></i> Kinh doanh: 1900.5678</span>
            <span class="spacer"></span>
            <a href="./pages/TrongBao/Showroom.html">Hệ thống Showroom</a>
            <a href="./pages/TrongBao/BaoHanh.html">Tra cứu Bảo hành</a>
            <a href="./pages/QuangToan/Login.html">Đăng nhập</a>
        </div>
    </div>
    <div class="header__main">
        <div class="container">
            <a href="./index.html" class="header__logo">
                <img src="./assets/images/banners/logo.png" alt="TTB" />
            </a>
            <div class="header__search">
                <input type="text" placeholder="Tìm kiếm bếp từ, robot hút bụi..." />
                <button><i class="fa fa-search"></i></button>
            </div>
            <div class="header__actions">
                <a href="pages/QuangToan/Account.html"><i class="fa fa-user"></i> Tài khoản</a>
                <a href="./pages/ThanhTruong/yeuthich.html"><i class="fa fa-heart"></i> Yêu thích</a>
                <a href="./pages/ThanhTruong/giohang.html"><i class="fa fa-shopping-cart"></i> Giỏ hàng (0)</a>
            </div>
        </div>
    </div>
    <nav class="header__nav">
        <div class="container">
            <ul>
                <li class="nav-item has-megamenu">
                    <a href="./pages/ThanhTruong/products.html">Sản phẩm <i class="fa fa-chevron-down"></i></a>
                    <div class="mega-menu">
                        <a href="./pages/QuangToan/Beptu.html">Bếp từ</a>
                        <a href="./pages/QuangToan/Tulanh.html">Tủ lạnh</a>
                        <a href="./pages/QuangToan/Robot.html">Robot Hút bụi</a>
                        <a href="./pages/QuangToan/Mayruabat.html">Máy rửa bát</a>
                        <a href="./pages/QuangToan/Cambien.html">Cảm biến & An ninh</a>
                        <a href="./pages/QuangToan/Phache.html">Pha chế</a>
                    </div>
                </li>
                <li class="nav-item"><a href="pages/TrongBao/giaiphapvacombo.html">Giải pháp & Combo</a></li>
                <li class="nav-item"><a href="./pages/TrongBao/goctuvan.html">Góc Tư vấn</a></li>
                <li class="nav-item"><a href="./pages/TrongBao/DichVuLapDat.html">Dịch vụ Lắp đặt</a></li>
                <li class="nav-item"><a href="./pages/ThanhTruong/vechungtoi.html">Về chúng tôi</a></li>
                <li class="nav-item"><a href="./pages/ThanhTruong/khuyenmai.html">Khuyến mãi</a></li>
            </ul>
        </div>
    </nav>
</header>
<body class="bg-gray-50">

<main class="container mx-auto px-4 py-10 max-w-6xl">
    <nav class="text-sm text-gray-500 mb-6">
        <a href="Home" class="hover:text-blue-600">Trang chủ</a> /
        <a href="listcombo" class="hover:text-blue-600">Combo Sản phẩm</a> /
        <span class="text-gray-900 font-bold">${c.name}</span>
    </nav>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 bg-white p-8 rounded-3xl shadow-sm">

        <div class="gallery-container">
            <div class="main-img-box">
                <img src="${c.products[0].image}" alt="Bếp Luxury" class="main-image">
                <span class="discount-badge">GIẢM ${c.getPercent(c.baseprice,c.discountprice)}%</span>


            </div>

            <div class="flex gap-4 mt-4">
                <c:forEach items="${c.products}" var="p" varStatus="loop">

                    <div class="thumb-item ${loop.first ? 'active' : ''}"><img src="${p.image}"></div>
                </c:forEach>
            </div>
        </div>

        <div class="product-info">
            <h1 class="text-4xl font-extrabold text-gray-900 leading-tight mb-4">${c.name}</h1>
            <p class="text-gray-500 text-lg mb-6">${c.content}</p>

            <div class="price-section mb-8">
                <div class="flex items-center gap-4">
                    <span class="text-4xl font-bold text-orange-600">${c.getPriceFormat(c.discountprice)}</span>
                    <span class="text-xl text-gray-400 line-through">${c.getPriceFormat(c.baseprice)}</span>
                </div>
                <p class="text-green-600 font-semibold mt-2"><i class="fa-solid fa-circle-check"></i> ${c.gift}</p>
            </div>

            <div class="equipment-list space-y-4">
                <span class="font-bold text-gray-800 uppercase tracking-wider text-sm mb-3">Số lượng còn lại : ${c.stock_quantity}</span>
                <h3 class="font-bold text-gray-800 uppercase tracking-wider text-sm mb-3">Thiết bị trong combo:</h3>
              <c:forEach var="i" items="${c.products}" >
                <a href="#" class="item-link">

                    <div class="flex-1">
                        <div class="flex justify-between items-center">
                            <span class="font-bold text-gray-800">${i.product_name}</span>

                        </div>

                    </div>
                </a>

              </c:forEach>


            </div>

            <div class="mt-10 flex gap-4">
                <button class="flex-1 bg-black text-white py-5 rounded-2xl font-bold text-lg hover:bg-gray-800 transition shadow-xl">
                    MUA COMBO NGAY
                </button>
                <button class="w-16 border-2 border-gray-200 rounded-2xl flex items-center justify-center hover:bg-gray-50 transition">
                    <i class="fa-regular fa-heart text-xl"></i>
                </button>
            </div>
        </div>
    </div>
</main>
<footer class="footer">
    <div class="container">
        <div class="footer-grid">
            <div class="footer-col">
                <h4>VỀ CHÚNG TÔI</h4>
                <p>
                    Công ty TNHH Bếp Thông Minh TTB<br />
                    MST: 031xxxxxxx<br />
                    Địa chỉ: Khu phố 6, Phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí
                    Minh<br />
                    Hotline: 1900.1234<br />
                    Email: 23130356@.hcmuaf.edu.vn
                </p>
            </div>
            <div class="footer-col">
                <h4>HỖ TRỢ KHÁCH HÀNG</h4>
                <ul>
                    <li><a href="./pages/ThanhTruong/chinhsachbaohanh.html">Chính sách Bảo hành</a></li>
                    <li><a href="./pages/TrongBao/chinhsachvanchuyenvalapdat.html">Chính sách Vận chuyển & Lắp
                        đặt</a></li>
                    <li><a href="./pages/TrongBao/chinhsachdoitra.html">Chính sách Đổi trả</a></li>
                    <li><a href="./pages/ThanhTruong/phuongthucthanhtoan.html">Phương thức Thanh toán</a></li>
                    <li><a href="#">Câu hỏi Thường gặp (FAQs)</a></li>
                </ul>
            </div>
            <div class="footer-col">
                <h4>LIÊN KẾT NHANH</h4>
                <ul>
                    <li><a href="./pages/ThanhTruong/vechungtoi.html">Về chúng tôi</a></li>
                    <li><a href="./pages/TrongBao/Showroom.html">Hệ thống Showroom</a></li>
                    <li><a href="./pages/ThanhTruong/tuyendung.html">Tuyển dụng</a></li>
                    <li><a href="./pages/ThanhTruong/doitacB2B.html">Dành cho Đối tác B2B</a></li>
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
                    <input type="email" placeholder="Email của bạn" />
                    <button type="submit">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <p>&copy; 2025 Bản quyền thuộc về Bếp Thông Minh TTB.</p>
    </div>
</footer>
<script src="assets/js/ctCombo.js"></script>
</body>
</html>