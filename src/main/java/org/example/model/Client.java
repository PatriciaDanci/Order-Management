package org.example.model;

/**
 * Represents a client.
 */
public class Client {

    private int id;
    private String name;
    private int age;
    private String email;
    private String phone;

    /**
     * Constructs a new Client with the provided details.
     * @param name The name of the client.
     * @param age The age of the client.
     * @param email The email of the client.
     * @param phone The phone number of the client.
     */
    public Client(String name, int age, String email, String phone) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Constructs a new Client with the provided ID and details.
     * @param id The ID of the client.
     * @param name The name of the client.
     * @param age The age of the client.
     * @param email The email of the client.
     * @param phone The phone number of the client.
     */
    public Client(int id, String name, int age, String email, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Constructs a new empty Client.
     */
    public Client() {

    }

    /**
     * Gets the ID of the client.
     * @return The ID of the client.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the client.
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the client.
     * @return The age of the client.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the email of the client.
     * @return The email of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the client.
     * @return The phone number of the client.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the ID of the client.
     * @param id The ID of the client.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the age of the client.
     * @param age The age of the client.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the name of the client.
     * @param name The name of the client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email of the client.
     * @param email The email of the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the client.
     * @param phone The phone number of the client.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the client.
     * @return A string representation of the client.
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
