package com.webthietbibep.dao;

import com.webthietbibep.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getBestSellers() {
        List<Product> list = new ArrayList<>();

        // Dữ liệu mô phỏng lấy từ giao diện cũ của bạn
        list.add(new Product(1, "Bếp từ Thông minh Bosch", 15000000, "assets/images/products/beptu-1.jpg"));
        list.add(new Product(2, "Robot Hút bụi Xiaomi", 8500000, "assets/images/products/robot-4.jpg"));

        // Thêm vài sản phẩm nữa để slide chạy đẹp hơn
        list.add(new Product(3, "Tủ Lạnh Hitachi Inverter", 32000000, "assets/images/products/Tulanh-1.jpg"));
        list.add(new Product(4, "Máy Rửa Bát Bosch Series 6", 21500000, "assets/images/products/mayruabat-4.jpg"));

        return list;
    }
}