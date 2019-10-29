import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import tw.session.parkinglot.Car;
import tw.session.parkinglot.InvalidTicketException;
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

    @Test
    void shouldGetCar_whenPickCarWithValidTicket_givenParkingBoyHasParkedTheCar() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        Car car = new Car("TheCarNumber");
        Ticket ticket = smartParkingBoy.park(car);

        //when
        Car pickedCar = smartParkingBoy.pick(ticket);

        //then
        assertEquals(car.getCarNum(), pickedCar.getCarNum());
    }


    @Test
    void shouldFailed_whenPickCarWithInvalidTicket_givenParkingBoyHasParkedTheCar() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        Car car = new Car("TheCarNumber");
        Ticket ticket = smartParkingBoy.park(car);
        ticket.destroy();

        //then
        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.pick(ticket));
    }
}
