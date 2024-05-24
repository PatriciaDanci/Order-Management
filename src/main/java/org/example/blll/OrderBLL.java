package org.example.blll;

import org.example.dao.*;
import org.example.model.*;

import javax.swing.*;
import java.util.*;

/**
 * Business Logic Layer class for managing orders.
 */
public class OrderBLL {
    private OrderDAO orderDAO;

    /**
     * Constructs a new OrderBLL object.
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Places an order for a product.
     *
     * @param order The order to be placed.
     * @throws IllegalArgumentException If there is not enough stock available for the order.
     */
    public void placeOrder(Order order) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(order.getProduct_id());

        if (product.getStock() < order.getQuantity()) {
            throw new IllegalArgumentException("Under-stock: Not enough products available.");
        }

        orderDAO.insertOrder(order);

        product.setStock(product.getStock() - order.getQuantity());
        productDAO.update(product);
    }

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order to find.
     * @return The order found, or null if not found.
     */
    public Order findOrderById(int id) {
        return orderDAO.findById(id);
    }

    /**
     * Retrieves all orders.
     *
     * @return A list of all orders.
     */
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    /**
     * Generates a JTable representation of the provided list of orders.
     *
     * @param orders The list of orders to be displayed in the table.
     * @return A JTable containing the orders data.
     */
    public JTable OrdersTable(ArrayList<Order> orders) {
        return orderDAO.createTable(orders);
    }
}
