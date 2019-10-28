import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tw.session.parkinglot.Car;
import tw.session.parkinglot.ParkingBoy;
import tw.session.parkinglot.ParkingLot;
import tw.session.parkinglot.ParkingLotFullException;
import tw.session.parkinglot.Ticket;

class ParkingBoyTest {

    @Test
    void shouldParkCarOrdered_whenParkCar_givenMoreThanOneEmptyParkingLots() {
        //given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA, parkingLotB);
        Car carA = new Car("CarANum");
        Car carB = new Car("CarBNum");

        //when
        Ticket ticketOfCarA = parkingBoy.park(carA);

        //then
        assertEquals("CarANum", ticketOfCarA.getCarNum());
        assertTrue(parkingLotA.isFull());
        assertTrue(parkingLotB.isNotFull());

        //and when
        Ticket ticketOfCarB = parkingBoy.park(carB);

        //and then
        assertEquals("CarBNum", ticketOfCarB.getCarNum());
        assertTrue(parkingLotA.isFull());
        assertTrue(parkingLotB.isFull());
    }

    @Test
    void shouldParkCarOrdered_whenParkingCar_givenMoreThanOneNotEmptyParkingLot() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        Ticket ticketOfFirstTempCar = parkingBoy.park(new Car("firstTempCarNum"));
        parkingBoy.park(new Car("secondTempCarNum"));
        parkingBoy.park(new Car("thirdTempCarNum"));
        parkingBoy.pick(ticketOfFirstTempCar);
        Car car = new Car("TheCarNum");

        assertTrue(firstParkingLot.isNotFull());
        assertTrue(firstParkingLot.isNotFull());

        //when
        parkingBoy.park(car);

        //then
        assertTrue(firstParkingLot.isFull());
        assertTrue(secondParkingLot.isNotFull());
    }

    @Test
    void shouldParkFailed_whenParkCar_givenParkingBoyWithAllFullParkingLots() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1), new ParkingLot(1));
        Car firstCar = new Car("FirstCarNum");
        Car secondCar = new Car("SecondCarNum");
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);
        Car car = new Car("TheCarNum");

        //then
        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(car));
    }

    @Test
    void shouldGetTheCar_whenPickCarByParkingBoy_givenParkingBoyParedTheCar() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1), new ParkingLot(1));
        Car car = new Car("TheCarNum");
        Ticket ticket = parkingBoy.park(car);

        //when
        Car pickedCar = parkingBoy.pick(ticket);

        //then
        assertEquals(car.getCarNum(), pickedCar.getCarNum());
    }
}
