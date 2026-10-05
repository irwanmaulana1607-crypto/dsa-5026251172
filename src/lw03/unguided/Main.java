package src.lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        LinkedHashMap<String, Integer> enrollment = new LinkedHashMap<String, Integer>();

        ArrayList<String> checkCourses = new ArrayList<String>();
        ArrayList<String> checkResults = new ArrayList<String>();

        int rejectedOperations = 0;

        File file = new File("src/lw03/unguided/enrollment.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] data = line.split(" ");

            String operation = data[0];
            String course = data[1];

            if (operation.equals("REGISTER")) {

                int count = Integer.parseInt(data[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else {

                    if (enrollment.containsKey(course)) {

                        int current = enrollment.get(course);
                        enrollment.put(course, current + count);

                    } else {

                        enrollment.put(course, count);
                    }
                }
            }

            if (operation.equals("WITHDRAW")) {

                int count = Integer.parseInt(data[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else {

                    if (enrollment.containsKey(course)) {

                        int current = enrollment.get(course);

                        if (current >= count) {

                            enrollment.put(course, current - count);

                        } else {

                            rejectedOperations++;
                        }

                    } else {

                        rejectedOperations++;
                    }
                }
            }

            if (operation.equals("CHECK")) {

                checkCourses.add(course);

                if (enrollment.containsKey(course)) {

                    int current = enrollment.get(course);
                    checkResults.add(course + ": " + current + " students");

                } else {

                    checkResults.add(course + ": Not found");
                }
            }
        }

        scanner.close();

        System.out.println("===== Enrollment Checks =====");

        for (int i = 0; i < checkResults.size(); i++) {
            System.out.println(checkResults.get(i));
        }

        System.out.println();

        System.out.println("===== Final Enrollment =====");

        for (String course : enrollment.keySet()) {
            System.out.println(course + ": " + enrollment.get(course) + " students");
        }

        System.out.println();

        System.out.println("Rejected operations: " + rejectedOperations);
    }
}