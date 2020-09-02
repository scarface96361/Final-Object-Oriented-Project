package DataStorage;

public class Score {
    private String name;
    private int score = 0;
    private String time = "0";
    private int timeValue =0;

    public Score(String name1){
        name = name1;
    }

    public Score(String name1, int Score, String time1){
        name = name1;
        score = Score;
        time = time1;
    }

    /**Public Method to increase the score stored within
     *
     */
    public void increaseScore(){
        score++;
    }

    public void setTime(int Time){
        timeValue += Time;
        time = "0:" +timeValue;
    }


    /**Getter to retrieve the name stored within score
     *
     * @return
     */
    public String getName(){
        return name;
    }

    /**Getter to retrieve the time string
     *
     * @return
     */
    public String getTime(){
        return time;
    }

    /**Getter to retrieve the score int
     *
     * @return
     */
    public int getScore(){
        return score;
    }

    public String toString(){
        return "\nName: " + name +
                "\nScore: " + score +
                "\nTime: " +time;
    }

}
