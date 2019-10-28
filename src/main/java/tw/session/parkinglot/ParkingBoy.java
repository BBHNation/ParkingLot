package tw.session.parkinglot;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot ... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isNotFull()) {
                return parkingLot.park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    public Car pick(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isParkingCarOfCarNum(ticket.getCarNum())) {
                return parkingLot.pick(ticket);
            }
        }
        throw new ParkingLotNotParkingTheCarException();
    }
}
