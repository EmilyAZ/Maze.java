package Model;

import Database.TriviaDatabase;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class QuestionFactory {
    private static TriviaDatabase myDatabase;
    private static List<Questions> myQuestions;
    private static Random random;
    public static void initialize(){
        try {
            myDatabase = new TriviaDatabase();
            myDatabase.InitializeDatabase();
            myDatabase.createQuestions();
            myDatabase.loadAllQuestions();
            myQuestions = myDatabase.getQuestionList();
            random = new Random();
        } catch (SQLException theException){
            theException.printStackTrace();
            System.exit(0);
        }
    }
    public static Questions getRandomQuestion(){
        if(myQuestions.isEmpty()){
            return null;
        }
        return myQuestions.get(random.nextInt(myQuestions.size()));
    }
}
