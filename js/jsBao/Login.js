// Chạy mã sau khi trang đã tải xong
document.addEventListener("DOMContentLoaded", function() {

    // --- 1. Lấy các phần tử (elements) ---
    const loginForm = document.getElementById("login-form");
    const usernameInput = document.getElementById("username");
    const passwordInput = document.getElementById("password");
    const messageElement = document.getElementById("message");

    // Các liên kết và nút mới
    const forgotPasswordLink = document.getElementById("forgot-password-link");
    const registerLink = document.getElementById("register-link");
    const googleLoginBtn = document.getElementById("google-login");
    const facebookLoginBtn = document.getElementById("facebook-login");

    // --- 2. Xử lý Form Đăng nhập chính ---
    loginForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Ngăn form gửi đi

        const username = usernameInput.value;
        const password = passwordInput.value;

        // Giả lập kiểm tra
        if (username === "admin" && password === "12345") {
            messageElement.textContent = "Đăng nhập thành công! Đang chuyển hướng...";
            messageElement.className = "success";

            setTimeout(() => {
                alert("Đã đăng nhập! (Chuyển đến trang dashboard...)");
                // window.location.href = "dashboard.html"; // Bỏ ghi chú để chuyển trang thật
            }, 1500);

        } else {
            messageElement.textContent = "Tên đăng nhập hoặc mật khẩu không đúng.";
            messageElement.className = "error";
        }
    });

    // --- 3. Xử lý các liên kết và nút phụ (Giả lập) ---

    // Chức năng "Quên mật khẩu"
    forgotPasswordLink.addEventListener("click", function(event) {
        event.preventDefault(); // Ngăn liên kết tải lại trang
        alert("Chức năng 'Quên mật khẩu' đã được nhấp!\n(Trong thực tế, sẽ chuyển đến trang đặt lại mật khẩu.)");
    });

    // Chức năng "Đăng ký"
    registerLink.addEventListener("click", function(event) {
        event.preventDefault();
        alert("Chức năng 'Đăng ký' đã được nhấp!\n(Trong thực tế, sẽ chuyển đến trang đăng ký tài khoản mới.)");
    });

    // Chức năng "Đăng nhập Google"
    googleLoginBtn.addEventListener("click", function() {
        alert("Chức năng 'Đăng nhập với Google' đã được nhấp!\n(Trong thực tế, sẽ mở cửa sổ popup xác thực của Google.)");
    });

    // Chức năng "Đăng nhập Facebook"
    facebookLoginBtn.addEventListener("click", function() {
        alert("Chức năng 'Đăng nhập với Facebook' đã được nhấp!\n(Trong thực tế, sẽ mở cửa sổ popup xác thực của Facebook.)");
    });

});