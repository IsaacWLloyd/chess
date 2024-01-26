package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
/**
 *
 * PieceMoveCalculator is an interface to implement move calculators for each piece type
 *
 *
 * **/
public interface PieceMoveCalculator {
    Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition);
    /**
     * checks if a move is valid and sets the capture flag when the move is a capture
     * @param board a chessboard object
     * @param move the move to test
     *
     * **/
    default boolean checkMove(ChessBoard board, ChessMove move) {
        if(isOnBoard(board, move.getEndPosition()) &&
                checkColor(board, move.getStartPosition()) != checkColor(board, move.getEndPosition())) {

            setCapture(board, move);
            return true;
        }
        return false;
    }

    private void setCapture(ChessBoard board, ChessMove move) {
        if(checkColor(board, move.getStartPosition()) == ChessGame.TeamColor.WHITE && checkColor(board, move.getEndPosition()) == ChessGame.TeamColor.BLACK){
            move.setCapture();
        } else if (checkColor(board, move.getStartPosition()) == ChessGame.TeamColor.BLACK && checkColor(board, move.getEndPosition()) == ChessGame.TeamColor.WHITE){
            move.setCapture();
        }
    }
    private boolean isOnBoard(ChessBoard board, ChessPosition checkPosition){
        return board.isValidPosition(checkPosition);
    }
    /**
     * checks if there is a piece at a coordinate and if so what color
     * @param board a chessboard object
     * @param checkPosition the position to check
     *
     * **/
    default ChessGame.TeamColor checkColor(ChessBoard board, ChessPosition checkPosition) {
        ChessPiece checkPiece = board.getPiece(checkPosition);
        if(checkPiece == null) {
            return null;
        } else {
            return checkPiece.getTeamColor();
        }

    }
}

class KingMoveCalculator implements PieceMoveCalculator{
    /**
     * returns a collection of possible moves
     *
     * @param board the chess board object
     * @param myPosition the current position on the board
     *
     * **/
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> moves = new ArrayList<ChessMove>();

        int[][] iteratorPairs = {{1,1},{1,-1},{-1,1},{-1,-1},{0,1},{0,-1},{-1,0},{1,0}};

        for(int[] iteratorPair : iteratorPairs) {
            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            if(checkMove(board, move)) {
                moves.add(move);
            }
        }

        return moves;
    }
}

class QueenMoveCalculator implements PieceMoveCalculator{
    /**
     * returns a collection of possible moves
     *
     * @param board the chess board object
     * @param myPosition the current position on the board
     *
     * **/
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> moves = new ArrayList<ChessMove>();
        int[][] iteratorPairs = {{1,1},{-1,-1},{1,-1},{-1,1},{1,0},{-1,0},{0,1},{0,-1}};

        for(int[] iteratorPair : iteratorPairs) {
            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            while(checkMove(board, move)) {
                moves.add(move);
                if(move.getCapture()){
                    break;
                }
                endPosition = new ChessPosition(endPosition.getRow() + iteratorPair[0],
                        endPosition.getColumn() + iteratorPair[1]);
                move = new ChessMove(myPosition, endPosition);
            }
        }

        return moves;
    }
}

class BishopMoveCalculator implements PieceMoveCalculator{
    /**
     * returns a collection of possible moves
     *
     * @param board the chess board object
     * @param myPosition the current position on the board
     *
     * **/
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> moves = new ArrayList<ChessMove>();

        int[][] iteratorPairs = {{1,1},{-1,-1},{1,-1},{-1,1}};

        for(int[] iteratorPair : iteratorPairs) {
            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            while(checkMove(board, move)) {
                moves.add(move);
                if(move.getCapture()){
                    break;
                }
                endPosition = new ChessPosition(endPosition.getRow() + iteratorPair[0],
                        endPosition.getColumn() + iteratorPair[1]);
                move = new ChessMove(myPosition, endPosition);
            }
        }

        return moves;
    }
}

class KnightMoveCalculator implements PieceMoveCalculator{
    /**
     * returns a collection of possible moves
     *
     * @param board the chess board object
     * @param myPosition the current position on the board
     *
     * **/
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> moves = new ArrayList<ChessMove>();

        int[][] iteratorPairs = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};

        for(int[] iteratorPair : iteratorPairs) {

            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            if(checkMove(board, move)) {
                moves.add(move);
            }
        }

        return moves;
    }
}

class RookMoveCalculator implements PieceMoveCalculator{
    /**
     * returns a collection of possible moves
     *
     * @param board the chess board object
     * @param myPosition the current position on the board
     *
     * **/
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> moves = new ArrayList<ChessMove>();
        int[][] iteratorPairs = {{1,0},{-1,0},{0,1},{0,-1}};

        for(int[] iteratorPair : iteratorPairs) {
            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            while(checkMove(board, move)) {
                moves.add(move);
                if(move.getCapture()){
                    break;
                }
                endPosition = new ChessPosition(endPosition.getRow() + iteratorPair[0],
                        endPosition.getColumn() + iteratorPair[1]);
                move = new ChessMove(myPosition, endPosition);
            }
        }

        return moves;
    }
}

