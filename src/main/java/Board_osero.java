public class Board_osero {
    String[][] board = new String[8][8];

    String empty = " ";
    String black = "●";
    String white = "○";

    Board_osero(String empty, String black, String white, String[][] board){
        this.empty = empty;
        this.black = black;
        this.white = white;
        this.board = board;

    }


    public String[][] initialise(){
        for (int a = 0; a < 8; a++){
            for (int b = 0; b < 8; b++){
                board[a][b] = empty;
            }
        }
        return board;
    }

}
