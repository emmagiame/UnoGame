package com.example.unogame;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.unogame.cards.UnoCard;
import com.example.unogame.info.UnoGameState;

import java.util.ArrayList;

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
    public void isCardColorRandomized(){
        UnoCard testCard = new UnoCard();
        char cardColor = testCard.getCardColor();
        assertTrue((testCard.getCardColor() == 'r') || (testCard.randomizeCardColor() == 'y') || (testCard.randomizeCardNumber() == 'b') || (testCard.randomizeCardNumber() == 'g'));
    }

    @Test
    public void areTheGameStateObjectsCopied(){
        UnoGameState ogGame = new UnoGameState(2);
        UnoGameState copyGame = new UnoGameState(ogGame);
        assertTrue(ogGame.getPlayerTurn() == copyGame.getPlayerTurn());
        assertTrue(ogGame.getIsReversed() == copyGame.getIsReversed());
        assertTrue(ogGame.getPlayer0Score() == copyGame.getPlayer0Score());
        assertTrue(ogGame.getPlayer1Score() == copyGame.getPlayer1Score());
        assertTrue(ogGame.getPlayer2Score() == copyGame.getPlayer2Score());
        assertTrue(ogGame.getPrevPlayer(2) == copyGame.getPrevPlayer(2));
        assertTrue(ogGame.getCurrentSetupTurn() == copyGame.getCurrentSetupTurn());
        assertTrue(ogGame.getNumSetupTurns() == copyGame.getNumSetupTurns());
        assertTrue(ogGame.getCurrentPlayableNumber() == copyGame.getCurrentPlayableNumber());
        assertTrue(ogGame.getCurrentPlayableColor() == copyGame.getCurrentPlayableColor());

    }

    @Test
    public void areTheGameStateArraysCopied(){
        UnoGameState ogGame = new UnoGameState(2);
        UnoGameState copyGame = new UnoGameState(ogGame);

        //check drawPile was copied correctly
        ArrayList<UnoCard> ogDrawPile = ogGame.getDrawPile();
        ArrayList<UnoCard> newDrawPile = copyGame.getDrawPile();
        for(UnoCard card : ogDrawPile){
            assertTrue(newDrawPile.equals(card));
        }

        //check discardPile was copied correctly
        ArrayList<UnoCard> ogDiscardPile = ogGame.getDiscardPile();
        ArrayList<UnoCard> newDiscardPile = copyGame.getDiscardPile();
        for(UnoCard card : ogDiscardPile){
            assertTrue(newDiscardPile.equals(card));
        }

        //check player 0 hand was copied correctly
        ArrayList<UnoCard> ogPlayer0Hand = ogGame.getPlayer0Hand();
        ArrayList<UnoCard> newPlayer0Hand = copyGame.getPlayer0Hand();
        for(UnoCard card : ogPlayer0Hand){
            assertTrue(newPlayer0Hand.equals(card));
        }
        //check player 1 hand was copied correctly
        ArrayList<UnoCard> ogPlayer1Hand = ogGame.getPlayer1Hand();
        ArrayList<UnoCard> newPlayer1Hand = copyGame.getPlayer1Hand();
        for(UnoCard card : ogPlayer1Hand){
            assertTrue(newPlayer1Hand.equals(card));
        }
        //check player 2 hand was copied correctly
        ArrayList<UnoCard> ogPlayer2Hand = ogGame.getPlayer2Hand();
        ArrayList<UnoCard> newPlayer2Hand = copyGame.getPlayer2Hand();
        for(UnoCard card : ogPlayer2Hand){
            assertTrue(newPlayer2Hand.equals(card));
        }
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