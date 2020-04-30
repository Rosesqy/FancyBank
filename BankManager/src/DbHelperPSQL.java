import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbHelperPSQL {
    public static Connection con = null;

    public  DbHelperPSQL(){
        if (con==null){
            ConfigureConnection();
        }
    }

    public static Connection getCon() {
        return con;
    }

    private  void ConfigureConnection(){
        try{
            Class.forName("org.postgresql.Driver");;
            // database info
            String dbType = "jdbc:postgresql:";
            String dbUrl = "//localhost:";
            String dbPort = "5432/";
            String dbName = "bank";
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
