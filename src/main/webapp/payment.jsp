<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thanh toán đơn hàng</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/payment.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

</head>

<body>

<jsp:include page="common/header.jsp"/>

<main class="profile-page">
    <div class="container profile-layout">

        <section class="profile-content">

            <h2 class="payment-title">Thanh toán đơn hàng</h2>

            <div class="payment-card">
                <div class="payment-header">
                    <div>Mã đơn: <b>#${order.order_id}</b></div>
                    <div class="status waiting">Chờ thanh toán</div>
                </div>

                <!-- PRODUCTS -->
                <c:forEach items="${orderItems}" var="i">
                    <div class="payment-product">
                        <img src="${i.productImage}" alt="">
                        <div class="info">
                            <div class="name">${i.productName}</div>
                            <div class="qty">Số lượng: ${i.quantity}</div>
                        </div>
                        <div class="price">
                                ${i.price_at_purchase} đ
                        </div>
                    </div>
                </c:forEach>

                <div class="payment-summary">
                    <div>
                        <span>Tổng tiền</span>
                        <span class="total">${order.formattedTotal}</span>
                    </div>
                    <div>
                        <span>Phương thức</span>
                        <span>${order.payment_method}</span>
                    </div>
                </div>
            </div>

            <div class="payment-card">
                <h3>Thông tin thanh toán</h3>

                <c:if test="${order.payment_method eq 'BANK'}">
                    <p><b>Ngân hàng:</b> Vietcombank</p>
                    <p><b>Số tài khoản:</b> 0123456789</p>
                    <p><b>Chủ tài khoản:</b> WEB THIẾT BỊ BẾP</p>
                    <p class="note">
                        Nội dung chuyển khoản:
                        <b>TT DON #${order.order_id}</b>
                    </p>
                </c:if>

                <c:if test="${order.payment_method eq 'VNPAY'}">
                    <p>Vui lòng nhấn nút bên dưới để thanh toán qua VNPay.</p>
                </c:if>
            </div>

            <div class="payment-actions">
                <form action="confirm-payment" method="post">
                    <input type="hidden" name="orderId" value="${order.order_id}">
                    <button class="btn-primary">Tôi đã thanh toán</button>
                </form>

                <a href="orders" class="btn-secondary">Quay lại đơn mua</a>
            </div>

        </section>

    </div>
</main>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
