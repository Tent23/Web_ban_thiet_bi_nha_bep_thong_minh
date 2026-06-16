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


    <script src="${pageContext.request.contextPath}/assets/js/jsrsasign-all-min.js"></script>
</head>
<body>

<jsp:include page="common/header.jsp"/>

<main class="checkout-container" style="width: 1200px; max-width: 100%; margin: 0 auto;">
    <h1 class="checkout-title">Thanh toán</h1>

    <form action="checkout" method="post" class="checkout-content" id="checkoutForm" style="width: 1200px; max-width: 100%; margin: 0 auto;">
        <input type="hidden" name="mode" value="${mode}">
        <input type="hidden" id="cilentSign" name="cilentSign">
        <input type="hidden" id="signedDataHash" name="signedDataHash">
        <input type="hidden" id="signedData" name="signedData">


        <c:choose>
            <c:when test="${mode == 'buynow'}">
                <input type="hidden" id="productData" value='{
                    "productId": "${buyNowProduct.product_id}",
                    "productName": "${buyNowProduct.product_name}",
                    "price": "${buyNowProduct.price}",
                    "quantity": "${buyNowQuantity}"
                }'>
            </c:when>
            <c:otherwise>
                <input type="hidden" id="cartData" value='<c:out value="${cart.toJson()}" escapeXml="false"/>'>
            </c:otherwise>
        </c:choose>
        <input type="hidden" id="userId" value="${sessionScope.user.user_id}">
        <input type="hidden" id="publicKeyId" value="${sessionScope.user.publicKeyId}">


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


            <a href="${pageContext.request.contextPath}/addresses" class="add-address">
                + Thêm địa chỉ mới
            </a>

            <h2>Phương thức thanh toán</h2>

            <label class="payment-method">
                <input type="radio" name="paymentMethod" value="COD" checked>
                Thanh toán khi nhận hàng (COD)
            </label>

            <label class="payment-method">
                <input type="radio" name="paymentMethod" value="BANK">
                Chuyển khoản ngân hàng
            </label>

            <div style="margin-top: 20px; padding: 15px; border: 1px solid #ccc; border-radius: 5px;">
                <h2>Ký đơn hàng bằng Private Key</h2>
                <p>Vui lòng tải lên Private Key (.pem) của bạn để ký xác nhận đơn hàng.</p>
                <input type="file" id="privateKeyFile" accept=".pem" style="margin-bottom: 10px;">
                <p id="keyStatus" style="color: red;"></p>
            </div>

        </div>

        <div class="checkout-right">

            <h2>Đơn hàng của bạn</h2>

            <c:choose>

                <c:when test="${mode == 'buynow'}">
                    <div class="order-item">
                        <img src="${buyNowProduct.image}">
                        <div>
                            <p>${buyNowProduct.product_name}</p>
                            <small>Số lượng: ${buyNowQuantity}</small><br>
                            <strong>
                                    ${buyNowTotalFormatted}
                            </strong>
                        </div>
                    </div>
                </c:when>


                <c:otherwise>
                    <c:forEach items="${cart.items}" var="ci">
                        <div class="order-item">
                            <img src="${ci.product.image}">
                            <div>
                                <p>${ci.product.product_name}</p>
                                <small>Số lượng: ${ci.quantity}</small><br>
                                <strong>${ci.formattedTotal}</strong>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>

            </c:choose>



            <div class="order-summary">
                <div>
                    <span>Tạm tính</span>
                    <c:choose>
                        <c:when test="${mode == 'buynow'}">
                            <span>${buyNowTotalFormatted}</span>
                        </c:when>
                        <c:otherwise>
                            <span>${cart.formatTotal}</span>
                        </c:otherwise>
                    </c:choose>



                </div>
                <div>
                    <span>Phí vận chuyển</span>
                    <span>Miễn phí</span>
                </div>
                <div class="total">
                    <span>Tổng cộng</span>
                    <c:choose>
                        <c:when test="${mode == 'buynow'}">
                            <span>${buyNowProduct.price * buyNowQuantity} đ</span>
                        </c:when>
                        <c:otherwise>
                            <span>${cart.formatTotal}</span>
                        </c:otherwise>
                    </c:choose>
                </div>

                <c:if test="${not empty error}">
                    <div style="color:red; margin-bottom:15px; font-weight:bold;">
                            ${error}
                    </div>
                </c:if>



                <button type="button" class="btn-order" id="placeOrderBtn">
                    Đặt hàng
                </button>
            </div>

        </div>

    </form>

