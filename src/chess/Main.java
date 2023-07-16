package chess;
import java.util.Arrays;
import java.util.Scanner;
import static chess.Data.*;
import static chess.Helper.*;
import static chess.Record.*;
import static chess.Player.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(currentPlayer + "'s turn:");
            printBoard();
            System.out.print("Choose Coin , 'exit' to quit, 'print' to show the board, or '<Position> --help' for help : ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                System.out.println("Exiting the game.");
                break;
            }
            else if (command.equals("print")) {
                printBoard();
                continue;
            }
            else if (command.endsWith("--help")) {
                String position = command.split(" ")[0];
                if (isValidPosition(position)) {
                    String myPiece = getPiece(position);
                    if((currentPlayer.equals(WHITE) && Arrays.asList(whiteCoins).contains(myPiece)) ||
                            (currentPlayer.equals(BLACK) && Arrays.asList(blackCoins).contains(myPiece))){
                        System.out.println("The place is already filled with your coin...");
                    }
                    else possiblePrediction(position);
                } else System.out.println("Invalid position.");
                continue;
            }
            if (!isValidPosition(command)) {
                System.out.println("Invalid position. Try again.");
                continue;
            }
            String fromPos = command;
            String piece = getPiece(fromPos);
            if (piece == "   ") {
                System.out.println("No piece at the selected position. Try again.");
                continue;
            }
            if(currentPlayer.equals(WHITE) && Arrays.asList(blackCoins).contains(piece)){
                System.out.println("You chose a black coin. Try again.");
                continue;
            }
            if(currentPlayer.equals(BLACK) && Arrays.asList(whiteCoins).contains(piece)){
                System.out.println("You chose a white coin. Try again.");
                continue;
            }
            String possibleMoves = getPossibleMoves(piece, fromPos);
            System.out.println("The current type of coin : " + piece);
            System.out.println("The next set of positions the particular coin can move : " + possibleMoves);
            System.out.print("Move Coin to : ");
            String toPos = scanner.nextLine();
            if (!possibleMoves.contains(toPos)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            if (getPiece(toPos) != "   ") recordCapture(currentPlayer, piece, fromPos, toPos);
            else recordMove(currentPlayer, piece, fromPos, toPos);
            if(getPiece(toPos).equals("W_K")) {
                System.out.println("Black wins");
                saveFile();
                return;
            }
            if(getPiece(toPos).equals("B_K")) {
                System.out.println("White wins");
                saveFile();
                return;
            }
            setPiece(toPos, piece);
            setPiece(fromPos, "   ");
            switchTurn();
        }
        saveFile();
    }
}
