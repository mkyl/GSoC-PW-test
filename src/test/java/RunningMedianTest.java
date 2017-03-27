import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/*
 * Tests for the RunningMedian class
 */
public class RunningMedianTest {
    // setup Junit to capture Standard out for comparasion purposes
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
        String input = String.format("10%n1%n2%n3%n4%n5%n6%n7%n8%n9%n10%n");
        String expectedOutput = String.format("1.00%n1.50%n2.00%n2.50%n3.00%n3.50"
            + "%n4.00%n4.50%n5.00%n5.50%n");
        compareOutput(input, expectedOutput);
    }

    /**
     * Runs the test input similiar to that of the PW team, expect odd number of
     * elements, and checks against expected output
     */
    @Test public void oddNumberOfElements() {
        String input = String.format("9%n1%n2%n3%n4%n5%n6%n7%n8%n9%n");
        String expectedOutput = String.format("1.00%n1.50%n2.00%n2.50%n3.00%n3.50"
            + "%n4.00%n4.50%n5.00%n");
        compareOutput(input, expectedOutput);
    }

    /**
     * Runs the test input where the integers aren't a simple sequence,
     * and checks against expected output
     */
    @Test public void differentNumbers() {
        String input = String.format("10%n1%n3%n6%n7%n8%n11%n15%n16%n19%n21%n");
        String expectedOutput = String.format("1.00%n2.00%n3.00%n4.50%n6.00%n6.50"
            + "%n7.00%n7.50%n8.00%n9.50%n");
        compareOutput(input, expectedOutput);
    }

    /**
     * Attempts to find the median of a single element set
     */
    @Test public void singleElement() {
        String input = String.format("1%n271%n");
        String expectedOutput = String.format("271.00%n");
        compareOutput(input, expectedOutput);
    }

    /**
     * Tries to find median of set of size "-1", checks correct expection is
     * thrown
     */
    @Test (expected = IllegalArgumentException.class)
    public void illegalCount() {
        RunningMedian classUnderTest = new RunningMedian();
        Scanner inputScanner = new Scanner(String.format("-1%n5%n7%n"));
        classUnderTest.process(inputScanner);
    }

    /**
     * Helper function to abstract internal code
     */
    private void compareOutput(String input, String expectedOutput) {
        RunningMedian classUnderTest = new RunningMedian();
        Scanner inputScanner = new Scanner(input);
        classUnderTest.process(inputScanner);
        assertEquals("Sample output and actual output must match.", 
            expectedOutput, outContent.toString());
    }
}
