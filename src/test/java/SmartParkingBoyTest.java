import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.session.parkinglot.Car;
import tw.session.parkinglot.ParkingLot;
import tw.session.parkinglot.SmartParkingBoy;
import tw.session.parkinglot.Ticket;

class SmartParkingBoyTest {

    @Test
    void shouldGetTicketOfTheCar_whenParkOneCar_givenSmartParkingBoyWithOneParkingLot() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        Car car = new Car("TheCarNum");

        //when
        Ticket ticket = smartParkingBoy.park(car);

        //then
        assertEquals(car.getCarNum(), ticket.getCarNum());
    }

    @Test
    void shouldGetTicketOfTheCar_whenParkOneCar_givenSmartParkingBoyWithTwoParkingLots() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1), new ParkingLot(1));
        Car car = new Car("TheCarNum");

        //when
        Ticket ticket = smartParkingBoy.park(car);

        //then
        assertEquals(car.getCarNum(), ticket.getCarNum());
    }
}
