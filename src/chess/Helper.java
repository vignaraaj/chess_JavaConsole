package chess;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static chess.Data.*;
import static chess.ValidMoves.isValidMove;
import static chess.Player.*;
public class Helper {
    static void printBoard() {
        System.out.println("    a    b    c    d    e    f    g    h ");
        System.out.println("-----------------------------------------");
        for (int i = 0; i <= 7; i++) {
            System.out.print((8-i) + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "  ");
                if(board[i][j]==" ") System.out.print("  ");
            }System.out.println();
        }System.out.println("-----------------------------------------");
    }
    static boolean isValidPosition(String position) {
        if (position.length() != 2) return false;
        char col = position.charAt(0);
        char row = position.charAt(1);
        return col >= 'a' && col <= 'h' && row >= '1' && row <= '8';
    }
    static String getPiece(String position) {
        int col = position.charAt(0) - 'a';
        int row = '8' - position.charAt(1) ;
        return board[row][col];
    }
    static void setPiece(String position, String piece) {
        int col = position.charAt(0) - 'a';
        int row = '8' - position.charAt(1) ;
        board[row][col] = piece;
    }
    static void switchTurn() {
        currentPlayer = currentPlayer.equals(WHITE) ? BLACK : WHITE;
    }
    static String getPossibleMoves(String piece, String fromPos) {
        StringBuilder moves = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String toPos = String.valueOf((char) ('a' + j)) + (i + 1);
                if (isValidMove(piece, fromPos, toPos)) moves.append(toPos).append(" ");
            }
        }
        return moves.toString().trim();
    }
    static List<String> isPositionUnderAttack(String position) {
        List potentialDangerAreas = new ArrayList();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String fromPos = String.valueOf((char) ('a' + j)) + (i + 1);
                String piece = getPiece(fromPos);
                if (isValidMove(piece, fromPos, position)) potentialDangerAreas.add(fromPos);
            }
        }
        return potentialDangerAreas;
    }
    static void possiblePrediction(String position){
        List<String> notSafeAreas = isPositionUnderAttack(position);
        //for(String s:notSafeAreas) System.out.println(s);
        List<String> whiteArea = new ArrayList<>();
        List<String> blackArea = new ArrayList<>();
        for (String s : notSafeAreas) {
            String piece1 = getPiece(s);
            if (Arrays.asList(whiteCoins).contains(piece1)) whiteArea.add(s);
            else blackArea.add(s);
        }
        if (currentPlayer.equals(WHITE)) {
            if (whiteArea.isEmpty()) {
                System.out.println("The place cannot be reached by our white  coins at this time");
                return;
            }
            if(blackArea.isEmpty()) {
                System.out.println("safe place");
                return;
            }
            for (String s1 : blackArea) {
                for (String s2 : whiteArea) {
                    System.out.printf("The %s in %s can capture your %s\n", getPiece(s1), s1, getPiece(s2));
                }
            }
        } else {
            if (blackArea.isEmpty()) {
                System.out.println("The place cannot be reached by our black coins at this time");
                return;
            }
            if(whiteArea.isEmpty()){
                System.out.println("safe place");
                return;
            }
            for (String s1 : blackArea) {
                for (String s2 : whiteArea) {
                    System.out.printf("The %s in %s can capture your %s\n", getPiece(s2), s2, getPiece(s1));
                }
            }
        }
    }
}
