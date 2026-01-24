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
<jsp:include page="/common/header.jsp" />
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
<jsp:include page="/common/footer.jsp" />
<script src="assets/js/ctCombo.js"></script>
</body>
</html>