package processors;

import java.sql.*;

public class DatabaseCreation {
    private static String  DBPath = "jdbc:sqlite:C:/DB/";
    private static String DBName = "final.db";


    private static String url = DBPath + DBName;

    public static void createNewDatabase(){

        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url);

            if(conn!= null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The Driver name is " + meta.getDriverName());
                System.out.println("A new Database has been created");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        createTables();
    }

    public static void createTables(){



        //this object handles the connection to the database file
        Connection conn = null;

        //this object handles the statements in SQL
        Statement stmt = null;

        try{

            //this line fills the connection object with a connection to the database handled by driveManager.getConnection
            conn = DriverManager.getConnection(url);
            System.out.println("successfully called database!");

            // this fills the statement object with a specific method to create statements from the connection
            stmt = conn.createStatement();

            //creating the sql statements to create table
            String sql = "CREATE TABLE SCORES" +
                    "(ID  INTEGER PRIMARY KEY ,"+
                    "Name             TEXT  NOT NULL," +
                    "Score            INT   NOT NULL," +
                    "Time             TEXT)";

            //executing the update to the database
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

        }
        catch(Exception e) {
            //this line prints the error class, name and then message to better track the error
            System.err.println( e.getClass().getName() + e.getMessage());
            System.exit(0);
        }
    }

}
