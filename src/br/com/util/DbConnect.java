package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to connect SQLite database
 *
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public class DbConnect {

    private static Connection connection;

    private static void connect() {
        try {
            // db parameters
            String dir = System.getProperty("user.dir") + "\\lib\\sqlite\\db\\chinook.db";
            String url = "jdbc:sqlite:" + dir;
            // create a connection to the database
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS user(usu_id INTEGER PRIMARY KEY, usu_nickname TEXT, usu_email TEXT, usu_password TEXT, usu_year_of_birth INTEGER )");
            statement.execute("CREATE TABLE IF NOT EXISTS session(ses_id INTEGER PRIMARY KEY, usu_id INTEGER, last_update TEXT, token TEXT, FOREIGN KEY(usu_id) REFERENCES user(usu_id))");
            statement.execute("CREATE TABLE IF NOT EXISTS contact(usu_id INTEGER, usu_id_contact INTEGER, FOREIGN KEY(usu_id) REFERENCES user(usu_id), FOREIGN KEY(usu_id_contact) REFERENCES user(usu_id_contact))");
            statement.execute("CREATE UNIQUE INDEX IF NOT EXISTS idx_user_email ON user(usu_email)");
            statement.execute("CREATE UNIQUE INDEX IF NOT EXISTS idx_unq_contact ON contact(usu_id, usu_id_contact) WHERE usu_id <> usu_id_contact");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            connect();
            createDatabase();
        }
        return connection;
    }
}
