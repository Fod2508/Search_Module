package com.example.dao;

import com.example.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	// Hàm tìm kiếm nâng cấp: Thêm lọc theo Giá (minPrice, maxPrice)
     public List<Product> searchProducts(String keyword, String size, String color, String minPrice, String maxPrice) {
        List<Product> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT p.product_id, p.product_name, p.price, p.image_url ");
        sql.append("FROM Product p ");
        sql.append("JOIN ProductVariant pv ON p.product_id = pv.product_id ");
        sql.append("WHERE 1=1 ");

        // 1. Xử lý từ khóa
        String[] words = null;
        if (keyword != null && !keyword.trim().isEmpty()) {
            words = keyword.trim().split("\\s+"); 
            for (int i = 0; i < words.length; i++) {
                sql.append(" AND p.product_name LIKE ? ");
            }
        }

        // 2. Xử lý Size/Màu
        if (size != null && !size.isEmpty()) {
            sql.append(" AND pv.size = ? ");
        }
        if (color != null && !color.isEmpty()) {
            sql.append(" AND pv.color = ? ");
        }

        // 3. Xử lý Giá tiền
        if (minPrice != null && !minPrice.isEmpty()) {
            sql.append(" AND p.price >= ? ");
        }
        if (maxPrice != null && !maxPrice.isEmpty()) {
            sql.append(" AND p.price <= ? ");
        }

        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int index = 1;

            // Gán từ khóa
            if (words != null) {
                for (String w : words) {
                    ps.setNString(index++, "%" + w + "%");
                }
            }

            // Gán Size/Màu
            if (size != null && !size.isEmpty()) ps.setString(index++, size);
            if (color != null && !color.isEmpty()) ps.setNString(index++, color);

            // Gán Giá tiền - Cần ép kiểu String sang Int
            if (minPrice != null && !minPrice.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(minPrice));
            }
            if (maxPrice != null && !maxPrice.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(maxPrice));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("product_id"),
                    rs.getNString("product_name"),
                    rs.getInt("price"),
                    rs.getString("image_url")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Hàm gợi ý 
    public List<String> getSuggestions(String keyword) {
        List<String> list = new ArrayList<>();
        // SQL Server dùng TOP 5
        String sql = "SELECT TOP 5 product_name FROM Product WHERE product_name LIKE ?";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setNString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getNString("product_name"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
 // Hàm lấy 4 sản phẩm ngẫu nhiên để gợi ý
    public List<Product> getRandomProducts() {
        List<Product> list = new ArrayList<>();
        // SQL Server dùng NEWID() để random
        String sql = "SELECT TOP 4 p.product_id, p.product_name, p.price, p.image_url " +
                     "FROM Product p " +
                     "ORDER BY NEWID()"; 

        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("product_id"),
                    rs.getNString("product_name"),
                    rs.getInt("price"),
                    rs.getString("image_url")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}