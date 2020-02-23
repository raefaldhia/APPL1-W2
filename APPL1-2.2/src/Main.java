import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the file sources: ");
        String srcPath = input.nextLine();

        File inputFile = new File(srcPath);

        try {
            Scanner inputScanner = new Scanner(inputFile);

            while (inputScanner.hasNext()) {
                System.out.println(inputScanner.next());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
        }
    }
}
