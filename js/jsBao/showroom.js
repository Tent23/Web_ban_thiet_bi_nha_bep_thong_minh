// Chờ cho toàn bộ nội dung HTML được tải xong
document.addEventListener('DOMContentLoaded', function() {

    // ===== CHỨC NĂNG 1: KHỞI TẠO HERO BANNER SLIDER (BLOCK 1) =====
    // Sử dụng thư viện Swiper.js đã liên kết ở HTML
    const heroSlider = new Swiper('#hero-slider', {
        // Tùy chọn
        loop: true, // Lặp lại
        autoplay: {
            delay: 5000, // Tự động trượt sau 5 giây
        },
        pagination: { // Hiển thị dấu chấm phân trang
            el: '.swiper-pagination',
            clickable: true,
        },
    });
});

