package Model;
import java.util.List;
public class MultipleChoiceQuestion extends Questions{
    private final List<String> myChoices;
    private final int myCorrectAnswerIndex;

    public MultipleChoiceQuestion(String theQuestionText, List<String> theChoices, int theCorrectAnswerIndex){
        super(theQuestionText);
        myChoices = theChoices;
        myCorrectAnswerIndex = theCorrectAnswerIndex;
    }

    public List<String> getChoices() {
        return myChoices;
    }

    @Override
    public boolean checkAnswer(String theAnswer){
        try {
            int index = Integer.parseInt(theAnswer);
            return index == myCorrectAnswerIndex;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
