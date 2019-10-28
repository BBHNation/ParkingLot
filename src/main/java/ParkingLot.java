public class ParkingLot {

    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        return new Ticket();
    }

}
