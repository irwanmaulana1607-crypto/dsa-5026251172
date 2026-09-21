package src.lw01.unguided;

public class ProjectorRental extends Rental {

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    
    public int calculateCharge() {

        int days = getDays();

        if (days <= 3) {
            return (days * 60000) + 20000;
        }

        return (3 * 60000) + ((days - 3) * 45000) + 20000;
    }

    
    public String label() {
        return "Projector";
    }
}