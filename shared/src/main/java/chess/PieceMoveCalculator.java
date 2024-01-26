package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
public interface PieceMoveCalculator {
    Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition);
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
    private ChessGame.TeamColor checkColor(ChessBoard board, ChessPosition checkPosition) {
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

        //implement logic
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
            System.out.println(iteratorPair[0] + " " + iteratorPair[1]);
            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            while(checkMove(board, move)) {
                moves.add(move);
                System.out.println(move.toString());
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
            System.out.println(iteratorPair[0] + " " + iteratorPair[1]);
            ChessPosition endPosition = new ChessPosition(myPosition.getRow() + iteratorPair[0],
                    myPosition.getColumn() + iteratorPair[1]);

            ChessMove move = new ChessMove(myPosition, endPosition);
            while(checkMove(board, move)) {
                moves.add(move);
                System.out.println(move.toString());
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

        //implement logic
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

        //implement logic
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

        //implement logic
        return moves;
    }
}