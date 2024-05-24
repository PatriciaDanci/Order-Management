package org.example.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.connection.ConnectionFactory;
import org.example.dao.AbstractDAO;
import org.example.presentation.*;
import org.example.model.*;
import org.example.blll.*;

import javax.swing.*;
import javax.swing.table.*;

/**
 * A generic DAO (Data Access Object) for interacting with the database.
 *
 * @param <T> The type of object this DAO handles.
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * Constructs a new AbstractDAO instance.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append(" WHERE ");
        sb.append(field);
        sb.append(" = ?");
        return sb.toString();
    }

    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append(" WHERE ");
        sb.append(field);
        sb.append(" = ?");
        return sb.toString();
    }

    String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append(" (");

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                sb.append(field.getName());
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append(") VALUES (");
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                sb.append("?");
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    private String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append(" SET ");
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                sb.append(field.getName()).append("=?,");
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append(" WHERE id=?");
        return sb.toString();
    }

    /**
     * Retrieves all objects of type T from the database.
     *
     * @return A list of objects of type T.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Retrieves an object of type T by its ID from the database.
     *
     * @param id The ID of the object.
     * @return The object of type T if found, otherwise null.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> result = createObjects(resultSet);
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Deletes an object by its ID from the database.
     *
     * @param id The ID of the object to delete.
     */
    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Inserts an object into the database.
     *
     * @param t The object to insert.
     * @return The inserted object.
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            Field[] fields = type.getDeclaredFields();
            int index = 1;
            for (Field field : fields) {
                if (!field.getName().equalsIgnoreCase("id")) {
                    field.setAccessible(true);
                    statement.setObject(index++, field.get(t));
                }
            }

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                Method setIdMethod = type.getMethod("setId", int.class);
                setIdMethod.invoke(t, id);
            }
            return t;
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Updates an object in the database.
     *
     * @param t The object to update.
     * @return The updated object.
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            Field[] fields = type.getDeclaredFields();
            int i = 1;
            Object idValue = null;
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase("id")) {
                    idValue = field.get(t);
                } else {
                    statement.setObject(i++, field.get(t));
                }
            }
            statement.setObject(i, idValue);

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }


    List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0) break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException |
                 InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Creates a JTable filled with data from the provided list of objects.
     *
     * @param data The list of objects.
     * @return A JTable filled with the data.
     */
    public JTable createTable(List<T> data) {
        if (data == null || data.isEmpty()) {
            return new JTable();
        }

        List<String> columnsName = getColumnNames(data.get(0).getClass());
        DefaultTableModel tableModel = createTableModel(columnsName);
        populateTableModel(tableModel, data);

        return new JTable(tableModel);
    }

    private List<String> getColumnNames(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> columnsName = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            columnsName.add(field.getName());
        }
        return columnsName;
    }

    private DefaultTableModel createTableModel(List<String> columnsName) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsName.toArray());
        return tableModel;
    }

    private void populateTableModel(DefaultTableModel tableModel, List<T> data) {
        for (T item : data) {
            List<Object> rowData = extractFieldsData(item);
            tableModel.addRow(rowData.toArray());
        }
    }

    private List<Object> extractFieldsData(T item) {
        List<Object> fieldData = new ArrayList<>();
        Field[] fields = item.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                fieldData.add(field.get(item));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field data", e);
            }
        }
        return fieldData;
    }
}
