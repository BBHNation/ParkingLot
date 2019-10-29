package tw.session.parkinglot;

import java.util.Arrays;
import java.util.Optional;
import java.util.PriorityQueue;

public class SmartParkingBoy {

    private PriorityQueue<ParkingLot> parkingLots;

    public SmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = new PriorityQueue<>(Arrays.asList(parkingLots));
    }

    public Ticket park(Car car) {
        return Optional.ofNullable(parkingLots.peek())
            .orElseThrow(ParkingLotUnavailableException::new)
            .park(car);
    }

    public Car pick(Ticket ticket) {
        return parkingLots
            .stream()
            .dropWhile(parkingLot -> !parkingLot.has(ticket.getCarNum()))
            .findFirst()
            .orElseThrow(ParkingLotNotParkingTheCarException::new)
            .pick(ticket);
    }
}
