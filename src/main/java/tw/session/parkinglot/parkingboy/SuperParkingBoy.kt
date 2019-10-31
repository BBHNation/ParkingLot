package tw.session.parkinglot.parkingboy

import tw.session.parkinglot.Car
import tw.session.parkinglot.ParkingLot
import tw.session.parkinglot.Ticket

class SuperParkingBoy(private var parkingLots: List<ParkingLot>) {

    fun park(car: Car): Ticket {
        return parkingLots.first().park(car)
    }

    fun pick(ticket: Ticket): Car {
        return parkingLots.first().pick(ticket)
    }
}
