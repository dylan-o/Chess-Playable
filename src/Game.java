import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    public static final Game INSTANCE = new Game();
    private boolean whiteTurn = true;
    private Player white;
    private Player black;
    private LinkedList<Move> gameLog;
    private final Square[][] board = new Square[8][8];
    public Game(){
        init();
        gameLog = new LinkedList<>();
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Square[][] getBoard() {
        return board;
    }

    public Square[][] previewMove(Move move){
        Square[][] boardCopy = deepCopy2DArray(board);
        Piece pieceCopy = move.piece.cloneOnNewBoard(boardCopy);
        pieceCopy.square = boardCopy[move.end.getX()][move.end.getY()];

        boardCopy[move.end.getX()][move.end.getY()].setPiece(pieceCopy);
        boardCopy[move.start.getX()][move.start.getY()].setPiece(null);

        //System.out.println("Previewing move: " + move.piece.getPieceName() + move.start.getSquareName() + move.end.getSquareName());
        return boardCopy;
    }

    public void makeMove(Move move){
        System.out.println("Making move: " + move.piece.getPieceName() + move.start.getSquareName() + move.end.getSquareName());
        move.piece.square = move.end;
        move.end.setPiece(move.piece);
        move.start.setPiece(null);
        whiteTurn = !whiteTurn;
        System.out.println(checkForGameOver());
        addMoveToLog(move);
        if(move.isEnPassant){
            Game.INSTANCE.findSquare(move.end, 0, move.piece.isWhite ? -1 : 1).setPiece(null);
        }
        else if(move.isPromotion){
            move.end.setPiece(new Queen(move.piece.isWhite, move.end));
        }
        else if(move.isCastling().equals("queenside")){
            Rook queenSideRook = (Rook) move.end.left().left().getPiece();
            queenSideRook.square = move.end.right();
            queenSideRook.setHasMoved(true);
            move.end.right().setPiece(queenSideRook);
            move.end.left().left().setPiece(null);
        }
        else if(move.isCastling().equals("kingside")){
            Rook kingSideRook = (Rook) move.end.right().getPiece();
            kingSideRook.square = move.end.left();
            kingSideRook.setHasMoved(true);
            move.end.left().setPiece(kingSideRook);
            move.end.right().setPiece(null);
        }
        System.out.println("Castle value: " + move.isCastling());
        if(move.piece instanceof King){
            ((King) move.piece).setHasMoved(true);
        }
        if(move.piece instanceof Rook){
            ((Rook) move.piece).setHasMoved(true);
        }
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = board[i][j];
                if (square.getPiece() != null && square.getPiece() instanceof King) {
                    if (((King) square.getPiece()).isInCheck(board)) {
                        System.out.println("Check");
                    }
                }
            }
        }
    }
    public LinkedList<Move> getGameLog() {
        return gameLog;
    }
    public Player getBlack() {
        return black;
    }
    public Player getWhite() {
        return white;
    }
    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void addMoveToLog(Move move){
        gameLog.add(move);
    }

    public void init(){
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
    public int getGameLength(){
        return gameLog.size();
    }

    public boolean isInCheckAfterMove(Move move) {
        Square[][] boardAfterMove = this.previewMove(move);
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(boardAfterMove[col][row].getPiece() instanceof King){
                    King king = (King) boardAfterMove[col][row].getPiece();
                    if(king.isWhite == whiteTurn){
                        return king.isInCheck(boardAfterMove);
                    }
                }
            }
        }
        System.out.println("No king found");
        return false;
    }

    public Square findSquare(Square startSquare, int dx, int dy){
        Square[][] domain = startSquare.getBoard();
        if(startSquare.getX() + dx < 0 || startSquare.getX() + dx > 7 || startSquare.getY() + dy < 0 || startSquare.getY() + dy > 7){
            return null;
        }
        return domain[startSquare.getX() + dx][startSquare.getY() + dy];
    }

    // Method to perform a deep copy of a 2D array with objects 
    private static Square[][] deepCopy2DArray(Square[][] originalArray) {
        int rows = originalArray.length;
        int cols = originalArray[0].length;

        Square[][] copiedArray = new Square[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create a new instance of the object for each element 
                copiedArray[i][j] = new Square(originalArray[i][j].getX(), originalArray[i][j].getY(), copiedArray);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create a new instance of the object for each element
                if(originalArray[i][j].getPiece() != null){
                    copiedArray[i][j].setPiece(originalArray[i][j].getPiece().cloneOnNewBoard(copiedArray));
                }
            }
        }
        return copiedArray;
    }

    public String checkForGameOver(){
        King playerKing = null;
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(board[col][row].getPiece() != null){
                    Piece piece = board[col][row].getPiece();
                    if(piece.isWhite == whiteTurn){
                        if(piece instanceof King){
                            playerKing = (King) piece;
                        }

                        ArrayList<Move> moves = piece.getLegalMoves();
                        for(Move move : moves){
                            if(!isInCheckAfterMove(move)){
                                return "";
                            }
                        }
                    }
                }
            }
        }
        if(playerKing.isInCheck(board)){
            if(whiteTurn){
                return "Black wins";
            }
            else {
                return "White wins";
            }
        }
        else {
            return "Stalemate";
        }
    }
}
