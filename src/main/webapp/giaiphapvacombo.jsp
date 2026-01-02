<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Giải pháp và Combo</title>
    <link rel="stylesheet" href="assets/css/Header.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="assets/css/giaiphapvacombo.css">
</head>
<body>
<header class="header">
    <div class="header__top-bar">
        <div class="container">
            <span><i class="fa fa-phone"></i> Hỗ trợ Kỹ thuật: 1900.1234</span>
            <span><i class="fa fa-phone"></i> Kinh doanh: 1900.5678</span>
            <span class="spacer"></span>
            <a href="Showroom.jsp">Hệ thống Showroom</a>
            <a href="BaoHanh.jsp">Tra cứu Bảo hành</a>
            <a href="../QuangToan/Login.html">Đăng nhập</a>
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
                <a href="../ThanhTruong/yeuthich.html"><i class="fa fa-heart"></i> Yêu thích</a>
                <a href="../ThanhTruong/giohang.html"><i class="fa fa-shopping-cart"></i> Giỏ hàng (0)</a>
            </div>
        </div>
    </div>
    <nav class="header__nav">
        <div class="container">
            <ul>
                <li class="nav-item has-megamenu">
                    <a href="#">Sản phẩm <i class="fa fa-chevron-down"></i></a>
                    <div class="mega-menu">
                        <a href="../QuangToan/Beptu.html">Bếp từ</a>
                        <a href="../QuangToan/Tulanh.html">Tủ lạnh</a>
                        <a href="../QuangToan/Robot.html">Robot Hút bụi</a>
                        <a href="../QuangToan/Mayruabat.html">Máy rửa bát</a>
                        <a href="../QuangToan/Cambien.html">Cảm biến & An ninh</a>
                        <a href="../QuangToan/Phache.html">Pha chế</a>
                    </div>
                </li>
                <li class="nav-item"><a href="giaiphapvacombo.html">Giải pháp & Combo</a></li>
                <li class="nav-item"><a href="goctuvan.html">Góc Tư vấn</a></li>
                <li class="nav-item"><a href="DichVuLapDat.html">Dịch vụ Lắp đặt</a></li>
                <li class="nav-item"><a href="../ThanhTruong/vechungtoi.html">Về chúng tôi</a></li>
                <li class="nav-item"><a href="../ThanhTruong/khuyenmai.html">Khuyến mãi</a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="combo-section section-padding bg-light">
        <div class="container">
            <h1 class="section-title text-center">Các Combo Sản phẩm </h1>
            <p class="section-subtitle text-center">Giảm giá lên đến 20% khi mua trọn bộ thiết bị.</p>
            <div class="combo-grid">
             <c:forEach var="c" items="${listC}">
                <div class="combo-card">
                    <div class="combo-header">
                        <span class="combo-label combo-basic">${c.tag}</span>
                        <img src="${c.image}" alt="Combo Căn hộ" class="combo-image">
                    </div>
                    <div class="combo-body">
                        <h4>${c.name}</h4>
                        <p class="combo-desc">${c.content}</p>
                        <ul>
                            <c:forEach var = "i" items="${c.listadvance}">
                            <li><i class="fa fa-check-circle"></i> ${i.advance}</li>
                            </c:forEach>
                            <li><i class="fa fa-gift"></i> ${c.gift}</li>
                        </ul>
                        <div class="combo-price-block">
                            <span class="old-price">${c.getPriceFormat(c.baseprice)}</span>
                            <span class="current-price">${c.getPriceFormat(c.discountprice)}</span>
                        </div>
                        <a href="combo?id=${c.id}" class="btn btn-primary btn-full-width">Mua Combo </a>
                    </div>
                </div>
             </c:forEach>


                </div>
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
                    <li><a href="../ThanhTruong/chinhsachbaohanh.html">Chính sách Bảo hành</a></li>
                    <li><a href="chinhsachvanchuyenvalapdat.html">Chính sách Vận chuyển & Lắp đặt</a></li>
                    <li><a href="chinhsachdoitra.html">Chính sách Đổi trả</a></li>
                    <li><a href="../ThanhTruong/phuongthucthanhtoan.html">Phương thức Thanh toán</a></li>
                    <li><a href="#">Câu hỏi Thường gặp (FAQs)</a></li>
                </ul>
            </div>
            <div class="footer-col">
                <h4>LIÊN KẾT NHANH</h4>
                <ul>
                    <li><a href="../ThanhTruong/vechungtoi.html">Về chúng tôi</a></li>
                    <li><a href="Showroom.jsp">Hệ thống Showroom</a></li>
                    <li><a href="../ThanhTruong/tuyendung.html">Tuyển dụng</a></li>
                    <li><a href="#">Dành cho Đối tác B2B</a></li>
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
</body>
</html>