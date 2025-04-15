package com.example;

import java.util.List;
import java.util.Scanner;

public class DatabaseApp {
    private static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        System.out.println("Iniciando aplicación con pool de conexiones PostgreSQL");

        try {
            // Crear la tabla si no existe
            userDAO.createTableIfNotExists();

            // Menú interactivo
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                showMenu();
                int option = getOption(scanner);

                switch (option) {
                    case 1:
                        createUser(scanner);
                        break;
                    case 2:
                        findUserById(scanner);
                        break;
                    case 3:
                        listAllUsers();
                        break;
                    case 4:
                        updateUser(scanner);
                        break;
                    case 5:
                        deleteUser(scanner);
                        break;
                    case 6:
                        simulateLoad(scanner);
                        break;
                    case 7:
                        showPoolStats();
                        break;
                    case 8:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }

                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

            System.out.println("Cerrando la aplicación...");

        } finally {
            // Cerrar el pool de conexiones al finalizar
            ConnectionPool.closePool();
        }
    }

    private static void showMenu() {
        System.out.println("\n=== GESTIÓN DE USUARIOS CON POOL DE CONEXIONES ===");
        System.out.println("1. Crear nuevo usuario");
        System.out.println("2. Buscar usuario por ID");
        System.out.println("3. Listar todos los usuarios");
        System.out.println("4. Actualizar usuario");
        System.out.println("5. Eliminar usuario");
        System.out.println("6. Simular carga (prueba de pool)");
        System.out.println("7. Mostrar estadísticas del pool");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int getOption(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }

    private static void createUser(Scanner scanner) {
        System.out.println("\n=== CREAR NUEVO USUARIO ===");

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        try {
            User user = new User(username, email);
            user = userDAO.create(user);
            System.out.println("Usuario creado con éxito: " + user);
        } catch (Exception e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
        }
    }

    private static void findUserById(Scanner scanner) {
        System.out.println("\n=== BUSCAR USUARIO POR ID ===");

        System.out.print("ID del usuario: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());

            User user = userDAO.findById(id);
            if (user != null) {
                System.out.println("Usuario encontrado: " + user);
            } else {
                System.out.println("No se encontró ningún usuario con ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.err.println("ID inválido");
        } catch (Exception e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
    }

    private static void listAllUsers() {
        System.out.println("\n=== LISTADO DE USUARIOS ===");

        try {
            List<User> users = userDAO.findAll();

            if (users.isEmpty()) {
                System.out.println("No hay usuarios registrados");
                return;
            }

            for (User user : users) {
                System.out.println(user);
            }
            System.out.println("Total usuarios: " + users.size());
        } catch (Exception e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
    }

    private static void updateUser(Scanner scanner) {
        System.out.println("\n=== ACTUALIZAR USUARIO ===");

        System.out.print("ID del usuario a actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());

            User user = userDAO.findById(id);
            if (user == null) {
                System.out.println("No se encontró ningún usuario con ID: " + id);
                return;
            }

            System.out.println("Usuario encontrado: " + user);

            System.out.print("Nuevo username [" + user.getUsername() + "]: ");
            String username = scanner.nextLine().trim();
            if (!username.isEmpty()) {
                user.setUsername(username);
            }

            System.out.print("Nuevo email [" + user.getEmail() + "]: ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty()) {
                user.setEmail(email);
            }

            boolean updated = userDAO.update(user);
            if (updated) {
                System.out.println("Usuario actualizado con éxito: " + user);
            } else {
                System.out.println("No se pudo actualizar el usuario");
            }
        } catch (NumberFormatException e) {
            System.err.println("ID inválido");
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    private static void deleteUser(Scanner scanner) {
        System.out.println("\n=== ELIMINAR USUARIO ===");

        System.out.print("ID del usuario a eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());

            User user = userDAO.findById(id);
            if (user == null) {
                System.out.println("No se encontró ningún usuario con ID: " + id);
                return;
            }

            System.out.println("Usuario a eliminar: " + user);
            System.out.print("¿Está seguro? (s/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("s")) {
                boolean deleted = userDAO.delete(id);
                if (deleted) {
                    System.out.println("Usuario eliminado con éxito");
                } else {
                    System.out.println("No se pudo eliminar el usuario");
                }
            } else {
                System.out.println("Operación cancelada");
            }
        } catch (NumberFormatException e) {
            System.err.println("ID inválido");
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    private static void simulateLoad(Scanner scanner) {
        System.out.println("\n=== SIMULACIÓN DE CARGA ===");

        System.out.print("Número de iteraciones (recomendado 100-1000): ");
        try {
            int iterations = Integer.parseInt(scanner.nextLine().trim());
            userDAO.simulateLoad(iterations);
        } catch (NumberFormatException e) {
            System.err.println("Número inválido");
        } catch (Exception e) {
            System.err.println("Error durante la simulación: " + e.getMessage());
        }
    }

    private static void showPoolStats() {
        System.out.println("\n=== ESTADÍSTICAS DEL POOL DE CONEXIONES ===");
        System.out.println(ConnectionPool.getPoolStats());
    }
}