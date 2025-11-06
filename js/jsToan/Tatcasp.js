const data = {
  "tu-bep": [
    { name: "Tủ bếp hiện đại", price: "15.000.000đ", desc: "Tủ bếp gỗ công nghiệp cao cấp" },
    { name: "Tủ bếp chữ L", price: "18.000.000đ", desc: "Thiết kế tối ưu không gian" }
  ],
  "ban-ghe": [
    { name: "Bàn ăn 4 ghế", price: "5.000.000đ", desc: "Gỗ tự nhiên bền đẹp" },
    { name: "Ghế sofa đơn", price: "4.000.000đ", desc: "Êm ái, thoải mái" }
  ],
  "giuong-ngu": [
    { name: "Giường 1m8", price: "8.000.000đ", desc: "Giường ngủ cao cấp" },
    { name: "Giường tầng trẻ em", price: "10.000.000đ", desc: "Tiện lợi cho bé" }
  ],
  "tu-quan-ao": [
    { name: "Tủ 3 cánh", price: "7.000.000đ", desc: "Rộng rãi, hiện đại" },
    { name: "Tủ cửa lùa", price: "12.000.000đ", desc: "Tiết kiệm không gian" }
  ],
  "sofa": [
    { name: "Sofa 2 chỗ", price: "8.000.000đ", desc: "Thiết kế tinh tế" },
    { name: "Sofa góc L", price: "15.000.000đ", desc: "Phù hợp phòng khách lớn" }
  ],
  "den-trang-tri": [
    { name: "Đèn chùm pha lê", price: "5.000.000đ", desc: "Sang trọng, đẳng cấp" },
    { name: "Đèn ngủ để bàn", price: "500.000đ", desc: "Nhỏ gọn, ấm cúng" }
  ]
};

const catButtons = document.querySelectorAll('.category button');
const title = document.getElementById('categoryTitle');
const productList = document.getElementById('productList');

catButtons.forEach(btn => {
  btn.addEventListener('click', () => {
    catButtons.forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    const cat = btn.dataset.cat;
    showProducts(cat);
  });
});

function showProducts(cat) {
  const products = data[cat] || [];
  title.textContent = getCategoryName(cat);
  productList.innerHTML = products.map(p => `
    <div class="product-card">
      <h4>${p.name}</h4>
      <p class="price">${p.price}</p>
      <p>${p.desc}</p>
    </div>
  `).join('');
}

function getCategoryName(cat) {
  const names = {
    "tu-bep": "Tủ bếp",
    "ban-ghe": "Bàn ghế",
    "giuong-ngu": "Giường ngủ",
    "tu-quan-ao": "Tủ quần áo",
    "sofa": "Sofa",
    "den-trang-tri": "Đèn trang trí"
  };
  return names[cat] || "Sản phẩm";
}
