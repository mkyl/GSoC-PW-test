import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RunningMedianTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test public void providedTest() {
        String input = String.format("10%n1%n2%n3%n4%n5%n6%n7%n8%n9%n10%n");
        String expectedOutput = String.format("1.00%n1.50%n2.00%n2.50%n3.00%n3.50"
            + "%n4.00%n4.50%n5.00%n5.50%n");
        compareOutput(input, expectedOutput);
    }

    private void compareOutput(String input, String expectedOutput) {
        RunningMedian classUnderTest = new RunningMedian();
        classUnderTest.process(input);
        assertEquals("Sample output and actual output must match.", 
            expectedOutput, outContent.toString());
    }
}
