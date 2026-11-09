
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void sortCards(int[] arr, int passes) {
        int n = arr.length;

        for (int i = 0; i < passes; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("cards.txt"));

        int n = input.nextInt();

        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = input.nextInt();
        }

        int m = input.nextInt();

        int[] requests = new int[m];

        for (int i = 0; i < m; i++) {
            requests[i] = input.nextInt();
        }

        input.close();

        sortCards(cards, 2);

        for (int i = 0; i < n; i++) {
            System.out.print(cards[i]);

            if (i < n - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i = 0; i < m; i++) {
            int index = findCard(cards, requests[i]);

            if (index != -1) {
                System.out.println(requests[i] + ": FOUND at index " + index);
            } else {
                System.out.println(requests[i] + ": NOT FOUND");
            }
        }
    }
}
