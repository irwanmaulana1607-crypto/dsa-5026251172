package src.lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("rentals.txt"));

        int totalRentals = scanner.nextInt();

        Rental[] rentals = new Rental[totalRentals];

        for (int i = 0; i < totalRentals; i++) {

            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days);
            } else if (type.equals("PROJECTOR")) {
                rentals[i] = new ProjectorRental(id, days);
            }

            rentals[i] = new RentalWithUnits(rentals[i], units);
        }

        scanner.close();

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }

    private static class RentalWithUnits extends Rental {

        private Rental rental;
        private int units;

        public RentalWithUnits(Rental rental, int units) {
            super(rental.getId(), rental.getDays());

            if (units <= 0) {
                throw new IllegalArgumentException("Units must be positive");
            }

            this.rental = rental;
            this.units = units;
        }

        
        public int calculateCharge() {
            return units * rental.calculateCharge();
        }

        
        public String label() {
            return rental.label();
        }
    }
}