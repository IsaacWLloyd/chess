package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
public interface PieceMoveCalculator {
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition);
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

        //implement logic
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

        //implement logic
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