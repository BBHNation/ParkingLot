package tw.session.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;

    private List<Car> parkedCars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
    }

    public boolean isNotFull() {
        return this.parkedCars.size() < this.capacity;
    }

    public boolean has(String carNum) {
        return parkedCars.stream().anyMatch(car -> car.getCarNum().equals(carNum));
    }

    public Ticket park(Car car) {
        if (parkedCars.size() >= capacity) {
            throw new ParkingLotFullException();
        }
        parkedCars.add(car);
        return new Ticket(car.getCarNum());
    }

    public Car pick(Ticket ticket) {
        if (ticket.isNotValid()) {
            throw new InvalidTicketException();
        }

        Car pickingCar = parkedCars
            .stream()
            .filter(car -> car.getCarNum().equals(ticket.getCarNum()))
            .findFirst()
            .orElseThrow(ParkingLotNotParkingTheCarException::new);

        parkedCars.remove(pickingCar);
        ticket.destroy();
        return pickingCar;
    }
}
