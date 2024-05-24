package org.example.model;

/**
 * Represents a product.
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;

    /**
     * Constructs a new Product with the provided details.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The stock quantity of the product.
     */
    public Product(String name, double price, int stock) {
        //this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructs a new Product with the provided details.
     * @param id The ID of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The stock quantity of the product.
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructs a new empty Product.
     */
    public Product() {

    }

    /**
     * Gets the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the product.
     * @return The ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the price of the product.
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the stock quantity of the product.
     * @return The stock quantity of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the ID of the product.
     * @param id The ID of the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the product.
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the product.
     * @param price The price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the stock quantity of the product.
     * @param stock The stock quantity of the product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns a string representation of the product.
     * @return A string representation of the product.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
