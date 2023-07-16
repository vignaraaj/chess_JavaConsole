package chess;
import java.util.Arrays;
import static chess.Data.*;
import static chess.Player.*;
public class ValidMoves {
    static boolean isValidMove(String piece, String fromPos, String toPos) {
        int fromCol = fromPos.charAt(0) - 'a';
        int fromRow =  '8' - fromPos.charAt(1);
        int toCol = toPos.charAt(0) - 'a';
        int toRow = '8' - toPos.charAt(1) ;
        switch (piece) {
            case "W_P" :
            case "B_P" :
                return isValidPawnMove(fromCol, fromRow, toCol, toRow);
            case "W_R" :
            case "B_R" :
                return isValidRookMove(fromCol, fromRow, toCol, toRow);
            case "W_N" :
            case "B_N" :
                return isValidKnightMove(fromCol, fromRow, toCol, toRow);
            case "W_B" :
            case "B_B" :
                return isValidBishopMove(fromCol, fromRow, toCol, toRow);
            case "W_Q" :
            case "B_Q" :
                return isValidQueenMove(fromCol, fromRow, toCol, toRow);
            case "W_K" :
            case "B_K" :
                return isValidKingMove(fromCol, fromRow, toCol, toRow);
            default : return false;
        }
    }
    static boolean isValidPawnMove(int fromCol, int fromRow, int toCol, int toRow) {
        String piece = board[fromRow][fromCol];
        int direction = piece.equals("W_P") ? -1 : 1;
        if (fromCol == toCol) {
            if (fromRow + direction == toRow && board[toRow][toCol] == "   ") return true;
            if (fromRow + (2 * direction) == toRow && fromRow == (direction == 1 ? 1 : 6) && board[toRow][toCol] == "   " && board[fromRow + direction][toCol] == "   ") return true;
        } else {
            if (fromRow + direction == toRow && Math.abs(fromCol - toCol) == 1 && board[toRow][toCol]!="   ") return true;
        }
        return false;
    }
    static boolean isValidRookMove(int fromCol, int fromRow, int toColumn, int toRow) {
        if (fromCol != toColumn && fromRow != toRow) return false;
        if (fromCol == toColumn && fromRow == toRow) return false;
        int colStep = Integer.compare(toColumn, fromCol);
        int rowStep = Integer.compare(toRow, fromRow);
        if(currentPlayer.equals(WHITE) && Arrays.asList(whiteCoins).contains(board[toRow][toColumn])) return false;
        if(currentPlayer.equals(BLACK) && Arrays.asList(blackCoins).contains(board[toRow][toColumn])) return false;
        int currCol = fromCol + colStep ;
        int currRow = fromRow + rowStep ;
        while (currCol != toColumn || currRow != toRow) {
            if (board[currRow][currCol] != "   ") return false;
            currCol += colStep;
            currRow += rowStep;
        }return true;
    }
    static boolean isValidKnightMove(int fromCol, int fromRow, int toCol, int toRow) {
        int colDiff = Math.abs(fromCol - toCol);
        int rowDiff = Math.abs(fromRow - toRow);
        if(currentPlayer.equals(WHITE) && Arrays.asList(whiteCoins).contains(board[toRow][toCol])) return false;
        if(currentPlayer.equals(BLACK) && Arrays.asList(blackCoins).contains(board[toRow][toCol])) return false;
        return (colDiff == 2 && rowDiff == 1) || (colDiff == 1 && rowDiff == 2);
    }
    static boolean isValidBishopMove(int fromCol, int fromRow, int toCol, int toRow) {
        int colDiff = Math.abs(fromCol - toCol);
        int rowDiff = Math.abs(fromRow - toRow);
        if (colDiff != rowDiff) return false;
        if (colDiff == 0 && rowDiff == 0) return false;
        int colStep = Integer.compare(toCol,fromCol);
        int rowStep = Integer.compare(toRow,fromRow);
        if(currentPlayer.equals(WHITE) && Arrays.asList(whiteCoins).contains(board[toRow][toCol])) return false;
        if(currentPlayer.equals(BLACK) && Arrays.asList(blackCoins).contains(board[toRow][toCol])) return false;
        int currCol = fromCol + colStep;
        int currRow = fromRow + rowStep;
        while (currCol != toCol || currRow != toRow) {
            if (board[currRow][currCol] != "   ")  return false;
            currCol += colStep;
            currRow += rowStep;
        }
        return true;
    }
    static boolean isValidQueenMove(int fromCol, int fromRow, int toCol, int toRow) {
        return isValidRookMove(fromCol, fromRow, toCol, toRow) || isValidBishopMove(fromCol, fromRow, toCol, toRow);
    }
    static boolean isValidKingMove(int fromCol, int fromRow, int toCol, int toRow) {
        int colDiff = Math.abs(fromCol - toCol);
        int rowDiff = Math.abs(fromRow - toRow);
        if(currentPlayer.equals(WHITE) && Arrays.asList(whiteCoins).contains(board[toRow][toCol])) return false;
        if(currentPlayer.equals(BLACK) && Arrays.asList(blackCoins).contains(board[toRow][toCol])) return false;
        return (colDiff == 1 && rowDiff == 0) || (colDiff == 0 && rowDiff == 1) || (colDiff == 1 && rowDiff == 1);
    }
}
