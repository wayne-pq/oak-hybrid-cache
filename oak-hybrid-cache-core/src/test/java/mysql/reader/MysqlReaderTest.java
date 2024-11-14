package mysql.reader;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author qian.pan on 2024/10/12.
 */
public class MysqlReaderTest {

    public void asyncUpdate() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://123.123.8.2:13306/*****";
        String user = "root";
        String password = "*****";

        // JDBC variables for opening and managing connection
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Open a connection
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            for (int i = 0; i < 20; i++) {
                // Create a statement with forward-only and read-only properties
                // Perform asynchronous update query to set target = 'test' for id = 1
                String updateSql = "UPDATE cn_crontab_log SET target = 'test' WHERE id = 1";
                int rowsAffected = stmt.executeUpdate(updateSql);
                System.out.println("Asynchronous Update: " + rowsAffected + " rows affected.");
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Clean-up the environment
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void asyncQuery() {
        String url = "jdbc:mysql://123.123.8.2:13306/*****";
        String user = "root";
        String password = "*****";

        // JDBC variables for opening and managing connection
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Open a connection
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            for (int i = 0; i < 20; i++) {
                // Create a statement with forward-only and read-only properties
                String sql = "SELECT * FROM cn_crontab_log WHERE id = 1";
                rs = stmt.executeQuery(sql);

                // Iterate through the result set
                while (rs.next()) {
                    // Assuming the table has a column named 'id' and 'name'
                    int id = rs.getInt("id");
                    String target = rs.getString("target");

                    // Print the results
                    System.out.println("ID: " + id + ", target: " + target);
                }
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Clean-up the environment
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void queryForStream() {

//        new Thread(this::asyncQuery).start();
//        new Thread(this::asyncUpdate).start();
// JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://123.123.8.2:13306/*****";
        String user = "root";
        String password = "*****";


        // JDBC variables for opening and managing connection
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Open a connection
            conn = DriverManager.getConnection(url, user, password);

            // Create a statement with forward-only and read-only properties
            stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
                    java.sql.ResultSet.CONCUR_READ_ONLY);

            // Set fetch size to Integer.MIN_VALUE to stream the data row by row
            stmt.setFetchSize(Integer.MIN_VALUE);

            // Execute a query
            String sql = "SELECT * FROM cn_crontab_log";
            rs = stmt.executeQuery(sql);

            // Iterate through the result set
            while (rs.next()) {

                Thread.sleep(1000);
                // Assuming the table has a column named 'id' and 'name'
                int id = rs.getInt("id");
                if (id == 1) {
                    rs.updateObject("target", "test2");
                    rs.updateRow();
                }
                String target = rs.getString("target");

                // Print the results
                System.out.println("ID: " + id + ", target: " + target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Clean-up the environment
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void queryForCursorBasedStreaming() {
        // JDBC URL with useCursorFetch enabled
        String url = "jdbc:mysql://123.123.8.2:13306/*****?useCursorFetch=true";
        String user = "root";
        String password = "*****";

        // JDBC variables for opening and managing connection
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Open a connection with useCursorFetch enabled
            conn = DriverManager.getConnection(url, user, password);

            // Create a statement with forward-only and read-only properties
            stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
                    java.sql.ResultSet.CONCUR_READ_ONLY);

            // Set fetch size to define the number of rows retrieved in each fetch
            stmt.setFetchSize(2);  // Use a reasonable fetch size for performance

            // Execute a query
            String sql = "SELECT * FROM cn_crontab_log";
            rs = stmt.executeQuery(sql);

            // Iterate through the result set and print each row
            while (rs.next()) {
                Thread.sleep(1000);
                int id = rs.getInt("id");
                String target = rs.getString("target");

                // Print the results
                System.out.println("ID: " + id + ", target: " + target);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Clean-up the environment
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
