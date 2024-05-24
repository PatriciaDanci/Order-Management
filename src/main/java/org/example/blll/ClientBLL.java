package org.example.blll;

import org.example.dao.ClientDAO;
import org.example.model.Client;
import org.example.blll.validators.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Business Logic Layer class for managing clients.
 */
public class ClientBLL {
    private ArrayList<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a new ClientBLL object.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new AgeValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * Retrieves a JTable representation of the provided list of clients.
     *
     * @param clients The list of clients to be displayed in the table.
     * @return A JTable containing the clients data.
     */
    public JTable getClientsTable(ArrayList<Client> clients) {
        return clientDAO.createTable(clients);
    }

    /**
     * Inserts a new client into the database after validation.
     *
     * @param client The client to be inserted.
     */
    public void insertClient(Client client) {
        validateClient(client);
        clientDAO.insert(client);
    }

    /**
     * Updates an existing client in the database after validation.
     *
     * @param client The client to be updated.
     */
    public void updateClient(Client client) {
        validateClient(client);
        clientDAO.update(client);
    }

    /**
     * Deletes a client from the database by its ID.
     *
     * @param id The ID of the client to be deleted.
     */
    public void deleteClient(int id) {
        clientDAO.deleteById(id);
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return A list of all clients.
     */
    public List<Client> findAllClients() {
        return clientDAO.findAll();
    }

    /**
     * Finds a client in the database by its ID.
     *
     * @param id The ID of the client to find.
     * @return The client found, or null if not found.
     */
    public Client findClientById(int id) {
        return clientDAO.findById(id);
    }

    private void validateClient(Client client) {
        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }
    }
}
