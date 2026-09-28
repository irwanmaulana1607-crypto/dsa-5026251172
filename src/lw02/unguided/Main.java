package src.lw02.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        int MAX_BORROW = 2;

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();

        Scanner scanner = new Scanner(
            new File("src/lw02/unguided/borrowing.txt")
        );

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(" ");

            String name = data[0];
            String book = data[1];

            requests.add(new String[] {name, book});

            boolean found = false;

            for (int i = 0; i < members.size(); i++) {
                if (members.get(i)[0].equals(name)) {
                    found = true;
                    break;
                }
            }

            if (found == false) {
                members.add(new String[] {name, "0"});
            }
        }

        scanner.close();

        books.add(new String[] {"Kalkulus", "2"});
        books.add(new String[] {"Fisika", "1"});
        books.add(new String[] {"Statistika", "2"});

        Queue<String[]> queue = new LinkedList<>();

        while (requests.isEmpty() == false) {
            queue.add(requests.removeFirst());
        }

        Stack<String[]> failedRequests = new Stack<>();
        LinkedList<String[]> successfulRequests = new LinkedList<>();

        while (queue.isEmpty() == false) {
            String[] request = queue.poll();

            String name = request[0];
            String bookName = request[1];

            int bookIndex = -1;
            int memberIndex = -1;

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookName)) {
                    bookIndex = i;
                    break;
                }
            }

            for (int i = 0; i < members.size(); i++) {
                if (members.get(i)[0].equals(name)) {
                    memberIndex = i;
                    break;
                }
            }

            int stock = Integer.parseInt(books.get(bookIndex)[1]);
            int borrowed = Integer.parseInt(members.get(memberIndex)[1]);

            if (stock > 0 && borrowed < MAX_BORROW) {

                stock = stock - 1;
                borrowed = borrowed + 1;

                books.get(bookIndex)[1] = String.valueOf(stock);
                members.get(memberIndex)[1] = String.valueOf(borrowed);

                successfulRequests.add(request);

            } else {
                failedRequests.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");

        for (int i = 0; i < successfulRequests.size(); i++) {
            String[] request = successfulRequests.get(i);

            System.out.println(
                request[0] + " " + request[1]
            );
        }

        System.out.println();
        System.out.println("=== Remaining Book Stock ===");

        for (int i = 0; i < books.size(); i++) {
            String[] book = books.get(i);

            System.out.println(
                book[0] + " : " + book[1]
            );
        }

        System.out.println();
        System.out.println("=== Failed Requests ===");

        while (failedRequests.isEmpty() == false) {
            String[] request = failedRequests.pop();

            System.out.println(
                request[0] + " " + request[1]
            );
        }
    }
}