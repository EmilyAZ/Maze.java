package Model;

public final class TrueFalseQuestion extends Questions {
    private final boolean myCorrectAnswer;

    public TrueFalseQuestion(String theQuestionText, boolean theCorrectAnswer){
        super(theQuestionText);
        myCorrectAnswer = theCorrectAnswer;
    }

    @Override
    public boolean checkAnswer(String theAnswer) {
        return Boolean.parseBoolean(theAnswer) == myCorrectAnswer;
    }
}
