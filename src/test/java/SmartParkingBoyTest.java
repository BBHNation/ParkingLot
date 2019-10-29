import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void shouldParkTheCarInFirstParkingLot_whenParkOneCarBySmartParkingBoy_givenThreeParkingLotsAndThirdHavingMoreAvailablePlaces() {
        //given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ParkingLot parkingLotC = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB,
            parkingLotC);
        Car car = new Car("TheCarNum");

        //when
        Ticket ticket = smartParkingBoy.park(car);

        //then
        assertTrue(parkingLotC.has(car.getCarNum()));
    }

    @Test
    void shouldParkTheCarInFirstParkingLot_whenParkOneCarBySmartParkingBoy_givenThreeEmptyParkingLots() {
        //given
        ParkingLot parkingLotA = new ParkingLot(3);
        ParkingLot parkingLotB = new ParkingLot(3);
        ParkingLot parkingLotC = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB,
            parkingLotC);
        Car car = new Car("TheCarNum");

        //when
        Ticket ticket = smartParkingBoy.park(car);

        //then
        assertTrue(parkingLotA.has(car.getCarNum()));
    }
}
