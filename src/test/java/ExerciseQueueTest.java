import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/*
 * Tests for the ExerciseQueue class
 */
public class ExerciseQueueTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    /**
     * Runs the test input provided by the PW team, and checks against expected output
     */
    @Test public void providedTest() {
        // using String.format to avoid newline (\r\n vs \n) compatibility problems
        String input = String.format("10%n1 42%n2%n1 14%n3%n1 28%n3%n1 60%n1 78%n2%n2%n");
        String expectedOutput = String.format("14%n14%n");
        compareQueueOutput(input, expectedOutput);
    }

    /**
     * Adds one element and peeks it
     */
    @Test public void oneElement() {
        String input = String.format("2%n1 21%n3%n");
        String expectedOutput = String.format("21%n");
        compareQueueOutput(input, expectedOutput);
    }

    /**
     * Tries to peek element that doesn't exist, checks correct expection is raised
     */
    @Test (expected = NoSuchElementException.class)
    public void peekEmptyQueue() {
        ExerciseQueue classUnderTest = new ExerciseQueue();
        String input = String.format("3%n1 21%n2%n3%n");
        classUnderTest.process(input);
    }

    /**
     * Tries to remove element in an empty queue, checks correct expection is raised
     */
    @Test (expected = NoSuchElementException.class)
    public void removeTooManyElements() {
        ExerciseQueue classUnderTest = new ExerciseQueue();
        String input = String.format("3%n1 21%n2%n2%n");
        classUnderTest.process(input);
    }

    /**
     * Compares output of ExerciseQueue to some input to an expected value
     */
    private void compareQueueOutput(String input, String expectedOutput) {
        ExerciseQueue classUnderTest = new ExerciseQueue();
        classUnderTest.process(input);
        assertEquals("Sample output and actual output must match.", 
            expectedOutput, outContent.toString());
    }
}
