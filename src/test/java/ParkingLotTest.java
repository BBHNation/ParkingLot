import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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


}
