package com.inventory.dao;

import com.inventory.bean.Supplier;
import com.inventory.exceptions.InventoryException;
import java.util.List;

public interface SupplierDao {
    String addSupplier(Supplier supplier) throws InventoryException;
    String deleteSupplier(int supplierId) throws InventoryException;
    List<Supplier> getAllSuppliers() throws InventoryException;
    Supplier getSupplierById(int supplierId) throws InventoryException;
}
