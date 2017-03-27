import java.io.InputStream;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class RunningMedian {
    Queue<Integer> integerList = new LinkedList<Integer>();

    public void process(String input) {
        Scanner inputScanner = new Scanner(input);
        int elementCount = inputScanner.nextInt();

        for(int i = 1; i <= elementCount / 2; i++) {
            integerList.add(inputScanner.nextInt());
            double firstElement = integerList.remove();
            System.out.printf("%.2f%n", firstElement);
            
            integerList.add(inputScanner.nextInt());
            int secondElement = integerList.element();
            double average = (firstElement + secondElement) / 2.0;
            System.out.printf("%.2f%n", average);
        }
    }
}
