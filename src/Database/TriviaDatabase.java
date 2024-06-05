package Database;

import Model.MultipleChoiceQuestion;
import Model.Questions;
import Model.ShortAnswerQuestion;
import Model.TrueFalseQuestion;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriviaDatabase {
    private SQLiteDataSource ds;
    private List<Questions> questionList;
    public TriviaDatabase() throws SQLException {
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.db");
            questionList = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void InitializeDatabase() {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS questions ( " +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "QUESTION TEXT NOT NULL, " +
                    "ANSWER TEXT NOT NULL, " +
                    "TYPE TEXT NOT NULL)";
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement()) {
                int rv = stmt.executeUpdate(createTableQuery);
                System.out.println("executeUpdate() returned " + rv);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
    }
    public void createQuestions() {

        // Insert 25 rows of data

        String[] questions = {
                // True/False Questions
                "Java is a programming language. (True/False)",
                "Water boils at 100 degrees Celsius. (True/False)",
                "The Earth is flat. (True/False)",
                "Mount Everest is the tallest mountain in the world. (True/False)",
                "The Sun is a planet. (True/False)",
                "Albert Einstein invented the telephone. (True/False)",
                "A square has 5 sides. (True/False)",
                "A circle is a shape. (True/False)",
                "There is a wrd spelled rong in this sentence. (True/False)",


                // Short Answer Questions
                "What is the capital of Spain?",
                "What is the chemical symbol for water?",
                "Who wrote Romeo and Juliet?",
                "What is the capital of Australia?",
                "Who painted the Mona Lisa?",
                "What is the capital of Brazil?",

                // Multiple Choice Questions
                "What is the capital of France?",
                "What is the result of 2 + 2?",
                "What is the last name of the Java creator?",
                "What is the largest mammal?",
                "What is the largest planet in our solar system?",
                "What is the chemical symbol for gold?",
                "What is the tallest mammal?",
                "What is the largest ocean on Earth?",
                "What is the chemical symbol for oxygen?",
                "What is the chemical symbol for helium?"
        };

        String[] answers = {
                // True/False Answers
                "True",
                "True",
                "False",
                "True",
                "False",
                "False",
                "False",
                "True",
                "True",


                // Short Answer Answers
                "Madrid",
                "H2O",
                "Shakespeare",
                "Canberra",
                "Leonardo da Vinci",
                "Brasilia",

                // Multiple Choice Answers
                "Marseille;Paris;Lyon;Paris",
                "4;6;3;4",
                "Smith;Gates;Gosling;Gosling",
                "Blue whale;Lion;Elephant;Blue whale",
                "Earth;Jupiter;Mars;Jupiter",
                "Au;Ag;Fe;Au",
                "Giraffe;Elephant;Rhino;Giraffe",
                "Atlantic;Artic;Pacific;Pacific",
                "H;O;C;O",
                "He;H;C;He"
        };

        String[] types = {
                // True/False Types
                "true_false", "true_false", "true_false", "true_false", "true_false", "true_false", "true_false", "true_false", "true_false",

                // Short Answer Types
                "short_answer", "short_answer", "short_answer", "short_answer", "short_answer", "short_answer",

                // Multiple Choice Types
                "multiple_choice", "multiple_choice", "multiple_choice", "multiple_choice", "multiple_choice",
                "multiple_choice", "multiple_choice", "multiple_choice", "multiple_choice", "multiple_choice"
        };

        // Inserting questions into the database
        for (int i = 0; i < 25; i++) {
            String insertQuery = String.format("INSERT INTO questions (QUESTION, ANSWER, TYPE) " +
                    "VALUES ('%s', '%s', '%s')", questions[i], answers[i], types[i]);
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement()) {
                int rv = stmt.executeUpdate(insertQuery);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
    public void loadAllQuestions() {
        questionList.clear();
        String selectQuery = "SELECT * FROM questions";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                String questionText = rs.getString("QUESTION");
                String answer = rs.getString("ANSWER");
                String type = rs.getString("TYPE");

                Questions question = null;
                switch (type) {
                    case "true_false":
                        question = new TrueFalseQuestion(questionText, Boolean.parseBoolean(answer));
                        break;
                    case "short_answer":
                        question = new ShortAnswerQuestion(questionText, answer);
                        break;
                    case "multiple_choice":
                        // Assuming multiple choice answers are stored in a specific format
                        // You need to adapt this to how your multiple-choice questions are stored
                        String[] choices = answer.split(";");
                        String correctAnswer = choices[choices.length-1];
                        String[] choicesList = Arrays.copyOfRange(choices,0,choices.length-1);
                        int correctIndex = Arrays.asList(choicesList).indexOf(correctAnswer);
                        question = new MultipleChoiceQuestion(questionText, choicesList, correctIndex);
                        break;
                }

                if (question != null) {
                    questionList.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Questions> getQuestionList() {
        return questionList;
    }

        // Retrieving questions from the database
//        System.out.println("Selecting all rows from questions table");
//        String selectQuery = "SELECT * FROM questions";
//
//        try (Connection conn = ds.getConnection();
//             Statement stmt = conn.createStatement();) {
//            ResultSet rs = stmt.executeQuery(selectQuery);
//
//            // Process the retrieved questions here or pass them to your trivia maze game
//            while (rs.next()) {
//                String question = rs.getString("QUESTION");
//                String answer = rs.getString("ANSWER");
//                String type = rs.getString("TYPE");
//
//                // Process each question as needed
//                System.out.println("Question: " + question);
//                System.out.println("Answer: " + answer);
//                System.out.println("Type: " + type);
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }
}
