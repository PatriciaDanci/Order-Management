package org.example.model;

/**
 * Represents an order.
 */
public class Order {

    private int id;
    private int client_id;
    private int product_id;
    private int quantity;

    /**
     * Constructs a new Order with the provided details.
     * @param client_id The ID of the client placing the order.
     * @param product_id The ID of the product being ordered.
     * @param quantity The quantity of the product being ordered.
     */
    public Order(int client_id, int product_id, int quantity) {
        //this.id = id;
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    /**
     * Constructs a new empty Order.
     */
    public Order() {

    }

    /**
     * Gets the ID of the order.
     * @return The ID of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the ID of the client placing the order.
     * @return The ID of the client placing the order.
     */
    public int getClient_id() {
        return client_id;
    }

    /**
     * Gets the ID of the product being ordered.
     * @return The ID of the product being ordered.
     */
    public int getProduct_id() {
        return product_id;
    }

    /**
     * Gets the quantity of the product being ordered.
     * @return The quantity of the product being ordered.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the ID of the order.
     * @param id The ID of the order.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the ID of the client placing the order.
     * @param client_id The ID of the client placing the order.
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    /**
     * Sets the ID of the product being ordered.
     * @param product_id The ID of the product being ordered.
     */
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    /**
     * Sets the quantity of the product being ordered.
     * @param quantity The quantity of the product being ordered.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the order.
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client_id='" + client_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
