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

    public void stonePlace(String myStone, String enemyStone, Board_osero c) {
        System.out.println("このボードは縦軸をx軸、横軸をy軸としており、x座標は上から0、一番下が7であり、y座標は左から0、一番右が7となっている。" +
                "例えば、x座標が3、y座標が2の位置つまり(x,y) = (3,2)の位置に石を置きたい時は、x=3,y=2と入力せよ。");
        System.out.println(myStone + "のターン");
        System.out.println("x=");
        String xPoint = scan.nextLine();
        System.out.println("y=");
        String yPoint = scan.nextLine();
        if (Integer.parseInt(xPoint) < 0 || Integer.parseInt(yPoint) < 0 || Integer.parseInt(xPoint) > 7 || Integer.parseInt(xPoint) > 7 || c.board[Integer.parseInt(xPoint)][Integer.parseInt(yPoint)] != empty){
            System.out.println("※この場所に石を置くことはできません。");
        }

        else {
            System.out.println("(" + Integer.parseInt(xPoint) + "," + Integer.parseInt(yPoint) + ")" + "に" + myStone + "が置かれました。");
            board[Integer.parseInt(xPoint)][Integer.parseInt(yPoint)] = myStone;

            c.reverseLeft(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
            c.reverseLeftUp(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
            c.reverseUp(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
            c.reverseRightUp(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
            c.reverseRight(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
            c.reverseRightDown(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
            c.reverseDown(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
            c.reverseLeftDown(Integer.parseInt(xPoint), Integer.parseInt(yPoint), c, myStone, enemyStone);
        }
    }

    /**
     * ターン毎のボードの状況を確認するためのメソッド。
     * @param c:インスタンスから生み出した変数
     */
    public void currentBoard(Board_osero c){
        System.out.println("現在の状況");
        for(int i = 0; i < 8; i++)
        System.out.println(Arrays.toString(c.board[i]));
    }



    /**
     * ターン毎に両チームの石の合計数を数え、全てのemptyが埋まると試合終了を告げるメソッド。
     * @param c:インスタンスから生み出した変数
     */
    public void calculateFinishGame(Board_osero c){
        int blackStoneNumber = 0;
        int whiteStoneNumber = 0;
        for (int a = 0; a < 8; a++){
            for (int b = 0; b < 8; b++){
                if (c.board[a][b] == black){
                    blackStoneNumber += 1;
                }
                if (c.board[a][b] == white){
                    whiteStoneNumber += 1;
                }
            }
        }

        if (blackStoneNumber + whiteStoneNumber == 64){
            System.out.println("ゲームが終了しました。");
            System.out.println("黒石" + ":" + blackStoneNumber);
            System.out.println("白石" + ":" + whiteStoneNumber);
            if (blackStoneNumber > whiteStoneNumber){
                System.out.println("漆黒悪魔 サターニャの勝利！");
            }
            if (whiteStoneNumber > blackStoneNumber){
                System.out.println("白天使 ガヴリールの勝利！");
            }

            c.gamingNow = false;
        }

        else{
            System.out.println("黒石" + ":" + blackStoneNumber);
            System.out.println("白石" + ":" + whiteStoneNumber);
        }

    }

    /**
     * 左上方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseLeftUp(int x, int y, Board_osero c, String myStone, String enemyStone){
        if(x>1 && y>1){
            String LeftUpStone = c.board[x-1][y-1];

            if (LeftUpStone.equals(enemyStone)){
                for (int i = 2; true; i++){
                    if (x-i < 0 || y-i < 0 || c.board[x-i][y-i].equals(empty)){
                        break;
                    }
                    else if (c.board[x-i][y-i].equals(myStone)){
                        for(int z = 1; z < i; z++){
                            c.board[x-z][y-z] = myStone;
                        }
                        break;
                    }
                }
            }

        }
    }

    /**
     * 上方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseUp(int x, int y, Board_osero c, String myStone, String enemyStone){
        if (x>1){
            String upStone = c.board[x-1][y];

            if (upStone.equals(enemyStone)){
                for (int i=2; true; i++){
                    if (x-i < 0 || c.board[x-i][y].equals(empty)){
                        break;
                    }
                    else if (c.board[x-i][y].equals(myStone)){
                        for (int z = 1; z < i; z++){
                            c.board[x-z][y] = myStone;
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 下方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseDown(int x, int y, Board_osero c, String myStone, String enemyStone){
        if (x<6){
            String downStone = c.board[x+1][y];

            if (downStone.equals(enemyStone)){
                for (int i=2; true; i++){
                    if (x+i > 7 || c.board[x+i][y].equals(empty)){
                        break;
                    }
                    else if (c.board[x+i][y].equals(myStone)){
                        for (int z = 1; z < i; z++){
                            c.board[x+z][y] = myStone;
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 右上方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseRightUp(int x, int y, Board_osero c, String myStone, String enemyStone){
        if (x>1 && y<6){
            String RightUpStone = c.board[x-1][y+1];

            if (RightUpStone.equals(enemyStone)){
                for (int i=2; true; i++){
                    if (x-i < 0 || y+i > 7 || c.board[x-i][y+i].equals(empty)){
                        break;
                    }
                    else if (c.board[x-i][y+1].equals(myStone)){
                        for (int z = 1; z < i; z++){
                            c.board[x-z][y+z] = myStone;
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 右方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseRight(int x, int y, Board_osero c, String myStone, String enemyStone){
        if (y<6){
            String rightStone = c.board[x][y+1];

            if (rightStone.equals(enemyStone)){
                for (int i=2; true; i++){
                    if (y+i > 7 || c.board[x][y+i].equals(empty)){
                        break;
                    }
                    else if (c.board[x][y+1].equals(myStone)){
                        for (int z = 1; z < i; z++){
                            c.board[x][y+z] = myStone;
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 左下方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseLeftDown(int x, int y, Board_osero c, String myStone, String enemyStone){
        if (x<6 && y>1){
            String LeftDownStone = c.board[x+1][y-1];

            if (LeftDownStone.equals(enemyStone)){
                for (int i=2; true; i++){
                    if (x+i > 7 || y-i < 0 || c.board[x+i][y-i].equals(empty)){
                        break;
                    }
                    else if (c.board[x+i][y-i].equals(myStone)){
                        for (int z = 1; z < i; z++){
                            c.board[x+z][y-z] = myStone;
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 左方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseLeft(int x, int y, Board_osero c, String myStone, String enemyStone){
        if (y>1){
            String LeftStone = c.board[x][y-1];

            if (LeftStone.equals(enemyStone)){
                for (int i=2; true; i++){
                    if (y-i < 0 || c.board[x][y-i].equals(empty)){
                        break;
                    }
                    else if (c.board[x][y-i].equals(myStone)){
                        for (int z = 1; z < i; z++){
                            c.board[x][y-z] = myStone;
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 右下方向の石をひっくり返すためのメソッド。
     * @param x:プレイヤーが入力したx座標
     * @param y:プレイヤーが入力したy座標
     * @param c:インスタンスから生み出した変数
     * @param myStone:自チームの石
     * @param enemyStone:敵チームの石
     */
    public void reverseRightDown(int x, int y, Board_osero c, String myStone, String enemyStone){
        if (x<6 && y<6){
            String rightDownStone = c.board[x+1][y+1];

            if (rightDownStone.equals(enemyStone)){
                for (int i=2; true; i++){
                    if (x+i > 7 || y+i > 7 || c.board[x+i][y+i].equals(empty)){
                        break;
                    }
                    else if (c.board[x+i][y+1].equals(myStone)){
                        for (int z = 1; z < i; z++){
                            c.board[x+z][y+z] = myStone;
                        }
                        break;
                    }
                }
            }
        }
    }


}


