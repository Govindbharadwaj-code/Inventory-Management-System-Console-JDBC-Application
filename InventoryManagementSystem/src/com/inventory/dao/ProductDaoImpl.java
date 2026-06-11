package com.inventory.dao;

import com.inventory.bean.Product;
import com.inventory.exceptions.ProductException;
import com.inventory.utility.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public String addProduct(Product p) throws ProductException {
        String msg = "Product could not be added.";
        String sql = "INSERT INTO product(productName, category, supplier, quantity, price, reorderLevel) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getCategory());
            ps.setString(3, p.getSupplier());
            ps.setInt(4, p.getQuantity());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getReorderLevel());
            int x = ps.executeUpdate();
            if (x > 0) msg = "Product '" + p.getProductName() + "' added successfully.";
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
        return msg;
    }

    @Override
    public String updateProduct(int productId, int newQuantity, double newPrice) throws ProductException {
        String msg = "Product not updated.";
        String sql = "UPDATE product SET quantity=?, price=? WHERE productId=?";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setDouble(2, newPrice);
            ps.setInt(3, productId);
            int x = ps.executeUpdate();
            if (x > 0) msg = "Product ID " + productId + " updated successfully.";
            else throw new ProductException("Product with ID " + productId + " not found.");
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
        return msg;
    }

    @Override
    public String deleteProduct(int productId) throws ProductException {
        String msg = "Product not deleted.";
        String sql = "DELETE FROM product WHERE productId=?";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            int x = ps.executeUpdate();
            if (x > 0) msg = "Product ID " + productId + " deleted successfully.";
            else throw new ProductException("Product with ID " + productId + " not found.");
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
        return msg;
    }

    @Override
    public Product getProductById(int productId) throws ProductException {
        String sql = "SELECT * FROM product WHERE productId=?";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
            throw new ProductException("No product found with ID " + productId);
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
    }

    @Override
    public Product getProductByName(String name) throws ProductException {
        String sql = "SELECT * FROM product WHERE productName=?";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
            throw new ProductException("No product found with name '" + name + "'");
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() throws ProductException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY productId";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Product> getLowStockProducts() throws ProductException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE quantity <= reorderLevel ORDER BY quantity";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws ProductException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE category=? ORDER BY productName";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new ProductException("DB Error: " + e.getMessage());
        }
        return list;
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("productId"),
            rs.getString("productName"),
            rs.getString("category"),
            rs.getString("supplier"),
            rs.getInt("quantity"),
            rs.getDouble("price"),
            rs.getInt("reorderLevel")
        );
    }
}
