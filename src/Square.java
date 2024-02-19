/**
 * Square class is used to represent a square on the board.
 * It has a piece, a position, and a board.
 */
public class Square {
    private int x;
    private int y;

    private Square[][] board;
    private Piece piece;

    public Square(int x, int y, Square[][] board){
        this.x = x;
        this.y = y;
        this.piece = null;
        this.board = board;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Piece getPiece() {
        return piece;
    }
    public String getSquareName(){
        switch(x){
            case 0:
                return "A" + Integer.toString(y + 1);
            case 1:
                return "B" + Integer.toString(y + 1);
            case 2:
                return "C" + Integer.toString(y + 1);
            case 3:
                return "D" + Integer.toString(y + 1);
            case 4:
                return "E" + Integer.toString(y + 1);
            case 5:
                return "F" + Integer.toString(y + 1);
            case 6:
                return "G" + Integer.toString(y + 1);
            case 7:
                return "H" + Integer.toString(y + 1);
        }
        return null;
    }

    public Square up(){
        if(y==7){
            return null;
        }
        return board[x][y+1];
    }
    public Square down(){
        if(y==0){
            return null;
        }
        return board[x][y-1];
    }
    public Square left(){
        if(x==0){
            return null;
        }
        return board[x-1][y];
    }
    public Square right(){
        if(x==7){
            return null;
        }
        return board[x+1][y];
    }

    public Square[][] getBoard() {
        return board;
    }
}
