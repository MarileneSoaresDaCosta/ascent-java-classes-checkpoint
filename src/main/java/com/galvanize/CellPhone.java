
package com.galvanize;

public class CellPhone {
    // state
    private boolean activeCall = false;
    private String history;
    private int duration;
    private CallingCard card;
    private int remainingMin;

    // constructor - we pass an instance of card
    CellPhone(CallingCard card) {
        this.card = card;
    }

    // methods
}
