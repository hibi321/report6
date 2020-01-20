package jp.ac.uryukyu.ie.e195710;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Board_oseroTest {
    @Test
    public void Board_oseroTest(){
        Board_osero test = new Board_osero(" ","●", "○", new String[8][8], true);
                assertEquals(true, test.gamingNow);
    }
}