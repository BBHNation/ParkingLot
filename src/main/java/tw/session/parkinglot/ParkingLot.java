package tw.session.parkinglot;

import java.util.ArrayList;
import java.util.List;
import tw.session.parkinglot.exception.InvalidTicketException;
import tw.session.parkinglot.exception.ParkingLotNotParkingTheCarException;
import tw.session.parkinglot.exception.ParkingLotUnavailableException;

public class ParkingLot implements Comparable {

    private int capacity;

    private List<Car> parkedCars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
    }

    private int availablePlacesNum() {
        return capacity - parkedCars.size();
    }

    public boolean isNotFull() {
        return this.parkedCars.size() < this.capacity;
    }

    public boolean has(String carNum) {
        return parkedCars.stream().anyMatch(car -> car.getCarNum().equals(carNum));
    }

    public Ticket park(Car car) {
        if (parkedCars.size() >= capacity) {
            throw new ParkingLotUnavailableException();
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

    @Override
    public int compareTo(Object o) {
        ParkingLot parkingLot = (ParkingLot)o;
        return Integer.compare(parkingLot.availablePlacesNum(), this.availablePlacesNum());
    }
}
