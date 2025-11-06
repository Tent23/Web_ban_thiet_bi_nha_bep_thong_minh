// ========== GIẢ LẬP DỮ LIỆU SẢN PHẨM ==========
const products = {
  "tu-bep": [
    { name: "Tủ bếp gỗ sồi", price: "12.000.000đ", img: "https://via.placeholder.com/250x180?text=Tủ+bếp+1" },
    { name: "Tủ bếp thông minh", price: "18.500.000đ", img: "https://via.placeholder.com/250x180?text=Tủ+bếp+2" }
  ],
  "chen": [
    { name: "Bộ chén sứ trắng cao cấp", price: "450.000đ", img: "https://via.placeholder.com/250x180?text=Chén+1" },
    { name: "Bộ chén họa tiết xanh", price: "520.000đ", img: "https://via.placeholder.com/250x180?text=Chén+2" }
  ],
  "noi": [
    { name: "Nồi inox 3 đáy", price: "650.000đ", img: "https://via.placeholder.com/250x180?text=Nồi+1" },
    { name: "Nồi áp suất điện", price: "1.200.000đ", img: "https://via.placeholder.com/250x180?text=Nồi+2" }
  ],
  "bep-tu": [
    { name: "Bếp từ đôi Panasonic", price: "5.900.000đ", img: "https://via.placeholder.com/250x180?text=Bếp+từ+1" },
    { name: "Bếp từ cảm ứng Bosch", price: "7.200.000đ", img: "https://via.placeholder.com/250x180?text=Bếp+từ+2" }
  ]
};

// ========== XỬ LÝ KHI CLICK DANH MỤC ==========
const heroBanner = document.getElementById("hero-banner");
const productSection = document.getElementById("product-section");
const menuItems = document.querySelectorAll(".menu-item");

menuItems.forEach(item => {
  item.addEventListener("click", () => {
    const category = item.dataset.category;
    const list = products[category] || [];

    // Cập nhật hero banner
    heroBanner.innerHTML = `
      <h1>${item.textContent}</h1>
      <p>Các sản phẩm nổi bật về ${item.textContent.toLowerCase()}.</p>
    `;

    // Render sản phẩm
    productSection.innerHTML = list.map(p => `
      <div class="product-card">
        <img src="${p.img}" alt="${p.name}">
        <h3>${p.name}</h3>
        <p>${p.price}</p>
      </div>
    `).join("");
  });
});
