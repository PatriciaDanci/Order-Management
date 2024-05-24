package org.example.blll;

import org.example.dao.BillDAO;
import org.example.model.Bill;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Business Logic Layer class for managing bills.
 */
public class BillBLL {
    private final BillDAO billDAO;

    /**
     * Constructs a new BillBLL object.
     */
    public BillBLL() {
        this.billDAO = new BillDAO();
    }

    /**
     * Inserts a new bill into the database.
     *
     * @param bill The bill to be inserted.
     */
    public void insertBill(Bill bill) {
        billDAO.insertBill(bill);
    }

    /**
     * Retrieves all bills from the database.
     *
     * @return A list of all bills.
     */
    public List<Bill> getAllBills() {
        return billDAO.findAllBills();
    }

    /**
     * Creates a JTable representation of the provided list of bills.
     *
     * @param bills The list of bills to be displayed in the table.
     * @return A JTable containing the bills data.
     */
    public JTable getBillsTable(ArrayList<Bill> bills) {
        return billDAO.createTable(bills);
    }
}
