package Model;

public final class ShortAnswerQuestion extends Questions{
    private final String myCorrectAnswer;

    public ShortAnswerQuestion(String theQuestionText, String theCorrectAnswer){
        super(theQuestionText);
        myCorrectAnswer = theCorrectAnswer;
    }

    @Override
    public boolean checkAnswer(String theAnswer){
        return myCorrectAnswer.equalsIgnoreCase(theAnswer.trim());
    }

    @Override
    public String getType() {
        return "Short Answer";
    }
}
