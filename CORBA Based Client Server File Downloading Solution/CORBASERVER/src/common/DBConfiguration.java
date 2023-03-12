package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConfiguration {

    public String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public String DB_URL;

    public String USER;

    public String PASS;

    public static Connection conn = null;

    public static Statement stmt = null;

    public String sql = null;

    public DBConfiguration() {
    }

    public DBConfiguration(int i, boolean createTables) {

        switch (i) {
            case 0: {
                try {
                    this.PASS = "";
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:file:./REPO/DB", "owner", "1213");
                    stmt = conn.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE, (String) null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
                break;
            }

            case 1:
                try {
                    this.PASS = "";
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/REPO/DB", "owner", "1213");
                    stmt = conn.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE, (String) null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
                break;
        }
        //To use remote database
     
        
        if (createTables) {
            DBShareFiles dbsf = new DBShareFiles();
            dbsf.createTable();
        }

    }

    public Connection getConnection() {
        return conn;
    }
}
