package org.example.blll.validators;

import org.example.model.Client;

/**
 * Validator implementation to check if a client's age meets a minimum limit.
 */
public class AgeValidator implements Validator<Client> {

    /**
     * Validates a client's age.
     *
     * @param client The client to be validated.
     * @throws IllegalArgumentException if the client's age is below the minimum limit.
     */
    @Override
    public void validate(Client client) {
        if (client.getAge() < 15) {
            throw new IllegalArgumentException("The age limit is not respected!");
        }
    }

}
