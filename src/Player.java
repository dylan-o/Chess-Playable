import java.util.ArrayList;

public abstract class Player {
    boolean isPlayingWhite;
    public abstract void turn();
    public ArrayList<Piece> pieces;
    public Player(boolean isPlayingWhite){
        this.isPlayingWhite = isPlayingWhite;
        pieces = new ArrayList<>();
    }

    public boolean isInCheck(Square[][] board){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Square square = board[i][j];
                if(square.getPiece() != null && square.getPiece().isWhite != isPlayingWhite){
                    for (Square s: square.getPiece().getSquaresSeen()) {
                        if(s.getPiece() instanceof King && s.getPiece().isWhite == isPlayingWhite){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
