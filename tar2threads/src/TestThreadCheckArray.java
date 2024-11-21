import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author:adan 
 * This class demonstrates processing data using two threads and verifying elements in a list.
 */
public class TestThreadCheckArray {

    /**
     * The main entry point of the program.
     * Performs the following steps:
     * 1. Accepts input from the user: size of the list, list values, and a target number.
     * 2. Creates a SharedData object to store the data.
     * 3. Creates two threads that perform checks on the data.
     * 4. Prints the results in a formatted manner.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Thread thread1, thread2;

            // Accept list size
            System.out.println("Enter array size");
            int num = input.nextInt();

            // Create a dynamic list and accept its values from the user
            ArrayList<Integer> arraylist = new ArrayList<>();
            System.out.println("Enter numbers for the ArrayList");
            for (int index = 0; index < num; index++) 
                arraylist.add(input.nextInt());

            // Accept a target number for processing
            System.out.println("Enter number");
            num = input.nextInt();

            // Create a SharedData object to share data between threads
            SharedData sd = new SharedData(arraylist, num);

            // Create two threads to check the data
            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
            thread1.start();
            thread2.start();

            // Wait for both threads to finish
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // If the flag is not set, display failure message and exit
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }

            // Display the results
            System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().size());
            System.out.print("I:    ");
            for (int index = 0; index < sd.getArray().size(); index++)
                System.out.print(index + "    ");
            System.out.println();

            System.out.print("A:    ");
            for (int index : sd.getArray()) {
                System.out.print(index);
                int counter = 5;
                while (true) {
                    index = index / 10;
                    counter--;
                    if (index == 0)
                        break;
                }
                for (int i = 0; i < counter; i++)
                    System.out.print(" ");
            }

            System.out.println();
            System.out.print("C:    ");
            for (boolean index : sd.getWinArray()) {
                if (index)
                    System.out.print("1    ");
                else
                    System.out.print("0    ");    
            }
        }
    }
}
