package com.webthietbibep.dao;

import com.webthietbibep.context.DBContext;
import com.webthietbibep.model.Combo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComboDAO {

    public static List<Combo> getAll() {
        List<Combo> list = new ArrayList<>();
        String sql = "SELECT * FROM Combos";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Combo c = new Combo();
                c.setComboId(rs.getInt("combo_id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setImageUrl(rs.getString("image_url"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
