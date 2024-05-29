package Tests;

import Model.MultipleChoiceQuestion;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MultipleChoiceQuestionTest {

    @Test
    public void testCorrectAnswer() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                "Answer: B",
                Arrays.asList("A", "B", "C", "D"),
                2
        );
        assertTrue(question.checkAnswer("2"));
    }

    @Test
    public void testIncorrectAnswer() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                "Answer: B ",
                Arrays.asList("A", "B", "C", "D"),
                2
        );
        assertFalse(question.checkAnswer("1"));
    }

    @Test
    public void testInvalidAnswer() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                "Answer: C ",
                Arrays.asList("A", "B", "C", "D"),
                3
        );
        assertFalse(question.checkAnswer("Invalid"));
    }
}

