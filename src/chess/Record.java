package chess;
import java.io.FileWriter;
import java.io.IOException;
import static chess.Data.record;
import static chess.Helper.getPiece;
public class Record {
    static void recordMove(Player player, String piece, String fromPos, String toPos) {
        record.append(player).append(" ").append(piece).append(" at ").append(fromPos).append(" has been moved to ").append(toPos).append("\n");
    }
    static void recordCapture(Player player, String piece, String fromPos, String toPos) {
        record.append(player).append(" ").append(piece).append(" at ").append(fromPos).append(" has captured  ").append(getPiece(toPos)).append(" at ").append(toPos).append("\n");
    }
    static void saveFile() {
        try (FileWriter fileWriter = new FileWriter("chess_record.txt")) {
            fileWriter.write(record.toString());
            System.out.println("Record saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the record: " + e.getMessage());
        }
    }
}
