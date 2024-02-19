import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * Chess
 * Designed and created by: Dylan Olmsted
 */
public class Main extends PApplet {
    static PImage boardImage;
    static PImage whitePawn;
    static PImage whiteKing;
    static PImage whiteQueen;
    static PImage whiteKnight;
    static PImage whiteBishop;
    static PImage whiteRook;
    static PImage blackPawn;
    static PImage blackKing;
    static PImage blackQueen;
    static PImage blackKnight;
    static PImage blackBishop;
    static PImage blackRook;
    static PImage selectionSquare;
    static PImage highlightCircle;
    private Square selectedSquare = null;
    final int FILE_A = 73;
    final int FILE_B = FILE_A + 92*1;
    final int FILE_C = FILE_A + 92*2;
    final int FILE_D = FILE_A + 92*3;
    final int FILE_E = FILE_A + 92*4;
    final int FILE_F = FILE_A + 92*5;
    final int FILE_G = FILE_A + 92*6;
    final int FILE_H = FILE_A + 92*7;
    final int RANK_1 = 715;
    final int RANK_2 = RANK_1 - 92*1;
    final int RANK_3 = RANK_1 - 92*2;
    final int RANK_4 = RANK_1 - 92*3;
    final int RANK_5 = RANK_1 - 92*4;
    final int RANK_6 = RANK_1 - 92*5;
    final int RANK_7 = RANK_1 - 92*6;
    final int RANK_8 = RANK_1 - 92*7;
    public static void main(String[] args){
        PApplet.main("Main");
//        System.out.println(Game.INSTANCE.getBoard()[3][1].getPiece().getAllMoves());
//        Game.INSTANCE.makeMove(Game.INSTANCE.getBoard()[3][1].getPiece().getAllMoves().get(0));
    }

    public void settings(){
        size(876, 876);
    }

    public void setup(){
        //background(255, 255, 255);
        boardImage = loadImage("Images/chess_board.png");
        whitePawn = loadImage("Images/white_pawn.png");
        whiteKing = loadImage("Images/white_king.png");
        whiteQueen = loadImage("Images/white_queen.png");
        whiteKnight = loadImage("Images/white_knight.png");
        whiteBishop = loadImage("Images/white_bishop.png");
        whiteRook = loadImage("Images/white_rook.png");
        blackPawn = loadImage("Images/black_pawn.png");
        blackKing = loadImage("Images/black_king.png");
        blackQueen = loadImage("Images/black_queen.png");
        blackKnight = loadImage("Images/black_knight.png");
        blackBishop = loadImage("Images/black_bishop.png");
        blackRook = loadImage("Images/black_rook.png");
        selectionSquare = loadImage("Images/selection_square.png");
        highlightCircle = loadImage("Images/highlight_circle.png");
        whitePawn.resize(85, 85);
        whiteRook.resize(85, 85);
        whiteKnight.resize(85, 85);
        whiteBishop.resize(85, 85);
        whiteKing.resize(85, 85);
        whiteQueen.resize(85, 85);
        blackPawn.resize(85, 85);
        blackRook.resize(85, 85);
        blackKnight.resize(85, 85);
        blackBishop.resize(85, 85);
        blackKing.resize(85, 85);
        blackQueen.resize(85, 85);
        selectionSquare.resize(92, 92);
        highlightCircle.resize(46, 46);
    }

    public void draw(){
        image(boardImage,0,0);
        if(selectedSquare != null){
            image(selectionSquare, FILE_A + 92 * selectedSquare.getX(), RANK_1 - 92 * selectedSquare.getY());
            ArrayList<Move> legalMoves = null;
            legalMoves = selectedSquare.getPiece().getLegalMoves();

            for (Move move: legalMoves) {
                image(highlightCircle, 23 + FILE_A + 92 * move.end.getX(), 23 + RANK_1 - 92 * move.end.getY());
            }
        }

        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                Piece piece = Game.INSTANCE.getBoard()[row][col].getPiece();
                if(piece != null){
                    image(piece.getPieceImage(), FILE_A + row*92, RANK_1 - col*92);
                }
            }
        }
