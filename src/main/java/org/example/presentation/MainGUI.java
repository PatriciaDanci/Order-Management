package org.example.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.example.dao.*;
import org.example.presentation.*;
import org.example.connection.ConnectionFactory;
import org.example.model.*;
import org.example.blll.*;

/**
 * The MainGUI class represents the main graphical user interface (GUI) for the application.
 * It provides buttons to navigate to different sections of the application, such as managing clients,
 * products, and orders.
 */
public class MainGUI extends JFrame {

    private JButton ClientButton; // Button to navigate to client management
    private JButton ProductButton; // Button to navigate to product management
    private JButton OrderButton; // Button to navigate to order management

    private JFrame frame; // The main frame of the GUI

    /**
     * Constructs a new MainGUI instance.
     * Initializes the frame and adds buttons for navigating to different sections.
     */
    public MainGUI() {
        // Setting up the frame
        frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 150));
        frame.getContentPane().setLayout(null);

        // Creating and adding buttons
        ClientButton = new JButton("Client"); // Button to navigate to client management
        ClientButton.setBounds(50, 50, 100, 25);
        frame.getContentPane().add(ClientButton);

        ProductButton = new JButton("Product"); // Button to navigate to product management
        ProductButton.setBounds(200, 50, 100, 25);
        frame.getContentPane().add(ProductButton);

        OrderButton = new JButton("Order"); // Button to navigate to order management
        OrderButton.setBounds(350, 50, 100, 25);
        frame.getContentPane().add(OrderButton);

        // Adding action listeners to buttons
        ClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientGUI(); // Open Client GUI when ClientButton is clicked
            }
        });

        ProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductGUI(); // Open Product GUI when ProductButton is clicked
            }
        });

        OrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderGUI(); // Open Order GUI when OrderButton is clicked
            }
        });

        // Packing and displaying the frame
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * The main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create and display the main GUI
        MainGUI main = new MainGUI();
    }
}

