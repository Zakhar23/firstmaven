package jdbc;

import java.sql.*;

public class PreparedStatementDemo {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/tothewin";   //jdbc:mysql://localhost:3306/tothewin

    static final String USER = "root";
    static final String PASSWORD= "Aa111111";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);

        System.out.println("Creating connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);


        try {
            String SQL = "SELECT * FROM tothewin.people";
            preparedStatement = connection.prepareStatement(SQL);
            System.out.println("Initial developers table content:");
            ResultSet resultSet = preparedStatement.executeQuery(SQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String number = resultSet.getString("fone_number");
                String email = resultSet.getString("email");

                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("number: " + number);
                System.out.println("email: $" + email);
            }

            SQL = "Update tothewin.people SET email=? WHERE id=?";
            System.out.println("Creating statement...");
            System.out.println("Executing SQL query...");

            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, "bobly@gmail.com");
            preparedStatement.setInt(2, 2);

            System.out.println("Rows impacted: " + preparedStatement.executeUpdate());

            System.out.println("Final developers table content:");
            SQL = "SELECT * FROM tothewin.people";
            resultSet = preparedStatement.executeQuery(SQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String number = resultSet.getString("fone_number");
                String email = resultSet.getString("email");

                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("number: " + number);
                System.out.println("email: $" + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }

        System.out.println("Thank You.");
    }
}
