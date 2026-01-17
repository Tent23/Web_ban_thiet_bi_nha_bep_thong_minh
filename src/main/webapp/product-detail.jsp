<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>${product.product_name}</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/product-detail.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>

<body>
<jsp:include page="common/header.jsp"/>

<main class="product-detail">
  <div class="container">

    <!-- THÔNG TIN CHÍNH -->
    <div class="product-main">

      <!-- ẢNH -->
      <div class="product-image">
        <img src="${product.image}" alt="${product.product_name}">
      </div>

      <!-- INFO -->
      <div class="product-info">
          <c:if test="${not empty sessionScope.message}">
              <div class="cart-alert-error">
                  <i class="fa fa-exclamation-circle"></i> ${sessionScope.message}
                  <c:remove var="message" scope="session" />
              </div>
          </c:if>
        <h1>${product.product_name}</h1>

        <p class="price">${product.priceFormat}</p>

        <p class="description">
          ${product.description}
        </p>

        <div class="actions">
            <a href="add-cart?id=${product.product_id}&q=1" class="btn btn-primary">
            <i class="fa fa-cart-plus"></i> Thêm vào giỏ
          </a>
          <a href="#" class="btn btn-danger">
            <i class="fa fa-bolt"></i> Mua ngay
          </a>
          <a href="#" class="wishlist">
            <i class="fa fa-heart"></i> Yêu thích
          </a>
        </div>
      </div>
    </div>

    <!-- TÍNH NĂNG -->
    <div class="product-features">
      <h2>Tính năng nổi bật</h2>
      <ul>
        <c:forEach var="f" items="${features}">
          <li>${f.featureText}</li>
        </c:forEach>
      </ul>
    </div>

    <!-- SẢN PHẨM TƯƠNG TỰ -->
    <div class="related-products">
      <h2>Sản phẩm tương tự</h2>

      <div class="related-grid">
        <c:forEach var="p" items="${relatedProducts}">
          <div class="related-card">
            <img src="${p.image}">
            <h4>${p.product_name}</h4>
            <p>${p.priceFormat}</p>
            <a href="${pageContext.request.contextPath}/product-detail?id=${p.product_id}">
              Xem chi tiết
            </a>
          </div>
        </c:forEach>
      </div>
    </div>

  </div>
</main>
<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>
