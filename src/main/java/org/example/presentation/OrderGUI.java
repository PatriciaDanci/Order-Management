package org.example.presentation;

import org.example.blll.BillBLL;
import org.example.blll.ClientBLL;
import org.example.blll.OrderBLL;
import org.example.blll.ProductBLL;
import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Order;
import org.example.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The OrderGUI class represents the graphical user interface (GUI) for managing orders.
 */
public class OrderGUI {
    private final JTextField quantity;
    private final JComboBox<Integer> clientIdcb;
    private final JComboBox<Integer> productIdcb;
    private final JFrame frame;
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;
    private final BillBLL billBLL;

    /**
     * Constructs a new OrderGUI instance.
     */
    public OrderGUI() {
        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 14);

        frame = new JFrame("Order");
        frame.setPreferredSize(new Dimension(650, 400));
        frame.getContentPane().setLayout(null);

        JLabel client = new JLabel("Client ID");
        client.setFont(timesNewRoman);
        client.setBounds(50, 50, 100, 25);
        frame.getContentPane().add(client);

        clientIdcb = new JComboBox<>();
        clientIdcb.setFont(timesNewRoman);
        clientIdcb.setBounds(150, 50, 100, 25);
        frame.getContentPane().add(clientIdcb);

        JLabel product = new JLabel("Product ID");
        product.setFont(timesNewRoman);
        product.setBounds(50, 100, 100, 25);
        frame.getContentPane().add(product);

        productIdcb = new JComboBox<>();
        productIdcb.setFont(timesNewRoman);
        productIdcb.setBounds(150, 100, 100, 25);
        frame.getContentPane().add(productIdcb);

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(timesNewRoman);
        quantityLabel.setBounds(50, 150, 100, 25);
        frame.getContentPane().add(quantityLabel);

        quantity = new JTextField();
        quantity.setFont(timesNewRoman);
        quantity.setBounds(150, 150, 100, 25);
        frame.getContentPane().add(quantity);

        JButton addOrder = new JButton("Order");
        addOrder.setFont(timesNewRoman);
        addOrder.setBounds(50, 200, 100, 25);
        frame.getContentPane().add(addOrder);

        JButton showBills = new JButton("Bills");
        showBills.setFont(timesNewRoman);
        showBills.setBounds(200, 200, 100, 25);
        frame.getContentPane().add(showBills);

        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        billBLL = new BillBLL();

        populateComboBoxes();

        addOrder.addActionListener(e -> placeOrder());

        showBills.addActionListener(e -> showBills());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Populates the client ID and product ID combo boxes with existing IDs.
     */
    private void populateComboBoxes() {
        List<Client> clients = clientBLL.findAllClients();
        for (Client client : clients) {
            clientIdcb.addItem(client.getId());
        }

        List<Product> products = productBLL.findAllProducts();
        for (Product product : products) {
            productIdcb.addItem(product.getId());
        }
    }

    /**
     * Places a new order.
     */
    private void placeOrder() {
        int clientId = (int) clientIdcb.getSelectedItem();
        int productId = (int) productIdcb.getSelectedItem();
        int orderQuantity = Integer.parseInt(quantity.getText());

        Product product = productBLL.findProductById(productId);
        float finalPrice = (float) (product.getPrice() * orderQuantity);

        Order order = new Order(clientId, productId, orderQuantity);
        orderBLL.placeOrder(order);

        Client client = clientBLL.findClientById(clientId);
        String clientName = client.getName();
        String productName = product.getName();
        Bill bill = new Bill(clientName, productName, orderQuantity, finalPrice);
        billBLL.insertBill(bill);

        JOptionPane.showMessageDialog(frame, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows all bills.
     */
    private void showBills() {
        List<Bill> bills = billBLL.getAllBills();
        JTable table = billBLL.getBillsTable((ArrayList<Bill>) bills);

        JFrame billFrame = new JFrame("Bills");
        billFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billFrame.getContentPane().add(new JScrollPane(table));
        billFrame.pack();
        billFrame.setVisible(true);
    }
}
