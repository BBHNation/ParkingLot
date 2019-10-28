package tw.session.parkinglot;

import lombok.Getter;

@Getter
public class Ticket {
    private String carNum;

    private boolean valid;

    Ticket(String carNum) {
        this.carNum = carNum;
        this.valid = true;
    }

    void destroy() {
        this.valid = false;
    }

    boolean isNotValid() {
        return !valid;
    }

}
