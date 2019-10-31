package tw.session.parkinglot.parkingboy

import tw.session.parkinglot.Car
import tw.session.parkinglot.ParkingLot
import tw.session.parkinglot.Ticket

class SuperParkingBoy(private var parkingLots: List<ParkingLot>) {

    fun park(car: Car): Ticket {
        return parkingLots.reduce { pre, current ->  if (pre.vacancyRate() > current.vacancyRate()) pre else current }.park(car)
    }

    fun pick(ticket: Ticket): Car {
        return parkingLots.first().pick(ticket)
    }
}
