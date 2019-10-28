import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tw.session.parkinglot.Car;
import tw.session.parkinglot.ParkingBoy;
import tw.session.parkinglot.ParkingLot;
import tw.session.parkinglot.Ticket;

class ParkingBoyTest {

    @Test
    void shouldParkCarInSort_whenParkCar_givenMoreThanOneParkingLots() {
        //given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA, parkingLotB);
        Car theCar = new Car("TheCarNum");

        //when
        Ticket ticket = parkingBoy.park(theCar);

        //then
        assertEquals("TheCarNum", ticket.getCarNum());
        assertTrue(parkingLotA.isFull());
        assertTrue(parkingLotB.isNotFull());
    }
}
