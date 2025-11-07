// ========== GIẢ LẬP DỮ LIỆU SẢN PHẨM ==========
const products = {
  "bep-tu": [
    { name: "Bếp từ đôi Panasonic", price: "5.900.000đ",  img: "../../assets/images/products/beptu-1.jpg" },
    { name: "Bếp từ cảm ứng Bosch", price: "7.200.000đ",  img: "../../assets/images/products/beptu-2.jpg"  }
  ],
  "robot": [
    { name: "Robot hút bụi Xiaomi", price: "4.500.000đ", img: "../../assets/images/products/robot-1.jpg" },
    { name: "Robot lau nhà Ecovacs", price: "6.200.000đ", img: "../../assets/images/products/robot-2.jpg" }
  ],
  "tu-lanh": [
    { name: "Tủ lạnh Samsung 500L", price: "12.500.000đ", img: "../../assets/images/products/tulanh-1.jpg" },
    { name: "Tủ lạnh LG Inverter", price: "15.800.000đ", img: "../../assets/images/products/tulanh-2.jpg" }
  ],
  "may-rua-bat": [
    { name: "Máy rửa bát Bosch Serie 4", price: "13.000.000đ", img: "../../assets/images/products/mayruabat-1.jpg" },
    { name: "Máy rửa bát Toshiba", price: "10.500.000đ", img: "../../assets/images/products/mayruabat-2.png" }
  ],
  "cam-bieng": [
    { name: "Cảm biến nhiệt độ", price: "450.000đ", img: "../../assets/images/products/cambieng-1.jpg" },
    { name: "Cảm biến khói", price: "620.000đ", img: "../../assets/images/products/cambieng-2.jpg" }
  ],
  "pha-che": [
    { name: "Máy pha cà phê Philips", price: "3.900.000đ", img: "../../assets/images/products/phache-1.jpg" },
    { name: "Máy xay sinh tố Bluestone", price: "1.200.000đ", img: "../../assets/images/products/phache-2.jpg" }
  ]
};

// ========== HÀM RENDER SẢN PHẨM ==========
function renderProducts(category) {
  const heroBanner = document.getElementById("hero-banner");
  const productSection = document.getElementById("product-section");
  const list = products[category] || [];

  // Cập nhật hero banner
  heroBanner.innerHTML = `
    <h1>${getCategoryName(category)}</h1>
    <p>Các sản phẩm nổi bật về ${getCategoryName(category).toLowerCase()}.</p>
  `;

  // Hiển thị sản phẩm
  productSection.innerHTML = list.length
    ? list.map(p => `
        <div class="product-card">
          <img src="${p.img}" alt="${p.name}">
          <h3>${p.name}</h3>
          <p>${p.price}</p>
        </div>
      `).join("")
    : "<p>Không có sản phẩm nào trong danh mục này.</p>";
}

// ========== HÀM LẤY TÊN DANH MỤC HIỂN THỊ ĐẸP ==========
function getCategoryName(key) {
  const names = {
    "bep-tu": "Bếp từ",
    "robot": "Robot",
    "tu-lanh": "Tủ lạnh",
    "may-rua-bat": "Máy rửa bát",
    "cam-bieng": "Cảm biến",
    "pha-che": "Pha chế"
  };
  return names[key] || "Sản phẩm";
}

// ========== LẤY CATEGORY TỪ URL ==========
document.addEventListener("DOMContentLoaded", () => {
  const params = new URLSearchParams(window.location.search);
  const category = params.get("category");

  // Nếu có category thì render tương ứng
  if (category) {
    renderProducts(category);
  } else {
    document.getElementById("hero-banner").innerHTML = `
      <h1>Danh mục sản phẩm</h1>
      <p>Vui lòng chọn danh mục sản phẩm để xem chi tiết.</p>
    `;
  }
});
