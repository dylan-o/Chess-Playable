import processing.core.PImage;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, Square square){
        this.isWhite = isWhite;
        this.square = square;
    }

    @Override
    public ArrayList<Move> getAllMoves() {
        ArrayList<Move> allMoves = new ArrayList<>();

        if (this.getSquareInFront().getPiece() == null) {
            //System.out.println(getPieceName() + square.getSquareName() + this.getSquareInFront().getSquareName());
            allMoves.add(new Move(square, this.getSquareInFront(), this));
            if (square.getY() == (isWhite ? 1 : 6) && Game.INSTANCE.findSquare(square, 0, isWhite ? 2 : -2).getPiece() == null) {
                //System.out.println(getPieceName() + square.getSquareName() + Game.INSTANCE.findSquare(square, 0, isWhite ? 2 : -2).getSquareName());
                allMoves.add(new Move(square, Game.INSTANCE.findSquare(square, 0, isWhite ? 2 : -2), this));
            }
        }
        ArrayList<Square> squaresSeen = this.getSquaresSeen();
        for(Square s: squaresSeen) {
            if(s.getPiece() != null && s.getPiece().isWhite != this.isWhite){
                allMoves.add(new Move(this.square, s, this));
            }
        }

        //en passant
        if (Game.INSTANCE.getGameLength() > 0) {
            Move lastMove = Game.INSTANCE.getGameLog().getLast();
            if (square == lastMove.end.left() || square == lastMove.end.right()) {
                if (lastMove.piece instanceof Pawn && Math.abs(lastMove.start.getY() - lastMove.end.getY()) == 2) {
                    Move enPassant = new Move(square, Game.INSTANCE.findSquare(lastMove.end, 0, (isWhite ? 1 : -1)), this);
                    enPassant.isEnPassant = true;
                    allMoves.add(enPassant);
                }
            }
        }
        return allMoves;
    }

    @Override
    public Piece cloneOnNewBoard(Square[][] newBoard) {
        return new Pawn(this.isWhite, newBoard[this.square.getX()][this.square.getY()]);
    }

    @Override
    public ArrayList<Square> getSquaresSeen() {
        ArrayList<Square> squaresSeen = new ArrayList<>();
        if(this.getSquareInFront().right() != null){
            squaresSeen.add(this.getSquareInFront().right());
        }
        if(this.getSquareInFront().left() != null){
            squaresSeen.add(this.getSquareInFront().left());
        }
        return squaresSeen;
    }

//    @Override
//    public void makeMove(Move move) {
//        System.out.println("Making move: " + getPieceName() + square.getSquareName() + move.end.getSquareName());
//        this.square = move.end;
//        move.end.setPiece(this);
//        move.start.setPiece(null);
//        Game.INSTANCE.addMoveToLog(move);
//
//        if(move.isEnPassant){
//            this.getSquareBehind().setPiece(null);
//        }
//        else if(move.isPromotion){
//            this.square.setPiece(new Queen(isWhite, square));
//        }
//    }

    @Override
    public String getPieceName() {
        return "Pawn";
    }

    @Override
    public PImage getPieceImage() {
        if(this.isWhite){
            return Main.whitePawn;
        }
        return Main.blackPawn;
    }

    private Square getSquareInFront(){
        if(this.isWhite){
            return this.square.up();
        }
        return this.square.down();
    }

    private Square getSquareBehind(){
        if(this.isWhite){
            return this.square.down();
        }
        return this.square.up();
    }
}
