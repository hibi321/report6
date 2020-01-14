/**
 * オセロゲームを作るためのコード
 */
public class Board_osero {
    String[][] board;
    String empty;
    String black;
    String white;

    /**
     * 変数に値を代入するためのコンストラクタ
     * @param empty:空白、何も行かれていない場所
     * @param black:黒の石が置かれている場所
     * @param white:白の石が置かれている場所
     * @param board:オセロのボードの大きさを決めるための多次元配列、基本は8✖︎8の大きさ
     */
    Board_osero(String empty, String black, String white, String[][] board){
        this.empty = empty;
        this.black = black;
        this.white = white;
        this.board = board;

    }

    /**
     * ボードを初期状態にセットするためのメソッド
     */
    public void initialise(){
        for (int a = 0; a < 8; a++){
            for (int b = 0; b < 8; b++){
                board[a][b] = empty;
            }
        }
        board[3][3] = black;
        board[3][4] = white;
        board[4][3] = white;
        board[4][4] = black;
    }





}
