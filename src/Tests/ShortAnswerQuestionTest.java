package Tests;

import Model.ShortAnswerQuestion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ShortAnswerQuestionTest {

    @Test
    public void testCorrectAnswer() {
        ShortAnswerQuestion question = new ShortAnswerQuestion(
                "How do you spell 'crazy'",
                "crazy"
        );
        assertTrue(question.checkAnswer("crazy"));
    }

    @Test
    public void testCorrectAnswerIgnoreCase() {
        ShortAnswerQuestion question = new ShortAnswerQuestion(
                "How do you spell 'crazy'",
                "crazy"
        );
        assertTrue(question.checkAnswer("CrAZy"));
    }

    @Test
    public void testIncorrectAnswer() {
        ShortAnswerQuestion question = new ShortAnswerQuestion(
                "How do you spell 'crazy'",
                "crazy"
        );
        assertFalse(question.checkAnswer("krazy"));
    }

    @Test
    public void testAnswerWithWhitespace() {
        ShortAnswerQuestion question = new ShortAnswerQuestion(
                "How do you spell 'crazy'",
                "crazy"
        );
        assertTrue(question.checkAnswer("  crazy   "));
    }
}
