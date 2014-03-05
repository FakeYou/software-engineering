package cards;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
/** the solution is a sequence of cards placed on the board according to the card positions
 example without border
 */
public class Solution extends Stack<Candidate>
{
    // The board is an 2D array.
    //  0123
    // 0..-.
    // 1---.
    // 2.---
    // 3..-.
    private Candidate[][] board = new Candidate[4][4];

    // card positions on the board
    // the first card position on the board are
    // {0,2}, {1,0}. {1,1}
    private int[] row    = { 0, 1, 1, 1, 2, 2, 2, 3 };
    private int[] column = { 2, 0, 1, 2, 1, 2, 3, 2 };

    // array with positions to check for bordering
    //
    //  indices cards that must be checked.
    //  e.g. when the 5th card is placed the cards 3&4 can be checked
    //                 0   1  2   3   4     5   6    7
    int [][] check = {{},{},{1},{0},{2},{3,4},{6},{5,7}};


    public Solution(){
    }


    // Checks whether a candidate with card CardChar is in
    // an adjacent position of the board position (row, column)
    // @param row, column, candidate
    // @return Boolean indicating if cardChar is found.
    // can be used in the methods fits and isCorrect
    private boolean bordersCard(int row, int column, char cardChar){
        if(cardChar == ' ') {
            return false;
        }

        //TODO
        if(row > 0) {
            Candidate neighbor = board[row - 1][column];
            if(neighbor != null && neighbor.getCardChar() == cardChar) {
                return true;
            }
        }

        if(row < board[0].length - 1) {
            Candidate neighbor = board[row + 1][column];
            if(neighbor != null && neighbor.getCardChar() == cardChar) {
                return true;
            }
        }

        if(column > 0) {
            Candidate neighbor = board[row][column - 1];
            if(neighbor != null && neighbor.getCardChar() == cardChar) {
                return true;
            }
        }

        if(column < board.length - 1) {
            Candidate neighbor = board[row][column + 1];
            if(neighbor != null && neighbor.getCardChar() == cardChar) {
                return true;
            }
        }

        return false;
    }


    /**
     * Checks whether candidate card of same kind.
     * Checks whether by placing candidate the solution sofar still complies with the rules
     * @param candidate
     * @return boolean indicating whether this candidate can be put in the
     * next free position.
     */
    public boolean fits(Candidate candidate){
        int index = this.size();

        if(bordersCard(row[index], column[index], candidate.getCardChar())) {
            return false;
        }

        return true;
    }

    public void record(Candidate candidate)
    {
        int i=this.size(); // i= index in this stack of next for the next candidate
        board [row[i]] [column[i]] = candidate; //x=row, y=column
        this.push(candidate);

    }

    public boolean complete()
    {
        return this.size()==8;
    }

    public void show()
    {
        System.out.println(this);
    }

    public Candidate eraseRecording()
    {
        int i=this.size()-1;           // i= index of the candidate that is removed from this Stack;
        board[row[i]][column[i]]=null; // remove candidate from board
        return this.pop();
    }

    // can be used in method isCorrect
    private char mustBeAdjacentTo(char card)
    {
        if (card=='A') return 'K';
        if (card=='K') return 'Q';
        if (card=='Q') return 'J';
        return '?'; //error
    }

    /**
     * Checks whether the rules below are fulfilled
     * For the positions that can be checked for solution sofar.
     * Rules:
     * Elke aas (ace) grenst (horizontaal of verticaal) aan een heer (king).
     * Elke heer grenst aan een vrouw (queen).
     * Elke vrouw grenst aan een boer (jack).
     * @return true if all checks are correct.
     */
    // uses methods borderCard and mustBeAdjacent to
    public boolean isCorrect() {
        //TODO

        for(int i = 0; i < row.length; i++) {
            int row = this.row[i];
            int col = this.column[i];

            Candidate candidate = board[row][col];
            char mustBeAdjacentChar = mustBeAdjacentTo(candidate.getCardChar());

            if(candidate != null && mustBeAdjacentChar != '?') {
                if(!bordersCard(row, col, mustBeAdjacentChar)) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * @return a representation of the solution on the board
     */
    public String toString(){
        String output = "";

        for(int y = 0; y < board.length; y++) {
            String row = "";
            for(int x = 0; x < board[0].length; x++) {

                Candidate candidate = board[y][x];

                if(candidate != null) {
                    row += "|" + candidate.getCardChar();

                    if(x == board[0].length - 1 || board[y][x + 1] == null) {
                        row += "|";
                    }
                }
                else {
                    row += "  ";
                }
            }

            output += row + "\n";
        }

        return output;
    }

}