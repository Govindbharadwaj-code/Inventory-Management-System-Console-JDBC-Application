package com.inventory.dao;

import com.inventory.bean.Supplier;
import com.inventory.exceptions.InventoryException;
import com.inventory.utility.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public String addSupplier(Supplier s) throws InventoryException {
        String msg = "Supplier could not be added.";
        String sql = "INSERT INTO supplier(supplierName, contactPerson, phone, email, city) VALUES (?,?,?,?,?)";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getSupplierName());
            ps.setString(2, s.getContactPerson());
            ps.setString(3, s.getPhone());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getCity());
            int x = ps.executeUpdate();
            if (x > 0) msg = "Supplier '" + s.getSupplierName() + "' added successfully.";
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
        return msg;
    }

    @Override
    public String deleteSupplier(int supplierId) throws InventoryException {
        String msg = "Supplier not deleted.";
        String sql = "DELETE FROM supplier WHERE supplierId=?";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supplierId);
            int x = ps.executeUpdate();
            if (x > 0) msg = "Supplier ID " + supplierId + " deleted.";
            else throw new InventoryException("No supplier found with ID " + supplierId);
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
        return msg;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws InventoryException {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM supplier ORDER BY supplierId";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Supplier(
                    rs.getInt("supplierId"),
                    rs.getString("supplierName"),
                    rs.getString("contactPerson"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("city")
                ));
            }
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Supplier getSupplierById(int supplierId) throws InventoryException {
        String sql = "SELECT * FROM supplier WHERE supplierId=?";
        try (Connection conn = DBUtil.provideConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supplierId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Supplier(
                    rs.getInt("supplierId"), rs.getString("supplierName"),
                    rs.getString("contactPerson"), rs.getString("phone"),
                    rs.getString("email"), rs.getString("city")
                );
            }
            throw new InventoryException("No supplier found with ID " + supplierId);
        } catch (SQLException e) {
            throw new InventoryException("DB Error: " + e.getMessage());
        }
    }
}
