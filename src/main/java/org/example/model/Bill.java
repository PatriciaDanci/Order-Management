package org.example.model;

/**
 * Represents a bill.
 */
public record Bill(String clientName, String productName, int quantity, float finalPrice) {

    /**
     * Constructs a new Bill record.
     * @param clientName The name of the client.
     * @param productName The name of the product.
     * @param quantity The quantity of the product.
     * @param finalPrice The final price of the bill.
     * @throws IllegalArgumentException if quantity is less than or equal to 0,
     *                                  or if final price is less than or equal to 0.
     */
    public Bill {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (finalPrice <= 0) {
            throw new IllegalArgumentException("Final price must be greater than zero.");
        }
    }
}