</main>

<jsp:include page="common/footer.jsp"/>

<script>
    document.getElementById('placeOrderBtn').addEventListener('click', async function(event) {
        event.preventDefault();

        const privateKeyFile = document.getElementById('privateKeyFile').files[0];
        const keyStatus = document.getElementById('keyStatus');
        const checkoutForm = document.getElementById('checkoutForm');

        if (!privateKeyFile) {
            keyStatus.textContent = "Vui lòng tải lên Private Key của bạn.";
            return;
        }

        keyStatus.textContent = "";

        const reader = new FileReader();
        reader.onload = async function(e) {
            try {
                const privateKeyPem = e.target.result;
                console.log("1. Private Key PEM loaded:", privateKeyPem.substring(0, 100) + "...");
                console.log("Full Private Key PEM content:", privateKeyPem);

                const selectedAddressRadio = document.querySelector('input[name="addressId"]:checked');
                if (!selectedAddressRadio) {
                    keyStatus.textContent = "Vui lòng chọn địa chỉ giao hàng.";
                    return;
                }
                const addressId = selectedAddressRadio.value;
                const userId = document.getElementById('userId').value;
                const publicKeyId = document.getElementById('publicKeyId').value;
                const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
                const mode = document.querySelector('input[name="mode"]').value;

                let orderItemsData = [];
                let totalAmount = 0;

                if (mode === 'buynow') {
                    const productData = JSON.parse(document.getElementById('productData').value);
                    orderItemsData.push({
                        productId: parseInt(productData.productId),
                        productName: productData.productName,
                        price: parseFloat(productData.price),
                        quantity: parseInt(productData.quantity)
                    });
                    totalAmount = parseFloat(productData.price) * parseInt(productData.quantity);
                } else {
                    const cartJsonString = document.getElementById('cartData').value;
                    const cartData = JSON.parse(cartJsonString);

                    cartData.items.forEach(item => {
                        orderItemsData.push({
                            productId: parseInt(item.product.product_id),
                            productName: item.product.product_name,
                            price: parseFloat(item.price),
                            quantity: parseInt(item.quantity)
                        });
                        totalAmount += parseFloat(item.price) * parseInt(item.quantity); // Use item.price
                    });
                }

                const dataToSign = {
                    userId: parseInt(userId),
                    publicKeyId: publicKeyId,
                    addressId: parseInt(addressId),
                    paymentMethod: paymentMethod,
                    mode: mode,
                    items: orderItemsData,
                    totalAmount: totalAmount,
                    timestamp: new Date().toISOString()
                };

                const jsonString = JSON.stringify(dataToSign);
                document.getElementById("signedData").value = jsonString;
                console.log("2. JSON String to sign:", jsonString);


                if (typeof KJUR === 'undefined' || typeof KJUR.crypto === 'undefined' || typeof KJUR.crypto.MessageDigest === 'undefined') {
                    throw new Error("Thư viện jsrsasign (KJUR.crypto.MessageDigest) chưa được tải hoặc không khả dụng.");
                }


                const md = new KJUR.crypto.MessageDigest({"alg": "sha256", "prov": "jsrsa"});
                md.updateString(jsonString);
                const dataHash = md.digest();
                console.log("3. Data Hash:", dataHash);

                if (typeof KJUR.crypto.Signature === 'undefined') {
                    throw new Error("Thư viện jsrsasign (KJUR.crypto.Signature) chưa được tải hoặc không khả dụng.");
                }


                console.log("Attempting to initialize signature with privateKeyPem:", privateKeyPem.substring(0, 100) + "..."); // Log for debugging
                const prvKeyObj = KEYUTIL.getKey(privateKeyPem);

                const sig = new KJUR.crypto.Signature({
                    alg: "SHA256withRSA"
                });

                sig.init(prvKeyObj);
                sig.updateString(dataHash);

                const signature = hextob64(sig.sign());

                console.log("4. Signature:", signature.substring(0, 100) + "...");


                document.getElementById('cilentSign').value = signature;
                document.getElementById('signedDataHash').value = dataHash;
                checkoutForm.submit();

            } catch (error) {
                console.error("Lỗi khi ký đơn hàng:", error);
                keyStatus.textContent = "Lỗi khi ký đơn hàng. Vui lòng kiểm tra lại Private Key của bạn hoặc thử lại. Chi tiết lỗi: " + (error.message || error);
            }
        };
        reader.readAsText(privateKeyFile);
    });
</script>

</body>
</html>