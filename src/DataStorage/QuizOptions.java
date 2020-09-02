package DataStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class QuizOptions {
    ArrayList<Object> quizObjects;

    ArrayList<String> quizLabels= new ArrayList<String>();


    public QuizOptions(ArrayList<Object> e){

        try {
            quizObjects = new ArrayList<Object>(e);

            for (Object z : e) {
                quizLabels.add(String.valueOf(z));
            }
        }catch (Exception p){
            System.out.println(p);
        }


    }



    public ArrayList<Object> getQuizObjects(){
        return quizObjects;
    }

    public ArrayList<String> getQuizLabels(){
        return quizLabels;
    }

    //TODO figure out a better way to compare or  rather add the values in an object
    //this entire function is still kind of a mess but it works at the moment so im not going to try and make it cleaner till
    //i have the rest of the program working
    public boolean compareItem(Object p){


        String tempString1 = null, tempString2 = null;
        Integer tempInt1 = 0, tempInt2 = 0;
        Double tempDouble1 = 0d, tempDouble2 = 0d;

        //checking the first object in testing
         if(quizObjects.get(0) instanceof Integer){
            tempInt1 = (Integer) quizObjects.get(0);
          }
         else if(quizObjects.get(0) instanceof Double){
             tempDouble1 = (Double) quizObjects.get(0);
         }
         else if(quizObjects.get(0) instanceof String){
             tempString1 = (String) quizObjects.get(0);
         }

       //checking type and filling depending on object 2
        if(quizObjects.get(1) instanceof Integer){
             tempInt2 = (Integer) quizObjects.get(1);
          }
        else if(quizObjects.get(1) instanceof Double){
             tempDouble2 = (Double) quizObjects.get(1);
         }
         else if(quizObjects.get(1) instanceof String){
            tempString2 = (String) quizObjects.get(1);
         }


        //converting back into an object for comparison
        Object tempAnswer1 = tempInt1 +tempInt2;
        Object tempAnswer2 = tempInt1 +tempString2;
        Object tempAnswer3 = tempInt1 +tempDouble2;
        Object tempAnswer4 = tempDouble1 +tempInt2;
        Object tempAnswer5 = tempDouble1 +tempString2;
        Object tempAnswer6 = tempDouble1 + tempDouble2;
        Object tempAnswer7 = tempString1 +tempInt2;
        Object tempAnswer8 = tempString1 + tempString2;
        Object tempAnswer9 = tempString1 + tempDouble2;


        //final if statement to determine if true or false
        //this is kinda a mess but im not really sure how else to fix it aside from possibly an arraylist but that would add 9 more lines of code to the project and make it a bit clunkier
        if (tempAnswer1.equals(p)  || tempAnswer2.equals(p)  || tempAnswer3.equals(p) ||tempAnswer4.equals(p) || tempAnswer5.equals(p) || tempAnswer6.equals(p) ||tempAnswer7.equals(p) || tempAnswer8.equals(p) || tempAnswer9.equals(p)   ){
            return true;
        }

        return false;
    }



}
