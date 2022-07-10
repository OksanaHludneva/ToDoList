import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class Database {

    static Database database = new Database();
    static Scanner scanner = new Scanner(System.in);
    private static String status;

    public void initialize() {
        this.displayDatabase();
    }

    private static void displayDatabase() {

        final String dbURL = "";
        final String user = "";
        final String password = "";

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {

            boolean quit = false;
            int choice;

            database.printInstructions();

            while (!quit) {
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        database.readData(conn);
                        break;
                    case 2:
                        database.insertData(conn);
                        break;
                    case 3: {
                        System.out.println("Enter Task ID you want to update: ");
                        int taskID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Update task status: ");
                        String status = scanner.nextLine();
                        database.updateData(conn, status, taskID);
                        break;
                    }
                    case 4:
                        System.out.println("Enter Task ID you want to delete: ");
                        int taskID = scanner.nextInt();
                        database.deleteData(conn, taskID);
                        break;
                    case 5:
                        database.pendingTasks(conn);
                        break;
                    case 6:
                        database.completedTasks(conn);
                        break;
                    case 7:
                        quit = true;
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("No connection " + e);
        }
    }

    public static void printInstructions() {
        System.out.println("Press\n" +
                "1 - To see all tasks\n" +
                "2 - To add a new task\n" +
                "3 - To update a task\n" +
                "4 - To remove a task\n" +
                "5 - To see all pending tasks \n" +
                "6 - To see all completed tasks \n" +
                "7 - To quit the application");
    }

    public static void readData(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ToDo_List;";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int taskID = resultSet.getInt(1);
            Date Date = resultSet.getDate(2);
            String name = resultSet.getString(3);
            String description = resultSet.getString(4);
            String priority = resultSet.getString(5);
            String status = resultSet.getString(6);

            String output = "Task: \n\t ID: %d \n\t Date: %s \n\t " +
                    "Name: %s \n\t Description: %s \n\t Priority: %s \n\t Status: %s ";

            System.out.printf((output) + "%n", taskID, Date, name, description, priority, status);
        }
    }

    public static void insertData(Connection conn) throws SQLException {

        String sql = "INSERT INTO ToDo_List (date, name, description, priority, status) VALUES (?,?,?,?,?);";

        System.out.println("Enter date (YYYY-MM-DD): ");
        Date date = Date.valueOf(scanner.nextLine());

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter description: ");
        String description = scanner.nextLine();

        System.out.println("Enter priority: ");
        String priority = scanner.nextLine();

        System.out.println("Enter status: ");
        String status = scanner.nextLine();

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setDate(1, date);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, description);
        preparedStatement.setString(4, priority);
        preparedStatement.setString(5, status);

        int rowInserted = preparedStatement.executeUpdate();

        if (rowInserted > 0) {
            System.out.println("A new task was inserted successfully");
        } else {
            System.out.println("Something went wrong");
        }
    }

    public static void updateData(Connection conn, String status, int taskID) throws SQLException {
        String sql = "UPDATE ToDo_List SET Status = ? WHERE TaskID = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, taskID);

        int rowUpdated = preparedStatement.executeUpdate();

        if (rowUpdated > 0) {
            System.out.println("A task was updated successfully");
        } else {
            System.out.println("Something went wrong");
        }
    }

    public static void deleteData(Connection conn, int taskID) throws SQLException {
        String sql = "DELETE FROM ToDo_List WHERE TaskID = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, taskID);

        int rowDeleted = preparedStatement.executeUpdate();

        if (rowDeleted > 0) {
            System.out.println("A task was deleted successfully");
        } else {
            System.out.println("Something went wrong");
        }
    }

    public static void pendingTasks(Connection conn) throws SQLException {
        String sql = "SELECT TaskID, Description FROM ToDo_List WHERE Status = 'Pending'";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Pending tasks: ");

        while (resultSet.next()) {
            int taskID = resultSet.getInt(1);
            String pendingTasks = resultSet.getString(2);
            System.out.println(taskID + ") " + pendingTasks);
        }
    }

    public static void completedTasks(Connection conn) throws SQLException {
        String sql = "SELECT TaskID, Description FROM ToDo_List WHERE Status = 'Done'";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Completed tasks: ");

        while (resultSet.next()) {
            int taskID = resultSet.getInt(1);
            String completedTasks = resultSet.getString(2);
            System.out.println(taskID + ") " + completedTasks);
        }
    }
}
