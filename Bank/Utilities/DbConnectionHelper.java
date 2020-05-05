package Bank.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionHelper {
    // This class creates a connection to the database called FancyBank in your localhost server
    public static Connection con = null;

    // Constructor
    public  DbConnectionHelper(){
        if (con==null){
            ConfigureConnection();
        }
    }

    public static Connection getConnection() {
        return con;
    }

    // Configuration of Connection change accordingly.
    private  void ConfigureConnection(){
        try{
            Class.forName("org.postgresql.Driver");;
            // database info
            String dbType = "jdbc:postgresql:";
            String dbUrl = "//localhost:";
            String dbPort = "5432/";
            String dbName = "fancybank";
            String dbUser = "postgres";
            String dbPass = null;
            // Establishing connection
            con = DriverManager.getConnection(dbType+dbUrl+dbPort+dbName,dbUser, dbPass);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
