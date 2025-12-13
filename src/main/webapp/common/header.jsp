<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
    <div class="header__top-bar">
        <div class="container">
            <span><i class="fa fa-phone"></i> Hỗ trợ Kỹ thuật: 1900.1234</span>
            <span><i class="fa fa-phone"></i> Kinh doanh: 1900.5678</span>
            <span class="spacer"></span>
            <a href="showroom">Hệ thống Showroom</a>
            <a href="tra-cuu-bao-hanh">Tra cứu Bảo hành</a>
            <a href="login.jsp">Đăng nhập</a>
        </div>
    </div>
    <div class="header__main">
        <div class="container">
            <a href="index.jsp" class="header__logo">
                <img src="assets/images/banners/logo.png" alt="TTB" />
            </a>
            <div class="header__search">
                <input type="text" placeholder="Tìm kiếm bếp từ, robot hút bụi..." />
                <button><i class="fa fa-search"></i></button>
            </div>
            <div class="header__actions">
                <a href="account.jsp"><i class="fa fa-user"></i> Tài kho</a>
                <a href="yeu-thich.jsp"><i class="fa fa-heart"></i> Yêu thích</a>
                <a href="gio-hang.jsp"><i class="fa fa-shopping-cart"></i> Giỏ hàng (0)</a>
            </div>
        </div>
    </div>
    <nav class="header__nav">
        <div class="container">
            <ul>
                <li class="nav-item has-megamenu">
                    <a href="products.jsp">Sản phẩm <i class="fa fa-chevron-down"></i></a>
                    <div class="mega-menu">
                        <a href="products?category=beptu">Bếp từ</a>
                        <a href="products?category=tulanh">Tủ lạnh</a>
                        <a href="products?category=robot">Robot Hút bụi</a>
                        <a href="products?category=mayruabat">Máy rửa bát</a>
                        <a href="products?category=cambien">Cảm biến & An ninh</a>
                        <a href="products?category=phache">Pha chế</a>
                    </div>
                </li>
                <li class="nav-item"><a href="giai-phap-combo.jsp">Giải pháp & Combo</a></li>
                <li class="nav-item"><a href="goc-tu-van.jsp">Góc Tư vấn</a></li>
                <li class="nav-item"><a href="dich-vu-lap-dat.jsp">Dịch vụ Lắp đặt</a></li>
                <li class="nav-item"><a href="ve-chung-toi.jsp">Về chúng tôi</a></li>
                <li class="nav-item"><a href="khuyen-mai.jsp">Khuyến mãi</a></li>
            </ul>
        </div>
    </nav>
</header>