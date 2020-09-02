package processors;

import DataStorage.Score;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseProcessor {
    //this is the path variables for the database
    private static String  DBPath = "jdbc:sqlite:C:/DB/";
    private static String DBName = "final.db";

    ArrayList<Score> tempList = null;




    /**insertScore takes a score object as an argument and inserts the values held within into the
     * database of scores to be stored long term. it uses the database javasql drivers
     *
     * @param currentScore
     */
    public void insertScore(Score currentScore){
        String url = DBPath + DBName;
        Connection conn = null;
        Statement stmt = null;


        try{
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            System.out.println("Opened database for score injection!");

            //handling the statement object to interact with the database
            stmt = conn.createStatement();

            //creating the specific values and string to be executed by sql
            String sql ="INSERT INTO SCORES (Name, Score, Time)" +
                        "VALUES ('" + currentScore.getName() +"'," + currentScore.getScore() +",'" + currentScore.getTime() +"')";

            stmt.executeUpdate(sql);

            stmt.close();
            conn.commit();
            conn.close();


        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }



    public ArrayList<Score> GenerateWinners(){
        String url = DBPath + DBName;

        tempList = new ArrayList<Score>();

        Connection conn = null;
        Statement stmt = null;

        try{
            conn = DriverManager.getConnection(url);
            //this is just a basic check that was put into place for testing
            System.out.println("Connection to database established");

            stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery("SELECT * FROM SCORES ORDER BY Score DESC;");

            //checking if theres another score in the database
            while(set.next()){
                //if there is another record in the database then it will copy into temp variables before building and adding an object
                String tempName = set.getString("Name");
                int tempScore = set.getInt("Score");
                String tempTime = set.getString("Time");

                //building an arraylist of scores to return
                tempList.add(new Score(tempName, tempScore, tempTime));
            }

            stmt.close();
            conn.close();

            return tempList;


        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //in case the method breaks it will return null
        return null;

    }

}
