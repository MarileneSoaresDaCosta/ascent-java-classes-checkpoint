package com.galvanize;

public class Testing {

    public static void main(String[] args) {
        CallingCard newCard = new CallingCard(7);
        System.out.println(newCard.toString());
        newCard.addDollars(3);
        System.out.println(newCard.toString());
        System.out.println(newCard.getRemainingMinutes());
        newCard.useMinutes(40);
        System.out.println(newCard.getRemainingMinutes());
    }


}

