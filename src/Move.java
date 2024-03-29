import java.util.ArrayList;

/**
 * Represents a move in the game of chess.
 * A move is defined by the square the piece is moving from, the square the piece is moving to, and the piece being moved.
 * Also contains information about whether the move is a special move, like a promotion, castling, or en passant.
 */
public class Move {
    Square start;
    Square end;
    Piece piece;
    boolean isPromotion = false;
    boolean isEnPassant = false;
    private String isCastling = "";

    public Move(Square start, Square end, Piece piece){
        this.start = start;
        this.end = end;
        this.piece = piece;
        if(this.piece instanceof Pawn && this.end.getY() == (this.piece.isWhite ? 7 : 0)){
            isPromotion = true;
        }
    }

    public boolean isLegal() throws CloneNotSupportedException {
        ArrayList<Move> legalMoves = piece.getLegalMoves();
        if(legalMoves.isEmpty()){
            return false;
        }
        for (Move move: legalMoves) {
            if(move.start == this.start && move.end == this.end){
                this.isEnPassant = move.isEnPassant;
                this.isPromotion = move.isPromotion;
                this.isCastling = move.isCastling;
                return true;
            }
        }
        return false;
    }

    public String isCastling() {
        return isCastling;
    }

    public void setIsCastling(String isCastling) {
        this.isCastling = isCastling;
    }
}
