package jp.ac.uryukyu.ie.e195710;

import java.util.Arrays;

public class Main {
    public static void main(String args[]){
        Board_osero c = new Board_osero(" ","●", "○", new String[8][8]);
        c.initialise();
        for(int i=0; i < 8; i++) {
            System.out.println(Arrays.toString(c.board[i]));
        }

    }
}
