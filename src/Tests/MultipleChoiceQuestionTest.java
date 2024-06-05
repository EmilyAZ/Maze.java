package Tests;

import Model.MultipleChoiceQuestion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MultipleChoiceQuestionTest {

    @Test
    public void testCorrectAnswer() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                "Answer: B",
                new String[]{"A", "B", "C", "D"},
                2
        );
        assertTrue(question.checkAnswer("2"));
    }

    @Test
    public void testIncorrectAnswer() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                "Answer: B ",
                new String[]{"A", "B", "C", "D"},
                2
        );
        assertFalse(question.checkAnswer("1"));
    }

    @Test
    public void testInvalidAnswer() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                "Answer: C ",
                new String[]{"A", "B", "C", "D"},
                3
        );
        assertFalse(question.checkAnswer("Invalid"));
    }
}

