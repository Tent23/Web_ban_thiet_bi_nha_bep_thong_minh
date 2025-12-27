<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tra cứu bảo hành </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="assets/css/Header.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/BaoHanh.css">
    <link rel="stylesheet" href="assets/css/indexfont.css.css">
</head>
<body>
<header class="header">
    <div class="header__top-bar">
        <div class="container">
            <span><i class="fa fa-phone"></i> Hỗ trợ Kỹ thuật: 1900.1234</span>
            <span><i class="fa fa-phone"></i> Kinh doanh: 1900.5678</span>
            <span class="spacer"></span>
            <a href="Showroom.jsp">Hệ thống Showroom</a>
            <a href="BaoHanh.html">Tra cứu Bảo hành</a>
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
                <li class="nav-item"><a href="giaiphapvacombo.jsp">Giải pháp & Combo</a></li>
                <li class="nav-item"><a href="goctuvan.html">Góc Tư vấn</a></li>
                <li class="nav-item"><a href="DichVuLapDat.html">Dịch vụ Lắp đặt</a></li>
                <li class="nav-item"><a href="../ThanhTruong/vechungtoi.html">Về chúng tôi</a></li>
                <li class="nav-item"><a href="../ThanhTruong/khuyenmai.html">Khuyến mãi</a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <%
        String error = (String) request.getAttribute("error");
        if(error == null) error = "";
        String pra = (String) request.getAttribute("pra");
        if(pra == null) pra = "";
    %>

    <div class = "main_container">
        <div ><h1>Tra Cứu Bảo Hành</h1></div>
        <form action="BaoHanh" method="get">
        <div class ="check_bnt">
           <div> <input type="radio" id="radio-1" name="Tra_Cuu_theo" value ="series" ${type == "series" ? "checked" : "" } > <label for = "radio-1" >Tra cứu theo Số Serie</label></div>
            <div><input type="radio" id="radio-2" name="Tra_Cuu_theo" value ="phone" ${type == "phone" ? "checked" : "" }> <label for = "radio-2">Tra cứu theo Số điện thoại  </label></div>

        </div>
        <div>
            <div class = "searh">

            <input type="text" id = "field" name ="pra" value ="<%= pra %>"  placeholder="Nhập thông tin tra cứu">
            <button type="submit"><i class="fa fa-search"></i></button>
            </div>
            <span ></span>
        </div>
        </form>
    </div>
    <div class = "result">
        <table > <thead>
        <tr>
            <th>Mã Series</th>
            <th>Mã Sản Phẩm  </th>
            <th>Tên Sản Phẩm</th>
            <th>Mã Khách Hàng</th>
            <th>Tên Khách Hàng</th>
            <th>SDT</th>
            <th>Mã Hóa Đơn</th>
            <th>Ngày Mua</th>
            <th>Thời Gian Bảo Hành</th>
        </tr>
        </thead>
            <tbody>
            <c:forEach var="w" items="${listWrranty}">
            <tr>
                <td>${w.series}</td>
                <td>${w.productid}</td>
                <td>${w.productName}</td>
                <td>${w.customerid}</td>
                <td>${w.customerName}</td>
                <td>${w.phoneNumber}</td>
                <td>${w.orderid}</td>
                <td>${w.purchaseDate}</td>
                <td>${w.warrantyMounth}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
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