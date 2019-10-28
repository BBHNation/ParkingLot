package tw.session.parkinglot;

import lombok.Getter;

@Getter
public
class Ticket {
    private String carNum;

    Ticket(String carNum) {
        this.carNum = carNum;
    }

}