class PawnMoveCalculator implements PieceMoveCalculator{
    /**
     * returns a collection of possible moves
     *
     * @param board the chess board object
     * @param myPosition the current position on the board
     *
     * **/
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> moves = new ArrayList<ChessMove>();

        ChessGame.TeamColor color = checkColor(board, myPosition);

        ChessMove move;
        ChessPosition endPosition;
        if(color == ChessGame.TeamColor.WHITE) {
            if(myPosition.getRow() == 2) {
                int[][] iteratorPairs = {{1,0},{2,0}};

                for(int[] iteratorPair : iteratorPairs) {
                    endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                            myPosition.getColumn() + iteratorPair[1]);
                    move = new ChessMove(myPosition, endPosition);

                    if(checkMove(board, move) && !(move.getCapture())){
                        moves.add(move);
                    } else {break;}
                }

                iteratorPairs[0][0] = 1;
                iteratorPairs[0][1] = 1;
                iteratorPairs[1][0] = 1;
                iteratorPairs[1][1] = -1;

                for(int[] iteratorPair : iteratorPairs) {
                    endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                            myPosition.getColumn() + iteratorPair[1]);
                    move = new ChessMove(myPosition, endPosition);

                    if(checkMove(board, move) && move.getCapture()){
                        moves.add(move);

                    }
                }
            }
            // up {1,0} diagonal {1,1} {1,-1}
            endPosition = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn());
            move = new ChessMove(myPosition, endPosition);

            if(checkMove(board, move) && !(move.getCapture())) {
                if(endPosition.getRow() == 8){
                    for(ChessPiece.PieceType type : ChessPiece.PieceType.values()){
                        if(type != ChessPiece.PieceType.KING && type != ChessPiece.PieceType.PAWN) {
                            moves.add(new ChessMove(myPosition, endPosition, type));
                        }
                    }
                } else {
                    moves.add(move);
                }
            }
            int[][] iteratorPairs = {{1,1},{1,-1}};

            for(int[] iteratorPair : iteratorPairs) {
                endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                        myPosition.getColumn() + iteratorPair[1]);
                move = new ChessMove(myPosition, endPosition);
                if(checkMove(board, move) && move.getCapture()) {
                    if(endPosition.getRow() == 8){
                        for(ChessPiece.PieceType type : ChessPiece.PieceType.values()){
                            if(type != ChessPiece.PieceType.KING && type != ChessPiece.PieceType.PAWN) {
                                moves.add(new ChessMove(myPosition, endPosition, type));
                            }
                        }
                    } else {
                        moves.add(move);
                    }
                }
            }
        } else if (color == ChessGame.TeamColor.BLACK) {
            if(myPosition.getRow() == 7) {
                int[][] iteratorPairs = {{-1,0},{-2,0}};

                for(int[] iteratorPair : iteratorPairs) {
                    endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                            myPosition.getColumn() + iteratorPair[1]);
                    move = new ChessMove(myPosition, endPosition);

                    if(checkMove(board, move) && !(move.getCapture())){
                        moves.add(move);
                    } else {break;}
                }

                iteratorPairs[0][0] = -1;
                iteratorPairs[0][1] = 1;
                iteratorPairs[1][0] = -1;
                iteratorPairs[1][1] = -1;

                for(int[] iteratorPair : iteratorPairs) {
                    endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                            myPosition.getColumn() + iteratorPair[1]);
                    move = new ChessMove(myPosition, endPosition);

                    if(checkMove(board, move) && move.getCapture()){
                        moves.add(move);

                    }
                }
            }
            //down {-1,0} diagonal {-1,1}{-1,-1}
            endPosition = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn());
            move = new ChessMove(myPosition, endPosition);
            boolean add = false;
            if(checkMove(board, move) && !(move.getCapture())) {
                if(endPosition.getRow() == 1){
                    for(ChessPiece.PieceType type : ChessPiece.PieceType.values()){
                        if(type != ChessPiece.PieceType.KING && type != ChessPiece.PieceType.PAWN) {
                            moves.add(new ChessMove(myPosition, endPosition, type));
                        }
                    }
                } else {
                    moves.add(move);
                }
            }
            int[][] iteratorPairs = {{-1,1},{-1,-1}};

            for(int[] iteratorPair : iteratorPairs) {
                endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                        myPosition.getColumn() + iteratorPair[1]);
                move = new ChessMove(myPosition, endPosition);
                if(checkMove(board, move) && move.getCapture()) {
                    if(endPosition.getRow() == 1){
                        for(ChessPiece.PieceType type : ChessPiece.PieceType.values()){
                            if(type != ChessPiece.PieceType.KING && type != ChessPiece.PieceType.PAWN) {
                                moves.add(new ChessMove(myPosition, endPosition, type));
                            }
                        }
                    } else {
                        moves.add(move);
                    }
                }
            }
        }

        /**
        ChessPosition endPosition
        int[][] iteratorPairs = {{1,1},{1,-1},{-1,1},{-1,-1},{0,1},{0,-1},{-1,0},{1,0}};

        for(int[] iteratorPair : iteratorPairs) {
            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            if(checkMove(board, move)) {
                moves.add(move);
            }
        }**/
        return moves;
    }
}