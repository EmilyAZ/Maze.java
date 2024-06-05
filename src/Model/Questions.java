package Model;

import java.io.Serializable;

public abstract class Questions implements Serializable {
    private final String myQuestionText;

    public Questions(String theQuestionText) {
        myQuestionText = theQuestionText;
    }

    public String getQuestionText() {
        return myQuestionText;
    }

    public abstract boolean checkAnswer(String theAnswer);
    public abstract String getType();
    public String[] getChoices() {
        return null;
    }
}


