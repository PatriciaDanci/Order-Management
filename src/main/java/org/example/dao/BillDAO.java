package org.example.dao;

import org.example.model.Bill;
import org.example.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillDAO extends AbstractDAO<Bill> {

    /**
     * Inserts a new bill into the database.
     *
     * @param bill The bill object to be inserted.
     */
    public void insertBill(Bill bill) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, bill.clientName());
            statement.setString(2, bill.productName());
            statement.setInt(3, bill.quantity());
            statement.setFloat(4, bill.finalPrice());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Failed to insert bill: " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Retrieves all bills from the database.
     *
     * @return A list of all bills.
     */
    public List<Bill> findAllBills() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Bill> bills = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM bill"); // Assuming "bill" is the table name
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getString("clientName"),
                        resultSet.getString("productName"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("finalPrice")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Failed to retrieve bills: " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return bills;
    }
}
