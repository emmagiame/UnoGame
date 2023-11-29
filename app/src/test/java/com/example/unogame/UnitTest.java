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

    UnoGameState unoGameStateRef;
    UnoCard unoCardRef;
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

        boolean allTrue = false;

        if(ogGame.getPlayerTurn() == copyGame.getPlayerTurn() && ogGame.getIsReversed() == copyGame.getIsReversed() &&
                ogGame.getPlayer0Score() == copyGame.getPlayer0Score() && ogGame.getPlayer1Score() == copyGame.getPlayer1Score() &&
                ogGame.getPlayer2Score() == copyGame.getPlayer2Score() && ogGame.getPrevPlayer(2) == copyGame.getPrevPlayer(2) &&
                ogGame.getCurrentSetupTurn() == copyGame.getCurrentSetupTurn() && ogGame.getNumSetupTurns() == copyGame.getNumSetupTurns() &&
                ogGame.getCurrentPlayableNumber() == copyGame.getCurrentPlayableNumber() && ogGame.getCurrentPlayableColor() == copyGame.getCurrentPlayableColor()){
            allTrue = true;
        }
        assertTrue(allTrue);
    }

    @Test
    public void areTheGameStateArraysCopied(){
        UnoGameState ogGame = new UnoGameState(2);
        UnoGameState copyGame = new UnoGameState(ogGame);

        //check drawPile was copied correctly
        ArrayList<UnoCard> ogDrawPile = ogGame.getDrawPile();
        ArrayList<UnoCard> newDrawPile = copyGame.getDrawPile();
        for (int i = 0; i < ogDrawPile.size(); i++) {
            assertTrue(newDrawPile.get(i).equals(ogDrawPile.get(i)));
        }

        //check discardPile was copied correctly
        ArrayList<UnoCard> ogDiscardPile = ogGame.getDiscardPile();
        ArrayList<UnoCard> newDiscardPile = copyGame.getDiscardPile();
        for (int i = 0; i < ogDiscardPile.size(); i++) {
            assertTrue(newDiscardPile.get(i).equals(ogDiscardPile.get(i)));
        }

        //check player 0 hand was copied correctly
        ArrayList<UnoCard> ogPlayer0Hand = ogGame.getPlayer0Hand();
        ArrayList<UnoCard> newPlayer0Hand = copyGame.getPlayer0Hand();
        for (int i = 0; i < ogPlayer0Hand.size(); i++) {
            assertTrue(newPlayer0Hand.get(i).equals(ogPlayer0Hand.get(i)));
        }

        //check player 1 hand was copied correctly
        ArrayList<UnoCard> ogPlayer1Hand = ogGame.getPlayer1Hand();
        ArrayList<UnoCard> newPlayer1Hand = copyGame.getPlayer1Hand();
        for (int i = 0; i < ogPlayer1Hand.size(); i++) {
            assertTrue(newPlayer1Hand.get(i).equals(ogPlayer1Hand.get(i)));
        }

        //check player 2 hand was copied correctly
        ArrayList<UnoCard> ogPlayer2Hand = ogGame.getPlayer2Hand();
        ArrayList<UnoCard> newPlayer2Hand = copyGame.getPlayer2Hand();
        for (int i = 0; i < ogPlayer2Hand.size(); i++) {
            assertTrue(newPlayer2Hand.get(i).equals(ogPlayer2Hand.get(i)));
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

    @Test
    public void cardColorMatches(){

        char discardColor = unoGameStateRef.getCurrentPlayableColor();

        if(unoGameStateRef.playCard(0, new UnoCard()) == true) {
            assertEquals(discardColor,unoGameStateRef.getCurrentPlayableColor());
        }

        else {
            System.out.println("Card trying to be played did not match color of discard pile");
        }
    }


    @Test
    public void cardNumberMatches() {

        char discardNumber = unoGameStateRef.getCurrentPlayableColor();
        if(unoGameStateRef.playCard(0, new UnoCard()) == true) {
            assertEquals(discardNumber,unoGameStateRef.getCurrentPlayableColor());
        }

        else {
            System.out.println("Card trying to be played did not match number of discard pile");
        }



    }

    @Test
    public void cardIsRemoved() {

        // num cards in players hand before discard
        int beforeDiscard = unoGameStateRef.getPlayer0Hand().size();

        // go through players card in their hands
        for(int i = 0; i < unoGameStateRef.getPlayer0Hand().size(); i++){

            UnoCard card = unoGameStateRef.getPlayer0Hand().get(i);

            // loop until they are able to play a card
            while(unoGameStateRef.playCard(0, card) == false) {
                unoGameStateRef.playCard(0, card);
            }

            // get num cards in players hand after discard
            int afterDiscard = unoGameStateRef.getPlayer0Hand().size();

            // make sure num cards in their hand after is one less than before
            assertEquals(afterDiscard, beforeDiscard - 1);
        }
    }

}