package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Script SQL para crear la tabla
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "username VARCHAR(100) NOT NULL UNIQUE, " +
                    "email VARCHAR(100) NOT NULL)";

    // Consultas CRUD
    private static final String INSERT_SQL = "INSERT INTO users (username, email) VALUES (?, ?) RETURNING id";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM users";
    private static final String UPDATE_SQL = "UPDATE users SET username = ?, email = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";

    // Inicializar la tabla
    public void createTableIfNotExists() {
        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(CREATE_TABLE_SQL);
            System.out.println("Tabla 'users' creada o ya existente");

        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Crear un usuario
    public User create(User user) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                    return user;
                } else {
                    throw new SQLException("Error al obtener ID generado");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Buscar usuario por ID
    public User findById(Long id) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("email")
                    );
                } else {
                    return null; // Usuario no encontrado
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Listar todos los usuarios
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return users;
    }

    // Actualizar usuario
    public boolean update(User user) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setLong(3, user.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Eliminar usuario
    public boolean delete(Long id) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {

            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Método de prueba para simular carga
    public void simulateLoad(int iterations) {
        System.out.println("Iniciando simulación de carga...");
        for (int i = 0; i < iterations; i++) {
            try {
                // Crear un usuario
                User user = new User("user" + i, "user" + i + "@example.com");
                user = create(user);

                // Buscar el usuario
                User foundUser = findById(user.getId());

                // Actualizar el usuario
                foundUser.setEmail("updated" + i + "@example.com");
                update(foundUser);

                // Eliminar el usuario
                delete(user.getId());

                if (i % 10 == 0) {
                    System.out.println("Iteración " + i + " - " + ConnectionPool.getPoolStats());
                }
            } catch (Exception e) {
                System.err.println("Error en iteración " + i + ": " + e.getMessage());
            }
        }
        System.out.println("Simulación de carga completada");
    }
}