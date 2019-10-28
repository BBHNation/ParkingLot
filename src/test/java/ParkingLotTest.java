import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import tw.session.parkinglot.Car;
import tw.session.parkinglot.InvalidTicketException;
import tw.session.parkinglot.ParkingLot;
import tw.session.parkinglot.ParkingLotFullException;
import tw.session.parkinglot.ParkingLotNotParkingTheCarException;
import tw.session.parkinglot.Ticket;

class ParkingLotTest {

    @Test
    void shouldGetOneTicket_whenParkOneCar_givenParkingLotHavingOnePlaceAvailable() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("OneCarNum");

        //when
        Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void shouldParkFailed_whenParkOneCar_givenUnavailableParkingLot() {
        // given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car("OneCarNum");

        // then
        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car));
    }

    @Test
    void shouldParkFirstCarSuccessAndSecondFailed_whenParkTwoCars_givenOnePlaceAvailableParkingLot() {
        // given
        ParkingLot parkingLot = new ParkingLot(1);
        Car firstCar = new Car("FirstCarNum");
        Car secondCar = new Car("SecondCarNum");

        // when
        Ticket firstTicket = parkingLot.park(firstCar);

        // then
        assertNotNull(firstTicket);
        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(secondCar));
    }

    @Test
    void shouldPickCar_whenPickCarWithTicket_givenParkingLotParkingTheCar() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("TheCarNum");
        Ticket ticket = parkingLot.park(car);

        //when
        Car pickedCar = parkingLot.pick(ticket);

        //then
        assertEquals("TheCarNum", pickedCar.getCarNum());
    }

    @Test
    void shouldPickCarFailed_whenPickCarWithTicket_givenParkingLotNotParkingTheCar() {
        //given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        Car car = new Car("TheCarNum");
        Ticket ticket = parkingLotA.park(car);

        //then
        assertThrows(ParkingLotNotParkingTheCarException.class, () -> parkingLotB.pick(ticket));
    }

    @Test
    void shouldPickCarFailed_whenPickCarWithInvalidTicket_givenParkingLotParkingTheCar() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("TheCarNum");
        Ticket ticket = parkingLot.park(car);
        car = parkingLot.pick(ticket);
        parkingLot.park(car);

        // then
        assertThrows(InvalidTicketException.class, () -> parkingLot.pick(ticket));
    }

    @Test
    void shouldGetTicketOfBCar_whenParkBCarAfterPickACar_givenFullParkingLotParkingACar() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car aCar = new Car("A_CarNum");
        Car bCar = new Car("B_CarNum");
        Ticket ticketOfACar = parkingLot.park(aCar);

        //when
        parkingLot.pick(ticketOfACar);
        Ticket ticketOfBCar = parkingLot.park(bCar);

        //then
        assertEquals("B_CarNum", ticketOfBCar.getCarNum());
    }
}
