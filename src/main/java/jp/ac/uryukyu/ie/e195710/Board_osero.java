package jp.ac.uryukyu.ie.e195710;

import java.util.Arrays;
import java.util.Scanner;

/**
 * オセロゲームを作るためのコード
 */
public class Board_osero {
    String[][] board;
    String empty;
    String black;
    String white;
    boolean gamingNow;


    /**
     * 変数に値を代入するためのコンストラクタ
     * @param empty:空白、何も行かれていない場所
     * @param black:黒の石が置かれている場所
     * @param white:白の石が置かれている場所
     * @param board:オセロのボードの大きさを決めるための多次元配列、基本は8✖︎8の大きさ
     */
    Board_osero(String empty, String black, String white, String[][] board, boolean gamingNow){
        this.empty = empty;
        this.black = black;
        this.white = white;
        this.board = board;
        this.gamingNow = gamingNow;

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

    /**
     * 石を置く時に出力する言葉と石を置く処理をするためのメソッド。
     */
    Scanner scan = new Scanner(System.in);

    public void stonePlace(String stone) {
        System.out.println("このボードは縦軸をx軸、横軸をy軸としており、x座標は上から0、一番下が7であり、y座標は左から0、一番右が7となっている。" +
                "例えば、x座標が3、y座標が2の位置つまり(x,y) = (3,2)の位置に石を置きたい時は、x=3,y=2と入力せよ。");
        System.out.println("x=");
        String xPoint = scan.nextLine();
        System.out.println("y=");
        String yPoint = scan.nextLine();
        System.out.println("(" + Integer.parseInt(xPoint) + "," + Integer.parseInt(yPoint) + ")" + "に" + stone + "が置かれました。");
        board[Integer.parseInt(xPoint)][Integer.parseInt(yPoint)] = stone;
    }

    /**
     * ターン毎のボードの状況を確認するためのメソッド。
     * @param boardNow:インスタンスから生み出した変数が入る。
     */
    public void currentBoard(Board_osero boardNow){
        System.out.println("現在のボードの状況");
        for(int i = 0; i < 8; i++)
        System.out.println(Arrays.toString(boardNow.board[i]));
    }

    public void reverseLeftUp(int x, int y, Board_osero c, String myStone, String enemyStone){
        if(x>1 && y>1){
            String LeftUpStone = c.board[x-1][y-1];

            if (LeftUpStone.equals(enemyStone)){
                for (int i = 2; true; i++){
                    if (x-i < 0 || y-i < 0 || c.board[x-1][y-1].equals(empty)){
                        break;
                    }
                    else if (c.board[x-1][y-1].equals(myStone)){
                        for(int z = 1; z < i; z++){
                            c.board[x-z][y-z] = myStone;
                        }
                        break;
                    }
                }
            }

        }
    }
}

