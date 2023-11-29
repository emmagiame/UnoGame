package com.example.unogame.cards;

import androidx.annotation.Nullable;

import java.util.Random;

public class UnoCard {

    //card color
    protected char cardColor;

    //card number
    protected int cardNumber;



    // instance variables
    protected int color;
    protected int number;

    /**
     * constructor
     */
    public UnoCard(){
        cardColor = randomizeCardColor();
        cardNumber = randomizeCardNumber();
    }


    /**
     * copy constructor
     *
     * @param card - card to copy
     */
    public UnoCard(UnoCard card){
        this.cardColor = card.cardColor;
        this.cardNumber = card.cardNumber;
    }

    /**
     *  gets the current cards color
     *
     * @return
     *      the current card color
     */
    public char getCardColor() { return this.cardColor; }

    /**
     *  gets current card number
     *
     * @return
     *      the current card number
     */
    public int getCardNumber() { return this.cardNumber; }

    /**
     * randomizes the cards color
     * setter method
     *
     * @return
     *      returns the first char of the color of the card
     */
    public char randomizeCardColor(){
        //randomize a number 1 through 4
        Random rand = new Random();
        int num = rand.nextInt(4);
        num += 1;

        if(num == 1){
            //return r for red
            return 'r';
        }
        if(num == 2){
            //return g for green
            return 'g';
        }
        if(num == 3){
            //return b for blue
            return 'b';
        }
        if(num == 4){
            //return y for yellow
            return 'y';
        }
        //if you hit this theres a problem
        else return 'n';
    }

    /**
     * randomizes card number
     * setter method
     *
     * @return
     *      return cards number
     */
    public int randomizeCardNumber(){
        //randomize a number 0 though 9
        Random rand = new Random();

        //this is right i promise
        int num = rand.nextInt(10);

        //return the number
        return num;
    }

    /**
     * prints current card number and color
     *
     * @return
     *      string description of card color and number
     */
    @Override
    //print current card
    public String toString() {
        return "Card Number: " + this.getCardNumber() + ", Card Color: " + this.getCardColor();
    }


}
