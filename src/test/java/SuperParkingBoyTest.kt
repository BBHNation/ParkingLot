import org.junit.jupiter.api.Assertions.*
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

    @Test
    internal fun shouldParkInSecondParkingLot_whenParkOneCar_givenSuperParkingBoyWithThreeParkingLotsAndTheSecondHasMaxVacancyRate() {
        val firstParkingLot = ParkingLot(10)
        val secondParkingLot = ParkingLot(10)
        val thirdParkingLot = ParkingLot(10)
        // first parking lot vacancy rate is 70%
        firstParkingLot.park(Car("car1"))
        firstParkingLot.park(Car("car2"))
        firstParkingLot.park(Car("car3"))
        // first parking lot vacancy rate is 90%
        secondParkingLot.park(Car("car4"))
        // first parking lot vacancy rate is 60%
        thirdParkingLot.park(Car("car5"))
        thirdParkingLot.park(Car("car6"))
        thirdParkingLot.park(Car("car7"))
        thirdParkingLot.park(Car("car8"))
        val superParkingBoy = SuperParkingBoy(listOf(firstParkingLot, secondParkingLot, thirdParkingLot))

        val car = Car("car9")

        superParkingBoy.park(car)

        assertTrue(secondParkingLot.has(car.carNum))
    }

    @Test
    internal fun shouldParkInFirstParkingLot_whenParkOneCarBySuperParkingBoy_givenThreeEmptyParkingLots() {
        val firstParkingLot = ParkingLot(10)
        val secondParkingLot = ParkingLot(10)
        val thirdParkingLot = ParkingLot(10)
        val superParkingBoy = SuperParkingBoy(listOf(firstParkingLot, secondParkingLot, thirdParkingLot))
        val car = Car("川A 38BR7")

        superParkingBoy.park(car)

        assertTrue(firstParkingLot.has(car.carNum))
    }
}
