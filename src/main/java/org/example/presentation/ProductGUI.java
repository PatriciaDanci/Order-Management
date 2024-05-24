package org.example.presentation;

import org.example.blll.ProductBLL;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A graphical user interface for managing products.
 */
public class ProductGUI extends JFrame {

    private final JButton addProduct;
    private final JButton editProduct;
    private final JButton deleteProduct;
    private final JButton viewProducts;

    private final JLabel productId;
    private final JLabel productName;
    private final JLabel productPrice;
    private final JLabel productStock;

    private final JComboBox<Integer> productIdcb;
    private final JTextField productNametf;
    private final JTextField productPricetf;
    private final JTextField productStocktf;

    private final JTable productTable;
    private JFrame frame;

    private final ProductBLL productBLL;

    /**
     * Constructs a new ProductGUI instance.
     */
    public ProductGUI() {
        productBLL = new ProductBLL();

        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 14);

        frame = new JFrame("Product");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.getContentPane().setLayout(null);

        addProduct = new JButton("Add");
        addProduct.setFont(timesNewRoman);
        addProduct.setBounds(50, 50, 100, 25);
        frame.getContentPane().add(addProduct);

        editProduct = new JButton("Edit");
        editProduct.setFont(timesNewRoman);
        editProduct.setBounds(200, 50, 100, 25);
        frame.getContentPane().add(editProduct);

        deleteProduct = new JButton("Delete");
        deleteProduct.setFont(timesNewRoman);
        deleteProduct.setBounds(350, 50, 100, 25);
        frame.getContentPane().add(deleteProduct);

        viewProducts = new JButton("View");
        viewProducts.setFont(timesNewRoman);
        viewProducts.setBounds(500, 50, 100, 25);
        frame.getContentPane().add(viewProducts);

        productId = new JLabel("Product ID");
        productId.setFont(timesNewRoman);
        productId.setBounds(50, 100, 100, 25);
        frame.getContentPane().add(productId);

        productName = new JLabel("Product Name");
        productName.setFont(timesNewRoman);
        productName.setBounds(50, 150, 100, 25);
        frame.getContentPane().add(productName);

        productPrice = new JLabel("Product Price");
        productPrice.setFont(timesNewRoman);
        productPrice.setBounds(50, 200, 100, 25);
        frame.getContentPane().add(productPrice);

        productStock = new JLabel("Product Stock");
        productStock.setFont(timesNewRoman);
        productStock.setBounds(50, 250, 100, 25);
        frame.getContentPane().add(productStock);

        productIdcb = new JComboBox<>();
        productIdcb.setFont(timesNewRoman);
        productIdcb.setBounds(200, 100, 100, 25);
        frame.getContentPane().add(productIdcb);

        productNametf = new JTextField();
        productNametf.setFont(timesNewRoman);
        productNametf.setBounds(200, 150, 100, 25);
        frame.getContentPane().add(productNametf);

        productPricetf = new JTextField();
        productPricetf.setFont(timesNewRoman);
        productPricetf.setBounds(200, 200, 100, 25);
        frame.getContentPane().add(productPricetf);

        productStocktf = new JTextField();
        productStocktf.setFont(timesNewRoman);
        productStocktf.setBounds(200, 250, 100, 25);
        frame.getContentPane().add(productStocktf);

        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 300, 700, 200);
        frame.getContentPane().add(scrollPane);

        populateProductIds();

        frame.pack();
        frame.setVisible(true);

        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        editProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct();
            }
        });

        deleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        viewProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });
    }

    /**
     * Populates the product IDs in the combo box.
     */
    private void populateProductIds() {
        List<Product> products = productBLL.findAllProducts();
        for (Product product : products) {
            productIdcb.addItem(product.getId());
        }
    }

    /**
     * Adds a new product.
     */
    private void addProduct() {
        String name = productNametf.getText();
        double price = Double.parseDouble(productPricetf.getText());
        int stock = Integer.parseInt(productStocktf.getText());

        Product product = new Product(name, price, stock);
        productBLL.addProduct(product);
        JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Edits an existing product.
     */
    private void editProduct() {
        int id = (int) productIdcb.getSelectedItem();
        String name = productNametf.getText();
        double price = Double.parseDouble(productPricetf.getText());
        int stock = Integer.parseInt(productStocktf.getText());

        Product product = new Product(id, name, price, stock);
        productBLL.updateProduct(product);
        JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Deletes a product.
     */
    private void deleteProduct() {
        int id = (int) productIdcb.getSelectedItem();
        productBLL.deleteProduct(id);
        JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Displays all products.
     */
    private void viewProducts() {
        List<Product> products = productBLL.findAllProducts();
        DefaultTableModel model = (DefaultTableModel) productBLL.getProductsTable((ArrayList<Product>) products).getModel();
        productTable.setModel(model);
    }
}
