package Bank.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBase {
    // Creates fancybank database in local server
    public CreateDataBase() throws SQLException {
        Connection Conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres",null);
        try {
            Statement Stmt = Conn.createStatement();
            try {
                try {
                    Stmt.execute ("CREATE DATABASE fancybank");
                    Conn.commit ();
                } catch (SQLException exception) {
                }
            } finally {
                Stmt.close ();
            }
        } catch (SQLException exception) {
            System.err.println ("SQLException : " + exception.toString ());
        } finally {
            Conn.close ();
        }
    }
}
