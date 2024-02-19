import processing.core.PImage;

import java.util.ArrayList;

/**
 * The Rook class is a subclass of the Piece class.
 */
public class Rook extends Piece{
    private boolean hasMoved = false;

    public Rook(boolean isWhite, Square square){
        this.isWhite = isWhite;
        this.square = square;
    }

    @Override
    public Piece cloneOnNewBoard(Square[][] newBoard) {
        return new Rook(this.isWhite, newBoard[this.square.getX()][this.square.getY()]);
    }

    @Override
    public ArrayList<Square> getSquaresSeen() {
        ArrayList<Square> squaresSeen = new ArrayList<>();

        boolean searchFurther = true;
        for(int dx = -1; searchFurther; dx--){
            Square square = Game.INSTANCE.findSquare(this.square, dx, 0);
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
            Square square = Game.INSTANCE.findSquare(this.square, dx, 0);
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
        for(int dy = -1; searchFurther; dy--){
            Square square = Game.INSTANCE.findSquare(this.square, 0, dy);
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
        for(int dy = 1; searchFurther; dy++){
            Square square = Game.INSTANCE.findSquare(this.square, 0, dy);
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
        return "Rook";
    }

    @Override
    public PImage getPieceImage() {
        if(isWhite){
            return Main.whiteRook;
        }
        return Main.blackRook;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasMoved() {
        return hasMoved;
    }
}
