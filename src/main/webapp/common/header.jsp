<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<header class="header">
    <div class="header__top-bar">
        <div class="container">
            <span><i class="fa fa-phone"></i> Hỗ trợ Kỹ thuật: 1900.1234</span>
            <span><i class="fa fa-phone"></i> Kinh doanh: 1900.5678</span>
            <span class="spacer"></span>
            <a href="showroom">Hệ thống Showroom</a>
            <a href="tra-cuu-bao-hanh">Tra cứu Bảo hành</a>
            <c:if test="${empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/login">
                    <i class="fa fa-user"></i> Đăng nhập
                </a>
            </c:if>

            <!-- ĐÃ LOGIN -->
            <c:if test="${not empty sessionScope.user}">
                <span class="user-info">
                    <i class="fa fa-user"></i>
                     Xin chào, ${sessionScope.user.username}
                </span>
                <a href="${pageContext.request.contextPath}/logout"
                   onclick="return confirmLogout();">
                    <i class="fa fa-sign-out-alt"></i> Đăng xuất
                </a>
            </c:if>
        </div>
    </div>
    <div class="header__main">
        <div class="container">
            <a href="Home" class="header__logo">
                <img src="assets/images/banners/logo.png" alt="TTB" />
            </a>
            <div class="header__search">
                <input type="text" placeholder="Tìm kiếm bếp từ, robot hút bụi..." />
                <button><i class="fa fa-search"></i></button>
            </div>
            <div class="header__actions">
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/login">
                            <i class="fa fa-user"></i> Tài khoản
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/profile">
                            <i class="fa fa-user"></i> Tài khoản
                        </a>
                    </c:otherwise>
                </c:choose>
                <a href="yeu-thich.jsp"><i class="fa fa-heart"></i> Yêu thích</a>
                <a href="cart"><i class="fa fa-shopping-cart"></i> Giỏ hàng (${sessionScope.cart.totalQuantity})</a>
            </div>
        </div>
    </div>
    <nav class="header__nav">
        <div class="container">
            <ul>
                <li class="nav-item has-megamenu">
                    <a href="${pageContext.request.contextPath}/products">Sản phẩm <i class="fa fa-chevron-down"></i></a>
                    <div class="mega-menu">
                        <c:forEach var="c" items="${applicationScope.categories}">
                            <a href="${pageContext.request.contextPath}/products?categoryId=${c.category_id}">
                                <c:if test="${not empty c.logo}">
                                    <img src="${c.logo}" alt="${c.category_name}" style="width:18px;margin-right:6px;">
                                </c:if>
                                    ${c.category_name}
                            </a>
                        </c:forEach>
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
<script src="${pageContext.request.contextPath}/assets/js/Logout.js"></script>