//        image(whitePawn, FILE_A, RANK_2);
//        image(whitePawn, FILE_B, RANK_2);
//        image(whitePawn, FILE_C, RANK_2);
//        image(whitePawn, FILE_D, RANK_2);
//        image(whitePawn, FILE_E, RANK_2);
//        image(whitePawn, FILE_F, RANK_2);
//        image(whitePawn, FILE_G, RANK_2);
//        image(whitePawn, FILE_H, RANK_2);
//
//        image(whiteRook, FILE_A, RANK_1);
//        image(whiteKnight, FILE_B, RANK_1);
//        image(whiteBishop, FILE_C, RANK_1);
//        image(whiteQueen, FILE_D, RANK_1);
//        image(whiteKing, FILE_E, RANK_1);
//        image(whiteBishop, FILE_F, RANK_1);
//        image(whiteKnight, FILE_G, RANK_1);
//        image(whiteRook, FILE_H, RANK_1);
//
//        image(blackPawn, FILE_A, RANK_7);
//        image(blackPawn, FILE_B, RANK_7);
//        image(blackPawn, FILE_C, RANK_7);
//        image(blackPawn, FILE_D, RANK_7);
//        image(blackPawn, FILE_E, RANK_7);
//        image(blackPawn, FILE_F, RANK_7);
//        image(blackPawn, FILE_G, RANK_7);
//        image(blackPawn, FILE_H, RANK_7);
//
//        image(blackRook, FILE_A, RANK_8);
//        image(blackKnight, FILE_B, RANK_8);
//        image(blackBishop, FILE_C, RANK_8);
//        image(blackQueen, FILE_D, RANK_8);
//        image(blackKing, FILE_E, RANK_8);
//        image(blackBishop, FILE_F, RANK_8);
//        image(blackKnight, FILE_G, RANK_8);
//        image(blackRook, FILE_H, RANK_8);
    }

    public void mouseClicked() {
        int selectedRow = -1, selectedCol = -1;
        if (mouseX > FILE_A && mouseX < FILE_B) {
            selectedCol = 0;
        }
        else if (mouseX > FILE_B && mouseX < FILE_C) {
            selectedCol = 1;
        }
        else if (mouseX > FILE_C && mouseX < FILE_D) {
            selectedCol = 2;
        }
        else if (mouseX > FILE_D && mouseX < FILE_E) {
            selectedCol = 3;
        }
        else if (mouseX > FILE_E && mouseX < FILE_F) {
            selectedCol = 4;
        }
        else if (mouseX > FILE_F && mouseX < FILE_G) {
            selectedCol = 5;
        }
        else if (mouseX > FILE_G && mouseX < FILE_H) {
            selectedCol = 6;
        }
        else if (mouseX > FILE_H && mouseX < FILE_H + 92) {
            selectedCol = 7;
        }
        else{
            selectedSquare = null;
            return;
        }

        if (mouseY > RANK_1 && mouseY < RANK_1 + 92) {
            selectedRow = 0;
        }
        else if (mouseY > RANK_2 && mouseY < RANK_1) {
            selectedRow = 1;
        }
        else if (mouseY > RANK_3 && mouseY < RANK_2) {
            selectedRow = 2;
        }
        else if (mouseY > RANK_4 && mouseY < RANK_3) {
            selectedRow = 3;
        }
        else if (mouseY > RANK_5 && mouseY < RANK_4) {
            selectedRow = 4;
        }
        else if (mouseY > RANK_6 && mouseY < RANK_5) {
            selectedRow = 5;
        }
        else if (mouseY > RANK_7 && mouseY < RANK_6) {
            selectedRow = 6;
        }
        else if (mouseY > RANK_8 && mouseY < RANK_7) {
            selectedRow = 7;
        }
        else{
            selectedSquare = null;
            return;
        }

        if(selectedSquare == null || selectedSquare.getPiece() == null) {
            if (selectedRow >= 0 && selectedCol >= 0 && Game.INSTANCE.getBoard()[selectedCol][selectedRow].getPiece() != null && Game.INSTANCE.isWhiteTurn() == Game.INSTANCE.getBoard()[selectedCol][selectedRow].getPiece().isWhite) {
                selectedSquare = Game.INSTANCE.getBoard()[selectedCol][selectedRow];
                System.out.println(selectedSquare.getPiece().getLegalMoves());
            }
        }
        else if(selectedSquare == Game.INSTANCE.getBoard()[selectedCol][selectedRow]){
            selectedSquare = null;
        }
        else{
            Move attemptedMove = new Move(selectedSquare, Game.INSTANCE.getBoard()[selectedCol][selectedRow], selectedSquare.getPiece());
            try {
                if(attemptedMove.isLegal()){
                    // Make move
                    System.out.println("legal");
                    Game.INSTANCE.makeMove(attemptedMove);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            selectedSquare = null;
        }
    }

    public void mouseMoved(){
//        System.out.println("Mouse X: " + mouseX);
//        System.out.println("Mouse Y: " + mouseY);
    }
}
