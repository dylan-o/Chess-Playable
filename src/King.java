import processing.core.PImage;

import java.util.ArrayList;

public class King extends Piece{
    private boolean hasMoved = false;

    public King(boolean isWhite, Square square){
        this.isWhite = isWhite;
        this.square = square;
    }

    @Override
    public Piece cloneOnNewBoard(Square[][] newBoard) {
        return new King(this.isWhite, newBoard[this.square.getX()][this.square.getY()]);
    }

    @Override
    public ArrayList<Square> getSquaresSeen() {
        ArrayList<Square> squaresSeen = new ArrayList<>();
        for(int dx = -1; dx < 2; dx++){
            for(int dy = -1; dy < 2; dy++){
                if(dx == 0 && dy == 0){
                    dy++;
                }
                Square square = Game.INSTANCE.findSquare(this.square, dx, dy);
                if(square != null){
                    squaresSeen.add(square);
                }
            }
        }
        return squaresSeen;
    }

    @Override
    public String getPieceName() {
        return "King";
    }

    // must test, written by copilot
    public boolean isInCheck(Square[][] board){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Square square = board[i][j];
                if(square.getPiece() != null && square.getPiece().isWhite != isWhite){
                    for (Square s: square.getPiece().getSquaresSeen()) {
                        if(s.getPiece() instanceof King && s.getPiece().isWhite == isWhite){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public PImage getPieceImage() {
        if(isWhite){
            return Main.whiteKing;
        }
        return Main.blackKing;
    }

    @Override
    public ArrayList<Move> getAllMoves(){
        // get all moves as usual
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

        //CASTLING
        if(!hasMoved && !isInCheck(this.square.getBoard())){

            // QUEENSIDE CASTLING:
            //check for empty squares that aren't attacked between king and rook

            boolean canCastleQueenside = true;
            ArrayList<Square> squaresBetween = new ArrayList<>();
            squaresBetween.add(this.square.left());
            squaresBetween.add(this.square.left().left());
            squaresBetween.add(this.square.left().left().left());
            for(Square s: squaresBetween){
                if(s.getPiece() == null){
                    Move fakeMove = new Move(this.square, s, this);
                    if(Game.INSTANCE.isInCheckAfterMove(fakeMove)){
                        canCastleQueenside = false;
                    }
                }
                else{
                    canCastleQueenside = false;
                }
            }

            if(canCastleQueenside){
                Piece queenSideRook = this.square.left().left().left().left().getPiece();
                //check for rook of same color that hasn't moved
                if(queenSideRook instanceof Rook && queenSideRook.isWhite == this.isWhite && !((Rook) queenSideRook).hasMoved()){
                    Move queensideCastle = new Move(this.square, this.square.left().left(), this);
                    queensideCastle.setIsCastling("queenside");
                    allMoves.add(queensideCastle);
                }
            }

            //KINGSIDE CASTLING:
            //check for empty squares that aren't attacked between king and rook

            boolean canCastleKingside = true;
            squaresBetween = new ArrayList<>();
            squaresBetween.add(this.square.right());
            squaresBetween.add(this.square.right().right());
            for(Square s: squaresBetween){
                if(s.getPiece() == null){
                    Move fakeMove = new Move(this.square, s, this);
                    if(Game.INSTANCE.isInCheckAfterMove(fakeMove)){
                        canCastleKingside = false;
                    }
                }
                else{
                    canCastleKingside = false;
                }
            }

            if(canCastleKingside){
                Piece kingSideRook = this.square.right().right().right().getPiece();
                //check for rook of same color that hasn't moved
                if(kingSideRook instanceof Rook && kingSideRook.isWhite == this.isWhite && !((Rook) kingSideRook).hasMoved()){
                    Move kingsideCastle = new Move(this.square, this.square.right().right(), this);
                    kingsideCastle.setIsCastling("kingside");
                    allMoves.add(kingsideCastle);
                }
            }
        }
        return allMoves;
    }
}
