package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] board;
    public ChessBoard() {
        this.board = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int arrayRow = position.getRow()-1;
        int arrayColumn = position.getColumn()-1;
        if(isValidPosition(position)){
            board[arrayRow][arrayColumn] = piece;
        }else{
            throw new IllegalArgumentException("Position is Invalid");
        }
    }
    /**
     * checks if a position is right
     *
     * @param position position to check
     * */
    public boolean isValidPosition(ChessPosition position) {
        int row = position.getRow();
        int column = position.getColumn();

        return row >= 1 && row <= 8 && column >= 1 && column <= 8;
    }
    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int arrayRow = position.getRow()-1;
        int arrayColumn = position.getColumn()-1;
        if(isValidPosition(position)){
            return board[arrayRow][arrayColumn];
        }else{
            throw new IllegalArgumentException("Position is Invalid");
        }
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        throw new RuntimeException("Not implemented");

    }
}
