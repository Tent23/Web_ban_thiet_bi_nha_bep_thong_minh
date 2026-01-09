<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đơn mua</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders.css">
</head>

<body>

<jsp:include page="common/header.jsp"/>

<main class="profile-page">
    <div class="container profile-layout">

        <!-- SIDEBAR -->
        <aside class="profile-sidebar">
            <h3>${sessionScope.user.username}</h3>
            <ul>
                <li><a href="profile">Hồ sơ</a></li>
                <li><a href="addresses">Địa chỉ</a></li>
                <li><a href="change-password">Đổi mật khẩu</a></li>
                <li class="active"><a href="orders">Đơn mua</a></li>
            </ul>
        </aside>

        <!-- CONTENT -->
        <section class="profile-content">

            <div class="order-tabs">
                <span class="active">Tất cả</span>
            </div>

            <c:if test="${empty orders}">
                <p style="text-align:center;margin-top:40px">Bạn chưa có đơn hàng nào.</p>
            </c:if>

            <c:forEach var="o" items="${orders}">
                <div class="order-card">

                    <!-- Header -->
                    <div class="order-header">
                        <div>Mã đơn: #${o.order_id}</div>
                        <div class="order-status">${o.status}</div>
                    </div>

                    <!-- Products -->
                    <c:forEach var="p" items="${orderProducts[o.order_id]}">
                        <div class="order-product">
                            <img src="${p.image}" alt="">
                            <div class="info">
                                <div class="name">${p.product_name}</div>
                                <div class="price">
                                        ${p.price} đ
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <!-- Footer -->
                    <div class="order-footer">
                        <div>
                            Phương thức: <b>${o.payment_method}</b>
                        </div>

                        <div class="total">
                            Thành tiền:
                            <span>${o.total_amount} đ</span>
                        </div>

                        <div class="actions">
                            <c:if test="${o.status eq 'COMPLETED'}">
                                <button>Mua lại</button>
                                <button>Đánh giá</button>
                            </c:if>

                            <c:if test="${o.status eq 'PENDING'}">
                                <button>Huỷ đơn</button>
                            </c:if>
                        </div>
                    </div>

                </div>
            </c:forEach>

        </section>

    </div>
</main>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
