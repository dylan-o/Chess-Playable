import processing.core.PImage;

import java.util.ArrayList;
/**
 * The Bishop class is a subclass of the Piece class.
 */
public class Bishop extends Piece{
    public Bishop(boolean isWhite, Square square){
        this.isWhite = isWhite;
        this.square = square;
    }

    @Override
    public Piece cloneOnNewBoard(Square[][] newBoard) {
        return new Bishop(this.isWhite, newBoard[this.square.getX()][this.square.getY()]);
    }

    @Override
    public ArrayList<Square> getSquaresSeen() {
        ArrayList<Square> squaresSeen = new ArrayList<>();

        boolean searchFurther = true;
        for(int dx = 1; searchFurther; dx++){
            Square square = Game.INSTANCE.findSquare(this.square, dx, dx);
            if(square == null){
                searchFurther = false;
            }
            else{
                squaresSeen.add(square);
                if(square.getPiece() != null){
                    searchFurther = false;
                }
            }
        }

        searchFurther = true;
        for(int dx = 1; searchFurther; dx++){
            Square square = Game.INSTANCE.findSquare(this.square, -dx, dx);
            if(square == null){
                searchFurther = false;
            }
            else{
                squaresSeen.add(square);
                if(square.getPiece() != null){
                    searchFurther = false;
                }
            }
        }

        searchFurther = true;
        for(int dx = 1; searchFurther; dx++){
            Square square = Game.INSTANCE.findSquare(this.square, dx, -dx);
            if(square == null){
                searchFurther = false;
            }
            else{
                squaresSeen.add(square);
                if(square.getPiece() != null){
                    searchFurther = false;
                }
            }
        }

        searchFurther = true;
        for(int dx = 1; searchFurther; dx++){
            Square square = Game.INSTANCE.findSquare(this.square, -dx, -dx);
            if(square == null){
                searchFurther = false;
            }
            else{
                squaresSeen.add(square);
                if(square.getPiece() != null){
                    searchFurther = false;
                }
            }
        }

        return squaresSeen;
    }

    @Override
    public String getPieceName() {
        return "Bishop";
    }

    @Override
    public PImage getPieceImage() {
        if(isWhite){
            return Main.whiteBishop;
        }
        return Main.blackBishop;
    }
}
