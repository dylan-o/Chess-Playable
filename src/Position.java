public class Position extends Exception{
    Square[][] board;
    public Position(){
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                board[col][row] = new Square(col, row, board);
            }
        }
        board[0][0].setPiece(new Rook(true, board[0][0]));
        board[1][0].setPiece(new Knight(true, board[1][0]));
        board[2][0].setPiece(new Bishop(true, board[2][0]));
        board[3][0].setPiece(new Queen(true, board[3][0]));
        board[4][0].setPiece(new King(true, board[4][0]));
        board[5][0].setPiece(new Bishop(true, board[5][0]));
        board[6][0].setPiece(new Knight(true, board[6][0]));
        board[7][0].setPiece(new Rook(true, board[7][0]));
        board[0][1].setPiece(new Pawn(true, board[0][1]));
        board[1][1].setPiece(new Pawn(true, board[1][1]));
        board[2][1].setPiece(new Pawn(true, board[2][1]));
        board[3][1].setPiece(new Pawn(true, board[3][1]));
        board[4][1].setPiece(new Pawn(true, board[4][1]));
        board[5][1].setPiece(new Pawn(true, board[5][1]));
        board[6][1].setPiece(new Pawn(true, board[6][1]));
        board[7][1].setPiece(new Pawn(true, board[7][1]));

        board[0][7].setPiece(new Rook(false, board[0][7]));
        board[1][7].setPiece(new Knight(false, board[1][7]));
        board[2][7].setPiece(new Bishop(false, board[2][7]));
        board[3][7].setPiece(new Queen(false, board[3][7]));
        board[4][7].setPiece(new King(false, board[4][7]));
        board[5][7].setPiece(new Bishop(false, board[5][7]));
        board[6][7].setPiece(new Knight(false, board[6][7]));
        board[7][7].setPiece(new Rook(false, board[7][7]));
        board[0][6].setPiece(new Pawn(false, board[0][6]));
        board[1][6].setPiece(new Pawn(false, board[1][6]));
        board[2][6].setPiece(new Pawn(false, board[2][6]));
        board[3][6].setPiece(new Pawn(false, board[3][6]));
        board[4][6].setPiece(new Pawn(false, board[4][6]));
        board[5][6].setPiece(new Pawn(false, board[5][6]));
        board[6][6].setPiece(new Pawn(false, board[6][6]));
        board[7][6].setPiece(new Pawn(false, board[7][6]));
    }
    public Position(Square[][] pos){
        board = pos;
    }
    public int evaluate(){
        //TODO
        return 0;
    }
}
