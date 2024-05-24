package org.example.dao;

import org.example.connection.ConnectionFactory;
import org.example.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing Order entities in the database.
 */
public class OrderDAO extends AbstractDAO<Order> {
    private static final String INSERT_QUERY = "INSERT INTO `order` (client_id, product_id, quantity) VALUES (?, ?, ?)";

    /**
     * Inserts an Order entity into the database.
     *
     * @param order The Order object to insert.
     */
    public void insertOrder(Order order) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, order.getClient_id());
            preparedStatement.setInt(2, order.getProduct_id());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting order: " + e.getMessage());
        }
    }
}
