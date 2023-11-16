package com.example.unogame;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.unogame.cards.UnoCard;

import java.util.Random;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkIfCardNumIsRandomized(){
        for(int i = 0; i <= 15; i++){
            UnoCard tester = new UnoCard();
            assertTrue(tester.getCardNumber() < 10 && tester.getCardNumber() >= 0);
        }
    }

    @Test
    public void testIfCardCopyConstructorWorks(){
        UnoCard tester = new UnoCard();
        UnoCard copyTest = new UnoCard(tester);
        assertEquals(tester.getCardNumber(), copyTest.getCardNumber());
        assertEquals(tester.getCardColor(), copyTest.getCardColor());
    }

    @Test
    public void testIfCardToStringWorks(){
        UnoCard tester = new UnoCard();
        String testerString = "Card Number: " + tester.getCardNumber() + ", Card Color: " + tester.getCardColor();
        assertTrue(testerString.equals(tester.toString()));
    }
}