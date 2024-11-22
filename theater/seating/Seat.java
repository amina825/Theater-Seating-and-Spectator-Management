package theater.seating;
import javax.annotation.processing.SupportedAnnotationTypes;

public class Seat {

    private boolean hasGift;
    private final String id  ;
    private final SeatType seatType ;
    private boolean isOccupied;

    public Seat(String id, boolean hasGift, SeatType seatType) {
        this.id = id;
        this.hasGift = hasGift;
        this.seatType=seatType;
    }

    public String getId() {
        return id;
    }

    public boolean getHasGift() {
        return hasGift;
    }

    public void setHasGift(boolean gift) {
        hasGift = gift;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean a) {
        isOccupied = a;
    }

}
