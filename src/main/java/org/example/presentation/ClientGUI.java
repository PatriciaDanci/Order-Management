package org.example.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.example.blll.ClientBLL;
import org.example.model.Client;

/**
 * The ClientGUI class represents the graphical user interface (GUI) for managing clients.
 */
public class ClientGUI extends JFrame {

    private JButton addClient;
    private JButton editClient;
    private JButton deleteClient;
    private JButton viewClients;
    private JLabel clientId;
    private JLabel clientName;
    private JLabel clientAge;
    private JLabel clientEmail;
    private JLabel clientPhone;

    private JComboBox<Integer> clientIdcb;
    private JTextField clientNametf;
    private JTextField clientAgetf;
    private JTextField clientEmailtf;
    private JTextField clientPhonetf;

    private JTable clientsTable;
    private JScrollPane scrollPane;

    private JFrame frame;
    private ClientBLL clientBLL;

    /**
     * Constructs a new ClientGUI instance.
     */
    public ClientGUI() {
        clientBLL = new ClientBLL();

        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 14);

        frame = new JFrame("Client");
        frame.setPreferredSize(new Dimension(650, 600));
        frame.getContentPane().setLayout(null);

        addClient = new JButton("Add");
        addClient.setFont(timesNewRoman);
        addClient.setBounds(50, 50, 100, 25);
        frame.getContentPane().add(addClient);

        editClient = new JButton("Edit");
        editClient.setFont(timesNewRoman);
        editClient.setBounds(200, 50, 100, 25);
        frame.getContentPane().add(editClient);

        deleteClient = new JButton("Delete");
        deleteClient.setFont(timesNewRoman);
        deleteClient.setBounds(350, 50, 100, 25);
        frame.getContentPane().add(deleteClient);

        viewClients = new JButton("View");
        viewClients.setFont(timesNewRoman);
        viewClients.setBounds(500, 50, 100, 25);
        frame.getContentPane().add(viewClients);

        clientId = new JLabel("Client ID");
        clientId.setFont(timesNewRoman);
        clientId.setBounds(50, 100, 100, 25);
        frame.getContentPane().add(clientId);

        clientName = new JLabel("Client Name");
        clientName.setFont(timesNewRoman);
        clientName.setBounds(50, 150, 100, 25);
        frame.getContentPane().add(clientName);

        clientAge = new JLabel("Client Age");
        clientAge.setFont(timesNewRoman);
        clientAge.setBounds(50, 200, 100, 25);
        frame.getContentPane().add(clientAge);

        clientEmail = new JLabel("Client Email");
        clientEmail.setFont(timesNewRoman);
        clientEmail.setBounds(50, 250, 100, 25);
        frame.getContentPane().add(clientEmail);

        clientPhone = new JLabel("Client Phone");
        clientPhone.setFont(timesNewRoman);
        clientPhone.setBounds(50, 300, 100, 25);
        frame.getContentPane().add(clientPhone);

        clientIdcb = new JComboBox<>();
        clientIdcb.setFont(timesNewRoman);
        clientIdcb.setBounds(200, 100, 100, 25);
        frame.getContentPane().add(clientIdcb);

        clientNametf = new JTextField();
        clientNametf.setFont(timesNewRoman);
        clientNametf.setBounds(200, 150, 100, 25);
        frame.getContentPane().add(clientNametf);

        clientAgetf = new JTextField();
        clientAgetf.setFont(timesNewRoman);
        clientAgetf.setBounds(200, 200, 100, 25);
        frame.getContentPane().add(clientAgetf);

        clientEmailtf = new JTextField();
        clientEmailtf.setFont(timesNewRoman);
        clientEmailtf.setBounds(200, 250, 100, 25);
        frame.getContentPane().add(clientEmailtf);

        clientPhonetf = new JTextField();
        clientPhonetf.setFont(timesNewRoman);
        clientPhonetf.setBounds(200, 300, 100, 25);
        frame.getContentPane().add(clientPhonetf);

        clientsTable = new JTable();
        scrollPane = new JScrollPane(clientsTable);
        scrollPane.setBounds(50, 350, 550, 200);
        frame.getContentPane().add(scrollPane);

        populateClientIds();

        frame.pack();
        frame.setVisible(true);

        // Add action listeners
        addClient.addActionListener(e -> addClient());
        editClient.addActionListener(e -> editClient());
        deleteClient.addActionListener(e -> deleteClient());
        viewClients.addActionListener(e -> viewClients());
    }

    /**
     * Populates the client ID combo box with existing client IDs.
     */
    private void populateClientIds() {
        List<Client> clients = clientBLL.findAllClients();
        for (Client client : clients) {
            clientIdcb.addItem(client.getId());
        }
    }

    /**
     * Adds a new client to the database.
     */
    private void addClient() {
        String name = clientNametf.getText();
        int age = Integer.parseInt(clientAgetf.getText());
        String email = clientEmailtf.getText();
        String phone = clientPhonetf.getText();

        Client client = new Client(name, age, email, phone);
        clientBLL.insertClient(client);
        JOptionPane.showMessageDialog(frame, "Client added successfully.");
    }

    /**
     * Edits an existing client in the database.
     */
    private void editClient() {
        int id = (int) clientIdcb.getSelectedItem();
        String name = clientNametf.getText();
        int age = Integer.parseInt(clientAgetf.getText());
        String email = clientEmailtf.getText();
        String phone = clientPhonetf.getText();

        Client client = new Client(id, name, age, email, phone);
        clientBLL.updateClient(client);
        JOptionPane.showMessageDialog(frame, "Client updated successfully.");
    }

    /**
     * Deletes a client from the database.
     */
    private void deleteClient() {
        int id = (int) clientIdcb.getSelectedItem();
        clientBLL.deleteClient(id);
        JOptionPane.showMessageDialog(frame, "Client deleted successfully.");
    }

    /**
     * Displays all clients in the database.
     */
    private void viewClients() {
        List<Client> clients = clientBLL.findAllClients();
        DefaultTableModel model = (DefaultTableModel) clientBLL.getClientsTable((ArrayList<Client>) clients).getModel();
        clientsTable.setModel(model);
    }
}
