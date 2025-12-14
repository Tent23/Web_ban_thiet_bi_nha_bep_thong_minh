<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <link rel="stylesheet" href="assets/css/Header.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/DangKy.css">
    <link rel="stylesheet" href="assets/css/Login.css">
</head>

<body>

<jsp:include page="common/header.jsp"></jsp:include>

<main>
    <main class="main_container">
        <form class="resigter" action="login" method="post">
            <h1>Đăng Nhập</h1>

            <%-- Hiển thị thông báo lỗi nếu có --%>
            <%
                String error = (String) request.getAttribute("errorMessage");
                if (error != null) {
            %>
            <div style="color: red; text-align: center; margin-bottom: 10px; font-weight: bold; background-color: #ffe6e6; padding: 10px; border-radius: 5px;">
                <i class="fa fa-circle-exclamation"></i> <%= error %>
            </div>
            <% } %>

            <div class="input_group main_group">
                <div class="input_field">
                    <i class="fa fa-user"></i>
                    <input type="text" id="username" name="username"
                           placeholder="Tên đăng nhập hoặc Email"
                           value=""
                           required>
                </div>

                <div class="input_field">
                    <i class="fa fa-lock"></i>
                    <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
                    <i class="fa fa-eye-slash toggle-password" data-target="password"></i>
                </div>
            </div>

            <div class="check_row">
                <label class="check_deal">
                    <input type="checkbox"> Ghi nhớ đăng nhập
                </label>
                <a href="${pageContext.request.contextPath}/Dangky.html" class="no_account">Tôi chưa có tài khoản</a>
            </div>

            <button type="submit" class="register_button">ĐĂNG NHẬP</button>

            <div class="social_login">
                <p>Hoặc đăng nhập bằng</p>
                <div class="social_buttons">
                    <a class="google_btn" href="#">
                        <i class="fab fa-instagram"></i> Instagram
                    </a>
                    <a class="facebook_btn" href="#">
                        <i class="fab fa-facebook-f"></i> Facebook
                    </a>
                </div>
            </div>
        </form>
    </main>
</main>

<jsp:include page="common/footer.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/assets/js/Dangky.js"></script>
</body>
</html>