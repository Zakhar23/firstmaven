package jdbc;

import java.sql.*;

public class ResultSetNavigationDemo {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/tothewin";   //jdbc:mysql://localhost:3306/tothewin

    static final String USER = "root";
    static final String PASSWORD = "Aa111111";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Class.forName(JDBC_DRIVER);

        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Creating statement...");

        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            );
            String SQL = "SELECT * FROM tothewin.people";
            ResultSet resultSet = statement.executeQuery(SQL);

            System.out.println("Moving cursor to the last position...");
            resultSet.last();

            System.out.println("Getting record...");
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String number = resultSet.getString("fone_number");
            String email = resultSet.getString("email");

            System.out.println("Last record in result set:");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("number: " + number);
            System.out.println("email: $" + email);
            System.out.println("\n=========================\n");

            System.out.println("Moving cursor to previous row...");
            resultSet.previous();

            System.out.println("Getting record...");
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            number = resultSet.getString("fone_number");
            email = resultSet.getString("email");

            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("number: " + number);
            System.out.println("email: $" + email);
            System.out.println("\n=========================\n");

            System.out.println("Moving cursor to the first row...");
            resultSet.first();

            System.out.println("Getting record...");
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            number = resultSet.getString("fone_number");
            email = resultSet.getString("email");

            System.out.println("First record:");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("number: " + number);
            System.out.println("email: $" + email);
            System.out.println("\n=========================\n");

            System.out.println("Adding record...");
            SQL = "INSERT INTO tothewin.people VALUES (3, 'Mike', '0992245698', 'Mike1990@ukr.net')";
            statement.executeUpdate(SQL);

            SQL = "SELECT * FROM tothewin.people";
            resultSet = statement.executeQuery(SQL);
            resultSet.last();

            System.out.println("Getting record...");
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            number = resultSet.getString("fone_number");
            email = resultSet.getString("email");

            System.out.println("Last record:");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("number: " + number);
            System.out.println("email: $" + email);
            System.out.println("\n=========================\n");


            System.out.println("Full list of records:");
            SQL = "SELECT * FROM tothewin.people";
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                number = resultSet.getString("fone_number");
                email = resultSet.getString("email");

                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("number: " + number);
                System.out.println("email: $" + email);
                System.out.println("\n=========================\n");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
