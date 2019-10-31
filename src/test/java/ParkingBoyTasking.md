# Common Cases For Parking Boy

**park**
- Given one parking boy with one parking lot, when park one car, then get one ticket.
- Given one parking boy with two parking lots, when park one car, then get one ticket.

**pick**
- Given parking boy has parked the car, when pick car with valid ticket, then get the car.
- Given parking boy has parked the car, when pick car with invalid ticket, then pick failed.

# Graduate Parking Boy Cases

**policy**
- Given three empty parking lots containing two places, when parking boy parks one car, then the car should parked in the first parking lot.
- Given two full parking lots, when parking boy parks one car, then failed.
- Given the car parked in the parking lot, when user pick the car by parking boy with the ticket, then user get the car.

# Smart Parking Boy Cases

**policy**
- Given three parking lots and the third has more available places, when park one car, then the car should parked in the third parking lot.
- Given three empty parking lots, when park one car, then the car should parked in the first parking lot.

# Super Parking Boy Cases

**policy**
- Given three parking lots and the second has maximum vacancy rate, when park one car, then the car should parked in the second parking lot.
- Given three empty parking lots, when park one car, then the car should parked in the first parking lot.

