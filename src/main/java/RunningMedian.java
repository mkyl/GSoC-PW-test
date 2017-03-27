import java.io.InputStream;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;


/**
 * RunningMedian will provide the Median of a set of numbers provided via an
 * input stream. The data **must** be provided in ascending order. Running Median
 * will provide the Median as the elements are added one by one.
 *
 * @author Mohammad Kayali (mkyl)
 */
public class RunningMedian {
    Queue<Integer> integerList = new LinkedList<Integer>();

    /**
     * Processes a stream of integers representing a growing set whose Median must
     * be found. First, provide a single integer representing the number of elements
     * in the set, total. Then, provide the elements one by one in ascending order.
     * Process() will then output the running median to Standard Out as each element
     * is added in.
     *
     * Throws IllegalArgumentException if element counter is less than 1.
     *
     * @param inputScanner A Scanner that streams in the integer list
     */
    public void process(Scanner inputScanner) {
        int elementCount = inputScanner.nextInt();

        if(elementCount < 1) {
            throw new IllegalArgumentException("Number of elements must be 1 or more");
        }

        double firstElement = 0;
        for(int i = 1; i <= elementCount; i++) {
            // need to grab elementCount times as that's what client expects
            int newNum = inputScanner.nextInt();

            // but we only actually need the left half of the data
            // because anything in the right half can't be the median, ever
            if(i <= elementCount / 2 + 1) {
                integerList.add(newNum);
            }

            if(i % 2 == 1) {
                firstElement = integerList.remove();
                System.out.printf("%.2f%n", firstElement);
            } else {
                double average = (firstElement + integerList.element()) / 2.0;
                System.out.printf("%.2f%n", average);
            }
        }
    }
}
