import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import tw.session.parkinglot.Car
import tw.session.parkinglot.ParkingLot
import tw.session.parkinglot.Ticket
import tw.session.parkinglot.parkingboy.SuperParkingBoy

class SuperParkingBoyTest {
    @Test
    internal fun shouldGetTicket_whenParkOneCar_givenSuperParkingBoyWithAvailableParkingLots() {
        val superParkingBoy = SuperParkingBoy(listOf<ParkingLot>(ParkingLot(1)))
        val car = Car("川A 38BR7")
        val ticket: Ticket = superParkingBoy.park(car)
        assertNotNull(ticket)
    }

    @Test
    internal fun shouldGetTheCar_whenPickCarBySuperParkingBoyWithValidTicket_givenTheCarParkedBySuperParkingBoy() {
        val superParkingBoy = SuperParkingBoy(listOf(ParkingLot(1)))
        val car = Car("川A 38BR7")
        val ticket = superParkingBoy.park(car)

        val pickedCar = superParkingBoy.pick(ticket)
        assertEquals(car.carNum, pickedCar.carNum)
    }
}
