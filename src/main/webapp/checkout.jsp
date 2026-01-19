
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thanh toán</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/checkout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Header.css">
</head>
<body>

<jsp:include page="common/header.jsp"/>

<main class="checkout-container">

    <h1 class="checkout-title">Thanh toán</h1>

    <form action="checkout" method="post" class="checkout-content">
        <input type="hidden" name="mode" value="${mode}">
        <!-- LEFT -->
        <div class="checkout-left">

            <h2>Thông tin giao hàng</h2>

            <c:forEach items="${addresses}" var="a">
                <label class="address-box">
                    <input type="radio" name="addressId"
                           value="${a.address_id}"
                        ${a.isIs_default() ? "checked" : ""}>
                    <b>${a.receiver_name}</b> - ${a.phone}<br>
                        ${a.address_detail}, ${a.ward}, ${a.district}, ${a.province}
                </label>
            </c:forEach>


            <a href="add-address" class="add-address">+ Thêm địa chỉ mới</a>

            <h2>Phương thức thanh toán</h2>

            <label class="payment-method">
                <input type="radio" name="paymentMethod" value="COD" checked>
                Thanh toán khi nhận hàng (COD)
            </label>

            <label class="payment-method">
                <input type="radio" name="paymentMethod" value="BANK">
                Chuyển khoản ngân hàng
            </label>

        </div>

        <!-- RIGHT -->
        <div class="checkout-right">

            <h2>Đơn hàng của bạn</h2>

            <c:choose>

                <c:when test="${mode == 'cart'}">
                    <c:forEach items="${sessionScope.cart.items}" var="ci">
                        <div class="order-item">
                            <img src="${ci.product.image}">
                            <div>
                                <p>${ci.product.product_name}</p>
                                <small>Số lượng: ${ci.quantity}</small><br>
                                <strong>${ci.formattedTotal}</strong>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>

                <c:when test="${mode == 'buy_now'}">
                    <div class="order-item">
                        <img src="${product.image}">
                        <div>
                            <p>${product.product_name}</p>
                            <small>Số lượng: 1</small><br>
                            <strong>${product.priceFormat}</strong>
                        </div>
                    </div>
                </c:when>

            </c:choose>


            <div class="order-summary">
                <div>
                    <span>Tạm tính</span>
                    <c:choose>
                        <c:when test="${mode == 'cart'}">
                            <span>${sessionScope.cart.formatTotal}</span>
                        </c:when>
                        <c:when test="${mode == 'buy_now'}">
                            <span>${product.priceFormat}</span>
                        </c:when>
                    </c:choose>

                </div>
                <div>
                    <span>Phí vận chuyển</span>
                    <span>Miễn phí</span>
                </div>
                <div class="total">
                    <span>Tổng cộng</span>
                    <span>${sessionScope.cart.formatTotal}</span>
                </div>

                <button type="submit" class="btn-order">
                    Đặt hàng
                </button>
            </div>

        </div>

    </form>

</main>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
