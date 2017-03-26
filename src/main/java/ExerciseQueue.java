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

    private int peek() throws NoSuchElementException {
        if (head != null)
            return head.value;
        else 
            throw new NoSuchElementException("Peeked an empty queue");
    }

    /**
     * Processes a preformatted string and outputs the result into an output stream.
     * Format is as follows: "1 x" queues the integer x into end of the queue; "2" 
     * queues the element at the front of the queue; "3" prints the element at the
     * front of the queue.
     *
     * @param input String of formatted commands
     * @param output The output stream to write results to.
     */
    public void process(String input, ByteArrayOutputStream output) {
        Scanner inputScanner = new Scanner(input);
        int commandCount = inputScanner.nextInt();

        try {
            for(int i = 1; i <= commandCount; i++) {
                int token = inputScanner.nextInt();
                switch(token) {
                    case 1: this.add(inputScanner.nextInt());
                            break;
                    case 2: this.remove();
                            break;
                    case 3: byte[] bytes = (this.peek() + "\n").getBytes("UTF8");
                            output.write(bytes);
                            break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("This system does not support UTF-8 encoding");
        } catch (IOException f) {
            System.err.println("Error occured while writing to output stream provided");
        }
    }

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
