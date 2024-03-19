import java.sql.*;

public class main {
    // Database connection parameters
    private static final String DB_NAME = "assignment3";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "5432";

    private static Connection connect() {
        Connection connection = null;
        try {
            String url = String.format("jdbc:postgresql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME);
            connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error while connecting to PostgreSQL: " + e.getMessage());
        }
        return connection;
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
    }

    public static void getAllStudents() {
        Connection connection = connect();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
                while (resultSet.next()) {
                    System.out.println("Student ID: " + resultSet.getInt("student_id") +
                            ", First Name: " + resultSet.getString("first_name") +
                            ", Last Name: " + resultSet.getString("last_name") +
                            ", Email: " + resultSet.getString("email") +
                            ", Enrollment Date: " + resultSet.getDate("enrollment_date"));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error while fetching data from PostgreSQL: " + e.getMessage());
            } finally {
                closeConnection(connection);
            }
        }
    }

    public static void addStudent(String firstName, String lastName, String email, String enrollmentDate) {
        Connection connection = connect();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)"
                );
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setDate(4, Date.valueOf(enrollmentDate));
                preparedStatement.executeUpdate();
                System.out.println("Student added successfully");
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error while adding student to PostgreSQL: " + e.getMessage());
            } finally {
                closeConnection(connection);
            }
        }
    }

    public static void updateStudentEmail(int studentId, String newEmail) {
        Connection connection = connect();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE students SET email = ? WHERE student_id = ?"
                );
                preparedStatement.setString(1, newEmail);
                preparedStatement.setInt(2, studentId);
                preparedStatement.executeUpdate();
                System.out.println("Email updated successfully");
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error while updating email in PostgreSQL: " + e.getMessage());
            } finally {
                closeConnection(connection);
            }
        }
    }

    public static void deleteStudent(int studentId) {
        Connection connection = connect();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM students WHERE student_id = ?"
                );
                preparedStatement.setInt(1, studentId);
                preparedStatement.executeUpdate();
                System.out.println("Student deleted successfully");
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error while deleting student in PostgreSQL: " + e.getMessage());
            } finally {
                closeConnection(connection);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("All students:");
        getAllStudents();

        System.out.println("\nAdding a new student:");
        addStudent("Alice", "Johnson", "alice.johnson@example.com", "2023-09-03");
        getAllStudents();

        System.out.println("\nUpdating student's email:");
        updateStudentEmail(1, "john.newemail@example.com");
        getAllStudents();

        System.out.println("\nDeleting a student:");
        deleteStudent(3);
        getAllStudents();
    }
}
