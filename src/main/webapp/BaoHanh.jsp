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
<jsp:include page="common/header.jsp"/>
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
    <div style="color: red; text-align: center; width: 100%; margin: 10px 0;">
        <%=error%>
    </div>
    <div class = "result" style = "    margin-bottom: 20px;">
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
            <th>Thời Gian Bảo Hành (Tháng)</th>
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
                <td style = "text-align: center">${w.warranty_time}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</main>

<jsp:include page="common/footer.jsp"/>
</body>
</html>