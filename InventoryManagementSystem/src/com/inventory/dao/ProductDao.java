package com.inventory.dao;

import com.inventory.bean.Product;
import com.inventory.exceptions.ProductException;
import java.util.List;

public interface ProductDao {
    String addProduct(Product product) throws ProductException;
    String updateProduct(int productId, int newQuantity, double newPrice) throws ProductException;
    String deleteProduct(int productId) throws ProductException;
    Product getProductById(int productId) throws ProductException;
    Product getProductByName(String name) throws ProductException;
    List<Product> getAllProducts() throws ProductException;
    List<Product> getLowStockProducts() throws ProductException;
    List<Product> getProductsByCategory(String category) throws ProductException;
}
