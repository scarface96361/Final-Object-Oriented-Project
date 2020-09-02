package sample;

import DataStorage.QuizOptions;
import DataStorage.Score;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import processors.DatabaseProcessor;
import processors.FileInput;

import java.util.ArrayList;


public class Controller {


    //creating temporary variables for the menu and various items needed
    private ArrayList<QuizOptions> menuItems = null;
    private Score tempScore = null;

    //creating a temp quizOptions object to manage the menu changing and to make sure the program doesnt extend past
    //the 10 questions
    private QuizOptions Question = null;
    private int questionCounter = 0;
    //TODO Figure out a timer and how to implement that.

    private boolean timerSafe = true;


    //importing all the buttons and labels needed to be used by the java program
    @FXML
    Button Answer1Button, Answer2Button, Answer3Button, Answer4Button;
    @FXML
    Label Answer1, Answer2, Answer3, Answer4, Object1, Object2;




    //these variables are only used for insert name so im packaging them near insertName
    @FXML
    Button nameButton;

    @FXML
    TextField nameEntry;

    //importing the label for the timer
    @FXML
    Label timerLabel;

    private Timeline timer;
    private Integer timeSeconds = 5;


    //this creates a score object with the user name when the user clicks the button
    public void insertName(ActionEvent actionEvent) {



        //checks if a name has been entered and if it has then itll create a score item with that name
        if(nameEntry.getText() != null){
            tempScore = new Score(nameEntry.getText());
            nameButton.setVisible(false);
            nameButton.isDisabled();
        }
        else{
            nameEntry.setPromptText("PLEASE ENTER YOUR NAME THIS TIME");
        }


        Answer1Button.setDisable(false);
        Answer2Button.setDisable(false);
        Answer3Button.setDisable(false);
        Answer4Button.setDisable(false);




        //these two lines call two functions to set temp variable question and its associated labels to the correct item
        incrementQuestion();
        setmenu();

        timer = new Timeline();
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                //every one second the timer will call its timer method stored beneath insert name
                ae -> timerEvent()));
        timer.playFromStart();

    }

    public void timerEvent(){
       //setting the timer label and incrementing the timer
        timeSeconds--;
        timerLabel.setText(timeSeconds.toString());


        if(timeSeconds <= 0){



            //it only increments the counter if the question hasnt been answered
            //it only runs these if this method was not called by a button push
            if(timerSafe) {
                questionCounter++;
                incrementQuestion();
                setmenu();
            }
            timerSafe = true;

            //restarting the timer
            timeSeconds = 5;
            timerLabel.setText(timeSeconds.toString());
            //the timer will only restart itself if

            if(questionCounter>9){
                timer.stop();
            }

        }


    }


    //this is just a simple item to set the temp question to the correct item as things get  changed
    private void incrementQuestion(){
            Boolean safe = false;

            safe = questionCheck();

            if(safe) {
                Question = menuItems.get(questionCounter);
            }




    }

    //this method takes the current question object and resets the labels to the appropriate items
    private void setmenu(){
        ArrayList<Object> quizObjects = Question.getQuizObjects();
        ArrayList<String> quizLabels = Question.getQuizLabels();

        Object1.setText(quizLabels.get(0));
        Object2.setText(quizLabels.get(1));
        Answer1.setText(quizLabels.get(2));
        Answer2.setText(quizLabels.get(3));
        Answer3.setText(quizLabels.get(4));
        Answer4.setText(quizLabels.get(5));

        //this sets the user data to the objects used for comparison in question
        Answer1Button.setUserData(quizObjects.get(2));
        Answer2Button.setUserData(quizObjects.get(3));
        Answer3Button.setUserData(quizObjects.get(4));
        Answer4Button.setUserData(quizObjects.get(5));

    }


    public boolean questionCheck(){
        if(questionCounter > 9){
            //todo write end program code here
            TextOutput.setText("");
            TextOutput.appendText(tempScore.toString());


            //disabling all of the answer buttons
            Answer1Button.setDisable(true);
            Answer2Button.setDisable(true);
            Answer3Button.setDisable(true);
            Answer4Button.setDisable(true);


            DatabaseProcessor finalEntry = new DatabaseProcessor();
            finalEntry.insertScore(tempScore);
            return false;
        }
        return true;

    }


    //this method just takes the answer given and runs comparison to adjust the source
    @FXML
    Text scoreCounter;

    public void pushAnswer(ActionEvent event){
        Button pushedButton = (Button) event.getSource();
        boolean rightAnswer = false;

        rightAnswer = Question.compareItem(pushedButton.getUserData());


        if(rightAnswer == true){
            tempScore.increaseScore();
        }

        questionCounter++;


        incrementQuestion();
        setmenu();


        scoreCounter.setText(tempScore.getScore() +" Points");


        //before timeseconds gets set to zero it gets passed to the score to increment the time of the score
        tempScore.setTime(timeSeconds);

        //setting the timersafe to false and setting timeseconds to 0 to force a restart of the timeline object
        timeSeconds = 0;
        timerSafe = false;


        //testing to see what manually forcing a timer event does
        //timerEvent();


    }



    public void initialize() {


        FileInput readme = new FileInput();
        menuItems = readme.getFile();


    }



    public void insertRecord(){
        DatabaseProcessor db = new DatabaseProcessor();
        db.insertScore(tempScore);
    }


    @FXML
    TextArea TextOutput;
    /**Method to create and append the score list to the textbox when the user hits generate report
     *
     *
     */
    public void generateReport(){
        DatabaseProcessor  DBHandler = new DatabaseProcessor();

        ArrayList<Score> reportScore = DBHandler.GenerateWinners();

        //emptying any leftover text in the textOuput area
        TextOutput.setText("");

        if(reportScore != null){
            for (Score e: reportScore) {
                //appending to toString of each score generated
                TextOutput.appendText(e.toString());

            }

        }

    }
}
