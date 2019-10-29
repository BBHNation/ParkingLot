package tw.session.parkinglot.parkingboy;

import java.util.Arrays;
import java.util.Optional;
import java.util.PriorityQueue;
import tw.session.parkinglot.Car;
import tw.session.parkinglot.ParkingLot;
import tw.session.parkinglot.Ticket;
import tw.session.parkinglot.exception.ParkingLotNotParkingTheCarException;
import tw.session.parkinglot.exception.ParkingLotUnavailableException;

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
