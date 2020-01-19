package jp.ac.uryukyu.ie.e195710;

import java.util.Arrays;

public class Main {
    public static void main(String args[]){
        Board_osero c = new Board_osero(" ","●", "○", new String[8][8]);
        c.initialise();
        c.currentBoard(c);

        c.stonePlace(c.black);

    }
}
