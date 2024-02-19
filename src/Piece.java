import processing.core.PImage;

import java.util.ArrayList;

/**
 * Abstract class representing a chess piece.
 * This class is extended by the specific piece classes (Pawn, Rook, Knight, Bishop, Queen, King).
 * This class contains methods that are common to all pieces.
 */
public abstract class Piece {
    boolean isWhite;
    Square square;

    /**
     * Returns a new piece of the same type on a different board.
     * This is used to clone the board to check potential moves.
     * @param newBoard The new board to place the piece on,
     * typically a clone of the current board made with Game.deepCopy2DArray(Square[][] originalArray).
     *
     * @return A new piece of the same type in the same position on newBoard.
     */
    public abstract Piece cloneOnNewBoard(Square[][] newBoard);

    /**
     * Returns a list of all the squares that the piece can see.
     * This is used to generate a list of possible moves.
     * Also used to check if a King is in check.
     * @return A list of all the squares that the piece can see.
     */
    public abstract ArrayList<Square> getSquaresSeen();

    public abstract String getPieceName();
    public abstract PImage getPieceImage();

    /**
     * Returns a list of all the moves that the piece can make.
     * This is used to generate a list of possible moves.
     *
     * Note: This method does not check if the move would put the King in check.
     *
     * @return A list of all the moves that the piece can make.
     */
    public ArrayList<Move> getAllMoves() {
        ArrayList<Move> allMoves = new ArrayList<>();
        ArrayList<Square> squaresSeen = this.getSquaresSeen();
        for(Square s: squaresSeen) {
            if(s.getPiece() == null || s.getPiece().isWhite != this.isWhite){
                allMoves.add(new Move(this.square, s, this));
            }
        }
        for (int i = 0; i < allMoves.size(); i++) {
            if(allMoves.get(i).end.getPiece() != null && allMoves.get(i).start.getPiece().isWhite == allMoves.get(i).end.getPiece().isWhite){
                allMoves.remove(i);
                i--;
            }
        }
        return allMoves;
    }

    /**
     * Returns a list of all the legal moves that the piece can make.
     * This is used to generate a list of legal moves.
     *
     * @return A list of all the legal moves that the piece can make.
     */
    public ArrayList<Move> getLegalMoves() {
        ArrayList<Move> allMoves = this.getAllMoves();

        for (int i = 0; i < allMoves.size(); i++) {
            if(Game.INSTANCE.isInCheckAfterMove(allMoves.get(i))){
                allMoves.remove(i);
                i--;
            }
        }
        return allMoves;
    }
}
