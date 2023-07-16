package chess;
public class Data {
    public static String[][] board = {
            {"B_R", "B_N", "B_B", "B_Q", "B_K", "B_B", "B_N", "B_R"},
            {"B_P", "B_P", "B_P", "B_P", "B_P", "B_P", "B_P", "B_P"},
            {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
            {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
            {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
            {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
            {"W_P", "W_P", "W_P", "W_P", "W_P", "W_P", "W_P", "W_P"},
            {"W_R", "W_N", "W_B", "W_Q", "W_K", "W_B", "W_N", "W_R"}
    };
    public static final String[] whiteCoins = { "W_R", "W_N", "W_B", "W_Q", "W_K", "W_P"};
    public static final String[] blackCoins = { "B_R", "B_N", "B_B", "B_Q", "B_K", "B_P"};
    public static Player currentPlayer = Player.WHITE;
    public static StringBuilder record = new StringBuilder();
}
