package Utilities;

import Utilities.CreateDataBase;
import Utilities.InitializeTables;

import java.sql.SQLException;

public class DBSetUp {

    public static void main(String[] args) throws SQLException {
        new CreateDataBase();
        new InitializeTables();
    }
}
