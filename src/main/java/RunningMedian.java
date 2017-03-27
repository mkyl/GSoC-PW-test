import java.io.InputStream;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class RunningMedian {
    Queue<Integer> integerList = new LinkedList<Integer>();

    public void process(String input) {
        Scanner inputScanner = new Scanner(input);
        int elementCount = inputScanner.nextInt();

        if(elementCount < 1) {
            throw new IllegalArgumentException("Number of elements must be 1 or more");
        }

        for(int i = 1; i <= elementCount / 2 + 1; i++) {
            integerList.add(inputScanner.nextInt());
        }

        double firstElement = integerList.remove();
        System.out.printf("%.2f%n", firstElement);

        while(!integerList.isEmpty()) {
            int secondElement = integerList.element();
            double average = (firstElement + secondElement) / 2.0;
            System.out.printf("%.2f%n", average);

            firstElement = integerList.remove();
            if (!integerList.isEmpty() || elementCount % 2 == 1)
                System.out.printf("%.2f%n", firstElement);
        }
    }
}
