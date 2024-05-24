package org.example.blll.validators;

/**
 * Interface for validating objects of a specific type.
 *
 * @param <T> The type of object to be validated.
 */
public interface Validator<T> {

    /**
     * Validates an object of type T.
     *
     * @param t The object to be validated.
     */
    void validate(T t);
}
