package com.inventory.dao;

import com.inventory.bean.StockTransaction;
import com.inventory.exceptions.InventoryException;
import com.inventory.utility.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao {

    @Override
    public String stockIn(int productId, int quantity, String remarks) throws InventoryException {
        String msg = "Stock-In failed.";
        try (Connection conn = DBUtil.provideConnection()) {
            // Insert transaction
            String insSql = "INSERT INTO stock_transaction(productId, transactionType, quantity, transactionDate, remarks) VALUES (?,?,?,CURDATE(),?)";
            PreparedStatement ps1 = conn.prepareStatement(insSql);
            ps1.setInt(1, productId);
            ps1.setString(2, "IN");
            ps1.setInt(3, quantity);
            ps1.setString(4, remarks);
            ps1.executeUpdate();

            // Update product quantity
            String updSql = "UPDATE product SET quantity = quantity + ? WHERE productId = ?";
            PreparedStatement ps2 = conn.prepareStatement(updSql);
            ps2.setInt(1, quantity);
            ps2.setInt(2, productId);
            int x = ps2.executeUpdate();
            if (x > 0) msg = "Stock-In of " + quantity + " units recorded for Product ID " + productId;
            else throw new InventoryException("Product ID " + productId + " not found.");
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
        return msg;
    }

    @Override
    public String stockOut(int productId, int quantity, String remarks) throws InventoryException {
        String msg = "Stock-Out failed.";
        try (Connection conn = DBUtil.provideConnection()) {
            // Check available quantity
            PreparedStatement chk = conn.prepareStatement("SELECT quantity FROM product WHERE productId=?");
            chk.setInt(1, productId);
            ResultSet rs = chk.executeQuery();
            if (!rs.next()) throw new InventoryException("Product ID " + productId + " not found.");
            int available = rs.getInt("quantity");
            if (available < quantity)
                throw new InventoryException("Insufficient stock! Available: " + available + ", Requested: " + quantity);

            // Insert transaction
            String insSql = "INSERT INTO stock_transaction(productId, transactionType, quantity, transactionDate, remarks) VALUES (?,?,?,CURDATE(),?)";
            PreparedStatement ps1 = conn.prepareStatement(insSql);
            ps1.setInt(1, productId);
            ps1.setString(2, "OUT");
            ps1.setInt(3, quantity);
            ps1.setString(4, remarks);
            ps1.executeUpdate();

            // Update product quantity
            String updSql = "UPDATE product SET quantity = quantity - ? WHERE productId = ?";
            PreparedStatement ps2 = conn.prepareStatement(updSql);
            ps2.setInt(1, quantity);
            ps2.setInt(2, productId);
            ps2.executeUpdate();

            msg = "Stock-Out of " + quantity + " units recorded for Product ID " + productId;
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
        return msg;
    }

    @Override
    public List<StockTransaction> getTransactionsByProduct(int productId) throws InventoryException {
        List<StockTransaction> list = new ArrayList<>();
        String sql = "SELECT * FROM stock_transaction WHERE productId=? ORDER BY transactionDate DESC";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<StockTransaction> getAllTransactions() throws InventoryException {
        List<StockTransaction> list = new ArrayList<>();
        String sql = "SELECT * FROM stock_transaction ORDER BY transactionDate DESC, transactionId DESC";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
        return list;
    }

    private StockTransaction mapRow(ResultSet rs) throws SQLException {
        return new StockTransaction(
            rs.getInt("transactionId"),
            rs.getInt("productId"),
            rs.getString("transactionType"),
            rs.getInt("quantity"),
            rs.getString("transactionDate"),
            rs.getString("remarks")
        );
    }
}
