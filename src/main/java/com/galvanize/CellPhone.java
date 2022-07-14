package com.galvanize;

public class CellPhone {
    // state
    private boolean activeCall = false;
    private String history = "";
    private int duration;
    private CallingCard card;
    private int remainingMin;
    private boolean cutOff = false;

    // constructor - we pass an instance of card
    CellPhone(CallingCard card) {
        this.card = card;
    }

    // methods
    @Override
    public String toString() {
        return "The call card: " + this.card.toString();
    }

    public boolean isTalking() {
        return activeCall;
    }

    public void call(String phoneNumber) {
        this.activeCall = true;
//        System.out.println(this.history.isEmpty());
        if ( this.history.length() == 0) {
            this.history = phoneNumber;
        } else {
            this.history += ", " + phoneNumber;
        }
    }

    public String getHistory() {
        return history;
    }

    public void endCall() {
        if (this.activeCall == false) {
//            System.out.println("No active call");
            return;
        }
        this.activeCall = false;
        // update used minutes in card
//        this.card.useMinutes(duration);

        // update history - remember to adjust min minutes
        if(this.cutOff == true) {
            this.history += " (cut off at " + this.duration + " minute";
            this.cutOff = false;
        } else {
            this.history += " (" + this.duration + " minute";
        }

        // check if plural
        if(this.duration > 1) {
            this.history += "s";
        }
        this.history += ")";

        // zero duration
        this.duration = 0;
    }

    public void tick() {
        // check if enough minutes
//        System.out.println("You have: " + this.card.getRemainingMinutes());

        if (this.card.getRemainingMinutes() == 1) {
            this.cutOff = true;
            duration += 1;
            this.card.useMinutes(1);
            this.endCall();
//            System.out.println("You have used all of your minutes.");

        } else {
            this.card.useMinutes(1);
            duration += 1;
        }
    }
}
