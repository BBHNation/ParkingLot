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

}
