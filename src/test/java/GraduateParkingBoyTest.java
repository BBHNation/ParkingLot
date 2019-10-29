import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tw.session.parkinglot.Car;
import tw.session.parkinglot.parkingboy.GraduateParkingBoy;
import tw.session.parkinglot.ParkingLot;
import tw.session.parkinglot.exception.ParkingLotUnavailableException;
import tw.session.parkinglot.Ticket;

class GraduateParkingBoyTest {

    @Test
    void shouldParkCarOrdered_whenParkCar_givenMoreThanOneEmptyParkingLots() {
        //given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotA, parkingLotB);
        Car carA = new Car("CarANum");
        Car carB = new Car("CarBNum");

        //when
        graduateParkingBoy.park(carA);
        graduateParkingBoy.park(carB);

        //then
        assertTrue(parkingLotA.has(carA.getCarNum()));
        assertTrue(parkingLotB.has(carB.getCarNum()));
    }

    @Test
    void shouldParkCarOrdered_whenParkingCar_givenMoreThanOneNotEmptyParkingLot() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLot, secondParkingLot);
        Ticket ticketOfFirstTempCar = graduateParkingBoy.park(new Car("firstTempCarNum"));
        graduateParkingBoy.park(new Car("secondTempCarNum"));
        graduateParkingBoy.park(new Car("thirdTempCarNum"));
        graduateParkingBoy.pick(ticketOfFirstTempCar);
        Car car = new Car("TheCarNum");

        assertTrue(firstParkingLot.isNotFull());
        assertTrue(firstParkingLot.isNotFull());

        //when
        graduateParkingBoy.park(car);

        //then
        assertFalse(firstParkingLot.isNotFull());
        assertTrue(secondParkingLot.isNotFull());
    }

    @Test
    void shouldParkFailed_whenParkCar_givenParkingBoyWithAllFullParkingLots() {
        //given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot(1), new ParkingLot(1));
        Car firstCar = new Car("FirstCarNum");
        Car secondCar = new Car("SecondCarNum");
        graduateParkingBoy.park(firstCar);
        graduateParkingBoy.park(secondCar);
        Car car = new Car("TheCarNum");

        //then
        assertThrows(ParkingLotUnavailableException.class, () -> graduateParkingBoy.park(car));
    }

    @Test
    void shouldGetTheCar_whenPickCarByParkingBoy_givenParkingBoyParedTheCar() {
        //given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkingLot(1), new ParkingLot(1));
        Car car = new Car("TheCarNum");
        Ticket ticket = graduateParkingBoy.park(car);

        //when
        Car pickedCar = graduateParkingBoy.pick(ticket);

        //then
        assertEquals(car.getCarNum(), pickedCar.getCarNum());
    }
}
