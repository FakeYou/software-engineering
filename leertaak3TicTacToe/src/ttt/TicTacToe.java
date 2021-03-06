package ttt;

import java.util.Random;

public class TicTacToe
{
	public static final int HUMAN        = 0;
	public static final int COMPUTER     = 1;
	public static final int EMPTY        = 2;

	public static final int HUMAN_WIN    = 0;
	public static final int DRAW         = 1;
	public static final int UNCLEAR      = 2;
	public static final int COMPUTER_WIN = 3;

	private int [][] board = new int[3][3];
    private Random random = new Random();
	private int side = random.nextInt(2);
	private int position = UNCLEAR;
	private char computerChar, humanChar;

	// Constructor
	public TicTacToe( )
	{
		clearBoard( );
		initSide();
	}
	
	private void initSide()
	{
	    if (this.side==COMPUTER) { computerChar='X'; humanChar='O'; }
		else                     { computerChar='O'; humanChar='X'; }
    }
    
    public void setComputerPlays()
    {
        this.side=COMPUTER;
        initSide();
    }
    
    public void setHumanPlays()
    {
        this.side=HUMAN;
        initSide();
    }

	public boolean computerPlays()
	{
	    return side == COMPUTER;
	}

	public int chooseMove()
	{
	    Best best = chooseMove(COMPUTER);
	    return best.row * 3 + best.column;
    }

    // Find optimal move
	private Best chooseMove(int side)
	{
		Best reply;           // Opponent's best reply
		int simpleEval;       // Result of an immediate evaluation
		int bestRow = 0;
		int bestColumn = 0;
        int tmpBoard[][] = board.clone();
        int value;

		if((simpleEval = positionValue()) != UNCLEAR) {
            return new Best(simpleEval);
        }

        // TODO: implementeren m.b.v. recursie/backtracking
        if(side == HUMAN) {
            value = COMPUTER_WIN;
        }
        else {
            value = HUMAN_WIN;
        }

        for(int y = 0; y < tmpBoard.length; y++) {
            for(int x = 0; x < tmpBoard[0].length; x++) {

                if(!squareIsEmpty(x, y) || !moveOk(x, y)) {
                    continue;
                }

                place(x, y, side);

                if(side == HUMAN) {
                    reply = chooseMove(COMPUTER);

                    if(reply.val < value) {
                        value = reply.val;
                        bestRow = x;
                        bestColumn = y;
                    }
                }
                else {
                    reply = chooseMove(HUMAN);

                    if(reply.val > value) {
                        value = reply.val;
                        bestRow = x;
                        bestColumn = y;
                    }
                }

                place(x, y, EMPTY);
            }
        }

        return new Best(value, bestRow, bestColumn);
    }

   
    //check if move ok
    public boolean moveOk(int move)
    {
        return ( move>=0 && move <=8 && board[move/3 ][ move%3 ] == EMPTY );
//        return true;
    }

    public boolean moveOk(int row, int column) {
        if(row > 2 || row < 0) {
            return false;
        }
        if(column > 2 || column < 0) {
            return false;
        }
        if(!squareIsEmpty(row, column)) {
            return false;
        }

        return true;
    }
    
    // play move
    public void playMove(int move)
    {
		board[move / 3][move % 3] = this.side;

		if (side == COMPUTER) {
            this.side = HUMAN;
        }
        else {
            this.side = COMPUTER;
        }
	}


	// Simple supporting routines
	public void clearBoard( )
	{
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
                board[y][x] = EMPTY;
            }
        }
	}


	private boolean boardIsFull( )
	{
        Boolean full = true;

        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
                int cell = board[y][x];

                if(cell == EMPTY) {
                    full = false;
                }
            }
        }

		return full;
	}

	// Returns whether 'side' has won in this position
	public boolean isAWin( int side )
	{
	    //TODO:
        int [][] winningPositions = {
                { 0, 1, 2 },
                { 3, 4, 5 },
                { 6, 7, 8 },

                { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 },

                { 0, 4, 8 },
                { 2, 4, 6 },
        };

        for(int i = 0; i < winningPositions.length; i++) {
            int[] winningPosition = winningPositions[i];

            // if every position in this winning position is human than human has won.
            if(getPlace(winningPosition[0]) == side &&
               getPlace(winningPosition[1]) == side &&
               getPlace(winningPosition[2]) == side) {

                return true;
            }
        }

	    return false;
    }

	// Play a move, possibly clearing a square
	private void place( int row, int column, int piece )
	{
		board[ row ][ column ] = piece;
	}

    private int getPlace(int move) {
        return board[move / 3][move % 3];
    }

	private boolean squareIsEmpty( int row, int column )
	{
		return board[ row ][ column ] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	public int positionValue( )
	{
        if(isAWin(HUMAN)) {
            return HUMAN_WIN;
        }
        else if(isAWin(COMPUTER)) {
            return COMPUTER_WIN;
        }
        else if(boardIsFull()) {
            return DRAW;
        }

		return UNCLEAR;
	}
	
	
	public String toString()
	{
        String string = "";

        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
                int cell = board[y][x];

                if(cell == HUMAN) {
                    string += humanChar;
                }
                else if(cell == COMPUTER) {
                    string += computerChar;
                }
                else if(cell == EMPTY) {
                    string += ".";
                }
            }

            string += "\n";
        }

        return string;
	}
	
	public boolean gameOver()
	{
	    this.position = positionValue();
	    return this.position != UNCLEAR;
    }
    
    public String winner()
    {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }
    
	
	private class Best
    {
       int row;
       int column;
       int val;

       public Best( int v )
         { this( v, 0, 0 ); }
      
       public Best( int v, int r, int c )
        { val = v; row = r; column = c; }
    } 
	
	
}