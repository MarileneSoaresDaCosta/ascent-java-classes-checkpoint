package com.galvanize;

public class Testing {

    public static void main(String[] args) {
//        CallingCard newCard = new CallingCard(7);
//        System.out.println(newCard.toString());
//        newCard.addDollars(3);
//        System.out.println(newCard.toString());
//        System.out.println(newCard.getRemainingMinutes());
//        newCard.useMinutes(30);
//        System.out.println("...after using 40:" + newCard.getRemainingMinutes());
//
//        CellPhone phone = new CellPhone(newCard);
//        System.out.println(phone.toString());
//        System.out.println(phone.isTalking());
//        phone.call("555-1212");
//        System.out.println(phone.isTalking());
//        System.out.println(phone.getHistory());
//
//        phone.tick();
////        phone.tick();
////        phone.tick();
//        phone.endCall();
//        System.out.println(phone.getHistory());
//        System.out.println(phone.toString());

        CallingCard card = new CallingCard(20);
        card.addDollars(1); // add 100 cents @ 20 cents/minute = 5 minutes added

        CellPhone phone = new CellPhone(card);
        phone.call("555-1111");
        phone.tick();       // 1 minute elapsed
        phone.tick();       // 2 minutes elapsed
        phone.endCall();
//        System.out.println("end of first");
        System.out.println(phone.getHistory());
        phone.getHistory(); // => returns "555-1111 (2 minutes), 555-3333 (cut of at 3 minutes)"
        System.out.println(card.getRemainingMinutes());
        card.getRemainingMinutes(); // => returns 0, because all 5 minutes have been used up


        phone.call("555-3333");
        phone.tick();       // 3 minutes elapsed
        phone.tick();       // 4 minutes elapsed
        phone.tick();       // this is the end of the 5th minute, so the call is ended

        System.out.println(phone.getHistory());
        phone.getHistory(); // => returns "555-1111 (2 minutes), 555-3333 (cut of at 3 minutes)"
        System.out.println(card.getRemainingMinutes());
        card.getRemainingMinutes(); // => returns 0, because all 5 minutes have been used up




    }


}
