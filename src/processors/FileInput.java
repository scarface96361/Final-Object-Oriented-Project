package processors;

import DataStorage.QuizOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInput {
    private static String  DBPath = "C:/DB/";
    private static String DBName = "input-1.txt";


    private static String url = DBPath + DBName;


    //this was a test class used early on. i left in in in case i need to test more
    public void testClass() throws FileNotFoundException {
        File file = new File(url);


        Scanner sc = new Scanner(file);

        ArrayList<Object> testlist = new ArrayList<Object>();


        while (sc.hasNext()){

            testlist.add(sc.next());
        }



        sc.close();
    }





    public ArrayList<QuizOptions> getFile(){
        try{
            File file = new File(url);

            int counter = 0;

            Scanner sc = new Scanner(file);
            ArrayList<Object> templist = new ArrayList<Object>();

            ArrayList<QuizOptions> tempOptions = new ArrayList<QuizOptions>();

            QuizOptions temptest = null;



            while(sc.hasNext() ){

                while(sc.hasNext() && counter < 6) {
                    //this code checks if the next object in the file is a float, a double, a int or a string
                    // and retrieves based on which is there
                    if (sc.hasNextInt()){
                        templist.add(sc.nextInt());
                    }
                    else  if (sc.hasNextDouble()){
                        templist.add(sc.nextDouble());
                    }

                    else {
                        templist.add(sc.next());
                    }
                    counter++;

                }
                //build and add a quiz options to the tempoptions arraylist
                tempOptions.add(new QuizOptions(templist));
                counter = 0;
                templist.clear();

            }


            return tempOptions;


        }catch(Exception e){
            System.out.println(e);
            System.out.println("Error thrown in getFile filling arrayList");
            System.exit(404);
        }
        //if something goes wrong it will return null
        return null;

    }


}
