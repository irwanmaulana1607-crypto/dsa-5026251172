package src.lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    public static void problem1() {
        System.out.println("===== Problem 1 =====");

        ArrayList<String> playlist = new ArrayList<String>();

        try {
            File file = new File("src/lw03/prelab/playlist.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ", 3);

                if (data[0].equals("ADD")) {
                    playlist.add(data[1]);
                } else if (data[0].equals("INSERT")) {
                    int index = Integer.parseInt(data[1]);
                    String song = data[2];

                    playlist.add(index, song);
                } else if (data[0].equals("REMOVE")) {
                    String song = data[1];

                    playlist.remove(song);
                }
            }

            scanner.close();

            System.out.println("Total songs: " + playlist.size());

            for (int i = 0; i < playlist.size(); i++) {
                System.out.println((i + 1) + ": " + playlist.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("playlist.txt not found.");
        }
    }

    public static void problem2() {
        System.out.println("===== Problem 2 =====");

        Set<String> participants = new LinkedHashSet<String>();
        int duplicates = 0;

        try {
            File file = new File("src/lw03/prelab/participants.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();

                if (participants.contains(name)) {
                    duplicates++;
                } else {
                    participants.add(name);
                }
            }

            scanner.close();

            System.out.println("Unique participants: " + participants.size());

            int number = 1;

            for (String name : participants) {
                System.out.println(number + ". " + name);
                number++;
            }

            System.out.println("Duplicate registrations: " + duplicates);

        } catch (FileNotFoundException e) {
            System.out.println("participants.txt not found.");
        }
    }

    public static void problem3() {
        System.out.println("===== Problem 3 =====");

        Map<String, Integer> inventory =
                new LinkedHashMap<String, Integer>();

        int failedSales = 0;

        try {
            File file = new File("src/lw03/prelab/inventory.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                String type = data[0];
                String product = data[1];
                int quantity = Integer.parseInt(data[2]);

                if (type.equals("ADD")) {

                    if (inventory.containsKey(product)) {
                        int stock = inventory.get(product);
                        inventory.put(product, stock + quantity);
                    } else {
                        inventory.put(product, quantity);
                    }

                } else if (type.equals("SELL")) {

                    if (inventory.containsKey(product)) {
                        int stock = inventory.get(product);

                        if (stock >= quantity) {
                            inventory.put(product, stock - quantity);
                        } else {
                            failedSales++;
                        }

                    } else {
                        failedSales++;
                    }
                }
            }

            scanner.close();

            for (String product : inventory.keySet()) {
                System.out.println(product + ": " + inventory.get(product));
            }

            System.out.println("Failed sales: " + failedSales);

        } catch (FileNotFoundException e) {
            System.out.println("inventory.txt not found.");
        }
    }
}
