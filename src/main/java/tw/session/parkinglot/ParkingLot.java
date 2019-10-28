package tw.session.parkinglot;

public class ParkingLot {

    private int capacity;

    private Car car;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (capacity < 1 || this.car != null) {
            throw new ParkingLotFullException();
        }
        this.car = car;
        return new Ticket(car.getCarNum());
    }

    public Car pick(Ticket ticket) {
        if (this.car != null && ticket.getCarNum().equals(this.car.getCarNum())) {
            return this.car;
        }
        throw new ParkingLotNotParkingTheCarException();
    }
}
