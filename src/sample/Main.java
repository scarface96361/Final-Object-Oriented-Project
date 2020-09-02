/**Name: Joseph Rosenbaum
 * Date 7/27/2020
 *
 * Description: this is a javaFX program that reads 10 levels of querstions and answers from a .txt file provided by my professor that is stored in c:/DB/ (this is because i couldnt get writing to the database to work
 * if it was just stored in the C drive due to windows file restrictions. from there it asks for the users name and builds a score object ot keep track of the time remaining and the users score. once the user has entered all questions it will
 * print the users information score and time they used for the quiz and then enter that info into the database while locking the answers (this is to prevent artificially inflating the score)
 * if the user selects generate report the program will read all the contents of the database into  a series of score objects and then print those to the screen. 
 *
 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import processors.DatabaseCreation;
import processors.FileInput;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 726, 525));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        //DatabaseCreation.createNewDatabase();



        //FileInput test = new FileInput();

        launch(args);

    }
}
