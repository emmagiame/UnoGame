package com.example.unogame;

import java.util.Random;

public class UnoCard {

    //card color
    protected char cardColor;

    //card number
    protected int cardNumber;

    public UnoCard(){
        cardColor = randomizeCardColor();
        cardNumber = randomizeCardNumber();
    }

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

    public int randomizeCardNumber(){
        //randomize a number 0 though 9
        Random rand = new Random();

        //this is right i promise
        int num = rand.nextInt(10);

        //return the number
        return num;
    }



}
