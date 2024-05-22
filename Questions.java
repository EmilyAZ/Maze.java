package Model;

public abstract class Questions {
    private final String myQuestionText;

    public Questions(String theQuestionText) {
        myQuestionText = theQuestionText;
    }

    public String getQuestionText() {
        return myQuestionText;
    }

    public abstract boolean checkAnswer(String theAnswer);
}


