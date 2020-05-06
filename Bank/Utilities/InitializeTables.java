package Bank.Utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeTables {
    DbConnectionHelper dbcon;
    Connection c;

    public InitializeTables() throws SQLException {
        dbcon = new DbConnectionHelper();
        c = dbcon.getConnection();
        initialize();
    }

    public void initialize() throws SQLException {
        String customer = "CREATE TABLE CUSTOMER " + "(ID BIGSERIAL PRIMARY KEY NOT NULL," + "USERNAME TEXT UNIQUE,"
                + "FIRST_NAME TEXT NOT NULL," + "MIDDLE_NAME TEXT," + "LAST_NAME TEXT NOT NULL,"
                + "PHONE_NUM TEXT NOT NULL," + "EMAIL TEXT NOT NULL,"+"SALT BYTEA NOT NULL,"+"PWD BYTEA NOT NULL)";

        String savingAccount = "CREATE TABLE SAVING_ACC " + "(SAVINGID BIGSERIAL PRIMARY KEY NOT NULL," + "CUSTOMERID BIGINT REFERENCES CUSTOMER(ID) NOT NULL,"
                + "BALANCE MONEY NOT NULL," +"CURRENCY VARCHAR(5) NOT NULL,"
                + "INTEREST_RATE NUMERIC(8,4) NOT NULL," + "CLOSEFEE MONEY NOT NULL,"
                + "OPENFEE MONEY NOT NULL)";

        String checkingAccount = "CREATE TABLE CHECKING_ACC " + "(CHECKINGID BIGSERIAL PRIMARY KEY NOT NULL,"
                + "CUSTOMERID BIGINT REFERENCES CUSTOMER(ID) NOT NULL," + "BALANCE MONEY NOT NULL," +"CURRENCY VARCHAR(5) NOT NULL,"
                + "CLOSEFEE MONEY NOT NULL," + "OPENFEE MONEY NOT NULL)";

        String securityAccount= "CREATE TABLE SEC_ACC " + "(SECID BIGSERIAL PRIMARY KEY NOT NULL," + "CUSTOMERID BIGINT REFERENCES CUSTOMER(ID) NOT NULL,"
                + "BALANCE MONEY NOT NULL," + "CURRENCY VARCHAR(5) NOT NULL,"
                +"CLOSEFEE MONEY NOT NULL," + "OPENFEE MONEY NOT NULL)";

        String transaction = "CREATE TABLE TRANSACTION " + "(TRANSID BIGSERIAL PRIMARY KEY NOT NULL," + "FROMNAME TEXT NOT NULL,"
                + "FROMID BIGINT NOT NULL," + "TONAME TEXT NOT NULL," + "TOID BIGINT NOT NULL," + "AMT MONEY NOT NULL,"
                + "CURRENCY VARCHAR(5) NOT NULL," + "TIME TIMESTAMP NOT NULL)";

        String loan = "CREATE TABLE LOAN " + "(LOANID BIGSERIAL PRIMARY KEY NOT NULL," + "CUSTOMERNAME TEXT NOT NULL,"
                + "CUSTOMERID BIGINT NOT NULL," + "AMT MONEY NOT NULL," + "COLLATERAL TEXT NOT NULL,"
                + "COLLATERAL_VALUE MONEY NOT NULL," + "INTEREST_RATE NUMERIC(8,4) NOT NULL," + "TIME TIMESTAMP NOT NULL)";

        String stockMarket = "CREATE TABLE STOCKMKT " + "(STOCKID BIGSERIAL PRIMARY KEY NOT NULL," + "STOCKNAME TEXT NOT NULL,"
                + "QUANTITY BIGINT NOT NULL," + "PRICE MONEY NOT NULL)";

        String customerStock = "CREATE TABLE MYSTOCK " + "(STOCKID BIGSERIAL PRIMARY KEY NOT NULL," + "STOCKNAME TEXT NOT NULL,"
                + "CUSTOMERNAME TEXT NOT NULL," + "CUSTOMERSECID BIGINT NOT NULL," + "PURCHASE_PRICE MONEY NOT NULL,"
                + "PURCHASE_QUANTITY BIGINT NOT NULL," + "CURRENT_PRICE MONEY NOT NULL)";

        String stockTransaction= "CREATE TABLE STOCKTRANS " + "(STRANSID BIGSERIAL PRIMARY KEY," + "STOCKNAME TEXT NOT NULL,"
                + "CUSTOMERNAME TEXT NOT NULL," + "CUSTOMERSECID BIGINT NOT NULL," + "PRICE MONEY NOT NULL,"
                + "QUANTITY BIGINT NOT NULL," + "TIME TIMESTAMP NOT NULL)";

        String bankManager = "CREATE TABLE MANAGER " + "(ID BIGSERIAL PRIMARY KEY NOT NULL," + "USERNAME TEXT UNIQUE,"
                +"SALT BYTEA NOT NULL," + "PASSWORD BYTEA NOT NULL)";
        try(Statement stmt = c.createStatement()){
            c.setAutoCommit(false);
            stmt.executeUpdate(customer);
            stmt.executeUpdate(savingAccount);
            stmt.executeUpdate(checkingAccount);
            stmt.executeUpdate(securityAccount);
            stmt.executeUpdate(transaction);
            stmt.executeUpdate(loan);
            stmt.executeUpdate(stockMarket);
            stmt.executeUpdate(customerStock);
            stmt.executeUpdate(stockTransaction);
            stmt.executeUpdate(bankManager);
            c.commit();
        } catch (SQLException e){
            c.rollback();
            throw e;
        }
    }
}


