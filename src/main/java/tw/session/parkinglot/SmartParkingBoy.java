package tw.session.parkinglot;

import java.util.Arrays;
import java.util.List;

public class SmartParkingBoy {
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(ParkingLot ... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public Ticket park(Car car) {
        return parkingLots.get(0).park(car);
    }

    public Car pick(Ticket ticket) {
        return parkingLots.get(0).pick(ticket);
    }
}
