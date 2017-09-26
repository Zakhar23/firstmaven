package jdbc;

import java.sql.*;

public class peopleJdbc {

    static final String JDBC_DRIVER = "com.mysql.jdbc.driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/tothewin";   //jdbc:mysql://localhost:3306/tothewin

    static final String USER = "root";
    static final String PASSWORD= "Aa111111";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("qq");

        //Class.forName("com.mysql.jdbc.driver");

        System.out.printf("");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("");
        statement = connection.createStatement();

        String sql;
        sql = "select * from people";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("1");
        System.out.println("\nPeoples:");

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

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();

    }

}
