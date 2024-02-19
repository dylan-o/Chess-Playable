import processing.core.PImage;

import java.util.ArrayList;

/**
 * The Knight class is a subclass of the Piece class.
 */
public class Knight extends Piece{
    public Knight(boolean isWhite, Square square){
        this.isWhite = isWhite;
        this.square = square;
    }

    @Override
    public Piece cloneOnNewBoard(Square[][] newBoard) {
        return new Knight(this.isWhite, newBoard[this.square.getX()][this.square.getY()]);
    }

    @Override
    public ArrayList<Square> getSquaresSeen() {
        ArrayList<Square> squaresSeen = new ArrayList<>();

        if(this.square.up() != null){
            if(this.square.up().up() != null){
                if(this.square.up().up().left() != null){
                    squaresSeen.add(this.square.up().up().left());
                }
                if(this.square.up().up().right() != null){
                    squaresSeen.add(this.square.up().up().right());
                }
            }
        }
        if(this.square.left() != null){
            if(this.square.left().left() != null){
                if(this.square.left().left().up() != null){
                    squaresSeen.add(this.square.left().left().up());
                }
                if(this.square.left().left().down() != null){
                    squaresSeen.add(this.square.left().left().down());
                }
            }
        }
        if(this.square.down() != null){
            if(this.square.down().down() != null){
                if(this.square.down().down().left() != null){
                    squaresSeen.add(this.square.down().down().left());
                }
                if(this.square.down().down().right() != null){
                    squaresSeen.add(this.square.down().down().right());
                }
            }
        }
        if(this.square.right() != null){
            if(this.square.right().right() != null){
                if(this.square.right().right().up() != null){
                    squaresSeen.add(square.right().right().up());
                }
                if(this.square.right().right().down() != null){
                    squaresSeen.add(square.right().right().down());
                }
            }
        }
        return squaresSeen;
    }

    @Override
    public String getPieceName() {
        return "Knight";
    }

    @Override
    public PImage getPieceImage() {
        if(isWhite){
            return Main.whiteKnight;
        }
        return Main.blackKnight;
    }
}
