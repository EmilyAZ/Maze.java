package Tests;

import Model.TrueFalseQuestion;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrueFalseQuestionTest {

    @Test
    public void testCorrectTrueAnswer() {
        TrueFalseQuestion question = new TrueFalseQuestion("The sky is blue.", true);
        assertTrue(question.checkAnswer("true"));
    }

    @Test
    public void testCorrectFalseAnswer() {
        TrueFalseQuestion question = new TrueFalseQuestion("The sky is green.", false);
        assertTrue(question.checkAnswer("false"));
    }

    @Test
    public void testIncorrectAnswer() {
        TrueFalseQuestion question = new TrueFalseQuestion("The sky is green.", false);
        assertFalse(question.checkAnswer("true"));
    }

    @Test
    public void testAnswerCaseInsensitive() {
        TrueFalseQuestion question = new TrueFalseQuestion("The sky is blue.", true);
        assertTrue(question.checkAnswer("True"));
        assertTrue(question.checkAnswer("TRUE"));
    }
}
