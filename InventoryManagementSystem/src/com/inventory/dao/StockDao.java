package com.inventory.dao;

import com.inventory.bean.StockTransaction;
import com.inventory.exceptions.InventoryException;
import java.util.List;

public interface StockDao {
    String stockIn(int productId, int quantity, String remarks) throws InventoryException;
    String stockOut(int productId, int quantity, String remarks) throws InventoryException;
    List<StockTransaction> getTransactionsByProduct(int productId) throws InventoryException;
    List<StockTransaction> getAllTransactions() throws InventoryException;
}
