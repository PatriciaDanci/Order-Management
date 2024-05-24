package org.example.blll;

import org.example.dao.*;
import org.example.model.*;

import javax.swing.*;
import java.util.*;

/**
 * Business Logic Layer class for managing products.
 */
public class ProductBLL {
    private ProductDAO productDAO;

    /**
     * Constructs a new ProductBLL object.
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Adds a new product.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        productDAO.insert(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product The product to be updated.
     */
    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     */
    public void deleteProduct(int id) {
        productDAO.deleteById(id);
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product to find.
     * @return The product found, or null if not found.
     */
    public Product findProductById(int id) {
        return productDAO.findById(id);
    }

    /**
     * Generates a JTable representation of the provided list of products.
     *
     * @param products The list of products to be displayed in the table.
     * @return A JTable containing the product's data.
     */
    public JTable getProductsTable(ArrayList<Product> products) {
        return productDAO.createTable(products);
    }
}
