package Model;
public final class MultipleChoiceQuestion extends Questions{
    private final String[] myChoices;
    private final int myCorrectAnswerIndex;

    public MultipleChoiceQuestion(String theQuestionText, String[] theChoices, int theCorrectAnswerIndex){
        super(theQuestionText);
        myChoices = theChoices;
        myCorrectAnswerIndex = theCorrectAnswerIndex;
    }
    @Override
    public String[] getChoices() {
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

    @Override
    public String getType() {
        return "Multiple Choice";
    }

}
