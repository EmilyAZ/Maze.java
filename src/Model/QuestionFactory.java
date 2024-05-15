package Model;

import java.util.List;

public class QuestionFactory {
    public static Questions createMultipleChoiceQuestion(String theQuestionText, List<String> theChoices, int theCorrectAnswerIndex) {
        return new MultipleChoiceQuestion(theQuestionText, theChoices, theCorrectAnswerIndex);
    }

    public static Questions createShortAnswerQuestion(String theQuestionText, String theCorrectAnswer) {
        return new ShortAnswerQuestion(theQuestionText, theCorrectAnswer);
    }

    public static Questions createTrueFalseQuestion(String theQuestionText, boolean theCorrectAnswer) {
        return new TrueFalseQuestion(theQuestionText, theCorrectAnswer);
    }
}
