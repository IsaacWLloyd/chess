package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private PieceType type;
    private PieceMoveCalculator calculator;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
        this.calculator = createMoveCalculator(type);
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return calculator.calculateMoves(board, myPosition);
    }
    private PieceMoveCalculator createMoveCalculator(PieceType type){
        return switch (type) {
            case KING -> new KingMoveCalculator();
            case QUEEN -> new QueenMoveCalculator();
            case BISHOP -> new BishopMoveCalculator();
            case KNIGHT -> new KnightMoveCalculator();
            case ROOK -> new RookMoveCalculator();
            case PAWN -> new PawnMoveCalculator();
            default -> throw new IllegalArgumentException("Invalid piece type :(");
        };
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + this.pieceColor +
                ", type=" + this.type +
                '}';
    }

}
