package com.accounting;

import java.sql.*;

public class DbUtils {

    private Connection connection;

    public DbUtils(String hostName, String dbName, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + hostName + "/" + dbName, userName, password);
    }


    public ResultSet getSummaryForPeriod(Date start, Date end) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT pharm_title AS name, SUM(income) AS revenues, SUM(spending) AS spendings " +
                "FROM balance INNER JOIN pharmacy ON balance.id_pharmacy = pharmacy.id_pharmacy " +
                "WHERE date BETWEEN ? AND ? " +
                "GROUP BY pharm_title");

        statement.setDate(1, start);
        statement.setDate(2, end);

        return statement.executeQuery();
    }
}
