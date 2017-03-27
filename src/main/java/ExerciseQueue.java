import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.Scanner;
import java.util.NoSuchElementException;


/**
 * ExerciseQueue is a custom queue implementation, designed for a GSOC
 * coding exercise. ExerciseQueue is initialized with no arguments,
 * and can only be interacted with using the process() function, which.
 * accepts commands in the format specified in the PW coding exercise.
 *
 * @author Mohammad Kayali (mkyl)
 */
public class ExerciseQueue {
    Node head = null;
    Node tail = null;

    /**
     * Add a single element to the end of the queue
     */
    private void add(int input) {
        Node newEntry = new Node(input);

        if (head == null) {
            // empty queue now has 1 element
            head = newEntry;
            tail = head;
        } else {
            // queue more than 2 elements now
            tail.next = newEntry;
            tail = tail.next;
        }
    }

    /**
     * Remove a single element from the start of the queue, discarding it
     */
    private int remove() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("Remove called on empty queue");
        }

        int result = head.value;
        head = head.next;

        // in case queue is empty after element removal
        if (head == null) {
            tail = null;
        }

        return result;
    }

    /**
     * Returns the first element in the queue without modifying anything
     */
    private int peek() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException("Peeked an empty queue");
        else 
            return head.value;
    }

    /**
     * Processes a preformatted string and outputs the result into Standard out.
     * Format is as follows: "1 x" queues the integer x into end of the queue; "2" 
     * queues the element at the front of the queue; "3" prints the element at the
     * front of the queue.
     *
     * Throws NoSuchElementException if peek or remove is called on empty queue.
     *
     * @param input String of formatted commands
     * @param output The output stream to write results to.
     */
    public void process(String input) {
        Scanner inputScanner = new Scanner(input);
        int commandCount = inputScanner.nextInt();

        for(int i = 1; i <= commandCount; i++) {
            int token = inputScanner.nextInt();
            switch(token) {
                case 1: this.add(inputScanner.nextInt());
                        break;
                case 2: this.remove();
                        break;
                case 3: System.out.println(Integer.toString(this.peek()));
                        break;
            }
        }
    }

    /**
     * Internal class, representing a single node in a linked list
     */
    class Node {
        int value;
        Node next;

        public Node(int value) {
            this(value, null);
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
