package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.io.*;
/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard board;
    private TeamColor teamTurn;

    public ChessGame() {
        board = new ChessBoard();
        board.resetBoard();
        teamTurn = TeamColor.WHITE;
    }
    public ChessGame(ChessBoard board, TeamColor teamTurn) {
        this.board = board;
        this.teamTurn = teamTurn;
    }
    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        if(piece == null) {
            return null;
        } else {
            return piece.pieceMoves(board, startPosition);
        }
    }

    public Collection<ChessMove> teamMoves(TeamColor color) {
        Collection<ChessMove> teamMoves = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition position = new ChessPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if(piece != null && piece.getTeamColor() == color) {
                    teamMoves.addAll(piece.pieceMoves(board, position));
                }
            }
        }

        return teamMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        Collection<ChessMove> moves = board.getPiece(move.getStartPosition()).pieceMoves(board, move.getStartPosition());

        ChessPiece startPiece = board.getPiece(move.getStartPosition());
        if(!moves.contains(move)) {
            throw new InvalidMoveException("move not valid");
        }
        if(startPiece == null) {
            throw new InvalidMoveException("move not valid");
        }
        ChessGame.TeamColor pieceColor = startPiece.getTeamColor();
        if(pieceColor != teamTurn) {
            throw new InvalidMoveException("move not valid");
        }

        ChessBoard newBoard = new ChessBoard(board.getBoard());
        newBoard.addPiece(move.getEndPosition(), startPiece);
        newBoard.removePiece(move.getStartPosition());

        if(isInCheck(teamTurn)) {
            throw new InvalidMoveException("move not valid");
        }
        board = newBoard;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = findPiece(teamColor, ChessPiece.PieceType.KING);
        if(kingPosition == null) {
            throw new IllegalArgumentException("king not found");
        }
        TeamColor oppositeTeam = null;
        if(teamColor == TeamColor.WHITE) {oppositeTeam =TeamColor.BLACK;}
        else if(teamColor == TeamColor.BLACK) {oppositeTeam =TeamColor.WHITE;}
        else {
            throw new IllegalArgumentException("no teamcolor");
        }
        Collection<ChessMove> oppositeTeamMoves = teamMoves(oppositeTeam);

        for(ChessMove move : oppositeTeamMoves) {
            if(move.getEndPosition() == kingPosition) {
                return true;
            }
        }
        return false;
    }

    public ChessPosition findPiece(TeamColor teamColor, ChessPiece.PieceType type) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition position = new ChessPosition(i, j);

                if(board.getPiece(position).getPieceType() ==  type) {
                    return position;
                }
            }
        }
        return null;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(!isInCheck(teamColor)) {
            throw new IllegalArgumentException("impleleleemement");
        }
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public Collection<ChessMove> getTeamMoves(TeamColor teamColor) {throw new RuntimeException("Not implemented");}
    public boolean isValid(ChessMove move) {throw new RuntimeException("Not implemented");}
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
