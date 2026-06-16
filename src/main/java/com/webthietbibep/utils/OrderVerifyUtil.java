package com.webthietbibep.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webthietbibep.dao.UserKeyDAO;
import com.webthietbibep.model.Order;
import com.webthietbibep.model.UserKey;

public class OrderVerifyUtil {

    public static String verifyOrder(Order order) {

        try {

            if (order.getSignature() == null
                    || order.getSignedData() == null
                    || order.getKeyId() == null) {

                return "CHUA_KY_SO";
            }

            UserKeyDAO dao = new UserKeyDAO();

            UserKey key = dao.findByKeyId(order.getKeyId());

            if (key == null) {
                return "KHONG_HOP_LE";
            }

            // 1. Verify chữ ký
            boolean valid = KeyUtil.verifySign(
                    order.getSignedData(),
                    order.getSignature(),
                    key.getPublicKey()
            );

            if (!valid) {
                return "KHONG_HOP_LE";
            }

            // 2. Parse JSON đã ký
            Gson gson = new Gson();

            JsonObject obj = gson.fromJson(
                    order.getSignedData(),
                    JsonObject.class
            );

            int signedUserId = obj.get("userId").getAsInt();
            int signedAddressId = obj.get("addressId").getAsInt();
            double signedTotal = obj.get("totalAmount").getAsDouble();
            String signedPayment = obj.get("paymentMethod").getAsString();

            // 3. So sánh với dữ liệu DB
            if (signedUserId != order.getUser_id()) {
                return "DU_LIEU_BI_THAY_DOI";
            }

            if (signedAddressId != order.getAddress_id()) {
                return "DU_LIEU_BI_THAY_DOI";
            }

            if (Double.compare(signedTotal, order.getTotal_amount()) != 0) {
                return "DU_LIEU_BI_THAY_DOI";
            }

            if (!signedPayment.equals(order.getPayment_method())) {
                return "DU_LIEU_BI_THAY_DOI";
            }

            return "HOP_LE";

        } catch (Exception e) {
            e.printStackTrace();
            return "KHONG_HOP_LE";
        }
    }
}