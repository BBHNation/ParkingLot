package tw.session.parkinglot;

import java.util.Arrays;
import java.util.List;

public class GraduateParkingBoy {

    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public Ticket park(Car car) {
        return parkingLots
            .stream()
            .filter(ParkingLot::isNotFull)
            .findFirst()
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
